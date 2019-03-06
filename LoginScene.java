import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScene {

    private Scene scene;
    AtmScene atmScene;

    public LoginScene(Stage stage, Account atmAccount) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        atmScene = new AtmScene(stage,atmAccount);
//  Scene Design -------------------------------------------------------------------------------------------------------
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);

        scene = new Scene(grid, 600, 500);

        TextField loginField = new TextField();
        loginField.setPromptText("Card Number");

        Button loginButton = new Button("Login");

        grid.add(loginField,0,0);
        grid.add(loginButton,0,1);
// End of Scene Design -------------------------------------------------------------------------------------------------

// Events --------------------------------------------------------------------------------------------------------------
        loginButton.setOnAction(event -> {
            if (loginField.getText().isEmpty()){
                alert.setContentText("Enter a number");
                alert.show();
                return;
            }
            else if (atmAccount.isValidCard(loginField.getText())){
                stage.setScene(atmScene.getScene());
            }
            else {
                alert.setContentText("Incorrect Number");
                alert.show();
                return;
            }

        });
// End of Events -------------------------------------------------------------------------------------------------------

    }

    public Scene getScene() {
        return scene;
    }
}
