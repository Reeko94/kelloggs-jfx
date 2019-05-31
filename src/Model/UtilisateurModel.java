package Model;

import Controller.Main;
import Controller.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UtilisateurModel {

    private static BDD database = new BDD("51.68.229.149","kelloggs","kelloggs","kelloggs");

    public static User selectUser(String login, String key) {
        String query = "SELECT * FROM utilisateur WHERE `email` = '"+login+"' AND type = 2 AND discr = 'commercial' AND actif = 1";
        User user = null;
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("discr"),
                        resultSet.getInt("type"),
                        resultSet.getInt("actif"),
                        resultSet.getString("token")
                );
                System.out.println(user.getPassword());
                user = (!Main.checkPassword(key, user.getPassword())) ? null : user;
            }

        }catch (SQLException exp) {
            exp.printStackTrace();
        }
        return user;
    }

    public static ArrayList<User> returnAllUserForList() {
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                User user = new User(
                        resultSet.getString("email"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("discr"),
                        resultSet.getInt("actif")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void updateField(String query) {
        executeQuery(query, database);
    }

    static void executeQuery(String query, BDD database) {
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int countUsers() {
        int nb = 0;
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("SELECT COUNT(*) as nb FROM utilisateur");
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
