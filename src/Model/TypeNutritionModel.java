package Model;

import Controller.TypeNutrition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeNutritionModel {

    private static BDD database = new BDD("51.68.229.149","kelloggs","kelloggs","kelloggs");

    public static ArrayList<TypeNutrition> returnAllTypeNutrition(){

        ArrayList<TypeNutrition> typeNutritions = new ArrayList<>();

        String query = "SELECT * FROM type_nutrition";

        database.login();

        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){

                TypeNutrition typeNutrition = new TypeNutrition(
                        resultSet.getInt("id"),
                        resultSet.getString("libelle")
                );

                typeNutritions.add(typeNutrition);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeNutritions;
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

    public static int getIdByName(String name){
        int id = 0;
        String query = "SELECT id FROM type_nutrition WHERE libelle='"+name+"';";
        database.login();
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

    public static String[] getAllArray() {
        List<String> typeNutrtitionList = new ArrayList<>();

        String query = "SELECT * FROM type_nutrition";
        database.login();
        try {
            Statement statement = database.getConnection().createStatement();
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                typeNutrtitionList.add(resultSet.getString("libelle"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        String[] typeNutrition = new String[typeNutrtitionList.size()];
        typeNutrtitionList.toArray(typeNutrition);
        return  typeNutrition;
    }
}
