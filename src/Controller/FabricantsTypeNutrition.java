package Controller;

public class FabricantsTypeNutrition {

    private int id;
    private String libelle;
    private String logo;
    private int tid;
    private String typeNutriton;
    private String format;
    private float poids;

    public FabricantsTypeNutrition(int id, String libelle, String logo, int tid, String typeNutriton) {
        this.id = id;
        this.libelle = libelle;
        this.logo = logo;
        this.tid = tid;
        this.typeNutriton = typeNutriton;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTypeNutriton() {
        return typeNutriton;
    }

    public void setTypeNutriton(String typeNutriton) {
        this.typeNutriton = typeNutriton;
    }
    
    public String getFormat() {
        return format;
    }

    public void setformat(String format) {
        this.format = format;
    }
    
    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }
}
