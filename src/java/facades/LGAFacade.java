/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import au.com.bytecode.opencsv.CSVReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vos.School;

/**
 *
 * @author edsgICT-WB52
 */
public class LGAFacade extends Facade{    

    public static Set<String> allLgaNamesInEdo = new HashSet<>();
    
    public JSONArray getAllLGAs(String year) throws Exception{        
        List<School> schools = new ArrayList<>();        
        JSONArray lgas = new JSONArray();        
        JSONObject jo= getJSONObject(path,"");
        JSONObject jsonObj = (JSONObject) jo.get("result");
        JSONArray resources = (JSONArray)jsonObj.get("resources");         
        for(Object obj: resources){                
            URL url = replaceHost(new URL((String)((JSONObject)obj).get("url")));
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(true);
            con.setUseCaches(false);
            con.connect();       
            CSVReader reader = new CSVReader(new InputStreamReader(con.getInputStream()));
            List<String[]> rows = reader.readAll();
            String yearFromDoc = rows.get(1)[getYearColumn(rows)].trim();
            if(yearFromDoc.equals(year)){                                    
                for(int i=1;i<rows.size();i++){
                    String[] row = rows.get(i);                    
                    School school = new School();
                    school.setLgaName(row[0]);
                    allLgaNamesInEdo.add(row[0]);
                    school.setSchoolName(row[1]);
                    school.setPassRate(getPassRate(row[8],row[7]));
                    school.setTeachersToPuilsRatio(getTeachersToPupilsRatio(row[5],row[4]));                    
                    if(school.getPassRate()!=Facade.DEFAULT_VALUE || school.getTeachersToPuilsRatio()!=Facade.DEFAULT_VALUE)                        
                        schools.add(school);
                }                                                                                            
            }                                               
         }
        
        int i = 1;
        for(String name: allLgaNamesInEdo){                                 
            double lgaPassRate= 0;            
            double teachersToPupilsRatio = 0;
            int counterForTP = 0;            
            int counterForPR = 0;
            for(School school: schools){                                            
                if(name.equals(school.getLgaName())){                    
                    if(school.getTeachersToPuilsRatio()!=0){
                        counterForTP++;
                    }
                    if(school.getPassRate()!=0){
                        counterForPR++;
                    }                                            
                    lgaPassRate = lgaPassRate + school.getPassRate();                    
                    teachersToPupilsRatio = teachersToPupilsRatio + school.getTeachersToPuilsRatio();
                }
            }           
            Double finalPR = lgaPassRate/counterForPR;
            Double finalTP = teachersToPupilsRatio/counterForTP;
            JSONObject lgaObj = new JSONObject();
            lgaObj.put("id",i);
            lgaObj.put("lgaName", name);
            lgaObj.put("passRate", finalPR);
            lgaObj.put("teacherPupilsRatio", finalTP);     
            if(!finalPR.isNaN() && !finalTP.isNaN())                        
                lgas.add(lgaObj);
            i++;
        }       
        return lgas;
    }       
    
    public static void main(String[] args){
        LGAFacade lf = new LGAFacade();
        try {
            System.out.println(lf.getAllLGAs("2011"));
        } catch (Exception ex) {
            Logger.getLogger(LGAFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
