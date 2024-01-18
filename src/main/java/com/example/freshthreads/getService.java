package com.example.freshthreads;

import java.sql.Date;

public class getService {
    private Integer id;
    private String serviceID;
    private String clothType;
    private String service;
    private Double pricePerKilo;
    private String image;
    private Date date;
    private Date udate;
    private Integer kilo;

////    public getService(Integer id, String serviceID,String clothType,String service,Double pricePerKilo,String image,Data date)
////    {
////
////    }
//
//    public getService(int id, String serviceID, String clothType, String service, double price, String image, Date date,Data udate) {
//
//
//    }

    public getService(Integer id, String serviceID, String clothType, String service, Double
            price, String image, Date date, Date dateUpdate) {
        this.id=id;
        this.serviceID=serviceID;
        this.clothType=clothType;
        this.service=service;
        this.pricePerKilo=price;
        this.image=image;
        this.date=date;
        this.udate=dateUpdate;
    }

    public getService(Integer id, String clothType, String service, Double pricePerKilo, String image)
    {
        this.id=id;
        this.clothType=clothType;
        this.pricePerKilo=pricePerKilo;
        this.service=service;
        this.image=image;

    }

    public getService(Integer id, String clothType, String service,Integer kilo, Double pricePerKilo, String image,Date date)
    {
        this.kilo=kilo;
        this.id=id;
        this.clothType=clothType;
        this.pricePerKilo=pricePerKilo;
        this.service=service;
        this.image=image;
        this.date=date;

    }
    private  String facultId,name;

    public getService(int id, String facultId, String name, String image) {
        this.id=id;
        this.facultId=facultId;
        this.name=name;
        this.image=image;
    }

    private  String roll;
    private String batch;
    private String email;
    //for the addition of Student to the grid
    public getService(int id, String name, String roll, String batch, String email, String image) {
        this.id=id;
        this.name=name;
        this.roll=roll;
        this.batch=batch;
        this.email=email;
        this.image=image;

    }

    private String userId,country,job,post,password;
    public getService(int id, String name, String userId, String batch, String roll, String country, String job, String post, String email, String password, String image) {
        this.id=id;
        this.name=name;
        this.userId=userId;
        this.batch=batch;
        this.roll=roll;
        this.country=country;
        this.job=job;
        this.post=post;
        this.email=email;
        this.password=password;
        this.image=image;

    }

    String sl,coursecode,tittle,credit,preReq;
    public getService(String sl, String coursecode, String tittle, String credit, String preReq) {
        this.sl=sl;
        this.coursecode=coursecode;
        this.tittle=tittle;
        this.credit=credit;
        this.preReq=preReq;
    }

    public getService(int id, String facultId, String name, String image, String email) {
        this.id=id;
        this.facultId=facultId;
        this.name=name;
        this.image=image;
        this.email=email;
    }

   // String jobpost;

    public String getCoursecode() {
        return coursecode;
    }

    public String getSl() {
        return sl;
    }

    public String getCredit() {
        return credit;
    }

    public String getPreReq() {
        return preReq;
    }

    public String getTittle() {
        return tittle;
    }

    public String getUserId() {
        return userId;
    }

    public String getCountry() {
        return country;
    }

    public String getJob() {
        return job;
    }

    public String getPost() {
        return post;
    }

    public String getPassword() {
        return password;
    }

    public String getRoll()
    {
        return roll;
    }

    public String getBatch() {
        return batch;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId()
    {
        return id;
    }
    public String getServiceID()
    {
        return serviceID;
    }
    public String getClothType()
    {
        return clothType;
    }
    public String getService()
    {
        return service;
    }
    public Double getPricePerKilo()
    {
        return pricePerKilo;
    }
    public String getImage()
    {
        return image;
    }
    public Date getDate()
    {
        return date;
    }

    public Date getUdate() {
        return udate;
    }

    public Integer getKilo()
    {
        return kilo;
    }
    public  String getFacultId()
    {
        return facultId;
    }
    public String getName()
    {
        return name;
    }

}
