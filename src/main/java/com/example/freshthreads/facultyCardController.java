package com.example.freshthreads;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class facultyCardController {

    @FXML
    private Label faculty_email;

    @FXML
    private ImageView faculty_imageView;

    @FXML
    private Label faculty_name;

    @FXML
    private Label faculty_phone;
    public void setData(getService getS)
    {
        faculty_name.setText(getS.getName());
        faculty_email.setText(getS.getEmail());


//        id=getS.getId();
////        clothType=getS.getClothType();
//        serviceName =getS.getService();
////        price= getS.getPricePerKilo();
        String path="File:"+getS.getImage();
//
//        card_serviceName.setText(serviceName);
//        //  card_price.setText("TK "+price);
//        // card_clothType.setText(getS.getClothType());
//
        Image image=new Image(path,200,150,false,true);

        faculty_imageView.setImage(image);

    }


}
