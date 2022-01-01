package pers.YuanchenBei.dbwork.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pers.YuanchenBei.dbwork.dao.StuScholarDao;
import pers.YuanchenBei.dbwork.dao.StudentDao;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.model.Stu_Scholar;
import pers.YuanchenBei.dbwork.model.Student;
import pers.YuanchenBei.dbwork.utils.Db_utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class QueryStuScholarController {

    @FXML
    private TableView<Stu_Scholar> stu_scholar_tab;

    @FXML
    private TableColumn<Stu_Scholar, String> id_col;

    @FXML
    private TableColumn<Stu_Scholar, String> name_col;

    @FXML
    private TableColumn<Stu_Scholar, String> major_col;

    @FXML
    private TableColumn<Stu_Scholar, String> scholar_col;

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private AnchorPane tab_pane;

    @FXML
    private AnchorPane query_pane;

    private Db_utils dbUtils=new Db_utils();
    private StuScholarDao stuScholarDao=new StuScholarDao();

    @FXML
    void query_action(ActionEvent event) {
        String id=id_text.getText();
        String name=name_text.getText();
        String sex=null;
        String birth=null;
        int grade=0;
        String m_id=null;
        Student student=new Student(id,name,sex,birth,grade,m_id);
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=stuScholarDao.query(con,student);
            fill_table(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill_table(ResultSet rs) throws Exception{
        ObservableList<Stu_Scholar> data= FXCollections.observableArrayList();
        while(rs.next()){
            Stu_Scholar in=new Stu_Scholar(rs.getString("Stu_id"),rs.getString("Stu_name"),
                    rs.getString("Major_name"),rs.getString("Scholar_name"));
            data.add(in);
        }
        id_col.setCellValueFactory(new PropertyValueFactory<>("stuId"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("stuName"));
        major_col.setCellValueFactory(new PropertyValueFactory<>("stuMajor"));
        scholar_col.setCellValueFactory(new PropertyValueFactory<>("scholarName"));
        stu_scholar_tab.setItems(data);
        show_tab();
    }

    private void show_tab() {
        query_pane.setVisible(false);
        tab_pane.toFront();
        tab_pane.setVisible(true);
    }

    private void show_query(){
        tab_pane.setVisible(false);
        query_pane.toFront();
        query_pane.setVisible(true);
    }

    @FXML
    void reset_action(ActionEvent event) {
        this.id_text.setText("");
        this.name_text.setText("");
    }


    @FXML
    void return_action(ActionEvent event) {
        this.id_text.setText("");
        this.name_text.setText("");
        show_query();
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("query_stu_scholar.fxml"));
    }

}
