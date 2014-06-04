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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vos.School;

/**
 *
 * @author edsgICT-WB52
 */
public class SchoolFacade extends Facade{    
    
    public JSONArray getSchoolsWithCoords(String year) throws Exception{
        JSONArray schools = new JSONArray();
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
                     JSONObject school = new JSONObject();                     
                     school.put("id",i);
                     school.put("lgaName",row[0]);
                     school.put("schoolName",row[1]);
                     school.put("passRate", getPassRate(row[8],row[7]));
                     school.put("teachersToPupilsRatio",getTeachersToPupilsRatio(row[5],row[4]));                     
                     try{
                        school.put("latitude",Double.parseDouble(row[9]));
                        school.put("longitude",Double.parseDouble(row[10]));                        
                        if((Double)school.get("teachersToPuilsRatio")!=Facade.DEFAULT_VALUE)  
                            schools.add(school);
                     }catch(Exception e){
                        //Logger.getAnonymousLogger().log(Level.WARNING, "A school without a geophical coordinate");
                     }                     
                     
                 }
                 break;                                                                               
             }                                               
         }
        return schools;        
    }    
    
    public static void main(String[] args){
        SchoolFacade lf = new SchoolFacade();
        try {        
            System.out.println(lf.getSchoolsWithCoords("2011"));
        } catch (Exception ex) {
            Logger.getLogger(SchoolFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
