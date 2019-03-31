package vn.poly.hailt.giaoan.model;

public class Subject {
    private String sId;
    private String sName;
    private String season;

    //Alt + Insert -> Constructor
    public Subject(String sId, String sName, String season) {
        this.sId = sId;
        this.sName = sName;
        this.season = season;
    }

    public Subject() {

    }

    // Alt + Insert -> Getter and Setter
    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
