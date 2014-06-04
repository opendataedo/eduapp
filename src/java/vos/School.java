/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vos;

import java.io.Serializable;

/**
 *
 * @author edsgICT-WB52
 */
public class School implements Serializable {
    
    public School(){        
    }
    
    private static int NUMBER_OF_SCHOOLS;
    private int id;
    private String lgaName;
    private String schoolName;
    private Double passRate;
    private Double teachersToPuilsRatio;
    private Double longitude;
    private Double latitude;

    static {
        NUMBER_OF_SCHOOLS = 1;
    }
    
    public School( String lgaName, String schoolName, Double passRate, Double teachersToPuilsRatio, Double longitude, Double latitude) {
        this.id = NUMBER_OF_SCHOOLS++;
        this.lgaName = lgaName;
        this.schoolName = schoolName;
        this.passRate = passRate;
        this.teachersToPuilsRatio = teachersToPuilsRatio;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    
}
