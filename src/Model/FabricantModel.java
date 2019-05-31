package Model;

import Controller.Fabricant;
import Controller.FabricantsTypeNutrition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FabricantModel {

    private static BDD database = new BDD("51.68.229.149","kelloggs","kelloggs","kelloggs");

    public static int getIdByName(String name){
        String query = "SELECT id FROM fabricants WHERE libelle = '"+name+"'";
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

    public static ArrayList<FabricantsTypeNutrition> getAll() {

        ArrayList<FabricantsTypeNutrition> fabricants = new ArrayList<>();
        String query = "SELECT * FROM fabricantstypenutrition";
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                FabricantsTypeNutrition fabricant = new FabricantsTypeNutrition(
                        resultSet.getInt("id"),
                        resultSet.getString("libelle"),
                        resultSet.getString("logo"),
                        resultSet.getInt("tid"),
                        resultSet.getString("typenutrition")
                );

                fabricants.add(fabricant);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return fabricants;
    }

    public static void updateField(String query) {
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getAllArray() {
        List<String> fabricantsList = new ArrayList<>();

        String query = "SELECT * FROM fabricants";
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                fabricantsList.add(resultSet.getString("libelle"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        String[] fabricants = new String[fabricantsList.size()];
        fabricantsList.toArray(fabricants);
        return  fabricants;
    }

    public static boolean newFabricant(String query) {
        System.out.println(query);
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            database.logout();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            database.logout();
            return false;
        }
    }


    public static int nbFabricants() {
        int nb = 0;
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("SELECT COUNT(*) as nb FROM fabricants");
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                nb = resultSet.getInt("nb");
            }
        }catch (SQLException e) {
            e.printStackTrace();
            database.logout();
        }
        database.logout();
        return nb;
    }









    public static int countFabricants() {
        int nb  = 0;
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute("SELECT COUNT(*) as nb FROM fabricants");
            ResultSet resultSet = statement.getResultSet();
            if(resultSet.next()) {
                nb  = resultSet.getInt("nb");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nb;
    }
}
