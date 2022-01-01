package pers.YuanchenBei.dbwork.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainFrameController {

    @FXML
    private BorderPane rootpane;

    @FXML
    void add_scholarship_event(ActionEvent event) {
        try {
            rootpane.setCenter(new AddScholarController().createNode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void query_scholarship_event(ActionEvent event) {
        try {
            rootpane.setCenter(new QueryScholarshipController().createNode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new UpdateScholarshipController().createNode());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void query_stu_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new QueryStuScholarController().createNode());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void add_stu_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new AddStuScholarController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void delete_stu_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new DeleteStuScholarController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void update_stu_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new UpdateStuScholarController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void delete_scholar_event(ActionEvent event) {
        try{
            rootpane.setCenter(new DeleteScholarshipController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void add_student_event(ActionEvent event) {
        try{
            rootpane.setCenter(new AddStudentController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void update_stu_event(ActionEvent event) {
        try{
            rootpane.setCenter(new UpdateStudentController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void delete_student_event(ActionEvent event) {
        try{
            rootpane.setCenter(new DeleteStudentController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void report_export_event(ActionEvent event) {
        try {
            rootpane.setCenter(new ReportExportController().createNode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
