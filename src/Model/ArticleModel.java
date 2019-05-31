package Model;

import Controller.Article;
import Controller.ArticlesFabricants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleModel {


    private static BDD database = new BDD("51.68.229.149","kelloggs","kelloggs","kelloggs");


    /**
     *
     * @return
     */
    public static ArrayList<Article> returnAllArticle(){
        ArrayList<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM articles";

        database.login();

        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                Article article = new Article(
                        resultSet.getInt("fabricant_id"),
                        resultSet.getString("libelle"),
                        resultSet.getInt("disponibilite"),
                        resultSet.getDouble("prix"),
                        resultSet.getDouble("calories")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;

    }

    /**
     *
     * @return
     */
    public static ArrayList<ArticlesFabricants> returnAllAF(){
        ArrayList<ArticlesFabricants> articles = new ArrayList<>();
        String query = "SELECT * FROM articlesFabricants";

        database.login();

        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                ArticlesFabricants article = new ArticlesFabricants(
                        resultSet.getString("libelle"),
                        resultSet.getInt("id"),
                        resultSet.getString("designation"),
                        resultSet.getInt("disponibilite"),
                        resultSet.getDouble("prix"),
                        resultSet.getDouble("calories"),
                        resultSet.getInt("aid")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;

    }


    /**
     *
     * @param query
     */
    public static void updateField(String query) {
        UtilisateurModel.executeQuery(query, database);
    }

    public static int getIdByName(String article) {
        String query = "SELECT id FROM articles WHERE libelle = '"+article+"'";
        database.login();
        int id = 0;
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    public static boolean newArticle(String query) {
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        database.logout();
        return true;
    }

    public static int countArticles() {
        int nb = 0;
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("SELECT COUNT(*) as nb FROM articles");
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                nb = resultSet.getInt("nb");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nb;
    }
}
