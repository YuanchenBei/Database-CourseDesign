package pers.YuanchenBei.dbwork.dao;

import javafx.scene.chart.ScatterChart;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.Stream;

public class ScholarshipDao {
    /*
     * 奖学金类别添加
     */
    public int add(Connection con, Scholarship scholarship) throws Exception {
        String sql = "insert into scholarship values(?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(scholarship.getId()));
        pstmt.setString(2, scholarship.getScholarName());
        pstmt.setString(3, scholarship.getScholarRank());
        pstmt.setString(4, String.valueOf(scholarship.getScholarYear()));
        pstmt.setString(5, scholarship.getScholarIssuer());
        return pstmt.executeUpdate();
    }

    /*
     *奖学金查询
     */
    public ResultSet query(Connection con, Scholarship scholarship) throws Exception {
        StringBuffer sql = new StringBuffer("select * from scholarship");
        //由于id为int类型,id为0标志其值为空
        if (scholarship.getId() != 0) {
            sql.append(" and Scholar_id=" + String.valueOf(scholarship.getId()));
        }
        if (String_isEmpty.isNotEmpty(scholarship.getScholarName())) {
            sql.append(" and Scholar_name like '%" + scholarship.getScholarName() + "%'");
        }
        if (String_isEmpty.isNotEmpty(scholarship.getScholarRank())) {
            sql.append(" and Scholar_rank='" + scholarship.getScholarRank() + "'");
        }
        if (scholarship.getScholarYear() != 0) {
            sql.append(" and Scholar_year=" + String.valueOf(scholarship.getScholarYear()));
        }
        if (String_isEmpty.isNotEmpty(scholarship.getScholarIssuer())) {
            sql.append(" and Scholar_issuer like '%" + scholarship.getScholarIssuer() + "%'");
        }
        PreparedStatement pstmt = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
        return pstmt.executeQuery();
    }

    /*
     *奖学金删除
     */
    public int delete(Connection con, int id) throws Exception {
        //更新获奖信息表
        String sql0 = "delete from stu_scholar where Scholar_id=?";
        PreparedStatement pstmt0 = con.prepareStatement(sql0);
        pstmt0.setString(1, String.valueOf(id));
        pstmt0.executeUpdate();

        //更新奖学金表
        String sql = "delete from scholarship where Scholar_id=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(id));
        return pstmt.executeUpdate();
    }

    /*
     *奖学金更新
     */
    public int update(Connection con, Scholarship scholarship)throws Exception{
        String sql="update scholarship set Scholar_name=?,Scholar_rank=?,Scholar_year=?,Scholar_issuer=? where Scholar_id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,scholarship.getScholarName());
        pstmt.setString(2,scholarship.getScholarRank());
        pstmt.setString(3,String.valueOf(scholarship.getScholarYear()));
        pstmt.setString(4,scholarship.getScholarIssuer());
        pstmt.setString(5,String.valueOf(scholarship.getId()));
        return pstmt.executeUpdate();
    }
}
