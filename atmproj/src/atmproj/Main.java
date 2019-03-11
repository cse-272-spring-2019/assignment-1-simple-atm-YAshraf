package atmproj;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    Account atmAccount;
    public static void main(String args[]){
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        atmAccount = new Account();
        LoginScene loginScene = new LoginScene(primaryStage,atmAccount);
        primaryStage.setScene(loginScene.getScene());
        primaryStage.setTitle("ATM");
        primaryStage.show();
    }
}
