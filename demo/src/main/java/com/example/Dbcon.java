package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbcon{
    public static  Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/gestion_evenements2";
        String utilisateur = "root";
        String pass = "";
        return DriverManager.getConnection(url, utilisateur, pass);
        
    }
}


