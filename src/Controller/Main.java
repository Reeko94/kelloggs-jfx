package Controller;

import Model.*;
import Util.BCrypt;
import View.*;
import com.jcraft.jsch.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.util.Properties;


public class Main extends Application {

    private static LoginView loginView = null;
    private static DashboardView dashboardView = null;
    private static NewArticleView newArticleView = null;
    private static NewFabricantView newFabricantView = null;
    private static ListUserView listUserView = null;
    private static ListArticleView listArticleView = null;
    private static TypeNutritionView typeNutritionView = null;
    private static ListFabricantView fabricantView = null;
    private static TestView testView = null;

    private static Scene scene;


    public static boolean checkCredentials(String login, String pwd, Text fieldError) {
        if(login.equals("") || pwd.equals("")){
            fieldError.setFill(Color.FIREBRICK);
            fieldError.setText("Au moins un des champs est vide");
            return false;
        } else {
            User user = UtilisateurModel.selectUser(login,pwd);
            if(user == null) {
                fieldError.setFill(Color.FIREBRICK);
                fieldError.setText("Les identifiants sont incorrect");

                return false;
            } else {
                //dashboardView = new DashboardView();
                //dashboardView.setVisible(true);
                return true;
            }
        }
    }

    public static boolean checkPassword(String passwordText, String DbHash) {
        boolean password_verified = false;
        DbHash = DbHash.replace("$2y$","$2a$");
        if (!DbHash.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid Hash");
        }
        password_verified = BCrypt.checkpw(passwordText,DbHash);

        return password_verified;
    }

    /**
        INSERT
     */

    public static boolean insertArticle(String[][] fieldValues) {
        String query = BDD.getQueryInsertTable("articles",fieldValues);
        String nameArticle = fieldValues[1][2];
        System.out.println(getIdTypeNutrition(nameArticle));
        if(getIdArticle(nameArticle) != 0) {
            return false;
        }
        if(!ArticleModel.newArticle(query)) {
            return false;
        }
        return true;
    }
    public static boolean insertFabricant(String[][] fieldValues) {
        String query = BDD.getQueryInsertTable("fabricants",fieldValues);
        String nameFabricant = fieldValues[1][2];
        System.out.println(getIdTypeNutrition(nameFabricant));
        if(getIdTypeNutrition(nameFabricant) != 0) {
            return false;
        }
        if(!FabricantModel.newFabricant(query)) {
            return false;
        }
        return true;
    }

    /*
        UPDATES
     */

    /**
     * if 3rd array is true it's a string
     * @param fieldValue
     * @param where
     */
    public static void updateArticle(String[][] fieldValue, String[][] where) {
        String query = BDD.getQueryUpdateTable("articles",fieldValue,where);
        ArticleModel.updateField(query);
    }
    public static void updateUser(String[][] fieldValue, String[][] where) {
        String query = BDD.getQueryUpdateTable("utilisateur",fieldValue,where);
        UtilisateurModel.updateField(query);
    }
    public static void updateTypeNutrition(String[][] fieldValue, String[][] where) {
        String query = BDD.getQueryUpdateTable("type_nutrition",fieldValue,where);
        TypeNutritionModel.updateField(query);
    }
    public static void updateFabricant(String[][] fieldValue, String[][] where) {
        String query = BDD.getQueryUpdateTable("fabricants",fieldValue,where);
        FabricantModel.updateField(query);
    }


    /**
        FUNCTIONS CHECK CORRECT DATA
     */

    public static int getIdFabricant(String fabricant){
        return FabricantModel.getIdByName(fabricant);
    }
    public static int getIdTypeNutrition(String typeNutrition) {
        return TypeNutritionModel.getIdByName(typeNutrition);
    }
    public static int getIdArticle(String article) {
        return ArticleModel.getIdByName(article);
    }


    /**
        GOTO
     */

    public static void gotoDashboard(GridPane gridPane) {
        gridPane.getChildren().clear();
        dashboardView = new DashboardView(gridPane);
    }
    public static void gotoNewArticle(GridPane gridPane) {
        gridPane.getChildren().clear();
        String[] fabricants = FabricantModel.getAllArray();
        newArticleView = new NewArticleView(gridPane,fabricants);
    }
    public static void gotoListUser(GridPane gridPane){
        gridPane.getChildren().clear();
        listUserView = new ListUserView(gridPane);

    }

    public static void gotoListFabricant(GridPane gridPane){
        gridPane.getChildren().clear();
        fabricantView = new ListFabricantView(gridPane);

    }
    public static void gotoListArticle(GridPane gridPane) {
        gridPane.getChildren().clear();
        listArticleView = new ListArticleView(gridPane);

    }
    public static void gotoListTypeNutrition(GridPane gridPane){
        gridPane.getChildren().clear();
        typeNutritionView = new TypeNutritionView(gridPane);
    }
    public static void gotoNewFabricant(GridPane gridPane) {
        gridPane.getChildren().clear();
        String[] typeNutrtion = TypeNutritionModel.getAllArray();
        newFabricantView = new NewFabricantView(gridPane,typeNutrtion);
    }

    public static int getNBArticles() {
        return ArticleModel.countArticles();
    }

    public static int getNbFabricants() {
        return FabricantModel.countFabricants();
    }

    public static int getNbUsers() {
        return UtilisateurModel.countUsers();
    }

    /**
        MAIN
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Administration de kellogg's");
        primaryStage.show();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25));

        loginView = new LoginView(gridPane);

        this.scene = new Scene(gridPane,590,420);

        primaryStage.setScene(scene);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);



    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void uploadFile(String absolutePathFile, String sftpDirectory) {
        JSch jSch = new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        Session session = null;
        try {
            session = jSch.getSession("root", "51.68.229.149", 22);
            session.setPassword("WNpsYP6c");
            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            ChannelSftp sftp = (ChannelSftp) channel;


            File file = new File(absolutePathFile);
            String filename = file.getAbsolutePath();

            sftp.put(filename,sftpDirectory, ChannelSftp.OVERWRITE);
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }


    }
}
