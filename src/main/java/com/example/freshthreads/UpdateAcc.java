package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.*;
import java.util.Optional;

public class UpdateAcc {

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
    private AnchorPane student_register_form,update_form;

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
    @FXML
    private AnchorPane main_form;
    Image image;


    public void faclty_update_btn()
    {
        student_register_form.setVisible(false);
        Faculty_register_form.setVisible(true);
        Aliumni_register_form.setVisible(false);
        update_form.setVisible(false);
    }

    public void alumni_update_btn()
    { update_form.setVisible(false);
        student_register_form.setVisible(false);
        Faculty_register_form.setVisible(false);
        Aliumni_register_form.setVisible(true);
    }

    public void student_update_btn()
    {
        update_form.setVisible(false);
        student_register_form.setVisible(true);
        Faculty_register_form.setVisible(false);
        Aliumni_register_form.setVisible(false);
    }

    public void back_menu()
    {
        update_form.setVisible(true);
        student_register_form.setVisible(false);
        Faculty_register_form.setVisible(false);
        Aliumni_register_form.setVisible(false);
    }


    //delete:

    public void Delete_facluty() {
        if (faculty_register_userId.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter User Id");
            alert.showAndWait();
        } else {
//            String checkRoll = "SELECT roll FROM loginfo WHERE roll='" + delete_roll.getText() + "'";
//            //   String insertData="INSERT INTO labtext(user,roll,password)VALUES(?,?,?)";
//            //  String UpdateData="UPDATE labtext SET user=?,password=? WHERE roll=?";
//            String deleteData = "DELETE FROM loginfo WHERE roll=?";
            String checkFaculty="SELECT facult_id FROM faculty WHERE facult_id='" + faculty_register_userId.getText() + "'";
            String deleteData = "DELETE FROM faculty WHERE facult_id=?";
            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(checkFaculty);
                resultSet = prepare.executeQuery();
                if (resultSet.next() == false) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This Faculty is not Registered");
                    alert.showAndWait();

                } else {
                    connect = database.connectDb();
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, faculty_register_userId.getText());
                    int rowUpdate = prepare.executeUpdate();
                    if (rowUpdate > 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infromation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Deleted Successfully");
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("No Records were Updated");
                        alert.showAndWait();
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void ShowInformation_faculty()
    {
        String facultyId= faculty_register_userId.getText();
        if(facultyId.isEmpty())
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter The Roll");
            alert.showAndWait();
            return;
        }
        else {
            connect=database.connectDb();
            try
            {
                String ShoeInfo="SELECT facult_id,name,email FROM faculty WHERE facult_id=?";

                prepare=connect.prepareStatement(ShoeInfo);
                prepare.setString(1,facultyId);
                resultSet= prepare.executeQuery();
                if(resultSet.next())
                {
//                    String Name= resultS.getString("user");
//                    String Password=result.getString("password");

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Name:"+resultSet.getString("name")+"\nFaculty Id:"+resultSet.getString("facult_id")+
                            "\nEmail:"+resultSet.getString("email"));
                    alert.showAndWait();

                }
                else {
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No Records Found");
                    alert.showAndWait();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



    //


    public void Delete_Student() {
        if (student_register_roll.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter User Id");
            alert.showAndWait();
        } else {
//            String checkRoll = "SELECT roll FROM loginfo WHERE roll='" + delete_roll.getText() + "'";
//            //   String insertData="INSERT INTO labtext(user,roll,password)VALUES(?,?,?)";
//            //  String UpdateData="UPDATE labtext SET user=?,password=? WHERE roll=?";
//            String deleteData = "DELETE FROM loginfo WHERE roll=?";
            String checkFaculty="SELECT roll FROM student WHERE roll='" + student_register_roll.getText() + "'";
            String deleteData = "DELETE FROM student WHERE roll=?";
            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(checkFaculty);
                resultSet = prepare.executeQuery();
                if (resultSet.next() == false) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This Student is not Registered");
                    alert.showAndWait();

                } else {
                    connect = database.connectDb();
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, student_register_roll.getText());
                    int rowUpdate = prepare.executeUpdate();
                    if (rowUpdate > 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infromation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Deleted Successfully");
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("No Records were Updated");
                        alert.showAndWait();
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void ShowInformation_student()
    {
        String roll= student_register_roll.getText();
        if(roll.isEmpty())
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter The Roll");
            alert.showAndWait();
            return;
        }
        else {
            connect=database.connectDb();
            try
            {
                String ShoeInfo="SELECT name,roll,batch,email FROM student WHERE roll=?";

                prepare=connect.prepareStatement(ShoeInfo);
                prepare.setString(1,roll);
                resultSet= prepare.executeQuery();
                if(resultSet.next())
                {
//                    String Name= resultS.getString("user");
//                    String Password=result.getString("password");

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Name:"+resultSet.getString("name")+"\nBatch:"+resultSet.getString("batch")+"\nRoll:"+resultSet.getString("roll")+
                            "\nEmail:"+resultSet.getString("email"));
                    alert.showAndWait();

                }
                else {
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No Records Found");
                    alert.showAndWait();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



    // alumni;;

    public void Delete_Alumni() {
        if (alumni_register_roll.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter User Id");
            alert.showAndWait();
        } else {
//            String checkRoll = "SELECT roll FROM loginfo WHERE roll='" + delete_roll.getText() + "'";
//            //   String insertData="INSERT INTO labtext(user,roll,password)VALUES(?,?,?)";
//            //  String UpdateData="UPDATE labtext SET user=?,password=? WHERE roll=?";
//            String deleteData = "DELETE FROM loginfo WHERE roll=?";
            String checkFaculty="SELECT roll FROM alumni WHERE roll='" + alumni_register_roll.getText() + "'";
            String deleteData = "DELETE FROM alumni WHERE roll=?";
            connect = database.connectDb();
            try {
                prepare = connect.prepareStatement(checkFaculty);
                resultSet = prepare.executeQuery();
                if (resultSet.next() == false) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("This Alumni is not Registered");
                    alert.showAndWait();

                } else {
                    connect = database.connectDb();
                    prepare = connect.prepareStatement(deleteData);
                    prepare.setString(1, alumni_register_roll.getText());
                    int rowUpdate = prepare.executeUpdate();
                    if (rowUpdate > 0) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infromation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Deleted Successfully");
                        alert.showAndWait();
                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("No Records were Updated");
                        alert.showAndWait();
                    }


                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void ShowInformation_Alumni()
    {
        String roll= alumni_register_roll.getText();
        if(roll.isEmpty())
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter The Roll");
            alert.showAndWait();
            return;
        }
        else {
            connect=database.connectDb();
            try
            {
                String ShoeInfo="SELECT name,userId,batch,roll,country,job,post,email FROM alumni WHERE roll=?";

                prepare=connect.prepareStatement(ShoeInfo);
                prepare.setString(1,roll);
                resultSet= prepare.executeQuery();
                if(resultSet.next())
                {
//                    String Name= resultS.getString("user");
//                    String Password=result.getString("password");

                    alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Name :"+resultSet.getString("name")+
                                    "\nUser ID :"+resultSet.getString("userId")+
                            "\nBatch :"+resultSet.getString("batch")+
                            "\nRoll:"+resultSet.getString("roll")+
                            "\nCountry :"+resultSet.getString("country")+
                            "\nJob:"+resultSet.getString("job")+
                            "\nJob's Post:"+resultSet.getString("post")+
                            "\nEmail:"+resultSet.getString("email"));
                    alert.showAndWait();

                }
                else {
                    alert=new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("No Records Found");
                    alert.showAndWait();
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }










    @FXML
    private Button return_btn;
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
                return_btn.getScene().getWindow().hide();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }






    public void StudentImageImportBtn()
    {
        FileChooser open =new FileChooser();
        File file= open.showOpenDialog(student_register_form.getScene().getWindow());

        if(file!=null)
        {
            Data.path=file.getAbsolutePath();
            //System.out.println(Data.path);
            image= new Image(file.toURI().toString(),136,150,false,true);
            student_register_imageView.setImage(image);
        }

    }

//    public void FacultyImageImportBtn()
//    {
//        FileChooser open =new FileChooser();
//        File file= open.showOpenDialog(main_form.getScene().getWindow());
//
//        if(file!=null)
//        {
//            Data.path=file.getAbsolutePath();
//            //System.out.println(Data.path);
//            image= new Image(file.toURI().toString(),136,150,false,true);
//          //  faculty_register_imageView.setImage(image);
//        }
//
//    }


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
//        all_registration_form.setVisible(true);
//        add_all_form.setVisible(false);
    }

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet resultSet;
    private Statement statement;

    Alert alert;
//    public void student_Register_func()
//    {
//        if(student_register_name.getText().isEmpty()
//                ||student_register_roll.getText().isEmpty()
//                ||student_register_email.getText().isEmpty()
//                ||student_register_password.getText().isEmpty()
//                ||student_register_confirmPass.getText().isEmpty()
//                ||student_register_batch.getText().isEmpty()
//                ||Data.path==null||Data.path.equals(""))
//        {
//            alert =new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("All fields are necessary to be filled");
//            alert.showAndWait();
//        }
//        else {
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
//
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
////            String checkServiceID = "SELECT roll FROM student WHERE roll = '" + student_register_roll.getText() + "'";
////
////
////
////
////            String insertData="INSERT INTO student"+
////                    "(name,roll,batch,email,password,image)"+
////                    "VALUES(?,?,?,?,?,?)";
//
//            String checkRoll="SELECT roll FROM student WHERE roll='"+student_register_roll.getText()+"'";
//            //   String insertData="INSERT INTO logininfo(user,roll,password)VALUES(?,?,?)";
//           // String UpdateData="UPDATE student SET name=?,roll=?,batch=?,email=?,password=? WHERE roll=?";
//            String UpdateData = "UPDATE student SET name=?, roll=?, batch=?, email=?, password=? WHERE roll=?";
//            connect =database.connectDb();
//            try {
//
//                prepare = connect.prepareStatement(checkRoll);
//                //prepare.setString(1,student_register_roll.getText());
//                resultSet = prepare.executeQuery();
//                prepare.clearParameters();
//                if (resultSet.next()==false) {
//                    alert =new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("The student is not registerd");
//                    alert.showAndWait();
//
//                } else {
//                    if(student_register_password.getText().equals(student_register_confirmPass.getText())&&student_register_confirmPass.getText().isBlank()==false)
//                    {
//
//
//                        prepare = connect.prepareStatement(UpdateData);
//
//                        prepare.setString(1, student_register_name.getText());
//
//                        prepare.setString(2,student_register_roll.getText());
//                        prepare.setString(3,student_register_batch.getText());
//
//                        prepare.setString(4,student_register_email.getText());
//                        prepare.setString(5,student_register_password.getText());
//
//                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
////                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
////                    prepare.setString(4, service_PPK_pricePerKilo.getText());
//
//                        String path = Data.path;
//                        path = path.replace("\\", "\\\\");
//
//                        // prepare.setString(5, Data.path);
//                        prepare.setString(6, Data.path);//for faculty image
////                        prepare.setString(4,faculty_register_email.getText());
////                        prepare.setString(5,faculty_register_password.getText());
//
////                Date date= new Date();
////                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                        // Get the current date and time as a java.sql.Timestamp
//
//                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
//
//
////                    prepare.setString(6, null);
////                    prepare.setString(7, null);
////                    prepare.setString(8, null);
//
//                        int rowUpdate=prepare.executeUpdate();
//                        if(rowUpdate>0) {
//                            alert = new Alert(Alert.AlertType.INFORMATION);
//                            alert.setTitle("Infromation Message");
//                            alert.setHeaderText(null);
//                            alert.setContentText("Update is Successfull");
//                            alert.showAndWait();
//                        }else {
//                            alert=new Alert(Alert.AlertType.WARNING);
//                            alert.setTitle("Error Message");
//                            alert.setHeaderText(null);
//                            alert.setContentText("No Records were Updated");
//                            alert.showAndWait();
//                        }
//                    }else
//                    {
//                        alert =new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Password Does't matches!! ");
//                        alert.showAndWait();
//                    }
//
//                }
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    public void student_Register_func() {
        update_form.setVisible(false);
        if (student_register_name.getText().isEmpty()
                || student_register_roll.getText().isEmpty()
                || student_register_email.getText().isEmpty()
                || student_register_password.getText().isEmpty()
                || student_register_confirmPass.getText().isEmpty()
                || student_register_batch.getText().isEmpty()
                || Data.path == null || Data.path.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("All fields are necessary to be filled");
            alert.showAndWait();
        } else {
            String checkRoll = "SELECT roll FROM student WHERE roll=?";
            String updateData = "UPDATE student SET name=?, batch=?, email=?, password=? WHERE roll=?";
            Connection connect = database.connectDb();

            try {
                PreparedStatement checkRollStmt = connect.prepareStatement(checkRoll);
                checkRollStmt.setString(1, student_register_roll.getText());
                ResultSet resultSet = checkRollStmt.executeQuery();

                if (!resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("The student is not registered");
                    alert.showAndWait();
                } else {
                    if (student_register_password.getText().equals(student_register_confirmPass.getText()) && !student_register_confirmPass.getText().isBlank()) {
                        PreparedStatement updateStmt = connect.prepareStatement(updateData);

                        updateStmt.setString(1, student_register_name.getText());
                        updateStmt.setString(2, student_register_batch.getText());
                        updateStmt.setString(3, student_register_email.getText());
                        updateStmt.setString(4, student_register_password.getText());
                        updateStmt.setString(5, student_register_roll.getText());

                        int rowUpdate = updateStmt.executeUpdate();
                        if (rowUpdate > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Update is successful");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("No records were updated");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password doesn't match!!");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //----->>>
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
        File file= open.showOpenDialog(Faculty_register_form.getScene().getWindow());

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
       // add_all_form.setVisible(false);
    }

    public void faculty_Register_func()
    {
        update_form.setVisible(false);
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
            String checkFaculty = "SELECT facult_Id FROM faculty WHERE facult_Id=?";
            String updateData = "UPDATE faculty SET name=?, image=?,email=?, password=? WHERE facult_Id=?";
            Connection connect = database.connectDb();

            try {
                PreparedStatement checkRollStmt = connect.prepareStatement(checkFaculty);
                checkRollStmt.setString(1, faculty_register_userId.getText());
                ResultSet resultSet = checkRollStmt.executeQuery();

                if (!resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("The Faculty Member is not registered");
                    alert.showAndWait();
                } else {
                    if(faculty_register_password.getText().equals(faculty_register_confirmPass.getText())&&faculty_register_password.getText().isBlank()==false)
                    {


                        prepare = connect.prepareStatement(updateData);
                        //prepare.setString(2,);



                        prepare.setString(1,faculty_register_name.getText());
                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                        String path = Data.path;
                        path = path.replace("\\", "\\\\");

                        // prepare.setString(5, Data.path);
                        prepare.setString(2, Data.path);//for faculty image
                        prepare.setString(3,faculty_register_email.getText());
                        prepare.setString(4,faculty_register_password.getText());
                        prepare.setString(5, faculty_register_userId.getText());


//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        // Get the current date and time as a java.sql.Timestamp

                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);
//
//                        prepare.executeUpdate();
                        int rowUpdate = prepare.executeUpdate();
                        if (rowUpdate > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Update is successful");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("No records were updated");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password doesn't match!!");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
//        else {
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
//
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
//            String checkServiceID = "SELECT facult_id FROM faculty WHERE facult_id = '" + faculty_register_userId.getText() + "'";
//
//
//
//
//            String insertData="INSERT INTO faculty"+
//                    "(facult_id,name,image,email,password)"+
//                    "VALUES(?,?,?,?,?)";
//
//            connect =database.connectDb();
//            try {
//                prepare = connect.prepareStatement(checkServiceID);
//                resultSet = prepare.executeQuery();
//                if (resultSet.next()) {
//                    alert =new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Service ID "+faculty_register_name.getText()+" is already taken");
//                    alert.showAndWait();
//
//                }
//                else {
//                    if(faculty_register_password.getText().equals(faculty_register_confirmPass.getText())&&faculty_register_password.getText().isBlank()==false)
//                    {
//
//
//                        prepare = connect.prepareStatement(insertData);
//                        //prepare.setString(2,);
//
//
//                        prepare.setString(1, faculty_register_userId.getText());
//
//                        prepare.setString(2,faculty_register_name.getText());
//                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
////                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
////                    prepare.setString(4, service_PPK_pricePerKilo.getText());
//
//                        String path = Data.path;
//                        path = path.replace("\\", "\\\\");
//
//                        // prepare.setString(5, Data.path);
//                        prepare.setString(3, Data.path);//for faculty image
//                        prepare.setString(4,faculty_register_email.getText());
//                        prepare.setString(5,faculty_register_password.getText());
//
////                Date date= new Date();
////                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                        // Get the current date and time as a java.sql.Timestamp
//
//                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
//
//
////                    prepare.setString(6, null);
////                    prepare.setString(7, null);
////                    prepare.setString(8, null);
//
//                        prepare.executeUpdate();
//                        alert =new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Service ID "+""+" is Successfully Added ");
//                        alert.showAndWait();
//
////                        servicesShowData();
////                        serviceClearBtn();
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Successfully Inserted!");
//                        alert.showAndWait();
//
//                    }else
//                    {
//                        alert =new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Password Does't matches!! ");
//                        alert.showAndWait();
//                    }
//
//                }
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }

    }


//    // Addition of Student Registration
//    @FXML
//    private TextField student_register_batch;
//
//    @FXML
//    private Button student_register_confirmBtn;
//
//    @FXML
//    private PasswordField student_register_confirmPass;
//
//    @FXML
//    private TextField student_register_email;
//
//    @FXML
//    private AnchorPane student_register_form;
//
//    @FXML
//    private ImageView student_register_imageView;
//
//    @FXML
//    private Button student_register_insertImageBtn;
//
//    @FXML
//    private TextField student_register_name;
//
//    @FXML
//    private PasswordField student_register_password;
//
//    @FXML
//    private TextField student_register_roll;
//
//    public void StudentImageImportBtn()
//    {
//        FileChooser open =new FileChooser();
//        File file= open.showOpenDialog(main_form.getScene().getWindow());
//
//        if(file!=null)
//        {
//            Data.path=file.getAbsolutePath();
//            //System.out.println(Data.path);
//            image= new Image(file.toURI().toString(),136,150,false,true);
//            student_register_imageView.setImage(image);
//        }
//
//    }
//
//
//
//    //private AnchorPane student_register_form;
//
//    public  void Student_AddBtn()
//    {
//        student_register_name.setText(null);
//        student_register_roll.setText(null);
//        student_register_email.setText(null);
//        student_register_password.setText(null);
//        student_register_confirmPass.setText(null);
//        student_register_batch.setText(null);
//
//        student_register_form.setVisible(true);
//        all_registration_form.setVisible(true);
//        add_all_form.setVisible(false);
//    }
//
//
//    public void student_Register_func()
//    {
//
//        if(student_register_name.getText().isEmpty()
//                ||student_register_roll.getText().isEmpty()
//                ||student_register_email.getText().isEmpty()
//                ||student_register_password.getText().isEmpty()
//                ||student_register_confirmPass.getText().isEmpty()
//                ||student_register_batch.getText().isEmpty()
//                ||Data.path==null||Data.path.equals(""))
//        {
//            alert =new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("All fields are necessary to be filled");
//            alert.showAndWait();
//        }
//        else {
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
//
////            String checkServiceID= "SELECT facult_id FROM faculty WHERE facult_id ='"+
////                    service_id.getText()+"'";
//            String checkServiceID = "SELECT roll FROM student WHERE roll = '" + student_register_roll.getText() + "'";
//
//
//
//
//            String insertData="INSERT INTO student"+
//                    "(name,roll,batch,email,password,image)"+
//                    "VALUES(?,?,?,?,?,?)";
//
//            connect =database.connectDb();
//            try {
//                prepare = connect.prepareStatement(checkServiceID);
//                resultSet = prepare.executeQuery();
//                if (resultSet.next()) {
//                    alert =new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Service ID "+service_id.getText()+" is already taken");
//                    alert.showAndWait();
//
//                } else {
//                    if(student_register_password.getText().equals(student_register_confirmPass.getText())&&student_register_confirmPass.getText().isBlank()==false)
//                    {
//
//
//                        prepare = connect.prepareStatement(insertData);
//                        //prepare.setString(2,);
//
//
//                        prepare.setString(1, student_register_name.getText());
//
//                        prepare.setString(2,student_register_roll.getText());
//                        prepare.setString(3,student_register_batch.getText());
//
//                        prepare.setString(4,student_register_email.getText());
//                        prepare.setString(5,student_register_password.getText());
//
//                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
////                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
////                    prepare.setString(4, service_PPK_pricePerKilo.getText());
//
//                        String path = Data.path;
//                        path = path.replace("\\", "\\\\");
//
//                        // prepare.setString(5, Data.path);
//                        prepare.setString(6, Data.path);//for faculty image
////                        prepare.setString(4,faculty_register_email.getText());
////                        prepare.setString(5,faculty_register_password.getText());
//
////                Date date= new Date();
////                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                        // Get the current date and time as a java.sql.Timestamp
//
//                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());
//
//
////                    prepare.setString(6, null);
////                    prepare.setString(7, null);
////                    prepare.setString(8, null);
//
//                        prepare.executeUpdate();
//                        alert =new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Service ID "+service_id.getText()+" is Successfully Added ");
//                        alert.showAndWait();
//
//                        servicesShowData();
//                        serviceClearBtn();
//                        alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setTitle("Information message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Successfully Inserted!");
//                        alert.showAndWait();
//
//                    }else
//                    {
//                        alert =new Alert(Alert.AlertType.ERROR);
//                        alert.setTitle("Error Message");
//                        alert.setHeaderText(null);
//                        alert.setContentText("Password Does't matches!! ");
//                        alert.showAndWait();
//                    }
//
//                }
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

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
        File file= open.showOpenDialog(Aliumni_register_form.getScene().getWindow());

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
      //  add_all_form.setVisible(false);
    }


    public void alumni_Register_func()
    {

        update_form.setVisible(false);
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
//            String checkServiceID = "SELECT roll FROM alumni WHERE roll = '" + alumni_register_roll.getText() + "'";
//
//
//
//
//            String insertData="INSERT INTO alumni"+
//                    "(name,userId,batch,roll,country,job,post,email,password,image)"+
//                    "VALUES(?,?,?,?,?,?,?,?,?,?)";


            String checkAlumni = "SELECT userId FROM alumni WHERE userId=?";
            String updateData = "UPDATE alumni SET name=?,batch=?,roll=?,country=?,job=?,post=?,email=?,password=?,image=?WHERE userId=?";

            connect =database.connectDb();

            try {
                PreparedStatement checkRollStmt = connect.prepareStatement(checkAlumni);
                checkRollStmt.setString(1, alumni_register_userId.getText());
                ResultSet resultSet = checkRollStmt.executeQuery();

                if (!resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("The Alumni is not registered");
                    alert.showAndWait();
                } else {

                    if(alumni_register_password.getText().equals(alumni_register_confPassword.getText())&&alumni_register_password.getText().isBlank()==false)
                    {


                        prepare = connect.prepareStatement(updateData);
                        //prepare.setString(2,);


                        prepare.setString(1, alumni_register_name.getText());

                        prepare.setString(2,alumni_register_batch.getText());

                        prepare.setString(3,alumni_register_roll.getText());
                        prepare.setString(4,alumni_register_country.getText());
                        prepare.setString(5,alumni_register_jobName.getText());
                        prepare.setString(6,alumni_register_jobPost.getText());
                        prepare.setString(7,alumni_register_email.getText());
                        prepare.setString(8,alumni_register_password.getText());

                        // prepare.setString(2, (String) .getSelectionModel().getSelectedItem());
//                    prepare.setString(3, (String) service_servicetype.getSelectionModel().getSelectedItem());
//                    prepare.setString(4, service_PPK_pricePerKilo.getText());

                        String path = Data.path;
                        path = path.replace("\\", "\\\\");

                        // prepare.setString(5, Data.path);
                        prepare.setString(9, Data.path);//for faculty image
//                        prepare.setString(4,faculty_register_email.getText());
//                        prepare.setString(5,faculty_register_password.getText());


                        prepare.setString(10,alumni_register_userId.getText());

//                Date date= new Date();
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        // Get the current date and time as a java.sql.Timestamp

                        // java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(System.currentTimeMillis());


//                    prepare.setString(6, null);
//                    prepare.setString(7, null);
//                    prepare.setString(8, null);

                        int rowUpdate = prepare.executeUpdate();
                        if (rowUpdate > 0) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Update is successful");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("No records were updated");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Password doesn't match!!");
                        alert.showAndWait();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connect.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        }

}








