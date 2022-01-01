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
import javafx.scene.paint.Color;
import pers.YuanchenBei.dbwork.dao.ScholarshipDao;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class UpdateScholarshipController {

    @FXML
    private AnchorPane main_pane;

    @FXML
    private AnchorPane modify_result_pane;

    @FXML
    private AnchorPane query_result_pane;

    @FXML
    private TableView<Scholarship> update_tab;

    @FXML
    private TableColumn<Scholarship, Integer> id_col;

    @FXML
    private TableColumn<Scholarship, String> name_col;

    @FXML
    private TableColumn<Scholarship, String> rank_col;

    @FXML
    private TableColumn<Scholarship, Integer> year_col;

    @FXML
    private TableColumn<Scholarship, String> issuer_col;

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private TextField rank_text;

    @FXML
    private TextField year_text;

    @FXML
    private TextField issuer_text;

    @FXML
    private TextField modify_id_text;

    private Scholarship update_scholar;
    private Db_utils dbUtils=new Db_utils();
    private ScholarshipDao scholarshipDao=new ScholarshipDao();

    @FXML
    void modify_action(ActionEvent event) {
        update_scholar.setScholarName(this.name_text.getText());
        update_scholar.setScholarRank(this.rank_text.getText());
        update_scholar.setScholarYear(Integer.parseInt(this.year_text.getText()));
        update_scholar.setScholarIssuer(this.issuer_text.getText());
        Connection con=null;
        try{
            con=dbUtils.getCon();
            int n=scholarshipDao.update(con,update_scholar);
            if(n==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION); // 创建一个消息对话框
                alert.setTitle("奖学金信息修改成功!");
                alert.setHeaderText(update_scholar.getScholarName()+"修改成功!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
                modify_result(update_scholar);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR); // 创建一个消息对话框
                alert.setTitle("奖学金信息修改失败!");
                alert.setHeaderText("很抱歉,奖学金修改失败!"); // 设置对话框的头部文本
                alert.setResizable(false);
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void modify_result(Scholarship update_scholar) {
        ObservableList<Scholarship> data= FXCollections.observableArrayList();
        data.add(update_scholar);
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("scholarName"));
        rank_col.setCellValueFactory(new PropertyValueFactory<>("scholarRank"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("scholarYear"));
        issuer_col.setCellValueFactory(new PropertyValueFactory<>("scholarIssuer"));
        update_tab.setItems(data);

        main_pane.setVisible(false);
        query_result_pane.setVisible(false);
        modify_result_pane.setVisible(true);
        modify_result_pane.toFront();
    }

    @FXML
    void query_button(ActionEvent event) {
        int id=0;
        int year=0;
        if(String_isEmpty.isNotEmpty(this.modify_id_text.getText())){
            id=Integer.parseInt(this.modify_id_text.getText());
        }
        String name="";
        String level="";
        String issuer="";
        Scholarship scholarship=new Scholarship(id,name,level,year,issuer);
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=scholarshipDao.query(con,scholarship);
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    update_scholar=new Scholarship(rs.getInt("Scholar_id"),rs.getString("Scholar_name"),
                            rs.getString("Scholar_rank"),rs.getInt("Scholar_year"),
                            rs.getString("Scholar_issuer"));
                    fill_text(update_scholar);
                    query_result_pane.setVisible(true);
                    query_result_pane.toFront();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
                alert.setTitle("该奖学金不存在!");
                alert.setHeaderText("不存该奖学金编号对应的奖学金"); // 设置对话框的头部文本
                alert.setContentText("请确定奖学金编号是否正确");
                alert.setResizable(false);
                alert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill_text(Scholarship update_scholar) {
        this.id_text.setEditable(false);
        this.id_text.setText(String.valueOf(update_scholar.getId()));
        this.name_text.setText(update_scholar.getScholarName());
        this.rank_text.setText(update_scholar.getScholarRank());
        this.year_text.setText(String.valueOf(update_scholar.getScholarYear()));
        this.issuer_text.setText(update_scholar.getScholarIssuer());
    }

    @FXML
    void reset_action(ActionEvent event) {
        query_result_pane.setVisible(false);
        main_pane.toFront();
        this.modify_id_text.setText("");
    }

    @FXML
    void return_action(ActionEvent event) {
        modify_result_pane.setVisible(false);
        main_pane.toFront();
        main_pane.setVisible(true);
        this.modify_id_text.setText("");
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("update_scholarship.fxml"));
    }
}
