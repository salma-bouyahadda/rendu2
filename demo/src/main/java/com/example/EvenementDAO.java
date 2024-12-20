package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EvenementDAO {

    public void add(Evenement evenement) {
        String sql = "INSERT INTO evenement (nom_event, date_event, description, id_user) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = Dbcon.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, evenement.getNomEvent());
            statement.setString(2, evenement.getDateEvent());
            statement.setString(3, evenement.getDescription());
            statement.setInt(4, evenement.getIdUser());
            statement.executeUpdate();
            System.out.println("Evenement ajoute avec succes.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'evenement : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Evenement get(String nomEvent) {
        String sql = "SELECT * FROM evenement WHERE nom_event = ?";
        Evenement evenement = null;

        try (Connection connection = Dbcon.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nomEvent);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int idEvent = resultSet.getInt("id_event");
                String dateEvent = resultSet.getString("date_event");
                String description = resultSet.getString("description");
                int idUser = resultSet.getInt("id_user");

                evenement = new Evenement(idEvent, nomEvent, dateEvent, description, idUser);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recuperation de l'evenement : " + e.getMessage());
            e.printStackTrace();
        }

        return evenement;
    }


    public List<Evenement> getAll() {
        List<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT * FROM evenement";

        try (Connection connection = Dbcon.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idEvent = resultSet.getInt("id_event");
                String nomEvent = resultSet.getString("nom_event");
                String dateEvent = resultSet.getString("date_event");
                String description = resultSet.getString("description");
                int idUser = resultSet.getInt("id_user");

                Evenement evenement = new Evenement(idEvent, nomEvent, dateEvent, description, idUser);
                evenements.add(evenement);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recuperation des evenements : " + e.getMessage());
            e.printStackTrace();
        }
        return evenements;
    }

    public void update(Evenement evenement) {
        String sql = "UPDATE evenement SET nom_event = ?, date_event = ?, description = ?, id_user = ? WHERE id_event = ?";

        try (Connection connection = Dbcon.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, evenement.getNomEvent());
            statement.setString(2, evenement.getDateEvent());
            statement.setString(3, evenement.getDescription());
            statement.setInt(4, evenement.getIdUser());
            statement.setInt(5, evenement.getIdEvent());

            statement.executeUpdate();
            System.out.println("Evenement mis a jour avec succes.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise a jour de l'evenement : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(String nomEvent) {
        String sql = "DELETE FROM evenement WHERE nom_event = ?";

        try (Connection connection = Dbcon.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nomEvent);
            statement.executeUpdate();
            System.out.println("Evenement supprime avec succes.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'evenement : " + e.getMessage());
            e.printStackTrace();
        }
    }
}