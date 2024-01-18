package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class CourseControl {

    @FXML
    private Button return_home;
Alert alert;
    public void Back()
    {

        try{
            alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to Return!");
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
                return_home.getScene().getWindow().hide();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public void FirsBtn()
    {
        CoursePlane cp=new CoursePlane();
        //cp.initialize();

    }








}
