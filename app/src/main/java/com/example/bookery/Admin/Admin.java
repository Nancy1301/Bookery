package com.example.bookery.Admin;

public class Admin {
    private String password;
    private String Admin_ID;
    private String Admin_Name;

    public Admin(){

    }

    public Admin(Admin admin){
        this.Admin_ID = admin.getAdmin_ID();
        this.Admin_Name = admin.getAdmin_Name();
    }
    public Admin(String password,String Admin_ID)
    {
        this.Admin_ID=Admin_ID;
        this.password=password;
    }




    public Admin(String password, String Admin_ID,String Admin_Name){
        this.password=password;
        this.Admin_ID=Admin_ID;
        this.Admin_Name=Admin_Name;
    }

    public boolean check(){
        //get the credentials from the DB
        return true;
    }

    public String getPassword(){
        return password;
    }

    public String getAdmin_ID(){
        return Admin_ID;
    }

    public String getAdmin_Name(){
        return Admin_Name;
    }

    public void setAdmin_Name(String Admin_Name){
        this.Admin_Name = Admin_Name;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setAdmin_ID(String Admin_ID){
        this.Admin_ID = Admin_ID;
    }
}

