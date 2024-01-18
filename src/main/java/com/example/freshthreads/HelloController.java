package com.example.freshthreads;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {

    @FXML
    private Label InvalidUser;

    @FXML
    private Label message;

    @FXML
    private Label not_match_pass;

    @FXML
    private PasswordField si_pass;

    @FXML
    private TextField si_user;

    @FXML
    private Button side_already;

    @FXML
    private Button side_createbutton;

    @FXML
    private AnchorPane sidefrom,verify_admin_form;

    @FXML
    private Button singin;

    @FXML
    private PasswordField su_conf_pass;

    @FXML
    private TextField su_contact;

    @FXML
    private AnchorPane su_form;

    @FXML
    private TextField su_location;

    @FXML
    private PasswordField su_pass;

    @FXML
    private TextField su_user;

    @FXML
    private Button subutton;

    @FXML
    private Label successfulmessage;



    @FXML
    private TextField su_email;




    //admin account cretion

//    public void create_an_admin() throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_acc.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
//        Stage stage = new Stage();
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        //stage.initStyle(StageStyle.UNDECORATED);
//        stage.show();
//        verify_admin_form.getScene().getWindow().hide();
//    }


    public void switchFrom(ActionEvent event)
    {
        side_already.setVisible(true);
        side_createbutton.setVisible(false);
        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == side_createbutton)
        {
            slider.setNode(sidefrom);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(0.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_createbutton.setVisible(false);
                side_already.setVisible(true);
            });
            slider.play();
        }
        else if (event.getSource() == side_already)
        {
            slider.setNode(sidefrom);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(0.5));
            slider.setOnFinished((ActionEvent e) -> {
                side_already.setVisible(false);
                side_createbutton.setVisible(true);
            });
            slider.play();
        }
    }


//    public void switchFrom(ActionEvent event)
//    {
//        //side_already.setVisible(true);
//        TranslateTransition slider= new TranslateTransition();
//        if(event.getSource()==side_createbutton)
//        {
//            slider.setNode(sidefrom);
//            slider.setToX(300);
//            slider.setDuration(Duration.seconds(0.5));
//            slider.setOnFinished((ActionEvent e)->{
//
//                side_createbutton.setVisible(false); side_already.setVisible(true);
//            });
//            slider.play();
//
//        } else if (event.getSource()==side_already) {
//            slider.setNode(sidefrom);
//            slider.setToX(0);
//            slider.setDuration(Duration.seconds(0.5));
//            slider.setOnFinished((ActionEvent e)->{
//                side_already.setVisible(false);
//                side_createbutton.setVisible(true);
//            });
//            slider.play();
//
//
//        }
//    }

    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private  ResultSet result;


//    public void EnterAccountAfterLogin()
//    {
//        if(si_user.getText().isEmpty()||si_user.getText().isEmpty())
//        {
//            alert =new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Incorrect UserName/Password");
//            alert.showAndWait();
//        }
//        else{
//            String sql= "SELECT * FROM information WHERE username= '"+si_user.getText()+"' AND password='"+si_pass.getText()+"'";
//
//            connect= database.connectDb();
//
//            try
//            {
//                prepare =connect.prepareStatement(sql);
//                result=prepare.executeQuery();
//
//                if(result.next())
//                {
//                    alert=new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Login!");
//                    alert.showAndWait();
//                }
//                else {
//                    alert=new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Incorrect Username/Password");
//                    alert.showAndWait();
//                }
//
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
public void EnterAccountAfterLogin() {
    String username = si_user.getText();
    String password = si_pass.getText();

    if (username.isEmpty() || password.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Username and/or Password cannot be empty");
        alert.showAndWait();
    } else {
        String sql = "SELECT * FROM information WHERE username = ? AND password = ?";
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
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                Stage stage = new Stage();
                stage.setTitle("Hello!");
                stage.setScene(scene);
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

                singin.getScene().getWindow().hide();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Username/Password");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




    public void ClickLogin(ActionEvent e)
    {
        if(si_user.getText().isBlank()==false&&si_pass.getText().isBlank()==false)
        {

            validateLogin();
        }
        else
        {
            message.setText("Please Enter Information");

        }

    }

    public boolean cheeckUserValidity()
    {



            DatabaseConnection connectNow= new DatabaseConnection();
            Connection connectBD=connectNow.getConnection();

            // SELECT count(1) FROM freshthreads.information WHERE username='tirtho' AND password='117'

            //String varifylogin="SELECT count(1) FROM tirtho.logininfo WHERE userId='"+textfieldUser.getText()+"' AND passward='"+textfieldPass.getText()+"'";
            String varifylogin="SELECT count(1) FROM freshthreads.information WHERE username='"+su_user.getText()+"'";


            // String varifylogin="SELECT count(1) FROM freshthreads.information WHERE userId='"+textfieldUser.getText()+"' AND passward='"+textfieldPass.getText()+"'";
            try
            {
                Statement statement =connectBD.createStatement();
                ResultSet queryResuly= statement.executeQuery(varifylogin);

                while (queryResuly.next())
                {
                    if(queryResuly.getInt(1)==1)
                    {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                // e.getCause();

            }
            return  true;
    }

    @FXML

    public void validateLogin()
    {

        Data.usercollect=si_user.getText();
        DatabaseConnection connectNow= new DatabaseConnection();
        Connection connectBD=connectNow.getConnection();

        // SELECT count(1) FROM freshthreads.information WHERE username='tirtho' AND password='117'

        //String varifylogin="SELECT count(1) FROM tirtho.logininfo WHERE userId='"+textfieldUser.getText()+"' AND passward='"+textfieldPass.getText()+"'";
        String varifylogin="SELECT count(1) FROM freshthreads.information WHERE username='"+si_user.getText()+"' AND password='"+si_pass.getText()+"'";


       // String varifylogin="SELECT count(1) FROM freshthreads.information WHERE userId='"+textfieldUser.getText()+"' AND passward='"+textfieldPass.getText()+"'";
        try
        {
            Statement statement =connectBD.createStatement();
            ResultSet queryResuly= statement.executeQuery(varifylogin);

            while (queryResuly.next())
            {
                if(queryResuly.getInt(1)==1)
                {
                    message.setText("Login Successfully");


                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                    Stage stage = new Stage();
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();

                    singin.getScene().getWindow().hide();

                }
                else {
                    message.setText("Invalid Login ");
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
           // e.getCause();

        }
    }



    //---> registration:

    public void registraaationButton(ActionEvent e) {
        if (cheeckUserValidity()) {
            successfulmessage.setText("Invalid User Name");
        } else {
            registration();
        }
    }

    public void registration()
    {
        if(su_pass.getText().equals(su_conf_pass.getText())&&su_pass.getText().isBlank()==false)
        {
            database();
           // confirmation.setText("Password Match");
            successfulmessage.setText("Registration Successfully done");


        }
        else {
            successfulmessage.setText("Registration unsuccessful");
            not_match_pass.setText("Password doesn't matched");
        }
    }

    public void database() {
        DatabaseConnection connectnow = new DatabaseConnection();
        Connection connectDb = connectnow.getConnection();

        String Username = su_user.getText();
        String contact = su_contact.getText();
        String Password = su_pass.getText();
        String loca = su_location.getText();


       // INSERT INTO freshthreads.information(username,contactno,location,password)VALUES('mondal','0202','kapilmuni','765')

//        String insertfield = "INSERT INTO tirtho.logininfo(userId, passward, email, uniCode) VALUES('";
//        String insertValues = User + "','" + Password + "','" + Email + "','" + Uni + "')";


        String insertfield = "INSERT INTO freshthreads.information(username,contactno,location,password)VALUES('";
        String insertValues = Username + "','" + contact + "','" + loca + "','" + Password + "')";

        String insertToDb = insertfield + insertValues;

        Statement statement = null;
        try {
            statement = connectDb.createStatement();
            statement.executeUpdate(insertToDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ForgottenPassword() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }


    //register User

    public void Register_func()
    {

        if(su_user.getText().isEmpty()
                ||su_location.getText().isEmpty()
                ||su_conf_pass.getText().isEmpty()
                ||su_email.getText().isEmpty()
                ||su_pass.getText().isEmpty()
                )
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
            String checkUser = "SELECT name FROM userinfo WHERE name = '" + su_user.getText() + "'";




            String insertData="INSERT INTO userinfo"+
                    "(name,email,location,password)"+
                    "VALUES(?,?,?,?)";

            connect =database.connectDb();
            try {
                prepare = connect.prepareStatement(checkUser);
                result = prepare.executeQuery();
                if (result.next()) {
                    alert =new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_user.getText()+" is already taken");
                    alert.showAndWait();

                } else {
                    if(su_pass.getText().equals(su_conf_pass.getText())&&su_pass.getText().isBlank()==false)
                    {


                        prepare = connect.prepareStatement(insertData);
                        //prepare.setString(2,);


                        prepare.setString(1, su_user.getText());

                        prepare.setString(2,su_email.getText());
                        prepare.setString(3,su_location.getText());
                        prepare.setString(4,su_pass.getText());

//                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
////                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
////                    prepare.setString(4, service_PPK_pricePerKilo.getText());
//
//                        String path = Data.path;
//                        path = path.replace("\\", "\\\\");
//
//                        // prepare.setString(5, Data.path);
//                        prepare.setString(10, Data.path);//for faculty image
////                        prepare.setString(4,faculty_register_email.getText());
////                        prepare.setString(5,faculty_register_password.getText());

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
                        alert.setContentText(su_user.getText()+" is Successfully Added ");
                        alert.showAndWait();

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

    @FXML
    private ComboBox<?> si_accountType;
    private String[] servicesList={"Faculty","Alumni","Student","User"};
    public void servicesServiceList()
    {
        List<String> listS=new ArrayList<>();
        for(String data: servicesList)
        {
            listS.add(data);
        }

        ObservableList listData= FXCollections.observableArrayList(listS);
        si_accountType.setItems(listData);


    }
    public void initialize()
    {
        servicesServiceList();
    }

    public void SignIn()
    {
        if((String)si_accountType.getSelectionModel().getSelectedItem()=="Faculty")
        {
            LoginFaculty();
        }else if((String)si_accountType.getSelectionModel().getSelectedItem()=="Student")
        {
            LoginStudent();
        }
        else if((String)si_accountType.getSelectionModel().getSelectedItem()=="Alumni"){
            LoginAlumni();
        }
        else {
            LoginUser();
        }
    }
    public void LoginFaculty() {
        String username = si_user.getText();
        String password = si_pass.getText();

        if (username.isEmpty() || password.isEmpty()||si_accountType.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Any field cannot be empty");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM faculty WHERE name = ? AND password = ?";
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                    Stage stage = new Stage();
//                    stage.setTitle("Hello!");
                    stage.setScene(scene);
//                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    //  service_form.setVisible(true);
//                    verify_admin_form.setVisible(false);
//                    add_all_form.setVisible(true);
//                    //  Faculty_register_form.setVisible(true);
//                    all_registration_form.setVisible(false);
                    singin.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void LoginStudent() {
        String username = si_user.getText();
        String password = si_pass.getText();

        if (username.isEmpty() || password.isEmpty()||si_accountType.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Any field cannot be empty");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM student WHERE name = ? AND password = ?";
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                    Stage stage = new Stage();
//                    stage.setTitle("Hello!");
                    stage.setScene(scene);
//                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    //  service_form.setVisible(true);
//                    verify_admin_form.setVisible(false);
//                    add_all_form.setVisible(true);
//                    //  Faculty_register_form.setVisible(true);
//                    all_registration_form.setVisible(false);
                    singin.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void LoginAlumni() {
        String username = si_user.getText();
        String password = si_pass.getText();

        if (username.isEmpty() || password.isEmpty()||si_accountType.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Any field cannot be empty");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM alumni WHERE name = ? AND password = ?";
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                    Stage stage = new Stage();
//                    stage.setTitle("Hello!");
                    stage.setScene(scene);
//                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    //  service_form.setVisible(true);
//                    verify_admin_form.setVisible(false);
//                    add_all_form.setVisible(true);
//                    //  Faculty_register_form.setVisible(true);
//                    all_registration_form.setVisible(false);
                    singin.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void LoginUser() {
        String username = si_user.getText();
        String password = si_pass.getText();

        if (username.isEmpty() || password.isEmpty()||si_accountType.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Any field cannot be empty");
            alert.showAndWait();
        } else {
            String sql = "SELECT * FROM userinfo WHERE name = ? AND password = ?";
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
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginSuccessful.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
                    Stage stage = new Stage();
//                    stage.setTitle("Hello!");
                    stage.setScene(scene);
//                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    //  service_form.setVisible(true);
//                    verify_admin_form.setVisible(false);
//                    add_all_form.setVisible(true);
//                    //  Faculty_register_form.setVisible(true);
//                    all_registration_form.setVisible(false);
                    singin.getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





}
