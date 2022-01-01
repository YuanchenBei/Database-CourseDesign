package pers.YuanchenBei.dbwork.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import pers.YuanchenBei.dbwork.dao.UserDao;
import pers.YuanchenBei.dbwork.model.User;
import pers.YuanchenBei.dbwork.utils.Db_utils;
import pers.YuanchenBei.dbwork.utils.String_isEmpty;
import pers.YuanchenBei.dbwork.utils.MD5_Encryption;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField userName_txt;

    @FXML
    private PasswordField password_txt;

    @FXML
    private Button login_btn;

    @FXML
    private Button reset_btn;

    private Db_utils dbUtils=new Db_utils();
    private UserDao userDao=new UserDao();

    /*
     *登录按钮事件处理
     */
    @FXML
    void login_event(ActionEvent event) {
        String userName = this.userName_txt.getText();
        String password = this.password_txt.getText();

        if(String_isEmpty.isEmpty(userName)){
            Alert alert = new Alert(Alert.AlertType.WARNING); // 创建一个消息对话框
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("用户名不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新输入用户名和密码");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
            this.userName_txt.setText("");
            this.password_txt.setText("");
        }else if(String_isEmpty.isEmpty(password)){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("好像出了点问题...");
            alert.setHeaderText("密码不能为空!"); // 设置对话框的头部文本
            alert.setContentText("请重新输入用户名和密码");
            alert.setResizable(false);
            // 设置对话框的内容文本
            alert.show(); // 显示对话框
            this.userName_txt.setText("");
            this.password_txt.setText("");
        }
        else{
            password = MD5_Encryption.md5(password); //对密码进行MD5加密
            User user=new User(userName,password);
            Connection con=null;
            try {
                con=dbUtils.getCon();
                User current_user=userDao.login(con,user);
                if(current_user!=null){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("登录成功");
                    alert.setHeaderText("登录成功!"); // 设置对话框的头部文本
                    alert.setContentText("欢迎使用");
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                    Thread.sleep(1500);
                    alert.close();
                    Platform.runLater(() -> {
                        //创建主界面窗口
                        try {
                            new MainFrameStart().start(new Stage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //关闭登陆窗口
                        Stage now=(Stage)login_btn.getScene().getWindow();
                        now.hide();
                    });
                }else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("好像出了点问题...");
                    alert.setHeaderText("用户名或密码错误!"); // 设置对话框的头部文本
                    alert.setContentText("请确认后重新输入用户名和密码");
                    alert.setResizable(false);
                    // 设置对话框的内容文本
                    alert.show(); // 显示对话框
                    this.userName_txt.setText("");
                    this.password_txt.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
     *重置按钮事件处理
     */
    @FXML
    void reset_event(ActionEvent event) {
        this.userName_txt.setText("");
        this.password_txt.setText("");
    }

}
