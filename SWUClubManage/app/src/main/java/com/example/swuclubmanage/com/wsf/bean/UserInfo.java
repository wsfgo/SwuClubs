package com.example.swuclubmanage.com.wsf.bean;

/**
 * Created by 王书发 on 2016/6/17.
 */
public class UserInfo {
    private String name;
    private String myclass;
    private String gender;
    private String hoppy;
    private String  mail;
    private String zuoyouming;
    private String age;
    private String image;

    public String getMyclass() {
        return myclass;
    }

    public void setMyclass(String myclass) {
        this.myclass = myclass;
    }
    public String getZuoyouming() {
        return zuoyouming;
    }

    public void setZuoyouming(String zuoyouming) {
        this.zuoyouming = zuoyouming;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHoppy() {
        return hoppy;
    }

    public void setHoppy(String hoppy) {
        this.hoppy = hoppy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", myclass='" + myclass + '\'' +
                ", gender='" + gender + '\'' +
                ", hoppy='" + hoppy + '\'' +
                ", mail='" + mail + '\'' +
                ", zuoyouming='" + zuoyouming + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
