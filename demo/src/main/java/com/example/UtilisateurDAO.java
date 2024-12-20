package JAVA.rendu_2.demo.src.main.java.com.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UtilisateurDAO implements GenericDAO<Utilisateur> {

    @Override
    public void add(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs (nom, email, type) VALUES (?, ?, ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getType());
            statement.executeUpdate();
            System.out.println("Utilisateur ajouté : " + utilisateur.getNom());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public Utilisateur get(String nom) {
        String sql = "SELECT * FROM utilisateurs WHERE nom = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utilisateur(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("type")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Utilisateur> getAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                utilisateurs.add(new Utilisateur(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("type")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs : " + e.getMessage());
        }
        return utilisateurs;
    }

    @Override
    public void update(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateurs SET nom = ?, email = ?, type = ? WHERE id_user = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, utilisateur.getNom());
            statement.setString(2, utilisateur.getPrenom());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getType());
            statement.setInt(5, utilisateur.getIdUser());
            statement.executeUpdate();
            System.out.println("Utilisateur mis à jour : " + utilisateur.getNom());

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public void delete(String nom) {
        String sql = "DELETE FROM utilisateurs WHERE nom = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            statement.executeUpdate();
            System.out.println("Utilisateur supprimé : " + nom);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }

    public Object get(int idUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}
