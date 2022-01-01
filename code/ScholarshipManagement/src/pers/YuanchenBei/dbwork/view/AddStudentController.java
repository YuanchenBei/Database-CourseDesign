package pers.YuanchenBei.dbwork.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import pers.YuanchenBei.dbwork.dao.StudentDao;
import pers.YuanchenBei.dbwork.model.Student;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;

public class AddStudentController {

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private DatePicker birth_text;

    @FXML
    private TextField grade_text;

    @FXML
    private TextField major_text;

    private Db_utils dbUtils=new Db_utils();
    private StudentDao studentDao=new StudentDao();
    private String sex=null;

    @FXML
    void man_action(ActionEvent event) {
        sex="M";
    }

    @FXML
    void reset_action(ActionEvent event) {
        this.id_text.setText("");
        this.grade_text.setText("");
        this.major_text.setText("");
        this.name_text.setText("");
    }

    @FXML
    void woman_action(ActionEvent event) {
        sex="F";
    }

    @FXML
    void yes_action(ActionEvent event) {
        if(String_isEmpty.isEmpty(this.id_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("学号不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新修改要添加的学生信息");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else if(String_isEmpty.isEmpty(this.name_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("学生姓名不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新修改要添加的学生信息");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else{
            String id=this.id_text.getText();
            String name=this.name_text.getText();
            String major_id=this.major_text.getText();
            LocalDate date=this.birth_text.getValue();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dateStr = date.format(fmt);
            System.out.println(dateStr);
            int grade=Integer.parseInt(this.grade_text.getText());
            Student student=new Student(id,name,sex,dateStr,grade,major_id);
            Connection con=null;
            try {
                con= dbUtils.getCon();
                int n=studentDao.add(con,student);
                if(n==1){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("学生信息添加成功");
                    alert.setHeaderText(name+" 同学信息添加成功!"); // 设置对话框的头部文本
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("学生信息添加失败");
                    alert.setHeaderText(name+" 同学信息添加失败“"); // 设置对话框的头部文本
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                    this.id_text.setText("");
                    this.name_text.setText("");
                    this.major_text.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("add_student.fxml"));
    }
}
