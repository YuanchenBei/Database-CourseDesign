package pers.YuanchenBei.dbwork.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pers.YuanchenBei.dbwork.dao.StudentDao;
import pers.YuanchenBei.dbwork.model.Student;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class DeleteStudentController {
    @FXML
    private TextField id_text;

    @FXML
    private TableView<Student> del_tab;

    @FXML
    private TableColumn<Student, String> id_col;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, String> sex_col;

    @FXML
    private TableColumn<Student, String> date_col;

    @FXML
    private TableColumn<Student, Integer> grade_col;

    @FXML
    private TableColumn<Student, String> major_col;

    @FXML
    private Button del_button;

    @FXML
    private Button reset_button;

    private Student del_stu;
    private Db_utils dbUtils=new Db_utils();
    private StudentDao studentDao=new StudentDao();

    @FXML
    void delete_action(ActionEvent event) {
        Connection con=null;
        try {
            con=dbUtils.getCon();
            int n=studentDao.delete(con,del_stu.getId());
            System.out.println(n);
            if(n!=0){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("学生信息删除成功");
                alert.setHeaderText(del_stu.getName()+" 同学的信息删除成功!"); // 设置对话框的头部文本
                alert.setResizable(false);
                // 设置对话框的内容文本
                alert.show(); // 显示对话框
                this.id_text.setText("");
                del_tab.getItems().clear();
                del_tab.refresh();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("学生信息删除失败");
                alert.setHeaderText(del_stu.getName()+" 同学的信息删除失败!"); // 设置对话框的头部文本
                alert.setResizable(false);
                // 设置对话框的内容文本
                alert.show(); // 显示对话框
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void query_action(ActionEvent event) {
        String id=null;
        if(String_isEmpty.isNotEmpty(this.id_text.getText())){
            id=this.id_text.getText();
        }
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=studentDao.query(con,id);
            if(rs.isBeforeFirst()){
                fill_table(rs);
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

    private void fill_table(ResultSet rs) throws Exception{
        ObservableList<Student> data= FXCollections.observableArrayList();
        while(rs.next()){
            Student in=new Student(rs.getString("Stu_id"),rs.getString("Stu_name"),
                    rs.getString("Stu_sex"),rs.getString("Stu_birth"),
                    rs.getInt("Stu_grade"),rs.getString("Major_id"));
            del_stu=in;
            data.add(in);
        }
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        sex_col.setCellValueFactory(new PropertyValueFactory<>("sex"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("birth"));
        grade_col.setCellValueFactory(new PropertyValueFactory<>("grade"));
        major_col.setCellValueFactory(new PropertyValueFactory<>("m_id"));
        del_tab.setItems(data);
        tab_vis(true);
    }

    private void tab_vis(boolean x) {
        if(x){
            del_tab.setVisible(true);
            del_button.setVisible(true);
            reset_button.setVisible(true);
        } else{
            del_tab.setVisible(false);
            del_button.setVisible(false);
            reset_button.setVisible(false);
        }
    }

    @FXML
    void reset_action(ActionEvent event) {
        this.id_text.setText("");
        tab_vis(false);
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("delete_student.fxml"));
    }
}
