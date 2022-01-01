package pers.YuanchenBei.dbwork.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import pers.YuanchenBei.dbwork.dao.StuScholarDao;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;

public class AddStuScholarController {

    @FXML
    private TextField stu_text;

    @FXML
    private TextField scholar_text;

    private Db_utils dbUtils=new Db_utils();
    private StuScholarDao stuScholarDao=new StuScholarDao();

    @FXML
    void no_action(ActionEvent event) {
        stu_text.setText("");
        scholar_text.setText("");

    }

    @FXML
    void yes_action(ActionEvent event) {
        if(String_isEmpty.isEmpty(this.stu_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("获奖学生学号不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请输入获奖学生学号");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else if(String_isEmpty.isEmpty(this.scholar_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("奖学金编号不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请输入奖学金编号");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else{
            String stu_id=this.stu_text.getText();
            Integer scholar_id=Integer.parseInt(this.scholar_text.getText());
            Connection con=null;
            try {
                con=dbUtils.getCon();
                int n=stuScholarDao.add(con,stu_id,scholar_id);
                if(n==1){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("学生获奖信息添加成功");
                    alert.setHeaderText("学生获奖记录添加成功!");
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("学生获奖信息添加失败");
                    alert.setHeaderText("学生获奖记录添加失败!");
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                    this.stu_text.setText("");
                    this.scholar_text.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("add_stu_scholar.fxml"));
    }
}
