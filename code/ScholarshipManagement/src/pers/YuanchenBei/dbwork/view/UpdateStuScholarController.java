package pers.YuanchenBei.dbwork.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pers.YuanchenBei.dbwork.dao.StuScholarDao;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class UpdateStuScholarController {

    @FXML
    private TextField stu_text;

    @FXML
    private TextField scholar_text;

    @FXML
    private TextField modify_scholar_text;

    @FXML
    private TextField modify_id_text;

    @FXML
    private AnchorPane query_pane;

    @FXML
    private AnchorPane modify_pane;

    private Db_utils dbUtils=new Db_utils();
    private StuScholarDao stuScholarDao=new StuScholarDao();
    private String update_stu_id;
    private int update_scholar_id;

    @FXML
    void no_event(ActionEvent event) {
        modify_pane.setVisible(false);
        query_pane.toFront();
        modify_id_text.setText("");
        modify_scholar_text.setText("");
    }

    @FXML
    void query_event(ActionEvent event) {
        String id=null;
        String scholar_id=null;
        if(String_isEmpty.isNotEmpty(this.modify_id_text.getText())){
            id=this.modify_id_text.getText();
        }
        if(String_isEmpty.isNotEmpty(this.modify_scholar_text.getText())){
            scholar_id=this.modify_scholar_text.getText();
        }
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=stuScholarDao.query_table(con,id,scholar_id);
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    update_stu_id=rs.getString("Stu_id");
                    update_scholar_id=rs.getInt("Scholar_id");
                    fill_text(update_stu_id,update_scholar_id);
                    modify_pane.setVisible(true);
                    modify_pane.toFront();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
                alert.setTitle("该学生学号不存在获奖信息!");
                alert.setHeaderText("不存该学号学生的获奖信息"); // 设置对话框的头部文本
                alert.setContentText("请确定输入学生学号是否正确");
                alert.setResizable(false);
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill_text(String update_stu_id, int update_scholar_id) {
        this.stu_text.setEditable(false);
        this.stu_text.setText(update_stu_id);
        this.scholar_text.setText(String.valueOf(update_scholar_id));
    }

    @FXML
    void yes_event(ActionEvent event) {
        update_stu_id=this.stu_text.getText();
        update_scholar_id=Integer.parseInt(this.scholar_text.getText());
        Connection con=null;
        try{
            con=dbUtils.getCon();
            int n=stuScholarDao.update(con,update_stu_id,update_scholar_id);
            if(n==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
                alert.setTitle("学生信息修改成功!");
                alert.setHeaderText("学号为:"+update_stu_id+"的同学的获奖信息修改成功!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR); // 创建一个消息对话框
                alert.setTitle("学生获奖信息修改失败!");
                alert.setHeaderText("很抱歉,学生获奖信息修改失败!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("update_stu_scholar.fxml"));
    }
}
