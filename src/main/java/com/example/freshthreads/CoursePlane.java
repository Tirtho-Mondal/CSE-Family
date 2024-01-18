package com.example.freshthreads;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class CoursePlane {

    @FXML
    private TableColumn<getService, String> syllabus_1_1_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_1_1_credit;

    @FXML
    private AnchorPane syllabus_1_1_form;

    @FXML
    private TableColumn<getService, String> syllabus_1_1_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_1_1_sl;

    @FXML
    private TableView<getService> syllabus_1_1_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_1_1_tittle;

    @FXML
    private TableColumn<getService, String> syllabus_1_2_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_1_2_credit;

    @FXML
    private AnchorPane syllabus_1_2_form;

    @FXML
    private TableColumn<getService, String> syllabus_1_2_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_1_2_sl;

    @FXML
    private TableView<getService> syllabus_1_2_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_1_2_tittle;


    //insert table of 1-1:

//    @FXML
//    private TableColumn<getService, String> syllabus_1_1_courseCode;
//
//    @FXML
//    private TableColumn<getService, String> syllabus_1_1_credit;
//
//    @FXML
//    private AnchorPane syllabus_1_1_form;
//
//    @FXML
//    private TableColumn<getService, String> syllabus_1_1_preResuist;
//
//    @FXML
//    private TableColumn<getService, String> syllabus_1_1_sl;
//
//    @FXML
//    private TableColumn<getService, String> syllabus_1_1_tittle;

    //    @FXML
//    private TableColumn<getService, String> syllabus_1_1_
//    private  ObservableList<getService>Course=FXCollections.observableArrayList(
//            new getService("1","CSE 1101","Structured Programming","3","")
//
//
//);
    private ObservableList<getService> Course = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initializeOneOne() {

        syllabus_1_1_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_1_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_1_1_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_1_1_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_1_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_1_1_tableView.setItems(Course);
    }

    //1-2
    private ObservableList<getService> Course_12 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initializeOneTwo() {

        syllabus_1_2_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_1_2_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_1_2_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_1_2_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_1_2_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_1_2_tableView.setItems(Course_12);
    }


    @FXML
    private AnchorPane course_Second;
    @FXML
    private TableColumn<getService, String> syllabus_2_1_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_2_1_credit;

    @FXML
    private AnchorPane syllabus_2_1_form;

    @FXML
    private TableColumn<getService, String> syllabus_2_1_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_2_1_sl;

    @FXML
    private TableView<getService> syllabus_2_1_tableView,syllabus_2_2_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_2_1_tittle;

    @FXML
    private TableColumn<getService, String> syllabus_2_2_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_2_2_credit;

    @FXML
    private TableColumn<getService, String> syllabus_2_2_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_2_2_sl;

    @FXML
    private TableColumn<getService, String> syllabus_2_2_tittle;

    //2-1
    private ObservableList<getService> Course_21 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initializeTwoOne() {

        syllabus_2_1_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_2_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_2_1_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_2_1_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_2_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_2_1_tableView.setItems(Course_21);
    }




    //2-2
    private ObservableList<getService> Course_22 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initializeTwoTwo() {

        syllabus_2_2_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_2_2_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_2_2_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_2_2_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_2_2_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_2_2_tableView.setItems(Course_22);
    }

    @FXML
    private TableColumn<getService, String> syllabus_3_1_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_3_1_credit;

    @FXML
    private AnchorPane syllabus_3_1_form;

    @FXML
    private TableColumn<getService, String> syllabus_3_1_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_3_1_sl;

    @FXML
    private TableView<getService> syllabus_3_1_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_3_1_tittle;

    @FXML
    private TableColumn<getService, String> syllabus_3_2_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_3_2_credit;

    @FXML
    private AnchorPane syllabus_3_2_form;

    @FXML
    private TableColumn<getService, String> syllabus_3_2_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_3_2_sl;

    @FXML
    private TableView<getService> syllabus_3_2_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_3_2_tittle;

    //2-1
    private ObservableList<getService> Course_31 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initializeThreeOne() {

        syllabus_3_1_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_3_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_3_1_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_3_1_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_3_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_3_1_tableView.setItems(Course_31);
    }




    //2-2
    private ObservableList<getService> Course_32 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initialize3Two() {

        syllabus_3_2_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_3_2_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_3_2_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_3_2_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_3_2_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_3_2_tableView.setItems(Course_32);
    }

    // 4

    @FXML
    private TableColumn<getService, String> syllabus_4_1_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_4_1_credit;

    @FXML
    private AnchorPane syllabus_4_1_form;

    @FXML
    private TableColumn<getService, String> syllabus_4_1_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_4_1_sl;

    @FXML
    private TableView<getService> syllabus_4_1_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_4_1_tittle;

    @FXML
    private TableColumn<getService, String> syllabus_4_2_courseCode;

    @FXML
    private TableColumn<getService, String> syllabus_4_2_credit;

    @FXML
    private AnchorPane syllabus_4_2_form;

    @FXML
    private TableColumn<getService, String> syllabus_4_2_preResuist;

    @FXML
    private TableColumn<getService, String> syllabus_4_2_sl;

    @FXML
    private TableView<getService> syllabus_4_2_tableView;

    @FXML
    private TableColumn<getService, String> syllabus_4_2_tittle;

    private ObservableList<getService> Course_41 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initialize4One() {

        syllabus_4_1_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_4_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_4_1_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_4_1_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_4_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_4_1_tableView.setItems(Course_41);
    }




    //2-2
    private ObservableList<getService> Course_42 = FXCollections.observableArrayList(
            new getService("1", "CSE 1101", "Structured Programming", "3", ""),

            new getService("2", "CSE 1102", "Structured Programming Laboratory", "3", ""),
            new getService("3", "CSE 1107", "Discrete Mathematics", "3", ""),
            new getService("4", "PHY 1107", "Physics", "3", ""),
            new getService("5", "PHY 1108", "Physics Laboratory", "1.5", ""),
            new getService("6", "HUM 1107", "English and Human Communication", "3", ""),
            new getService("7", "HUM 1108", "English and Human Communication Laboratory", "0.75", ""),
            new getService("8", "MATH 1107", "Differential and Integral Calculus", "3", "")
            //new getService("1", "CSE 1102", "Structured Programming Laboratory", "3", "")

    );

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initialize4Two() {

        syllabus_4_2_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_4_2_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_4_2_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_4_2_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_4_2_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_4_2_tableView.setItems(Course_42);
    }
    @FXML
    private AnchorPane courseControl;

//    @FXML
//    private AnchorPane course_Second;

    @FXML
    private AnchorPane course_first;

    @FXML
    private AnchorPane course_fourth;

    @FXML
    private AnchorPane course_third;

    @FXML
    private Button return_home;

    public void FirstYear()
    {   initializeOneOne();
        initializeOneTwo();
        courseControl.setVisible(false);
        course_first.setVisible(true);
    }
    public void FirstBack()
    {
        courseControl.setVisible(true);
        course_first.setVisible(false);

    }

    public void SecondYear()
    {   initializeTwoOne();
        initializeTwoTwo();
        courseControl.setVisible(false);
        course_Second.setVisible(true);
    }

    public void SecondBack()
    {

        courseControl.setVisible(true);
        course_Second.setVisible(false);

    }

    public void ThirdYear()
    {   initializeThreeOne();
        initialize3Two();
        courseControl.setVisible(false);
        course_third.setVisible(true);
    }
    public void thirdBack()
    {
        courseControl.setVisible(true);
        course_third.setVisible(false);
    }
    public void fourthYear()
    {   initialize4One();
        initialize4Two();
        courseControl.setVisible(false);
        course_fourth.setVisible(true);
    }

    public void fourthBack()
    {
        courseControl.setVisible(true);
        course_fourth.setVisible(false);
    }
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



}


