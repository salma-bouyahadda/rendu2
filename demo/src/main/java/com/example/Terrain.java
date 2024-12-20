package JAVA.rendu_2.demo.src.main.java.com.example;

public class Terrain {
    private int idTerrain;
    private String nomTerrain;
    private String typeTerrain;

    public Terrain(int idTerrain, String nomTerrain, String typeTerrain) {
        this.idTerrain = idTerrain;
        this.nomTerrain = nomTerrain;
        this.typeTerrain = typeTerrain;
    }

    public int getIdTerrain() {
        return idTerrain;
    }

    public void setIdTerrain(int idTerrain) {
        this.idTerrain = idTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public String getTypeTerrain() {
        return typeTerrain;
    }

    public void setTypeTerrain(String typeTerrain) {
        this.typeTerrain = typeTerrain;
    }

    @Override
    public String toString() {
        return "Terrain{" +
                "idTerrain=" + idTerrain +
                ", nomTerrain='" + nomTerrain + '\'' +
                ", typeTerrain='" + typeTerrain + '\'' +
                '}';
    }
}
