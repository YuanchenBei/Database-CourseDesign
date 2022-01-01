package pers.YuanchenBei.dbwork.model;

import javafx.beans.property.SimpleStringProperty;

public class Stu_Scholar {
    private final SimpleStringProperty stuId;
    private final SimpleStringProperty stuName;
    private final SimpleStringProperty stuMajor;
    private final SimpleStringProperty scholarName;

    public Stu_Scholar(String stuId,String stuName,
                       String stuMajor,String scholarName){
        super();
        this.stuId=new SimpleStringProperty(stuId);
        this.stuName=new SimpleStringProperty(stuName);
        this.stuMajor=new SimpleStringProperty(stuMajor);
        this.scholarName=new SimpleStringProperty(scholarName);
    }

    public String getStuId() {
        return stuId.get();
    }

    public SimpleStringProperty stuIdProperty() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId.set(stuId);
    }

    public String getStuName() {
        return stuName.get();
    }

    public SimpleStringProperty stuNameProperty() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName.set(stuName);
    }

    public String getStuMajor() {
        return stuMajor.get();
    }

    public SimpleStringProperty stuMajorProperty() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor.set(stuMajor);
    }

    public String getScholarName() {
        return scholarName.get();
    }

    public SimpleStringProperty scholarNameProperty() {
        return scholarName;
    }

    public void setScholarName(String scholarName) {
        this.scholarName.set(scholarName);
    }
}
