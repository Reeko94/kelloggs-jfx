package Controller;

public class User {

    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String discr;
    private int type;
    private int actif;
    private String token;

    public User(int id,String email,String password,String nom,String prenom,String discr,int type,int actif,String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.discr = discr;
        this.type = type;
        this.actif = actif;
        this.token = token;
    }

    public User(String email,String password,String nom,String prenom,String discr,int type,int actif,String token) {
        this.id = 0;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.discr = discr;
        this.type = type;
        this.actif = actif;
        this.token = token;
    }

    public User(String email,String nom,String prenom,String discr,int actif){
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.discr = discr;
        this.actif = actif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDiscr() {
        return discr;
    }

    public void setDiscr(String discr) {
        this.discr = discr;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getActif() {
        return actif;
    }

    public void setActif(int actif) {
        this.actif = actif;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
