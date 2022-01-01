package pers.YuanchenBei.dbwork.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Scholarship {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty scholarName;
    private final SimpleStringProperty scholarRank;
    private final SimpleIntegerProperty scholarYear;
    private final SimpleStringProperty scholarIssuer;

    public Scholarship(int id,String scholarName,String scholarRank,
                       int scholarYear,String scholarIssuer){
        super();
        this.id=new SimpleIntegerProperty(id);
        this.scholarName=new SimpleStringProperty(scholarName);
        this.scholarRank=new SimpleStringProperty(scholarRank);
        this.scholarYear=new SimpleIntegerProperty(scholarYear);
        this.scholarIssuer=new SimpleStringProperty(scholarIssuer);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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

    public String getScholarRank() {
        return scholarRank.get();
    }

    public SimpleStringProperty scholarRankProperty() {
        return scholarRank;
    }

    public void setScholarRank(String scholarRank) {
        this.scholarRank.set(scholarRank);
    }

    public int getScholarYear() {
        return scholarYear.get();
    }

    public SimpleIntegerProperty scholarYearProperty() {
        return scholarYear;
    }

    public void setScholarYear(int scholarYear) {
        this.scholarYear.set(scholarYear);
    }

    public String getScholarIssuer() {
        return scholarIssuer.get();
    }

    public SimpleStringProperty scholarIssuerProperty() {
        return scholarIssuer;
    }

    public void setScholarIssuer(String scholarIssuer) {
        this.scholarIssuer.set(scholarIssuer);
    }
}
