package pers.YuanchenBei.dbwork.model;

public class Major {
    private String id;//专业号
    private String name;//专业名
    private String d_id;//所属系号

    public Major(){
        super();
    }

    public Major(String id, String name, String d_id) {
        this.id = id;
        this.name = name;
        this.d_id = d_id;
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

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }
}
