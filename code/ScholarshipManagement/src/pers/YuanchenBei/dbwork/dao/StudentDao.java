package pers.YuanchenBei.dbwork.dao;

import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.model.Student;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
    /*
     * 学生信息添加
     */
    public int add(Connection con, Student student)throws Exception{
        String sql="insert into student values(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,student.getId());
        pstmt.setString(2,student.getName());
        pstmt.setString(3,student.getSex());
        pstmt.setString(4,student.getBirth());
        pstmt.setString(5,String.valueOf(student.getGrade()));
        pstmt.setString(6,student.getM_id());
        return pstmt.executeUpdate();
    }

    /*
     *学生查询
     */
    public ResultSet query(Connection con, String id) throws Exception {
        StringBuffer sql = new StringBuffer("select * from student");
        if (String_isEmpty.isNotEmpty(id)) {
            sql.append(" and Stu_id like '%" + id +"%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /*
     *学生删除
     */
    public int delete(Connection con, String id) throws Exception {
        //更新获奖信息表
        String sql0 = "delete from stu_scholar where Stu_id=?";
        PreparedStatement pstmt0 = con.prepareStatement(sql0);
        pstmt0.setString(1, id);
        pstmt0.executeUpdate();

        //更新学生表
        String sql = "delete from student where Stu_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /*
     *奖学金更新
     */
    public int update(Connection con, Student student)throws Exception{
        String sql="update student set Stu_name=?,Stu_sex=?,Stu_birth=?,Stu_grade=?,Major_id=? where Stu_id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,student.getName());
        pstmt.setString(2,student.getSex());
        pstmt.setString(3,student.getBirth());
        pstmt.setString(4,String.valueOf(student.getGrade()));
        pstmt.setString(5,student.getM_id());
        pstmt.setString(6,student.getId());
        return pstmt.executeUpdate();
    }
}
