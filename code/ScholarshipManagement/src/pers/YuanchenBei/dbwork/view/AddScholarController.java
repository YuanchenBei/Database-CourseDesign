package pers.YuanchenBei.dbwork.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pers.YuanchenBei.dbwork.dao.ScholarshipDao;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;

public class AddScholarController {

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private TextField year_text;

    @FXML
    private TextField company_text;

    @FXML
    private Button insert_button;

    private String level=null;

    @FXML
    private RadioButton country_level;

    @FXML
    void country_level_action(ActionEvent event) {
        level="国家级";
    }

    @FXML
    private RadioButton province_level;

    @FXML
    void province_level_action(ActionEvent event) {
        level="省级";
    }

    @FXML
    private RadioButton school_level;

    @FXML
    void school_level_action(ActionEvent event) { level="校级"; }

    @FXML
    private Button reset_button;

    private Db_utils dbUtils=new Db_utils();
    private ScholarshipDao scholarshipDao=new ScholarshipDao();

    /*
     *插入新的奖学金信息
     */
    @FXML
    void insert_scholar(ActionEvent event) {
        if(String_isEmpty.isEmpty(this.id_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("奖学金编号不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新修改奖学金信息");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else if(String_isEmpty.isEmpty(this.name_text.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("奖学金名称不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新修改奖学金信息");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
        }
        else{
            int id=Integer.parseInt(this.id_text.getText());
            String name=this.name_text.getText();
            int year=Integer.parseInt(this.year_text.getText());
            String issuer=this.company_text.getText();
            Scholarship scholarship=new Scholarship(id,name,level,year,issuer);
            Connection con=null;
            try {
                con= dbUtils.getCon();
                int n=scholarshipDao.add(con,scholarship);
                if(n==1){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("奖学金类别添加成功");
                    alert.setHeaderText(name+"添加成功!"); // 设置对话框的头部文本
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("奖学金类别添加失败");
                    alert.setHeaderText(name+"添加失败“"); // 设置对话框的头部文本
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                    this.id_text.setText("");
                    this.name_text.setText("");
                    this.year_text.setText("");
                    this.company_text.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void reset_text(ActionEvent event) {
        this.id_text.setText("");
        this.name_text.setText("");
        this.year_text.setText("");
        this.company_text.setText("");
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("add_scholarship.fxml"));
    }
}
