package JAVA.rendu_2.demo.src.main.java.com.example;

public class Salle {
    private int idSalle;
    private String nomSalle;
    private int capacite;

    public Salle(int idSalle, String nomSalle, int capacite) {
        this.idSalle = idSalle;
        this.nomSalle = nomSalle;
        this.capacite = capacite;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", nomSalle='" + nomSalle + '\'' +
                ", capacite=" + capacite +
                '}';
    }
}
