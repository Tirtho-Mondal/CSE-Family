package com.example.freshthreads;
// JsonParseController.java
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class LoginSuccessful {

    @FXML
    private ListView<String> jobListView;


    @FXML
    private AnchorPane Dashboard_form;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private GridPane customer_gridPane;

    @FXML
    private ScrollPane customer_scrollPane;

    @FXML
    private BarChart<?, ?> dashboard_IPD_chart;

    @FXML
    private AreaChart<?, ?> dashboard_NOC_chart;

    @FXML
    private LineChart<?, ?> dashboard_NOO_chart;

    @FXML
    private Label dashboard_To;

    @FXML
    private Label dashboard_Todayi;

    @FXML
    private Label dashboard_Totalincome;

    @FXML
    private Button dashboard_btn,courses_btn;

    @FXML
    private Label dashboard_customerN;

    @FXML
    private Button logout_btn;

    @FXML
    private TextField order_amount;

    @FXML
    private Button order_btn;

    @FXML
    private Label order_change;

    @FXML
    private TableColumn<?, ?> order_co_Price;

    @FXML
    private TableColumn<?, ?> order_co_Qty;

    @FXML
    private TableColumn<?, ?> order_co_Service;

    @FXML
    private TableColumn<?, ?> order_co_Type;

    @FXML
    private TableColumn<?, ?> order_co_orderID;

    @FXML
    private DatePicker order_dateP;

    @FXML
    private AnchorPane order_form;

    @FXML
    private AnchorPane order_from;

    @FXML
    private GridPane order_gridPane;

    @FXML
    private GridPane faculty_gridPane1;

    @FXML
    private Button order_payBtn;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private ScrollPane order_scrollPane;

    @FXML
    private TableView<getService> order_tableView;

    @FXML
    private Label order_total;

    @FXML
    private TextField service_PPK_pricePerKilo;

    @FXML
    private Button service_btn;

    @FXML
    private Button service_clearBtn;

    @FXML
    private ComboBox<?> service_clothType;

    @FXML
    private TableColumn<getService, String> service_co_DI_dateinserted;

    @FXML
    private TableColumn<getService, String> service_co_DU_dateUpdate;

    @FXML
    private TableColumn<getService, String> service_co_clothType;

    @FXML
    private TableColumn<getService, String> service_co_id;

    @FXML
    private TableColumn<getService, String> service_co_priceperKilo_ppk;

    @FXML
    private TableColumn<getService, String> service_co_services;

    @FXML
    private Button service_deleteBtn;

    @FXML
    private AnchorPane service_form;

    @FXML
    private AnchorPane service_form_2;

    @FXML
    private TextField service_id;

    @FXML
    private ImageView service_imageView;

    @FXML
    private Button service_importBtn;

    @FXML
    private Button service_insertBtn;

    @FXML
    private Label service_service;

    @FXML
    private TableView<getService> service_tableView;
    @FXML
    private TableView<getService> syllabus_1_1_tableView;

    @FXML
    private Button service_uodateBtn;

    @FXML
    private Label username;

    private Alert alert;
    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> service_servicetype;

    //courses btn

    public void cousesBtn() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("course_plane.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        Stage stage = new Stage();
      //  stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        main_form.getScene().getWindow().hide();
    }
    private void fetchData() {
        try {
            URL url = new URL("https://api.myjson.online/v1/records/7d363520-c0a6-428f-b836-7a469a74922a");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            String json = response.toString();
            jsonParse(json);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void jsonParse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject data = jsonObject.getAsJsonObject("data");
        JsonArray jobs = data.getAsJsonArray("cse_jobs");

        jobListView.getItems().clear(); // Clear existing items

        for (int i = 0; i < jobs.size(); i++) {
            JsonObject job = jobs.get(i).getAsJsonObject();
            String company = job.get("company").getAsString();
            String location = job.get("location").getAsString();
            String jobTitle = job.get("job_title").getAsString();
            String jobDescription = job.get("job_description").getAsString();

//            Text companyText = new Text("Company: " + company);
//            companyText.setFont(Font.font("Arial", FontWeight.BOLD));
            Text companyText = new Text("Company: " + company);
            companyText.setStyle("-fx-font-weight: bold;");

            String listItem = "\nLocation: " + location + "\nJob Title: " + jobTitle + "\nJob Description: " + jobDescription;
            jobListView.getItems().addAll(companyText.getText(), listItem);

            JsonArray requirements = job.getAsJsonArray("requirements");

            for (int j = 0; j < requirements.size(); j++) {
                JsonObject requirement = requirements.get(j).getAsJsonObject();
                String degree = requirement.get("degree").getAsString();

                String requirementItem = "\nDegree: " + degree;

                JsonArray skills = requirement.getAsJsonArray("skills");
                requirementItem += "\nSkills: ";
                for (int k = 0; k < skills.size(); k++) {
                    requirementItem += skills.get(k).getAsString() + " ";
                }

                JsonArray additionalRequirements = requirement.getAsJsonArray("additional_requirements");
                requirementItem += "\nAdditional Requirements: ";
                for (int k = 0; k < additionalRequirements.size(); k++) {
                    requirementItem += additionalRequirements.get(k).getAsString() + " ";
                }

                jobListView.getItems().add(requirementItem);
            }

            jobListView.getItems().add("\n"); // Add a newline for better readability between jobs
        }
    }

//  public void switchFrom()
//  {
//      fetchData();
//  }
//
//    // Josn Parsing-->
//    public static void fetchData(){
//        //welcomeText.setText("Welcome to JavaFX Application!");
//        //String response = "\"Welcome to JavaFX Application!\"";
//        //jsonParse(response);
//
//        try {
//            URL url = new URL("https://api.myjson.online/v1/records/7d363520-c0a6-428f-b836-7a469a74922a");
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//
//            StringBuilder response = new StringBuilder();
//            String line;
//            System.out.println("ekahne elo");
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//
//            reader.close();
//            connection.disconnect();
//
//            String json = response.toString();
//            System.out.println("hi");
//            jsonParse(json);
//
//        } catch (Exception e) {
//            //throw new RuntimeException(e);
//            System.out.println("error khaise");
//        }
//
//
//    }
//    public static void jsonParse(String response) {
//        // Parse the JSON response using Gson
//        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
//
//        // Get the 'data' object
//        JsonObject data = jsonObject.getAsJsonObject("data");
//
//        // Get the 'cse_jobs' array
//        JsonArray jobs = data.getAsJsonArray("cse_jobs");
//
//        // Iterate through each job
//        for (int i = 0; i < jobs.size(); i++) {
//            JsonObject job = jobs.get(i).getAsJsonObject();
//            String company = job.get("company").getAsString();
//            String location = job.get("location").getAsString();
//            String jobTitle = job.get("job_title").getAsString();
//            String jobDescription = job.get("job_description").getAsString();
//
//            System.out.println("Company: " + company);
//            System.out.println("Location: " + location);
//            System.out.println("Job Title: " + jobTitle);
//            System.out.println("Job Description: " + jobDescription);
//
//            // Get the 'requirements' array
//            JsonArray requirements = job.getAsJsonArray("requirements");
//
//            // Iterate through each requirement
//            for (int j = 0; j < requirements.size(); j++) {
//                JsonObject requirement = requirements.get(j).getAsJsonObject();
//                String degree = requirement.get("degree").getAsString();
//
//                System.out.println("Degree: " + degree);
//
//                // Get the 'skills' array
//                JsonArray skills = requirement.getAsJsonArray("skills");
//
//                System.out.print("Skills: ");
//                for (int k = 0; k < skills.size(); k++) {
//                    System.out.print(skills.get(k).getAsString() + " ");
//                }
//                System.out.println();
//
//                // Get the 'additional_requirements' array
//                JsonArray additionalRequirements = requirement.getAsJsonArray("additional_requirements");
//
//                System.out.print("Additional Requirements: ");
//                for (int k = 0; k < additionalRequirements.size(); k++) {
//                    System.out.print(additionalRequirements.get(k).getAsString() + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println(); // Add a newline for better readability between jobs
//        }
//    }
//




    //adim creation
    public void create_an_admin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_acc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        verify_admin_form.getScene().getWindow().hide();
    }

    public void updateAccount() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Update_acc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        add_all_form.getScene().getWindow().hide();
    }

    public void coutAll()
    {
        fetchData();
        countAlumni();
        countFacultyMember();
        countStudent();
    }
    public void countAlumni() {
        String countAlum = "SELECT COUNT(*) as total FROM alumni";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(countAlum);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                int totalAlumni = resultSet.getInt("total");
               // System.out.println("Total alumni: " + totalAlumni);
                dashboard_Totalincome.setText(String.valueOf(totalAlumni));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void countFacultyMember() {
        String countAlum = "SELECT COUNT(*) as total FROM faculty";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(countAlum);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                int totalAlumni = resultSet.getInt("total");
               // System.out.println("Total alumni: " + totalAlumni);
                dashboard_customerN.setText(String.valueOf(totalAlumni));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void countStudent() {
        String countAlum = "SELECT COUNT(*) as total FROM student";
        connect = database.connectDb();
        try {
            prepare = connect.prepareStatement(countAlum);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                int totalAlumni = resultSet.getInt("total");
               // System.out.println("Total alumni: " + totalAlumni);
                dashboard_Todayi.setText(String.valueOf(totalAlumni));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    //datbase tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Statement statement;


    //insert table of 1-1:

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
    private TableColumn<getService, String> syllabus_1_1_tittle;

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
    public void initialize() {

        syllabus_1_1_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_1_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_1_1_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_1_1_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_1_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_1_1_tableView.setItems(Course);
    }

    //table course of 12
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

//    @FXML
//    private TableColumn<?, ?> syllabus_1_2_courseCode;
//
//    @FXML
//    private TableColumn<?, ?> syllabus_1_2_credit;
//
//    @FXML
//    private AnchorPane syllabus_1_2_form;
//
//    @FXML
//    private TableColumn<?, ?> syllabus_1_2_preResuist;
//
//    @FXML
//    private TableColumn<?, ?> syllabus_1_2_sl;
//
//    @FXML
//    private TableView<?> syllabus_1_2_tableView;
//
//    @FXML
//    private TableColumn<?, ?> syllabus_1_2_tittle;


    private final ObservableList<getService> Course_1_2 = FXCollections.observableArrayList(
            new getService("1", "CSE 1203", "Digital Logic Design", "3", ""),

            new getService("2", "CSE 1204", "Digital Logic Design Laboratory", "1.5", ""),
            new getService("3", "CSE 1205", "Object Oriented Programming", "3", ""),
            new getService("4", "CSE 1206", "Object Oriented Programming Laboratory", "1.5", ""),
            new getService("5", "CHEM 1207", "Chemistry", "1.5", ""),
            new getService("6", "CHEM 1208", "Chemistry Laboratory", "0.75", ""),
            new getService("7", "EEE 1207", "Basic Electrical Engineering", "3", ""),
            new getService("8", "EEE 1208", "Basic Electrical Engineering Laboratory", "1.5", ""),
            new getService("9", "CSE 1102", "Coordinate Geometry and Differential Equations", "3",""));

    // Initialize your TableView and set column cell value factories
    @FXML
    public void initialize_1_2() {
        syllabus_1_2_sl.setCellValueFactory(new PropertyValueFactory<>("sl"));
        syllabus_1_2_courseCode.setCellValueFactory(new PropertyValueFactory<>("coursecode"));
        syllabus_1_2_tittle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        syllabus_1_2_credit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        syllabus_1_2_preResuist.setCellValueFactory(new PropertyValueFactory<>("preReq"));

        // Set the items for the TableView
        syllabus_1_2_tableView.setItems(Course_1_2);
    }



//    //to show data in table view
//    public TextField SL,CourseCode,Tittle,Credit,Pre_Requiste;
//    public void setCourse()
//    {
//        syllabus_1_1_sl.setCellValueFactory(new PropertyValueFactory<>("SL"));
//        syllabus_1_1_courseCode.setCellValueFactory(new PropertyValueFactory<>("CourseCode"));
//        syllabus_1_1_tittle.setCellValueFactory(new PropertyValueFactory<>("Tittle"));
//        syllabus_1_1_credit.setCellValueFactory(new PropertyValueFactory<>("Credit"));
//        syllabus_1_1_preResuist.setCellValueFactory(new PropertyValueFactory<>("Pre_Requiste"));
//        //syllabus_1_1_sl.setCellValueFactory(new PropertyValueFactory<>("SL"));
//
//        //servicesListData=servicesDataList();
////        service_co_id.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
////        service_co_clothType.setCellValueFactory(new PropertyValueFactory<>("clothType"));
////        service_co_services.setCellValueFactory(new PropertyValueFactory<>("service"));
////        service_co_priceperKilo_ppk.setCellValueFactory(new PropertyValueFactory<>("pricePerKilo"));
////        service_co_DI_dateinserted.setCellValueFactory(new PropertyValueFactory<>("date"));
////        service_co_DU_dateUpdate.setCellValueFactory(new PropertyValueFactory<>("udate"));
//        service_tableView.setItems(Course);
//    }
    /*

    public ObservableList<getService>servicesDataList()
    {
        String sql="SELECT * FROM service ";
        ObservableList<getService>listData= FXCollections.observableArrayList();
        try{
            connect=database.connectDb();

            prepare=connect.prepareStatement(sql);
            resultSet= prepare.executeQuery();
            getService getS;

            while (resultSet.next())
            {
                getS=new getService(resultSet.getInt("id"),
                        resultSet.getString("serviceID"),
                        resultSet.getString("clothType"),
                        resultSet.getString("service"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getDate("date_insert"),
                        resultSet.getDate("date_update"));
                listData.add(getS);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;

    }

    private  ObservableList<getService>servicesListData;
    //to show data in table view
    public void servicesShowData()
    {
        servicesListData=servicesDataList();
        service_co_id.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        service_co_clothType.setCellValueFactory(new PropertyValueFactory<>("clothType"));
        service_co_services.setCellValueFactory(new PropertyValueFactory<>("service"));
        service_co_priceperKilo_ppk.setCellValueFactory(new PropertyValueFactory<>("pricePerKilo"));
        service_co_DI_dateinserted.setCellValueFactory(new PropertyValueFactory<>("date"));
        service_co_DU_dateUpdate.setCellValueFactory(new PropertyValueFactory<>("udate"));
        service_tableView.setItems(servicesListData);
    }

     */





// adding student to the grid
    private final ObservableList<getService>ListStudent=FXCollections.observableArrayList();
    //order from behaviors
    public ObservableList<getService>oderGetStudentData()
    {
        ObservableList<getService>listData=FXCollections.observableArrayList();
        String sql="SELECT * FROM student";
        connect= database.connectDb();

        try{
            prepare=connect.prepareStatement(sql);
            resultSet= prepare.executeQuery();

            getService getS;
            while (resultSet.next())
            {
                getS=new getService(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("roll"),
                        resultSet.getString("batch"),
                        resultSet.getString("email"),
                        resultSet.getString("image"));
                listData.add(getS);

            }


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listData;
    }


//setdata
    public void orderDisplayCard()
    {
        ListStudent.clear();
       // SeviceCardController serviceCC=new SeviceCardController();

        ListStudent.addAll(oderGetStudentData());
        int row=0;
        int column=0;

       try
       {
           order_gridPane.getColumnConstraints().clear();
           order_gridPane.getRowConstraints().clear();
           order_gridPane.getChildren().clear();
           for(int q=0; q<ListStudent.size();q++)
           {
               FXMLLoader load= new FXMLLoader();
               load.setLocation(getClass().getResource("ServiceCardAvailable.fxml"));
               StackPane pane=load.load();

               SeviceCardController serviceCC= load.getController();
               serviceCC.setStudent(ListStudent.get(q));

               if(column==4)
               {
                   column=0;
                   row++;
               }
               order_gridPane.add(pane,column++,row);

               order_gridPane.setMinHeight(GridPane.USE_COMPUTED_SIZE);
               order_gridPane.setPrefHeight(GridPane.USE_COMPUTED_SIZE);
               order_gridPane.setMaxHeight(GridPane.USE_PREF_SIZE);

               order_gridPane.setMinWidth(GridPane.USE_COMPUTED_SIZE);
               order_gridPane.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
               order_gridPane.setMaxWidth(GridPane.USE_PREF_SIZE);

            GridPane.setMargin(pane,new Insets(15));

           }

       }catch (Exception e)
       {
           e.printStackTrace();
       }

    }

    //faculty to grid
    private final ObservableList<getService>ListT=FXCollections.observableArrayList();
    public ObservableList<getService> FacultyInsert() {
        ObservableList<getService> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM faculty";

        try {
            connect = database.connectDb();
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                getService getS = new getService(
                        resultSet.getInt("id"),
                        resultSet.getString("facult_id"),
                        resultSet.getString("name"),
                        resultSet.getString("image"),
                                resultSet.getString("email")
                );
                listData.add(getS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception or log it more informatively
        } finally {
            // Close resources (prepared statement, result set, and connection)
            try {
                if (resultSet != null) resultSet.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the closing of resources exception or log it
            }
        }

        return listData;
    }

    public void FacultyCard() {
        ListT.clear();
        ListT.addAll(FacultyInsert());
        int row = 0;
        int column = 0;

        try {
            faculty_gridPane1.getColumnConstraints().clear();
            faculty_gridPane1.getRowConstraints().clear();
            faculty_gridPane1.getChildren().clear();

            for (int q = 0; q < ListT.size(); q++) {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("ServiceCardAvailable.fxml"));
                StackPane pane = load.load();

                SeviceCardController serviceCC = load.getController();
                serviceCC.setData(ListT.get(q));

                if (column == 4) {
                    column = 0;
                    row++;
                }
                faculty_gridPane1.add(pane, column++, row);

                // Set gridPane size properties here (if needed)
                GridPane.setMargin(pane, new Insets(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it more informatively
        }
    }

    //ALumni:::

    private final ObservableList<getService>ListAlumni=FXCollections.observableArrayList();
    public ObservableList<getService> AlumniIsert() {
        ObservableList<getService> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM alumni";

        try {
            connect = database.connectDb();
            prepare = connect.prepareStatement(sql);
            resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                getService getS = new getService(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("userId"),
                        resultSet.getString("batch"),
                        resultSet.getString("roll"),
                        resultSet.getString("country"),
                        resultSet.getString("job"),
                        resultSet.getString("post"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("image")
                );
                listData.add(getS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception or log it more informatively
        } finally {
            // Close resources (prepared statement, result set, and connection)
            try {
                if (resultSet != null) resultSet.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the closing of resources exception or log it
            }
        }

        return listData;
    }

    public void AlumniCard() {
        ListAlumni.clear();
        ListAlumni.addAll(AlumniIsert());
        int row = 0;
        int column = 0;

        try {
            customer_gridPane.getColumnConstraints().clear();
            customer_gridPane.getRowConstraints().clear();
            customer_gridPane.getChildren().clear();

            for (int q = 0; q < ListAlumni.size(); q++) {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("ServiceCardAvailable.fxml"));
                StackPane pane = load.load();

                SeviceCardController serviceCC = load.getController();
                serviceCC.setAlumni(ListAlumni.get(q));

                if (column == 4) {
                    column = 0;
                    row++;
                }
                customer_gridPane.add(pane, column++, row);

                // Set gridPane size properties here (if needed)
                GridPane.setMargin(pane, new Insets(15));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception or log it more informatively
        }
    }







//
//    //teacher insert:
//    private final ObservableList<getService>ListT=FXCollections.observableArrayList();
//    //order from behaviors
//    public ObservableList<getService>FacultyInsert()
//    {
//        ObservableList<getService>listData=FXCollections.observableArrayList();
//        String sql="SELECT * FROM faculty";
//        connect= database.connectDb();
//
//        try{
//            prepare=connect.prepareStatement(sql);
//            resultSet= prepare.executeQuery();
//
//            getService getS;
//            while (resultSet.next())
//            {
////                getS=new getService(
////                        resultSet.getInt("id"),
////                        resultSet.getString("clothType"),
////                        resultSet.getString("service"),
////                        resultSet.getDouble("price"),
////                        resultSet.getString("image"));
//                getS=new getService(
//                        resultSet.getInt("id"),
//                        resultSet.getString("facult_id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("image"));
//                listData.add(getS);
//
//            }
//
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return listData;
//    }
//
//
//
//    public void FacultyCard()
//    {
//        ListT.clear();
//        // SeviceCardController serviceCC=new SeviceCardController();
//
//        ListT.addAll(FacultyInsert());
//        int row=0;
//        int column=0;
//
//        try
//        {
//            faculty_gridPane1.getColumnConstraints().clear();
//            faculty_gridPane1.getRowConstraints().clear();
//            faculty_gridPane1.getChildren().clear();
//            for(int q=0; q<ListT.size();q++)
//            {
//                FXMLLoader load= new FXMLLoader();
//                load.setLocation(getClass().getResource("ServiceCardAvailable.fxml"));
//                StackPane pane=load.load();
//
//                SeviceCardController serviceCC= load.getController();
//                serviceCC.setData(ListT.get(q));
//
//                if(column==3)
//                {
//                    column=0;
//                    row++;
//                }
//                faculty_gridPane1.add(pane,column++,row);
//
//                faculty_gridPane1.setMinHeight(GridPane.USE_COMPUTED_SIZE);
//                faculty_gridPane1.setPrefHeight(GridPane.USE_COMPUTED_SIZE);
//                faculty_gridPane1.setMaxHeight(GridPane.USE_PREF_SIZE);
//
//                faculty_gridPane1.setMinWidth(GridPane.USE_COMPUTED_SIZE);
//                faculty_gridPane1.setPrefWidth(GridPane.USE_COMPUTED_SIZE);
//                faculty_gridPane1.setMaxWidth(GridPane.USE_PREF_SIZE);
//
//                GridPane.setMargin(pane,new Insets(15));
//
//            }
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//


    private ObservableList<getService>orderOrderList()
    {
        orderCustomerID();
        ObservableList<getService>lisData=FXCollections.observableArrayList();
        String sql="SELECT * FROM customer WHERE customer_id ='" +customerID+"'AND status= 'Active'";
        connect=database.connectDb();
        getService getS;
        try {
            prepare=connect.prepareStatement(sql);
            resultSet=prepare.executeQuery();

            while (resultSet.next())
            {
                getS= new getService(resultSet.getInt("id"),
                        resultSet.getString("clothType"),
                        resultSet.getString("service"),
                        resultSet.getInt("kilo"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getDate("date")
                        );
                lisData.add(getS);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lisData;
    }


    private ObservableList<getService>orderListData;
    public void orderDisplayOrder()
    {
        orderListData=orderOrderList();
        order_co_orderID.setCellValueFactory(new PropertyValueFactory<>("id"));

        order_co_Type.setCellValueFactory(new PropertyValueFactory<>("clothType"));
        order_co_Service.setCellValueFactory(new PropertyValueFactory<>("service"));
        order_co_Qty.setCellValueFactory(new PropertyValueFactory<>("kilo"));
        order_co_Price.setCellValueFactory(new PropertyValueFactory<>("pricePerKilo"));


        order_tableView.setItems(orderListData);
    }

    private int orederID;
    private String orderClothType;

    private String orderService;

    private int orderKilo;
    private double orderPrice;
    private String orderImage;

    private String orderDate;

    public void orderSelectOrder()
    {
        getService getS= order_tableView.getSelectionModel().getSelectedItem();
        int num =order_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1)return;;
        orederID =getS.getId();
        orderClothType= getS.getClothType();
        orderService= getS.getService();
        orderKilo= getS.getKilo();
        orderPrice=getS.getPricePerKilo();
        orderImage= getS.getImage();;
        orderDate= String.valueOf(getS.getDate());


    }





    //display total amount

    private float totalP;
    public void orderTotal() {
     //   orderCustomerID();
        String sql = "SELECT SUM(price) FROM customer WHERE customer_id = ? AND status = 'Active'";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, String.valueOf(customerID)); // Set the customerID as a parameter
            resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                totalP = resultSet.getFloat(1); // Use index 1 to get the result
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public void orderTotal()
//    {
//        orderCustomerID();
//        String sql="SELECT SUM(price) FROM customer WHERE customer_id= "+customerID+"AND status ='Active'";
//
//        connect= database.connectDb();
//
//        try
//        {
//            prepare=connect.prepareStatement(sql);
//            resultSet=prepare.executeQuery();
//            if(resultSet.next())
//            {
//             totalP=resultSet.getFloat("SUM(price)");
//            }
//        } catch (SQLException e) {
//           e.printStackTrace();
//        }
//
//    }

//    @FXML
//    private Label order_total;
    public void displyTotal()
    {
       // orderTotal();
        order_total.setText("TK "+totalP);

    }

    private float orderAmount;
    private float orderChange;

    public void orderAmount()
    {
        orderTotal();
        if(order_amount.getText().isEmpty())
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something Wrong");
            alert.showAndWait();

        }
        else if(totalP>Float.parseFloat(order_amount.getText()))
        {
            order_amount.setText("");
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter minimum TK "+totalP);
            alert.showAndWait();
        }else{
            orderAmount=Float.parseFloat(order_amount.getText());
            orderChange=orderAmount-totalP;
            order_change.setText("TK "+orderChange);
        }
    }






    //Creating CustomerId
    private int customerID;


    public void orderCustomerID()
    {
        String sql ="SELECT MAX(customer_id) FROM customer";
        connect=database.connectDb();

        int cID=0;


        try{

            prepare=connect.prepareStatement(sql);
            resultSet=prepare.executeQuery();
            if(resultSet.next())
            {
                customerID=resultSet.getInt("MAX(customer_id)");

            }

            String checkID="SELECT MAX(customer_id) FROM receipt";
            statement=connect.createStatement();
            resultSet=statement.executeQuery(checkID);

            if(resultSet.next())
            {
                cID=resultSet.getInt("MAX(customer_id)");
            }



            if(customerID==0)
            {
                customerID+=1;
            } else if (cID==customerID) {

                customerID=cID+1;

            }

            Data.cID=customerID;


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    //order_refreshBtn

    public void orderRefreshBtn()
    {

        orderDisplayCard();
        orderDisplayOrder();
        displyTotal();
    }
//
    public void orderPayBtn()
    {
        orderTotal();
        orderCustomerID();
        if(totalP==0)
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please order first");
            alert.showAndWait();
        } else if (order_dateP.getValue()==null) {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the date of pick-up you want ");
            alert.showAndWait();
        } else {
            alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure?");
            Optional<ButtonType>option=alert.showAndWait();
            if(option.get().equals(ButtonType.OK)) {

                String getData = "SELECT date from customer WHERE customer_id= " + customerID;

                connect = database.connectDb();

                //String getOrderDate="";
                Date getOrderDate;

                try {
                    statement = connect.createStatement();
                    resultSet = statement.executeQuery(getData);

                    if (resultSet.next()) {
                        getOrderDate = resultSet.getDate("date");

                        if (getOrderDate.compareTo(java.sql.Date.valueOf(order_dateP.getValue())) < 0) {
                            String insertDate = "INSERT INTO receipt(customer_id,total,date,pickup_date)"
                                    + "VALUES(?,?,?,?)";

                            prepare = connect.prepareStatement(insertDate);

                            prepare.setString(1, String.valueOf(customerID));
                            prepare.setString(2, String.valueOf(totalP));
                            prepare.setString(3, String.valueOf(getOrderDate));
                            prepare.setString(4, String.valueOf(order_dateP.getValue()));

                            prepare.executeUpdate();

                            alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Ordered Successfully!");
                            alert.showAndWait();

                            //clear all fields
                            order_dateP.setValue(null);
                            totalP=0;
                            order_total.setText("TK 0.0");
                            order_amount.setText("TK 0.0");
                            order_change.setText("TK 0.0");

                            //update table view
                            orderDisplayOrder();


                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid Calender");
                            alert.showAndWait();

                            order_dateP.setValue(null);
                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Message");
                alert.setHeaderText(null);
                alert.setContentText("Canclled order");
                alert.showAndWait();
            }
        }
    }




    //Order Remove Btn
    public void orderRemoveBtn() {
        orderCustomerID();
        if (orederID == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please select an item first");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete order ID " + orederID + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                String updateData = "UPDATE customer SET " +
                        "clothType = '" + orderClothType + "', " +
                        "service = '" + orderService + "', " +
                        "kilo = " + orderKilo + ", " +
                        "price = " + orderPrice + ", " +
                        "image = '" + orderImage + "', " +
                        "date = '" + orderDate + "', " +
                        "status = 'Deleted' " +
                        "WHERE id = " + orederID;

                connect = database.connectDb();
                try {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert= new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Order Id "+orederID+" successfully Removed!");
                    alert.showAndWait();


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


//    public void orderRemoveBtn()
//    {
//        orderCustomerID();
//        if(orederID==0)
//        {
//            alert= new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setContentText("Please select item first");
//            alert.showAndWait();
//        }
//        else{
//
//            alert=new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Conformation Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Are you sure you want to Delete order ID"+orederID+"?");
//            Optional<ButtonType>option =alert.showAndWait();
//
//            //String getOrderDate=String.valueOf(orderDate);
//
//            if(option.get().equals(ButtonType.OK))
//            {
//                String updateData="UPDATE customer SET customer_id= "+customerID+",clothType'"+orderClothType+"',service= '"+orderService+
//                        "'kilo= "+orderKilo+",price= "+orderPrice+"image='"+orderImage+"date= '"+orderDate+"'status='Deleted' WHERE id="+
//                        orederID;
//
//
//                connect=database.connectDb();
//                try
//                {
//                    prepare=connect.prepareStatement(updateData);
//                    prepare.executeUpdate();
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//
//        }
//    }



    //service from behaviors

    public void serviceInsertBtn()
    {
        if(service_id.getText().isEmpty()
                ||service_clothType.getSelectionModel().getSelectedItem()==null
                ||service_servicetype.getSelectionModel().getSelectedItem()==null
                ||service_PPK_pricePerKilo.getText().isEmpty()
                ||Data.path==null||Data.path.equals(""))
        {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        }
        else {
            //check if the service id is already taken?
            String checkServiceID= "SELECT serviceID FROM service WHERE serviceID ='"+
                    service_id.getText()+"'";



            String insertData="INSERT INTO service"+
                    "(serviceID,clothType,service,price,image,date_insert,date_update,status)"+
                    "VALUES(?,?,?,?,?,?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkServiceID);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Service ID "+service_id.getText()+" is already taken");
                    alert.showAndWait();

                } else {


                    prepare = connect.prepareStatement(insertData);


                    prepare.setString(1, service_id.getText());
                    prepare.setString(2, (String) service_clothType.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(5, Data.path);

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    // Get the current date and time as a java.sql.Timestamp

                    // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


                    prepare.setString(6, null);
                    prepare.setString(7, null);
                    prepare.setString(8, null);

                    prepare.executeUpdate();
                    alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Service ID "+service_id.getText()+" is Successfully Added ");
                    alert.showAndWait();

                    servicesShowData();
                    serviceClearBtn();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Inserted!");
                    alert.showAndWait();

                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    //facultY: new


    ///service admin verify btn
    @FXML
    private Button service_adminComformBtn;

    @FXML
    private PasswordField service_adminPass;

    @FXML
    private TextField service_adminUser;
    @FXML
    private AnchorPane verify_admin_form,add_all_form;

    public void ServiceVerfiyAdmin() {
        String username = service_adminUser.getText();
        String password = service_adminPass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Username and/or Password cannot be empty");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM adminpanel WHERE adminId = ? AND password = ?";
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = database.connectDb();
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Logged In!");
                    alert.showAndWait();
//                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
//                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
//                    Stage stage = new Stage();
//                    stage.setTitle("Hello!");
//                    stage.setScene(scene);
//                    //stage.initStyle(StageStyle.UNDECORATED);
//                    stage.show();
                  //  service_form.setVisible(true);
                    verify_admin_form.setVisible(false);
                    add_all_form.setVisible(true);
                  //  Faculty_register_form.setVisible(true);
                    all_registration_form.setVisible(false);
                    //service_adminComformBtn.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    private ComboBox<?> service_insertType;
    private String[] serviceInsertTypeList={"service","faculty"};
    public void InsertServices()
    {
        List<String>listS=new ArrayList<>();
        for(String data: serviceInsertTypeList)
        {
            listS.add(data);
        }

        ObservableList listData=FXCollections.observableArrayList(listS);
        service_insertType.setItems(listData);
    }

    public void insertBtnAll()
    {
        if((String)service_insertType.getSelectionModel().getSelectedItem()=="faculty")
        {
            faculty_insert();
        }else
        {
            serviceInsertBtn();
        }
    }

    //faculty Register Form:
    @FXML
    private Button faculty_register_confIrmBtn;

    @FXML
    private PasswordField faculty_register_confirmPass;

    @FXML
    private TextField faculty_register_email;

    @FXML
    private ImageView faculty_register_imageView;

    @FXML
    private Button faculty_register_insertImage;

    @FXML
    private TextField faculty_register_name;

    @FXML
    private PasswordField faculty_register_password;

    @FXML
    private TextField faculty_register_userId;

    public void FacultyImageImportBtn()
    {
        FileChooser open =new FileChooser();
        File file= open.showOpenDialog(main_form.getScene().getWindow());

        if(file!=null)
        {
            Data.path=file.getAbsolutePath();
            //System.out.println(Data.path);
            image= new Image(file.toURI().toString(),136,150,false,true);
            faculty_register_imageView.setImage(image);
        }

    }

    @FXML
    private Button after_admin_login_alumni;

    @FXML
    private Button after_admin_login_faculty;

    @FXML
    private AnchorPane after_admin_login_form;

    @FXML
    private Button after_admin_login_students;

    @FXML
    private AnchorPane Faculty_register_form;
    @FXML
    private AnchorPane all_registration_form;

    public  void faculty_member_AddBtn()
    {
        faculty_register_name.setText(null);
        faculty_register_userId.setText(null);
        faculty_register_email.setText(null);
        faculty_register_password.setText(null);
        faculty_register_confirmPass.setText(null);

        Faculty_register_form.setVisible(true);
        all_registration_form.setVisible(true);
        add_all_form.setVisible(false);
    }

    public void faculty_Register_func()
    {
//        faculty_register_name.setText(null);
//        faculty_register_userId.setText(null);
//        faculty_register_email.setText(null);
//        faculty_register_password.setText(null);
//        faculty_register_confirmPass.setText(null);
//        Data.path=null;

        if(faculty_register_name.getText().isEmpty()
            ||faculty_register_userId.getText().isEmpty()
                ||faculty_register_email.getText().isEmpty()
                ||faculty_register_password.getText().isEmpty()
                ||faculty_register_confirmPass.getText().isEmpty()
                ||Data.path==null||Data.path.equals(""))
        {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        }
        else {
//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";

//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";
            String checkServiceID = "SELECT facult_id FROM faculty WHERE facult_id = '" + faculty_register_userId.getText() + "'";




            String insertData="INSERT INTO faculty"+
                    "(facult_id,name,image,email,password)"+
                    "VALUES(?,?,?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkServiceID);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Faculty id  "+service_id.getText()+" is already taken");
                    alert.showAndWait();

                } else {
                    if(faculty_register_password.getText().equals(faculty_register_confirmPass.getText())&&faculty_register_password.getText().isBlank()==false)
                    {


                    prepare = connect.prepareStatement(insertData);
                    //prepare.setString(2,);


                    prepare.setString(1, faculty_register_userId.getText());

                    prepare.setString(2,faculty_register_name.getText());
                   // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    // prepare.setString(5, Data.path);
                    prepare.setString(3, Data.path);//for faculty image
                    prepare.setString(4,faculty_register_email.getText());
                    prepare.setString(5,faculty_register_password.getText());

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    // Get the current date and time as a java.sql.Timestamp

                    // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);

                    prepare.executeUpdate();
                    alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Faculty id :  "+service_id.getText()+" is Successfully Added ");
                    alert.showAndWait();

//                    servicesShowData();
//                    serviceClearBtn();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Inserted!");
                    alert.showAndWait();

                    }else
                    {
                        alert =new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password Does't matches!! ");
                        alert.showAndWait();
                    }

                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }


    // Addition of Student Registration
    @FXML
    private TextField student_register_batch;

    @FXML
    private Button student_register_confirmBtn;

    @FXML
    private PasswordField student_register_confirmPass;

    @FXML
    private TextField student_register_email;

    @FXML
    private AnchorPane student_register_form;

    @FXML
    private ImageView student_register_imageView;

    @FXML
    private Button student_register_insertImageBtn;

    @FXML
    private TextField student_register_name;

    @FXML
    private PasswordField student_register_password;

    @FXML
    private TextField student_register_roll;

    public void StudentImageImportBtn()
    {
        FileChooser open =new FileChooser();
        File file= open.showOpenDialog(main_form.getScene().getWindow());

        if(file!=null)
        {
            Data.path=file.getAbsolutePath();
            //System.out.println(Data.path);
            image= new Image(file.toURI().toString(),136,150,false,true);
            student_register_imageView.setImage(image);
        }

    }



    //private AnchorPane student_register_form;

    public  void Student_AddBtn()
    {
        student_register_name.setText(null);
        student_register_roll.setText(null);
        student_register_email.setText(null);
        student_register_password.setText(null);
        student_register_confirmPass.setText(null);
        student_register_batch.setText(null);

        student_register_form.setVisible(true);
        all_registration_form.setVisible(true);
        add_all_form.setVisible(false);
    }


    public void student_Register_func()
    {

        if(student_register_name.getText().isEmpty()
                ||student_register_roll.getText().isEmpty()
                ||student_register_email.getText().isEmpty()
                ||student_register_password.getText().isEmpty()
                ||student_register_confirmPass.getText().isEmpty()
                ||student_register_batch.getText().isEmpty()
                ||Data.path==null||Data.path.equals(""))
        {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        }
        else {
//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";

//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";
            String checkServiceID = "SELECT roll FROM student WHERE roll = '" + student_register_roll.getText() + "'";




            String insertData="INSERT INTO student"+
                    "(name,roll,batch,email,password,image)"+
                    "VALUES(?,?,?,?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkServiceID);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student ID "+service_id.getText()+" is already taken");
                    alert.showAndWait();

                } else {
                    if(student_register_password.getText().equals(student_register_confirmPass.getText())&&student_register_confirmPass.getText().isBlank()==false)
                    {


                        prepare = connect.prepareStatement(insertData);
                        //prepare.setString(2,);


                        prepare.setString(1, student_register_name.getText());

                        prepare.setString(2,student_register_roll.getText());
                        prepare.setString(3,student_register_batch.getText());

                        prepare.setString(4,student_register_email.getText());
                        prepare.setString(5,student_register_password.getText());

                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                        String path = Data.path;
                        path = path.replace("\\", "\\\\");

                        // prepare.setString(5, Data.path);
                        prepare.setString(6, Data.path);//for faculty image
//                        prepare.setString(4,faculty_register_email.getText());
//                        prepare.setString(5,faculty_register_password.getText());

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        // Get the current date and time as a java.sql.Timestamp

                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);

                        prepare.executeUpdate();
                        alert =new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Student ID "+service_id.getText()+" is Successfully Added ");
                        alert.showAndWait();

                        servicesShowData();
                        serviceClearBtn();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Inserted!");
                        alert.showAndWait();

                    }else
                    {
                        alert =new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password Does't matches!! ");
                        alert.showAndWait();
                    }

                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }

    //alumni registration form: done

    @FXML
    private AnchorPane Aliumni_register_form;
    @FXML
    private TextField alumni_register_batch;

    @FXML
    private PasswordField alumni_register_confPassword;

    @FXML
    private Button alumni_register_confirmBtn;

    @FXML
    private TextField alumni_register_country;

    @FXML
    private TextField alumni_register_email;

    @FXML
    private ImageView alumni_register_imageView;

    @FXML
    private Button alumni_register_insertImageBtn;

    @FXML
    private TextField alumni_register_jobName;

    @FXML
    private TextField alumni_register_jobPost;

    @FXML
    private TextField alumni_register_name;

    @FXML
    private PasswordField alumni_register_password;

    @FXML
    private TextField alumni_register_roll;

    @FXML
    private TextField alumni_register_userId;



    public void AlumniImageImportBtn()
    {
        FileChooser open =new FileChooser();
        File file= open.showOpenDialog(main_form.getScene().getWindow());

        if(file!=null)
        {
            Data.path=file.getAbsolutePath();
            //System.out.println(Data.path);
            image= new Image(file.toURI().toString(),136,150,false,true);
            alumni_register_imageView.setImage(image);
        }

    }



    public  void Alumni_AddBtn()
    {
        alumni_register_name.setText(null);
        alumni_register_userId.setText(null);
        alumni_register_batch.setText(null);
        alumni_register_roll.setText(null);
        alumni_register_country.setText(null);
        alumni_register_jobName.setText(null);
        alumni_register_jobPost.setText(null);
        alumni_register_email.setText(null);
        alumni_register_password.setText(null);
        alumni_register_confPassword.setText(null);

        Aliumni_register_form.setVisible(true);
        all_registration_form.setVisible(true);
        add_all_form.setVisible(false);
    }


    public void alumni_Register_func()
    {

        if(alumni_register_name.getText().isEmpty()
                ||alumni_register_userId.getText().isEmpty()
                ||alumni_register_batch.getText().isEmpty()
                ||alumni_register_roll.getText().isEmpty()
                ||alumni_register_country.getText().isEmpty()
                ||alumni_register_jobName.getText().isEmpty()
                ||alumni_register_jobPost.getText().isEmpty()
                ||alumni_register_email.getText().isEmpty()
                ||alumni_register_password.getText().isEmpty()
                ||alumni_register_confPassword.getText().isEmpty()
                ||Data.path==null||Data.path.equals(""))
        {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        }
        else
        {
//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";

//            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
//                    service_id.getText()+"'";
            String checkServiceID = "SELECT roll FROM alumni WHERE roll = '" + alumni_register_roll.getText() + "'";




            String insertData="INSERT INTO alumni"+
                    "(name,userId,batch,roll,country,job,post,email,password,image)"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkServiceID);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Alumni ID "+service_id.getText()+" is already taken");
                    alert.showAndWait();

                } else {
                    if(alumni_register_password.getText().equals(alumni_register_confPassword.getText())&&alumni_register_password.getText().isBlank()==false)
                    {


                        prepare = connect.prepareStatement(insertData);
                        //prepare.setString(2,);


                        prepare.setString(1, alumni_register_name.getText());

                        prepare.setString(2,alumni_register_userId.getText());
                        prepare.setString(3,alumni_register_batch.getText());

                        prepare.setString(4,alumni_register_roll.getText());
                        prepare.setString(5,alumni_register_country.getText());
                        prepare.setString(6,alumni_register_jobName.getText());
                        prepare.setString(7,alumni_register_jobPost.getText());
                        prepare.setString(8,alumni_register_email.getText());
                        prepare.setString(9,alumni_register_password.getText());

                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                        String path = Data.path;
                        path = path.replace("\\", "\\\\");

                        // prepare.setString(5, Data.path);
                        prepare.setString(10, Data.path);//for faculty image
//                        prepare.setString(4,faculty_register_email.getText());
//                        prepare.setString(5,faculty_register_password.getText());

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        // Get the current date and time as a java.sql.Timestamp

                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);

                        prepare.executeUpdate();
                        alert =new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Alumni ID "+alumni_register_name.getText()+" is Successfully Added ");
                        alert.showAndWait();

                        servicesShowData();
                        serviceClearBtn();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Inserted!");
                        alert.showAndWait();

                    }else
                    {
                        alert =new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password Does't matches!! ");
                        alert.showAndWait();
                    }

                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }










    //not included
    public void faculty_insert()
    {
        if(service_id.getText().isEmpty()
                ||service_clothType.getSelectionModel().getSelectedItem()==null
                ||service_servicetype.getSelectionModel().getSelectedItem()==null
                ||service_PPK_pricePerKilo.getText().isEmpty()
                ||Data.path==null||Data.path.equals(""))
        {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        }
        else {
            //check if the service id is already taken?
            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
                    service_id.getText()+"'";



            String insertData="INSERT INTO faculty"+
                    "(facult_id,name,image)"+
                    "VALUES(?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkServiceID);
                resultSet = prepare.executeQuery();
                if (resultSet.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Faculty ID "+service_id.getText()+" is already taken");
                    alert.showAndWait();

                } else {


                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1,service_id.getText());


                    prepare.setString(1, service_id.getText());
                    prepare.setString(2, (String) service_clothType.getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                   // prepare.setString(5, Data.path);
                    prepare.setString(3, Data.path);//for faculty image

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    // Get the current date and time as a java.sql.Timestamp

                    // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);

                    prepare.executeUpdate();
                    alert =new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Faculty ID "+service_id.getText()+" is Successfully Added ");
                    alert.showAndWait();

                    servicesShowData();
                    serviceClearBtn();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Inserted!");
                    alert.showAndWait();

                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    //faculy add end;

//    public void serviceUpdateBtn()
//    {
//        if(service_id.getText().isEmpty()
//                ||service_clothType.getSelectionModel().getSelectedItem()==null
//                ||service_servicetype.getSelectionModel().getSelectedItem()==null
//                ||service_PPK_pricePerKilo.getText().isEmpty()
//                ||Data.path==null||Data.path.equals(""))
//        {
//            alert =new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("All fields are necessary to be filled");
//            alert.showAndWait();
//        }
//        else {
//            String path= Data.path;
//            path=path.replace("\\","\\\\");
//
////            java.sql.Date date =new java.sql.Date();
////            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//           // LocalDate currentDate = LocalDate.now();
//
//// Convert it to java.sql.Date
//            //java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
//
//
//            String updateData="UPDATE service SET serviceID= '"+service_id.getText()+"',clothType=' "
//                    +service_clothType.getSelectionModel().getSelectedItem()+"',service='"
//                    +service_servicetype.getSelectionModel().getSelectedItem()+"',price=''"
//                    +service_PPK_pricePerKilo.getText()+"',image='"
//                    +path+"',date_insert= '"+null+"',status= '"+null+"'WHERE id= "
//                    +id+";";
//
//                    ;
//
//            connect=database.connectDb();
//
//            try{
//                alert=new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("Conformation Message");
//                alert.setHeaderText(null);
//                alert.setContentText("Are you sure that you want to Update Service ID: "+service_id.getText()+" ?");
//
//                Optional<ButtonType>option=alert.showAndWait();
//                if(option.get().equals(ButtonType.OK))
//                {
//                    prepare=connect.prepareStatement(updateData);
//                    prepare.executeUpdate();
//                    servicesShowData();
//                    serviceClearBtn();
//                }else{
//                    alert= new Alert(Alert.AlertType.WARNING);
//                    alert.setTitle("Warning Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Canclled Update");
//                    alert.showAndWait();
//                }
//
//               // prepare=connect.prepareStatement(updateData);
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//
//        }
//
//    }


    public void serviceUpdateBtn() {
        if (service_id.getText().isEmpty()
                || service_clothType.getSelectionModel().getSelectedItem() == null
                || service_servicetype.getSelectionModel().getSelectedItem() == null
                || service_PPK_pricePerKilo.getText().isEmpty()
                || Data.path == null || Data.path.equals("")) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        } else {
            String path = Data.path;
            if (path != null) {
                path = path.replace("\\", "\\\\");
            }

            String updateData = "UPDATE service SET serviceID = ?, clothType = ?, service = ?, price = ?, image = ?, date_update = ?, status = ? WHERE id = ?";

            connect = database.connectDb();

            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to Update Service ID: " + service_id.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, service_id.getText());
                    prepare.setString(2, (String) service_clothType.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());
                    if (service_PPK_pricePerKilo.getText().isEmpty()) {
                        prepare.setNull(4, Types.DOUBLE); // Assuming the price column is of type DOUBLE
                    } else {
                        prepare.setDouble(4, Double.parseDouble(service_PPK_pricePerKilo.getText()));
                    }

                    prepare.setString(5, path);
                    prepare.setDate(6, null); // Replace null with the appropriate date value
                    prepare.setString(7, null); // Replace null with the appropriate status value
                    prepare.setInt(8, id); // Assuming 'id' is defined somewhere

                    prepare.executeUpdate();
                    servicesShowData();
                    serviceClearBtn();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled Update");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





    public void serviceDeleteBtn()
    {
        if(id==0)
        {
            alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please selcet the item first");
            alert.showAndWait();
        }else {
            String deleteData ="DELETE FROM service WHERE id = "+id;
            connect=database.connectDb();
            try
            {
                alert=new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Service ID "+service_id.getText());
                Optional<ButtonType>option= alert.showAndWait();

                if(option.get().equals(ButtonType.OK))
                {

                    prepare=connect.prepareStatement(deleteData);
                    prepare.executeUpdate();
                    servicesShowData();
                    serviceClearBtn();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                }else {
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Canclled");
                    alert.showAndWait();

                }


            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    private  int id;
    //selected item
    public void serviceSelectData()
    {
        getService getS=service_tableView.getSelectionModel().getSelectedItem();
        int num=service_tableView.getSelectionModel().getSelectedIndex();

        if((num-1)<-1)return;
        id=getS.getId();
        service_id.setText(getS.getServiceID());
        service_PPK_pricePerKilo.setText(String.valueOf(getS.getPricePerKilo()));

        Data.path= getS.getImage();
        String path="File:"+Data.path;
        image=new Image(path,136,150,false,true);
        service_imageView.setImage(image);
    }

    public void serviceClearBtn()
    {
        service_id.setText("");
       service_clothType.getSelectionModel().clearSelection();
       service_servicetype.getSelectionModel().clearSelection();
       service_PPK_pricePerKilo.setText("");
       Data.path="";
       service_imageView.setImage(null);
    }





//    public void serviceInsertBtn() {
//        if (service_id.getText().isEmpty()
//                || service_clothType.getSelectionModel().getSelectedItem() == null
//                || service_servicetype.getSelectionModel().getSelectedItem() == null
//                || service_PPK_pricePerKilo.getText().isEmpty()
//                || Data.path == null || Data.path.equals("")) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("All fields are necessary to be filled");
//            alert.showAndWait();
//        } else {
//            String insertData = "INSERT INTO service" +
//                    "(serviceID, clothType, service, price, image, date_insert, date_update, status)" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//
//            connect = database.connectDb();
//            try {
//                prepare = connect.prepareStatement(insertData);
//
//                prepare.setString(1, service_id.getText());
//                prepare.setString(2, (String) service_clothType.getSelectionModel().getSelectedItem());
//                prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                prepare.setString(4, service_PPK_pricePerKilo.getText());
//
//                // Assuming Data.path is the path to an image file, use VARCHAR data type
//                prepare.setString(5, Data.path);
//
//                // Get the current date and time
//                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
//                prepare.setTimestamp(6, currentTimestamp);
//                prepare.setTimestamp(7, null); // Set date_update and status to null initially
//                prepare.setString(8, null);
//
//                prepare.executeUpdate();
//
//                servicesShowData();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }




    private String[] clothTypeList={"Cloth","Silk","Cotton","Linen","wool","worsted"};
    public void serviceClothTypeList()
    {
        List<String>listS=new ArrayList<>();
        for(String data: clothTypeList)
        {
            listS.add(data);
        }

        ObservableList listData=FXCollections.observableArrayList(listS);
        service_clothType.setItems(listData);

    }

    //import image
    private Image image;

    public void SeriviceImageImportBtn()
    {
        FileChooser open =new FileChooser();
        File file= open.showOpenDialog(main_form.getScene().getWindow());

        if(file!=null)
        {
            Data.path=file.getAbsolutePath();
            //System.out.println(Data.path);
            image= new Image(file.toURI().toString(),136,150,false,true);
            service_imageView.setImage(image);
        }

    }

    private String[] servicesList={"Washing","Washing and Drying","Washing, Drying and Ironing"};
    public void servicesServiceList()
    {
        List<String>listS=new ArrayList<>();
        for(String data: servicesList)
        {
            listS.add(data);
        }

        ObservableList listData=FXCollections.observableArrayList(listS);
        service_servicetype.setItems(listData);

    }



    public ObservableList<getService>servicesDataList()
    {
        String sql="SELECT * FROM service ";
        ObservableList<getService>listData= FXCollections.observableArrayList();
        try{
            connect=database.connectDb();

            prepare=connect.prepareStatement(sql);
            resultSet= prepare.executeQuery();
            getService getS;

            while (resultSet.next())
            {
                getS=new getService(resultSet.getInt("id"),
                        resultSet.getString("serviceID"),
                        resultSet.getString("clothType"),
                        resultSet.getString("service"),
                        resultSet.getDouble("price"),
                        resultSet.getString("image"),
                        resultSet.getDate("date_insert"),
                        resultSet.getDate("date_update"));
                listData.add(getS);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listData;

    }

    private  ObservableList<getService>servicesListData;
    //to show data in table view
    public void servicesShowData()
    {
        servicesListData=servicesDataList();
        service_co_id.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
        service_co_clothType.setCellValueFactory(new PropertyValueFactory<>("clothType"));
        service_co_services.setCellValueFactory(new PropertyValueFactory<>("service"));
        service_co_priceperKilo_ppk.setCellValueFactory(new PropertyValueFactory<>("pricePerKilo"));
        service_co_DI_dateinserted.setCellValueFactory(new PropertyValueFactory<>("date"));
        service_co_DU_dateUpdate.setCellValueFactory(new PropertyValueFactory<>("udate"));
        service_tableView.setItems(servicesListData);
    }


    public void logout()
    {
        try{
            alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType>option =alert.showAndWait();

            if(option.get().equals(ButtonType.OK))
            {
                //link to login form
                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage stage=new Stage();
                Scene scene=new Scene(root);
                stage.setScene(scene);
                stage.show();

                //hiding loginSuccessful.fxml
                logout_btn.getScene().getWindow().hide();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void switchFrom(ActionEvent event)
    {
        if(event.getSource()==dashboard_btn)
        {
            Dashboard_form.setVisible(true);
            order_form.setVisible(false);
            service_form.setVisible(false);
            customer_form.setVisible(false);
            verify_admin_form.setVisible(false);
            add_all_form.setVisible(false);
            faculty_form.setVisible(false);
            Faculty_register_form.setVisible(false);
            student_register_form.setVisible(false);
            Aliumni_register_form.setVisible(false);
            coutAll();
        } else if (event.getSource()==order_btn) {

            Dashboard_form.setVisible(false);
            order_form.setVisible(true);
            service_form.setVisible(false);
            customer_form.setVisible(false);
            faculty_form.setVisible(false);
            verify_admin_form.setVisible(false);

            Faculty_register_form.setVisible(false);

            orderDisplayCard();
            //orderDisplayOrder();
            //displyTotal();
            Faculty_register_form.setVisible(false);
            student_register_form.setVisible(false);
            add_all_form.setVisible(false);
            Aliumni_register_form.setVisible(false);
            coutAll();

        } else if (event.getSource()==service_btn) {


            service_adminUser.setText("");
            service_adminPass.setText("");


            Dashboard_form.setVisible(false);
            order_form.setVisible(false);
            verify_admin_form.setVisible(true);
            service_form.setVisible(false);
            customer_form.setVisible(false);
            faculty_form.setVisible(false);
            Faculty_register_form.setVisible(false);

            student_register_form.setVisible(false);

            Aliumni_register_form.setVisible(false);
            add_all_form.setVisible(false);

            coutAll();
          //  servicesShowData();
          //  servicesServiceList();
          //  serviceClothTypeList();
         //   InsertServices();


        } else if (event.getSource()==customer_btn) {

            //alumni
            Dashboard_form.setVisible(false);
            order_form.setVisible(false);
            service_form.setVisible(false);
            customer_form.setVisible(true);
            verify_admin_form.setVisible(false);

            student_register_form.setVisible(false);
            faculty_form.setVisible(false);
            AlumniCard();


            Faculty_register_form.setVisible(false);
            Aliumni_register_form.setVisible(false);
            add_all_form.setVisible(false);
            coutAll();

        }
        else if (event.getSource()==faculty_btn) {
            Dashboard_form.setVisible(false);
            order_form.setVisible(false);
            service_form.setVisible(false);
            verify_admin_form.setVisible(false);
            customer_form.setVisible(false);
            faculty_form.setVisible(true);
            FacultyCard();
           // InsertServices();
            Faculty_register_form.setVisible(false);
            Aliumni_register_form.setVisible(false);
            student_register_form.setVisible(false);
            add_all_form.setVisible(false);
            countAlumni();
            coutAll();


        }

    }
//    public void ServiceVerfiyAdmin()
//    {
//
//    }
    @FXML
    private Button faculty_btn;
    @FXML
    private AnchorPane faculty_form;
    public void displayUsername()
    {

        String user= Data.usercollect;
        user=user.substring(0,1).toUpperCase()+user.substring(1);
        username.setText(user);

    }

// public void create_an_admin()
// {
//
// }



    public void back_student()
    {
        add_all_form.setVisible(true);
        student_register_form.setVisible(false);
    }
    public void back_alumni()
    {
        add_all_form.setVisible(true);
        Aliumni_register_form.setVisible(false);
    }
    public void back_faculty()
    {
        add_all_form.setVisible(true);
        Faculty_register_form.setVisible(false);
    }


    public void mainPage()
    {
        coutAll();
        fetchData();

    }


    public void initialize(URL location, ResourceBundle resource)
    {
        mainPage();

        coutAll();
        displayUsername();
        servicesShowData();
        servicesServiceList();
        serviceInsertBtn();
        FacultyCard();
        orderDisplayCard();
        orderDisplayOrder();
        displyTotal();
        AlumniCard();
        initialize();
        countAlumni();
        initialize_1_2();
        coutAll();
        fetchData();
    }
}
