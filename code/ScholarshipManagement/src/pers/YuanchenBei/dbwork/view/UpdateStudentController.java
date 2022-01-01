package pers.YuanchenBei.dbwork.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pers.YuanchenBei.dbwork.dao.StudentDao;
import pers.YuanchenBei.dbwork.model.Student;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class UpdateStudentController {

    @FXML
    private AnchorPane result_pane;

    @FXML
    private TableView<Student> result_tab;

    @FXML
    private TableColumn<Student, String> id_col;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, String> sex_col;

    @FXML
    private TableColumn<Student, String> birth_col;

    @FXML
    private TableColumn<Student, Integer> grade_col;

    @FXML
    private TableColumn<Student, String> major_col;

    @FXML
    private AnchorPane modify_pane;

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private TextField sex_text;

    @FXML
    private TextField birth_text;

    @FXML
    private TextField grade_text;

    @FXML
    private TextField major_text;

    @FXML
    private AnchorPane main_pane;

    @FXML
    private TextField modify_id_text;

    private Db_utils dbUtils=new Db_utils();
    private Student update_stu;
    private StudentDao studentDao=new StudentDao();

    @FXML
    void modify_action(ActionEvent event) {
        update_stu.setName(this.name_text.getText());
        update_stu.setSex(this.sex_text.getText());
        update_stu.setBirth(this.birth_text.getText());
        update_stu.setGrade(Integer.parseInt(this.grade_text.getText()));
        update_stu.setM_id(this.major_text.getText());
        Connection con=null;
        try{
            con=dbUtils.getCon();
            int n=studentDao.update(con,update_stu);
            if(n==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
                alert.setTitle("学生信息修改成功!");
                alert.setHeaderText(update_stu.getName()+" 同学的信息修改成功!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
                modify_result(update_stu);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR); // 创建一个消息对话框
                alert.setTitle("学生信息修改失败!");
                alert.setHeaderText("很抱歉,学生信息修改失败!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void modify_result(Student update_stu) {
        ObservableList<Student> data= FXCollections.observableArrayList();
        data.add(update_stu);
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex_col.setCellValueFactory(new PropertyValueFactory<>("sex"));
        birth_col.setCellValueFactory(new PropertyValueFactory<>("birth"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("grade"));
        major_col.setCellValueFactory(new PropertyValueFactory<>("m_id"));
        result_tab.setItems(data);

        main_pane.setVisible(false);
        modify_pane.setVisible(false);
        result_pane.setVisible(true);
        result_pane.toFront();
    }

    @FXML
    void query_action(ActionEvent event) {
        String id=null;
        if(String_isEmpty.isNotEmpty(this.modify_id_text.getText())){
            id=this.modify_id_text.getText();
        }
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=studentDao.query(con,id);
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    update_stu=new Student(rs.getString("Stu_id"),rs.getString("Stu_name"),
                            rs.getString("Stu_sex"),rs.getString("Stu_birth"),
                            rs.getInt("Stu_grade"),rs.getString("Major_id"));
                    fill_text(update_stu);
                    modify_pane.setVisible(true);
                    modify_pane.toFront();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
                alert.setTitle("该学生信息不存在!");
                alert.setHeaderText("不存该学号对应的学生"); // 设置对话框的头部文本
                alert.setContentText("请确定学生学号是否正确");
                alert.setResizable(false);
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill_text(Student update_stu) {
        this.id_text.setEditable(false);
        this.id_text.setText(update_stu.getId());
        this.name_text.setText(update_stu.getName());
        this.sex_text.setText(update_stu.getSex());
        this.birth_text.setText(update_stu.getBirth());
        this.grade_text.setText(String.valueOf(update_stu.getGrade()));
        this.major_text.setText(update_stu.getM_id());
    }

    @FXML
    void reset_action(ActionEvent event) {
        modify_pane.setVisible(false);
        main_pane.toFront();
        this.modify_id_text.setText("");
    }

    @FXML
    void return_action(ActionEvent event) {
        result_pane.setVisible(false);
        main_pane.toFront();
        main_pane.setVisible(true);
        this.modify_id_text.setText("");
    }


    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("update_student.fxml"));
    }
}
