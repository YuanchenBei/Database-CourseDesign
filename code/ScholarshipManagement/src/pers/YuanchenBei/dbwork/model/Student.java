package pers.YuanchenBei.dbwork.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Student {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty sex;
    private final SimpleStringProperty birth;
    private final SimpleIntegerProperty grade;
    private final SimpleStringProperty m_id;

    public Student(String id,String name,String sex,String birth,
                   int grade,String m_id){
        this.birth=new SimpleStringProperty(birth);
        this.grade=new SimpleIntegerProperty(grade);
        this.id=new SimpleStringProperty(id);
        this.m_id=new SimpleStringProperty(m_id);
        this.name=new SimpleStringProperty(name);
        this.sex=new SimpleStringProperty(sex);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getBirth() {
        return birth.get();
    }

    public SimpleStringProperty birthProperty() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth.set(birth);
    }

    public int getGrade() {
        return grade.get();
    }

    public SimpleIntegerProperty gradeProperty() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }

    public String getM_id() {
        return m_id.get();
    }

    public SimpleStringProperty m_idProperty() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id.set(m_id);
    }
}
