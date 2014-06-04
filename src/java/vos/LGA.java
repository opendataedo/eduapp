/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vos;

/**
 *
 * @author edsgICT-WB52
 */
public class LGA {
    private static int NUMBER_OF_LGAS;
    private int id;
    private String lgaName;
    private Double passRate;
    private Double teachersToPuilsRatio;   
    
    public LGA(){
        
    }
    
    static {
        NUMBER_OF_LGAS = 1;
    }
    
    public LGA( String lgaName, String schoolName, Double passRate, Double teachersToPuilsRatio, Double longitude, Double latitude) {
        this.id = NUMBER_OF_LGAS++;
        this.lgaName = lgaName;        
        this.passRate = passRate;
        this.teachersToPuilsRatio = teachersToPuilsRatio;        
    }

    public static int getNUMBER_OF_LGAS() {
        return NUMBER_OF_LGAS;
    }

    public static void setNUMBER_OF_LGAS(int NUMBER_OF_LGAS) {
        LGA.NUMBER_OF_LGAS = NUMBER_OF_LGAS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLgaName() {
        return lgaName;
    }

    public void setLgaName(String lgaName) {
        this.lgaName = lgaName;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public Double getTeachersToPuilsRatio() {
        return teachersToPuilsRatio;
    }

    public void setTeachersToPuilsRatio(Double teachersToPuilsRatio) {
        this.teachersToPuilsRatio = teachersToPuilsRatio;
    }       
    
}
