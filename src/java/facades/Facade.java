/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author edsgICT-WB52
 */
public class Facade {
    public final static Double DEFAULT_VALUE = 0D;
    protected String path = "http://data.edostate.gov.ng/api/3/action/package_show?id=education-app-data-2011";
    //protected String path = "http://localhost/test/index.json";
    
    protected Double getTeachersToPupilsRatio(String noOfStudents,String noOfTeachers){
        Double result;
        try{
            result = Double.parseDouble(noOfStudents)/Double.parseDouble(noOfTeachers);
            if(result.isNaN()||result.isInfinite())
                result = DEFAULT_VALUE;
        }catch(Exception e){
            result = DEFAULT_VALUE;
        }
       return result;
    }
    
    protected Double getPassRate(String noOfEnrolments, String noOfPasses){
        Double result;
        try{
            result = Double.parseDouble(noOfEnrolments)/Double.parseDouble(noOfPasses)*100;
            if(result.isNaN()||result.isInfinite())
                result = DEFAULT_VALUE;
        }catch(Exception e){
            result = DEFAULT_VALUE;
        }
        return result;
    }
    
    protected Integer getYearColumn(List<String[]> lines){
        Integer col = 0;
        for(String column: lines.get(0)){
            if(column.trim().equalsIgnoreCase("year")){
                break;
            }
            col++;
        }
        return col;
    }
    
    protected URL replaceHost(URL url) throws MalformedURLException{
        URL newUrl = new URL(url.getProtocol()+"://50.57.74.15"+url.getPath());        
        return newUrl;
    }
    
    protected JSONObject getJSONObject(String ur, String path) throws IOException, ParseException{
        JSONObject jo;        
        JSONParser parser = new JSONParser();
        URL url;
        if(path.isEmpty())
            url = new URL(ur);
        else
            url = new URL(ur+path);
        
        URLConnection con = url.openConnection();
        con.setAllowUserInteraction(true);
        con.setUseCaches(false);
        con.setRequestProperty("Authorization", "d0855fc3-456d-4736-862e-82b9b543cb32");
        con.connect();                            
        Object obj = parser.parse(new InputStreamReader(con.getInputStream()));
        jo = (JSONObject)obj;        
        return jo;
    }
}
