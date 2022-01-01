package pers.YuanchenBei.dbwork.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pers.YuanchenBei.dbwork.dao.ScholarshipDao;
import pers.YuanchenBei.dbwork.model.Scholarship;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class DeleteScholarshipController {

    @FXML
    private TextField id_text;

    @FXML
    private TableView<Scholarship> delete_tab;

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
    private Button del_button;

    @FXML
    private Button reset_button;

    private Scholarship del_scholar;
    private Db_utils dbUtils=new Db_utils();
    private ScholarshipDao scholarshipDao=new ScholarshipDao();

    @FXML
    void query_action(ActionEvent event) {
        int id=0;
        int year=0;
        if(String_isEmpty.isNotEmpty(this.id_text.getText())){
            id=Integer.parseInt(this.id_text.getText());
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
                fill_table(rs);
                tab_vis(true);
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

    private void fill_table(ResultSet rs) throws Exception{
        ObservableList<Scholarship> data= FXCollections.observableArrayList();
        while(rs.next()){
            Scholarship in=new Scholarship(rs.getInt("Scholar_id"),rs.getString("Scholar_name"),
                    rs.getString("Scholar_rank"),rs.getInt("Scholar_year"),
                    rs.getString("Scholar_issuer"));
            del_scholar=in;
            data.add(in);
        }
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("scholarName"));
        rank_col.setCellValueFactory(new PropertyValueFactory<>("scholarRank"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("scholarYear"));
        issuer_col.setCellValueFactory(new PropertyValueFactory<>("scholarIssuer"));
        delete_tab.setItems(data);
    }

    @FXML
    void delete_action(ActionEvent event) {
        Connection con=null;
        try {
            con=dbUtils.getCon();
            int n=scholarshipDao.delete(con,del_scholar.getId());
            System.out.println(n);
            if(n!=0){
                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("奖学金删除成功");
                alert.setHeaderText(del_scholar.getScholarName()+"删除成功!"); // 设置对话框的头部文本
                alert.setResizable(false);
                // 设置对话框的内容文本
                alert.show(); // 显示对话框
                this.id_text.setText("");
                delete_tab.getItems().clear();
                delete_tab.refresh();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("奖学金删除失败");
                alert.setHeaderText(del_scholar.getScholarName()+"删除失败!"); // 设置对话框的头部文本
                alert.setResizable(false);
                // 设置对话框的内容文本
                alert.show(); // 显示对话框
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void reset_action(ActionEvent event) {
        this.id_text.setText("");
        tab_vis(false);
    }

    void tab_vis(boolean x){
        if(x){
            delete_tab.setVisible(true);
            del_button.setVisible(true);
            reset_button.setVisible(true);
        } else{
            delete_tab.setVisible(false);
            del_button.setVisible(false);
            reset_button.setVisible(false);
        }
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("delete_scholarship.fxml"));
    }
}
