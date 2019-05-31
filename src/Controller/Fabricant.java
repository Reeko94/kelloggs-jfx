package Controller;

public class Fabricant {

    private int id;
    private int type_nutrition_id;
    private String libelle;
    private String logo;
    private String format;
    private float poids;

    public Fabricant(int id, int type_nutrition_id, String libelle, String logo,String format,float poids) {
        this.id = id;
        this.type_nutrition_id = type_nutrition_id;
        this.libelle = libelle;
        this.logo = logo;
        this.format= format;
        this.poids= poids;
    }

    public Fabricant(int type_nutrition_id, String libelle, String logo, String format,float poids) {
        this.type_nutrition_id = type_nutrition_id;
        this.libelle = libelle;
        this.logo = logo;
        this.format= format;
        this.poids=poids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_nutrition_id() {
        return type_nutrition_id;
    }

    public void setType_nutrition_id(int type_nutrition_id) {
        this.type_nutrition_id = type_nutrition_id;
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
    
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    
    public float setPoids() {
        return poids;
    }

    public float getPoids() {
        return poids;
    }
}
