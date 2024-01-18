package com.example.freshthreads;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class SeviceCardController implements Initializable {



    @FXML
    private Label card_batch;

    @FXML
    private Label card_country;

    @FXML
    private Label card_email;

    @FXML
    private StackPane card_form;

    @FXML
    private ImageView card_imageView;

    @FXML
    private Label card_job;

    @FXML
    private Label card_name;

    @FXML
    private Label card_roll;






    @FXML
    private Button card_addBtn;

//    @FXML
//    private StackPane card_form;
//
//    @FXML
//    private ImageView card_imageView;

    @FXML
    private TextField card_kilo;

    @FXML
    private Label card_price;

    @FXML
    private Label card_serviceName,country_name;

    @FXML
    private Label card_clothType;

    private getService getS;

    private Image image;
    private String serviceName;
    private double price;

    private int kilo;
    private int id;
    private String path,roll,batch;

    //database
    private Connection connect;
    private PreparedStatement prepare;
    private  ResultSet result;
    private Statement statement;
    private String clothType;



    public void setAlumni(getService getS)
    {

        card_name.setText(getS.getName().toUpperCase());
        card_batch.setText(getS.getJob().toUpperCase());
        card_roll.setText(getS.getPost().toUpperCase());
        card_email.setText(getS.getEmail());
        card_country.setText(getS.getCountry().toUpperCase());
        card_job.setText("Batch:"+getS.getBatch().toUpperCase()+" Roll:"+getS.getRoll().toUpperCase());
//        clothType=getS.getClothType();
        serviceName =getS.getService();
//        price= getS.getPricePerKilo();
        path="File:"+getS.getImage();

        //card_serviceName.setText(serviceName);
      //  card_price.setText("TK "+price);
       // card_clothType.setText(getS.getClothType());

        image=new Image(path,200,150,false,true);
        card_imageView.setImage(image);

    }

    String name,email;
    public void setStudent(getService getS)
    {
        roll=getS.getRoll();
        batch=getS.getBatch();
        name =getS.getName();
       // price= getS.getPricePerKilo();
        path="File:"+getS.getImage();

        card_name.setText(name.toUpperCase());
        card_roll.setText(roll);
        card_batch.setText(batch.toUpperCase());

        image=new Image(path,200,150,false,true);
        card_imageView.setImage(image);
        email=getS.getEmail();
        card_email.setText(email);
        country_name.setText("Computer Science and Engineering");
        card_country.setText(null);
        card_job.setText("KUET");


    }

    public void setData(getService getS)
    {
        card_name.setText(getS.getName().toUpperCase());
        email=getS.getEmail();
        card_email.setText(email);
        card_batch.setText("CSE");
        card_roll.setText("KUET");

//        id=getS.getId();
//        clothType=getS.getClothType();
//        serviceName =getS.getService();
        path="File:"+getS.getImage();
//
//        card_serviceName.setText(serviceName);
//
//        card_clothType.setText(getS.getClothType());

        image=new Image(path,200,150,false,true);
        card_imageView.setImage(image);

        country_name.setText("Khulna -9203, Bangladesh.");
        card_country.setText(null);
        card_job.setText(null);

    }

    public Alert alert;
    private double priceService;

    public void addBtn()
    {
        LoginSuccessful mainF=new LoginSuccessful();
        mainF.orderCustomerID();

//        Date date = new Date();
//        java.sql.Date sqlDate= new java.sql.Date(date.getTime());

        if(card_kilo.getText().isEmpty())
        {
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please indicate the Kg(s) of your cloths");
            alert.showAndWait();

        }
        else {
            String insertDate="INSERT INTO customer (customer_id,clothType,service, kilo,price,image,date,status)" +
                    "VALUES(?,?,?,?,?,?,?,?)";

            connect=database.connectDb();
            try{

                String getPrice= "SELECT price FROM service WHERE clothType='" +
                        clothType+"' AND service ='"+serviceName+"'";
                statement=connect.createStatement();
                result=statement.executeQuery(getPrice);

                double priceS=0;
                if(result.next())
                {
                    priceS=result.getFloat("price");

                }

                prepare=connect.prepareStatement(insertDate);
                prepare.setString(1,String.valueOf(Data.cID));
                prepare.setString(2,clothType);
                prepare.setString(3,serviceName);
                prepare.setString(4,card_kilo.getText());

                priceService=(priceS*Integer.parseInt(card_kilo.getText()));

                prepare.setString(5,String.valueOf(priceService));

//                String path = Data.path;
//                path = path.replace("\\", "\\\\");
//
                prepare.setString(6, path);


                Date date = new Date();
                java.sql.Date sqlDate= new java.sql.Date(date.getTime());


                prepare.setString(7,String.valueOf(sqlDate));
                prepare.setString(8,"Active");


                prepare.executeUpdate();
               // mainF.serviceInsertBtn();


            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
