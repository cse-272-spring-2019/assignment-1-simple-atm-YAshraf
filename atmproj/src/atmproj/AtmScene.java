package atmproj;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AtmScene {
    private Scene scene;
    WithdrawalScene withdrawalScene;
    DepositScene depositScene;

    public AtmScene(Stage stage, Account atmAccount) {
        withdrawalScene = new WithdrawalScene(stage,atmAccount);
        depositScene = new DepositScene(stage,atmAccount);
//  Scene Design -------------------------------------------------------------------------------------------------------
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(-30);
        scene = new Scene(grid, 600, 500);

        Button withdrawButton = new Button("Withdrawal");
        withdrawButton.setFont(Font.font(22));

        Button depositButton = new Button("Deposit");
        depositButton.setFont(Font.font(22));

        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font(17));

        Button previousButton = new Button("Previous");
        previousButton.setFont(Font.font(17));

        Button balanceButton = new Button("Balance");
        balanceButton.setFont(Font.font(17));

        Label transactionsLabel = new Label("test");
        transactionsLabel.setFont(Font.font(20));
        transactionsLabel.setText("Balance: $"+Integer.toString(atmAccount.getBalance()));

        grid.add(withdrawButton,0,0);
        grid.add(depositButton,0,1);
        grid.add(previousButton,0, 3);
        grid.add(nextButton,1, 3);
        grid.add(balanceButton,0,4);
        grid.add(transactionsLabel,0, 5);
// End of Scene Design -------------------------------------------------------------------------------------------------

// Events --------------------------------------------------------------------------------------------------------------
        withdrawButton.setOnAction(event -> {
            withdrawalScene.setHomeScene(this.scene);
            withdrawalScene.setTransactionLabel(transactionsLabel);
            stage.setScene(withdrawalScene.getScene());
        });
        depositButton.setOnAction(event -> {
            depositScene.setHomeScene(this.scene);
            depositScene.setTransactionLabel(transactionsLabel);
            stage.setScene(depositScene.getScene());
        });
        balanceButton.setOnAction(event -> {
            transactionsLabel.setText("Balance: $"+Integer.toString(atmAccount.getBalance()));
        });
        nextButton.setOnAction(event -> {
            transactionsLabel.setText(atmAccount.nextTransactionHistory());
        });
        previousButton.setOnAction(event -> {
            transactionsLabel.setText(atmAccount.prevTransactionHistory());
        });
// End of Events -------------------------------------------------------------------------------------------------------

    }


    public Scene getScene() {
        return scene;
    }
}

