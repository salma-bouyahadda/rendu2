package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SalleDAO implements GenericDAO<Salle> {

    @Override
    public void add(Salle salle) {
        String sql = "INSERT INTO salles (nom_salle, capacite) VALUES (?, ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, salle.getNomSalle());
            statement.setInt(2, salle.getCapacite());
            statement.executeUpdate();
            System.out.println("Salle ajoutée : " + salle.getNomSalle());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la salle : " + e.getMessage());
        }
    }

    @Override
    public Salle get(String nom) {
        String sql = "SELECT * FROM salles WHERE nom_salle = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Salle(
                        resultSet.getInt("id_salle"),
                        resultSet.getString("nom_salle"),
                        resultSet.getInt("capacite")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la salle : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Salle> getAll() {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM salles";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                salles.add(new Salle(
                        resultSet.getInt("id_salle"),
                        resultSet.getString("nom_salle"),
                        resultSet.getInt("capacite")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des salles : " + e.getMessage());
        }
        return salles;
    }

    @Override
    public void update(Salle salle) {
        String sql = "UPDATE salles SET nom_salle = ?, capacite = ? WHERE id_salle = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, salle.getNomSalle());
            statement.setInt(2, salle.getCapacite());
            statement.setInt(3, salle.getIdSalle());
            statement.executeUpdate();
            System.out.println("Salle mise à jour : " + salle.getNomSalle());

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la salle : " + e.getMessage());
        }
    }

    @Override
    public void delete(String nom) {
        String sql = "DELETE FROM salles WHERE nom_salle = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            statement.executeUpdate();
            System.out.println("Salle supprimée : " + nom);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la salle : " + e.getMessage());
        }
    }
}
