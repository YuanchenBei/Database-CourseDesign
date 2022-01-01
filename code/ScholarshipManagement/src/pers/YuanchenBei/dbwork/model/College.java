package pers.YuanchenBei.dbwork.model;

public class College {
    private String id;//学院号
    private String name;//学院名
    private String address;//学院地址

    public College(){
        super();
    }

    public College(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
