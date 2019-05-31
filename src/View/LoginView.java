package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView {

    private Text title = new Text("Administration de Kelloggs");
    private final Text actionTarget = new Text();

    private Label userLabel = new Label("Adresse e-mail");
    private Label pw = new Label("Mot de passe");

    private TextField userTextField = new TextField();

    private PasswordField passwordField = new PasswordField();

    private Button button = new Button("Se connecter");

    private HBox hbBtn = new HBox(10);



    public LoginView(GridPane gridPane)
    {
        title.setFont(Font.font("Tahoma", FontWeight.NORMAL,20));

        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(button);

        gridPane.add(title,0,0,2,1);
        gridPane.add(userLabel,0,1);
        gridPane.add(userTextField,1,1);
        gridPane.add(pw,0,2);
        gridPane.add(passwordField,1,2);
        gridPane.add(hbBtn,1,4);
        gridPane.add(actionTarget,1,6);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String login = userTextField.getText();
                String pwd = passwordField.getText();

                if(Main.checkCredentials(login,pwd,actionTarget)){
                    Main.gotoDashboard(gridPane);
                }
            }
        });


    }

}
