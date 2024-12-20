package JAVA.rendu_2.demo.src.main.java.com.example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        // Utilisation de la methode Dbcon pour verifier la connexion (optionnel)
        Dbcon.getConnection();

        // 1. Test des Utilisateurs avec UtilisateurDAO
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

        // Ajouter un utilisateur
        Utilisateur utilisateur1 = new Utilisateur(0, "hiba", "Jamila", "jamila.hiba@example.com", "etudiant");
        utilisateurDAO.add(utilisateur1);

        // Recuperer un utilisateur par son nom
        Utilisateur utilisateur = utilisateurDAO.get("hiba");
        System.out.println("Utilisateur recupere : " + utilisateur);

        // Mettre a jour l'utilisateur
        utilisateur.setPrenom("houda");
        utilisateurDAO.update(utilisateur);

        // Supprimer un utilisateur
        utilisateurDAO.delete("hiba");

        // 2. Test des Terrains avec TerrainDAO
        TerrainDAO terrainDAO = new TerrainDAO();

        // Ajouter un terrain
        Terrain terrain1 = new Terrain(0, "Terrain A", "Football");
        terrainDAO.add(terrain1);

        // Recuperer un terrain
        Terrain terrainRecupere = terrainDAO.get("Terrain A");
        System.out.println("Terrain recupere : " + terrainRecupere);

        // Recuperer tous les terrains
        List<Terrain> terrains = terrainDAO.getAll();
        System.out.println("Tous les terrains : " + terrains);

        // Mettre a jour le terrain
        terrainRecupere.setTypeTerrain("Basketball");
        terrainDAO.update(terrainRecupere);

        // Supprimer le terrain
        terrainDAO.delete("Terrain A");

        // 3. Test des Salles avec SalleDAO
        SalleDAO salleDAO = new SalleDAO();

        // Ajouter une salle
        Salle salle1 = new Salle(0, "Salle 101", 100);
        salleDAO.add(salle1);

        // Recuperer une salle
        Salle salleRecuperee = salleDAO.get("Salle 101");
        System.out.println("Salle recuperee : " + salleRecuperee);

        // Recuperer toutes les salles
        List<Salle> salles = salleDAO.getAll();
        System.out.println("Toutes les salles : " + salles);

        // Mettre a jour la salle
        salleRecuperee.setCapacite(120);
        salleDAO.update(salleRecuperee);

        // Supprimer la salle
        salleDAO.delete("Salle 101");

        // 4. Test des Evenements avec EvenementDAO
        EvenementDAO evenementDAO = new EvenementDAO();

        // Ajouter un evenement
        Evenement evenement1 = new Evenement(0, "Match de football", "2024-12-25", "Stade", 100);
        evenementDAO.add(evenement1);

        // Recuperer un evenement
        Evenement evenementRecupere = evenementDAO.get("Match de football");
        System.out.println("Evenement recupere : " + evenementRecupere);

        // Recuperer tous les evenements
        List<Evenement> evenements = evenementDAO.getAll();
        System.out.println("Tous les evenements : " + evenements);

        // Mettre a jour l'evenement
        evenementRecupere.setDescription("Stade de France");
        evenementDAO.update(evenementRecupere);
        System.out.println("Tous les evenements : " + evenements);

        // Supprimer l'evenement
        evenementDAO.delete("Match de football");
        System.out.println("Tous les evenements : " + evenements);

        // 5. Test des Reservations avec ReservationDAO
        ReservationDAO reservationDAO = new ReservationDAO();

        // Ajouter une reservation
        Reservation reservation1 = new Reservation(0, 1, 1, 1, 1, LocalDate.of(2024,12,25));
        reservationDAO.add(reservation1);

        // Recuperer une reservation
        Reservation reservationRecuperee = reservationDAO.get("2024-12-25");
        System.out.println("Reservation recuperee : " + reservationRecuperee);

        // Recuperer toutes les reservations
        List<Reservation> reservations = reservationDAO.getAll();
        System.out.println("Toutes les reservations : " + reservations);

        // Mettre a jour la reservation
        reservationRecuperee.setDateReservation(LocalDate.of(2024, 12, 26));
        reservationDAO.update(reservationRecuperee);

        // Supprimer la reservation
        reservationDAO.delete("2024-12-25");

        // 6. Test de la gestion des transactions avec TransactionManager
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.executeInTransaction(() -> {
            // Effectuer des operations en transaction (exemple : ajouter un utilisateur et un terrain)
            Utilisateur utilisateur2 = new Utilisateur(0, "Martin", "Paul", "paul.martin@example.com", "user");
            utilisateurDAO.add(utilisateur2);

            Terrain terrain2 = new Terrain(0, "Terrain B", "Basketball");
            terrainDAO.add(terrain2);
        });

        // Verification finale pour s'assurer que tout a bien fonctionne
        List<Utilisateur> utilisateurs = utilisateurDAO.getAll();
        System.out.println("Utilisateurs : " + utilisateurs);

        List<Terrain> terrainsFinal = terrainDAO.getAll();
        System.out.println("Terrains apres transaction : " + terrainsFinal);
    }
}
