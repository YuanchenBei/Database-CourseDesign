package pers.YuanchenBei.dbwork.model;

public class Department {
    private String id;//系号
    private String name;//系名
    private String c_id;//所属学院号

    public Department(){
        super();
    }

    public Department(String id, String name, String c_id) {
        this.id = id;
        this.name = name;
        this.c_id = c_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
