package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerrainDAO implements GenericDAO<Terrain> {

    @Override
    public void add(Terrain terrain) {
        String sql = "INSERT INTO terrains (nom_terrain, type_terrain) VALUES (?, ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, terrain.getNomTerrain());
            statement.setString(2, terrain.getTypeTerrain());
            statement.executeUpdate();
            System.out.println("Terrain ajouté : " + terrain.getNomTerrain());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du terrain : " + e.getMessage());
        }
    }

    @Override
    public Terrain get(String nom) {
        String sql = "SELECT * FROM terrains WHERE nom_terrain = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Terrain(
                        resultSet.getInt("id_terrain"),
                        resultSet.getString("nom_terrain"),
                        resultSet.getString("type_terrain")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération du terrain : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Terrain> getAll() {
        List<Terrain> terrains = new ArrayList<>();
        String sql = "SELECT * FROM terrains";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                terrains.add(new Terrain(
                        resultSet.getInt("id_terrain"),
                        resultSet.getString("nom_terrain"),
                        resultSet.getString("type_terrain")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des terrains : " + e.getMessage());
        }
        return terrains;
    }

    @Override
    public void update(Terrain terrain) {
        String sql = "UPDATE terrains SET nom_terrain = ?, type_terrain = ? WHERE id_terrain = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, terrain.getNomTerrain());
            statement.setString(2, terrain.getTypeTerrain());
            statement.setInt(3, terrain.getIdTerrain());
            statement.executeUpdate();
            System.out.println("Terrain mis à jour : " + terrain.getNomTerrain());

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du terrain : " + e.getMessage());
        }
    }

    @Override
    public void delete(String nom) {
        String sql = "DELETE FROM terrains WHERE nom_terrain = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            statement.executeUpdate();
            System.out.println("Terrain supprimé : " + nom);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du terrain : " + e.getMessage());
        }
    }
}
