package com.example.cafeautomation;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName="cafe";
        String databaseUser="root";
        String databasePassword="onur123";
        String url="jdbc:mysql://localhost:3306/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            //throw new RuntimeException("as");
            e.printStackTrace();
        }

        return databaseLink;
    }

}
