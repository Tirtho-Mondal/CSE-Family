package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class AdminAcc {


    @FXML
    private TextField adimn_acc_cre_adminId;

    @FXML
    private PasswordField adimn_acc_cre_confPass;

    @FXML
    private PasswordField adimn_acc_cre_password;

    @FXML
    private TextField adimn_acc_cre_reffId;

    Alert alert;
    private Connection connect;
    private PreparedStatement prepared;
    private ResultSet result;
    private Statement statement;

    @FXML
    private AnchorPane admin_account_form;

    public void registation()
    {


        if (adimn_acc_cre_adminId.getText().isEmpty()
                || adimn_acc_cre_password.getText().isEmpty()
                || adimn_acc_cre_confPass.getText().isEmpty()
                || adimn_acc_cre_reffId.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        } else {
            try {
                // Check if the reference ID exists
                String checkId = "SELECT adminId FROM adminpanel WHERE adminId=?";
                connect = database.connectDb();
                prepared = connect.prepareStatement(checkId);
                prepared.setString(1, adimn_acc_cre_reffId.getText());
                result = prepared.executeQuery();

                if (!result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your Reference ID is not an Admin");
                    alert.showAndWait();
                } else {
                    // Password validation
                    String password = adimn_acc_cre_password.getText();
                    String confirmPassword = adimn_acc_cre_confPass.getText();

                    if (password.equals(confirmPassword) && !password.isBlank()) {
                        // Insert data into the database
                        String insertData = "INSERT INTO adminpanel(adminId, password, referenceID) VALUES (?, ?, ?)";
                        prepared = connect.prepareStatement(insertData);
                        prepared.setString(1, adimn_acc_cre_adminId.getText());
                        prepared.setString(2, password);
                        prepared.setString(3, adimn_acc_cre_reffId.getText());
                        prepared.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Registration is Successful");
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password doesn't match");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle database errors appropriately
            } finally {
                // Close resources in a finally block
                try {
                    if (result != null) result.close();
                    if (prepared != null) prepared.close();
                    if (connect != null) connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }






//    {
//        if(adimn_acc_cre_adminId.getText().isEmpty()
//                ||adimn_acc_cre_password.getText().isEmpty()
//                ||adimn_acc_cre_confPass.getText().isEmpty()
//                ||adimn_acc_cre_reffId.getText().isEmpty())
//        {
//            alert=new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("All fileds are necessary to be filled");
//            alert.showAndWait();
//        }
//        else
//        {
//            String checkId="SELECT adminId FROM adminpanel WHERE adminId='"+adimn_acc_cre_reffId.getText()+"'";
//            String insertData="INSERT INTO adminpanel(adminId,password,referenceID)VALUES(?,?,?)";
//            connect=database.connectDb();
//            try
//            {
//                prepared=connect.prepareStatement(checkId);
//                result=prepared.executeQuery();
//                if(result.next())
//                {
//                    alert=new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Your Refference id is not an Admin");
//                    alert.showAndWait();
//
//                }else
//                {
//                    if(adimn_acc_cre_password.getText().equals(adimn_acc_cre_confPass.getText())&&adimn_acc_cre_confPass.getText().isBlank()==false)
//                    {
//                        prepared=connect.prepareStatement(insertData);
//                        prepared.setString(1,adimn_acc_cre_adminId.getText());
//                        prepared.setString(2,adimn_acc_cre_password.getText());
//                        prepared.setString(3,adimn_acc_cre_reffId.getText());
//                        prepared.executeUpdate();
//
//                        alert=new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Infromation Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Registration is Successfull");
//                        alert.showAndWait();
//                    }
//                    else {
//                        alert=new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Password Does't match");
//                        alert.showAndWait();
//                    }
//
//                }
//
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }

    }

    @FXML
    private Button back_btn;

//    @FXML
//    private Button return_btn;
    public void Back()
    {

            try{
                alert =new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to logout?");
                Optional<ButtonType> option =alert.showAndWait();

                if(option.get().equals(ButtonType.OK))
                {
                    //link to login form
                    Parent root = FXMLLoader.load(getClass().getResource("loginSuccessful.fxml"));
                    Stage stage=new Stage();
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                    //hiding loginSuccessful.fxml
                    back_btn.getScene().getWindow().hide();
                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }


}
