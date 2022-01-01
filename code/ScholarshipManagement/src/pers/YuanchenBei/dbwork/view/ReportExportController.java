package pers.YuanchenBei.dbwork.view;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.SplitMenuButton;
import pers.YuanchenBei.dbwork.utils.ReportExport;

import java.io.File;
import java.io.IOException;

public class ReportExportController{
    private String filepath;
    private boolean selected=false;
    @FXML
    private SplitMenuButton top_button;

    @FXML
    void print_event(ActionEvent event) {
        try{
            if(selected){
                ReportExport.export();
                Runtime.getRuntime().exec("cmd.exe /C start AcroRd32 /P "+filepath);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
                alert.setTitle("请先选择所要生成的报表!");
                alert.setHeaderText("您还没有选择所要生成的报表"); // 设置对话框的头部文本
                alert.setContentText("请您先选择所要生成的报表");
                alert.setResizable(false);
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    void view_event(ActionEvent event) {
        try{
            if(selected){
                ReportExport.export();
                Runtime.getRuntime().exec("cmd.exe /C start AcroRd32 "+filepath);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
                alert.setTitle("请先选择所要生成的报表!");
                alert.setHeaderText("您还没有选择所要生成的报表"); // 设置对话框的头部文本
                alert.setContentText("请您先选择所要生成的报表");
                alert.setResizable(false);
                alert.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
     *测试使用2019-2020学年国家励志奖学金，奖学金编号5
     */
    @FXML
    void scholar_id5(ActionEvent event) {
        top_button.setText("2019-2020学年国家励志奖学金");
        filepath="E:/report/report1.pdf";
        selected=true;
    }

    Parent createNode() throws IOException {
        return FXMLLoader.load(getClass().getResource("report_export.fxml"));
    }
}
