package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO implements GenericDAO<Reservation> {

    @Override
    public void add(Reservation reservation) {
        String sql = "INSERT INTO reservations (id_user, id_event, id_salle, id_terrain, date_reservation) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reservation.getIdUser());
            statement.setInt(2, reservation.getIdEvent());
            statement.setInt(3, reservation.getIdSalle());
            statement.setInt(4, reservation.getIdTerrain());
            statement.setDate(5, Date.valueOf(reservation.getDateReservation()));
            statement.executeUpdate();
            System.out.println("Réservation ajoutée pour l'événement : " + reservation.getIdEvent());

        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réservation : " + e.getMessage());
        }
    }

    @Override
    public Reservation get(String nom) {
        String sql = "SELECT * FROM reservations WHERE id_event = (SELECT id_event FROM evenements WHERE nom_event = ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(
                        resultSet.getInt("id_reservation"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_event"),
                        resultSet.getInt("id_salle"),
                        resultSet.getInt("id_terrain"),
                        resultSet.getDate("date_reservation").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la réservation : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reservations.add(new Reservation(
                        resultSet.getInt("id_reservation"),
                        resultSet.getInt("id_user"),
                        resultSet.getInt("id_event"),
                        resultSet.getInt("id_salle"),
                        resultSet.getInt("id_terrain"),
                        resultSet.getDate("date_reservation").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des réservations : " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        String sql = "UPDATE reservations SET id_user = ?, id_event = ?, id_salle = ?, id_terrain = ?, date_reservation = ? WHERE id_reservation = ?";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reservation.getIdUser());
            statement.setInt(2, reservation.getIdEvent());
            statement.setInt(3, reservation.getIdSalle());
            statement.setInt(4, reservation.getIdTerrain());
            statement.setDate(5, Date.valueOf(reservation.getDateReservation()));
            statement.setInt(6, reservation.getIdReservation());
            statement.executeUpdate();
            System.out.println("Réservation mise à jour : " + reservation.getIdReservation());

        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la réservation : " + e.getMessage());
        }
    }

    @Override
    public void delete(String nom) {
        String sql = "DELETE FROM reservations WHERE id_event = (SELECT id_event FROM evenements WHERE nom_event = ?)";
        try (Connection connection = Dbcon.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nom);
            statement.executeUpdate();
            System.out.println("Réservation supprimée pour l'événement : " + nom);

        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la réservation : " + e.getMessage());
        }
    }
}
