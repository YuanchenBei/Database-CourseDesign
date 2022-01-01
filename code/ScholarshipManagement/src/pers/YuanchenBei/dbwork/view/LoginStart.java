package pers.YuanchenBei.dbwork.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginStart extends Application {
    @Override
    public void start(Stage stage)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.setTitle("用户登录");
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
