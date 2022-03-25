package com.pisicilesalbatice.ams.user;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uId;
    private String userName;
    private String password;
    private int category;
    private String fullName;
    private String about;

    public User() {
    }

    public User(int uId, String userName, String password, int category, String fullName, String about) {
        this.uId = uId;
        this.userName = userName;
        this.password = password;
        this.category = category;
        this.fullName = fullName;
        this.about = about;
    }

    public User(String userName, String password, int category, String fullName, String about) {
        this.userName = userName;
        this.password = password;
        this.category = category;
        this.fullName = fullName;
        this.about = about;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", category=" + category +
                ", fullName='" + fullName + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
