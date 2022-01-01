package pers.YuanchenBei.dbwork.view;

import com.sun.xml.internal.bind.v2.runtime.property.PropertyFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import pers.YuanchenBei.dbwork.dao.ScholarshipDao;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class QueryScholarshipController {

    @FXML
    private AnchorPane tab_pane;

    @FXML
    private AnchorPane query_pane;

    @FXML
    private TextField id_text;

    @FXML
    private TextField name_text;

    @FXML
    private TextField year_text;

    @FXML
    private TextField issuer_text;

    @FXML
    private TableView<Scholarship> scholar_tab;

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


    private String level=null;
    private int counter_country=0;
    private int counter_province=0;
    private int counter_school=0;
    private Db_utils dbUtils=new Db_utils();
    private ScholarshipDao scholarshipDao=new ScholarshipDao();

    @FXML
    void country_level_action(MouseEvent event) {
        counter_country=(counter_country+1)%2;
        if(counter_country==1) level="国家级";
        else level=null;
    }

    @FXML
    void province_level_action(MouseEvent event) {
        counter_province=(counter_province+1)%2;
        if(counter_province==1) level="省级";
        else level=null;
    }

    @FXML
    void school_level_action(MouseEvent event) {
        counter_school=(counter_school+1)%2;
        if(counter_school==1) level="校级";
        else level=null;
    }

    /*
     *查询生成表格
     */
    @FXML
    void query_button_action(ActionEvent event) {
        int id=0;
        int year=0;
        if(String_isEmpty.isNotEmpty(this.id_text.getText())){
            id=Integer.parseInt(this.id_text.getText());
        }
        if(String_isEmpty.isNotEmpty(this.year_text.getText())){
            year=Integer.parseInt(this.year_text.getText());
        }
        String name=this.name_text.getText();
        String issuer=this.issuer_text.getText();
        Scholarship scholarship=new Scholarship(id,name,level,year,issuer);
        Connection con=null;
        try {
            con=dbUtils.getCon();
            ResultSet rs=scholarshipDao.query(con,scholarship);
            fill_table(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fill_table(ResultSet rs) throws Exception{
        ObservableList<Scholarship> data= FXCollections.observableArrayList();
        while(rs.next()){
            Scholarship in=new Scholarship(rs.getInt("Scholar_id"),rs.getString("Scholar_name"),
                    rs.getString("Scholar_rank"),rs.getInt("Scholar_year"),
                    rs.getString("Scholar_issuer"));
            data.add(in);
        }
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("scholarName"));
        rank_col.setCellValueFactory(new PropertyValueFactory<>("scholarRank"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("scholarYear"));
        issuer_col.setCellValueFactory(new PropertyValueFactory<>("scholarIssuer"));
        scholar_tab.setItems(data);
        show_tab();
    }

    private void show_tab() {
        query_pane.setVisible(false);
        query_pane.toBack();
        tab_pane.setVisible(true);
    }

    private void show_query(){
        tab_pane.setVisible(false);
        tab_pane.toBack();
        query_pane.setVisible(true);
    }

    /*
     *重置输入框
     */
    @FXML
    void reset_button_action(ActionEvent event) {
        this.id_text.setText("");
        this.name_text.setText("");
        this.year_text.setText("");
        this.issuer_text.setText("");
    }

    /*
     *返回事件:返回查询界面
     */
    @FXML
    void return_event(ActionEvent event) {
        this.id_text.setText("");
        this.name_text.setText("");
        this.year_text.setText("");
        this.issuer_text.setText("");
        show_query();
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("query_scholarship.fxml"));
    }
}

