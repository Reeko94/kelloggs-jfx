package Controller;

public class ArticlesFabricants {

    private String libelle;
    private int id;
    private String designation;
    private String disponibilite;
    private double prix;
    private double calories;
    private int aid;


    public ArticlesFabricants(String ibelle, int id, String designation, int disponibilite, double prix, double calories,int aId) {
        this.libelle = ibelle;
        this.id = id;
        this.designation = designation;
        this.disponibilite = (disponibilite==0?"Non":"Oui");
        this.prix = prix;
        this.calories = calories;
        this.aid = aId;
    }


    public int getAid() {
        return aid;
    }

    public void setAid(int aId) {
        this.aid = aId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
