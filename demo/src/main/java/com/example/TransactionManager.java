package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManager {

    public void executeInTransaction(Runnable operation) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_evenements", "root", "");
             conn) {
            conn.setAutoCommit(false);
            operation.run();
            conn.commit();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la transaction : " + e.getMessage());
        }
    }
}
