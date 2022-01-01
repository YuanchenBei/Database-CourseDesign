package pers.YuanchenBei.dbwork.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db_utils {
    private String dbUrl="jdbc:mysql://localhost:3306/db_scholarship?serverTimezone=UTC";//数据库地址
    private String dbUserName="root";
    private String dbPassword="123456";
    private String jdbcName="com.mysql.cj.jdbc.Driver";//驱动名;

    /*
    获取数据库连接
     */
    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection con= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    /*
    关闭数据库连接
     */
    public void closeCon(Connection con) throws SQLException {
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args){
        Db_utils dbUtils=new Db_utils();
        try {
            dbUtils.getCon();
            System.out.println("数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }
}
