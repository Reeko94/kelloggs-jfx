package Controller;

public class Article {

    private int id;
    private int fabricant_id;
    private String libelle;
    private String disponibilite;
    private double prix;
    private double calories;
    private String url_media;


    public Article(int id, int fabricant, String libelle, int disponibilite, double prix, double calories, String url_media) {
        this.id = id;
        this.fabricant_id = fabricant;
        this.libelle = libelle;
        this.disponibilite = (disponibilite==0?"Oui":"non");
        this.prix = prix;
        this.calories = calories;
        this.url_media = url_media;
    }

    public Article(int fabricant, String libelle, int disponibilite, double prix, double calories, String url_media) {
        this.fabricant_id = fabricant;
        this.libelle = libelle;
        this.disponibilite = (disponibilite==0?"Oui":"non");
        this.prix = prix;
        this.calories = calories;
        this.url_media = url_media;
    }

    public Article(int fabricant, String libelle, int disponibilite, double prix, double calories) {
        this.fabricant_id = fabricant;
        this.libelle = libelle;
        this.disponibilite = (disponibilite==0?"Oui":"non");
        this.prix = prix;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFabricant_id(int fabricant_id) {
        this.fabricant_id = fabricant_id;
    }

    public int getFabricant() {
        return fabricant_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String  getDisponibilite() {
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

    public String getUrl_media() {
        return url_media;
    }

    public void setUrl_media(String url_media) {
        this.url_media = url_media;
    }
}
