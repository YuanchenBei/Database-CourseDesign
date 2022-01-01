package pers.YuanchenBei.dbwork.dao;

import pers.YuanchenBei.dbwork.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    /*
     *登录验证
     */
    public User login(Connection con, User user)throws Exception{
        User resultUser=null;
        String sql="select * from user where User_name=? and User_password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUserName());
        pstmt.setString(2,user.getPassword());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            resultUser=new User();
            resultUser.setId(rs.getInt("User_id"));
            resultUser.setUserName(rs.getString("User_name"));
            resultUser.setPassword(rs.getString("User_password"));
        }
        return resultUser;
    }
}
