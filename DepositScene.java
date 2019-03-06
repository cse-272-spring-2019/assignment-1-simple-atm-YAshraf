import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DepositScene {
    private Scene scene;
    private Scene homeScene;
    private Alert errorAlert;
    private Alert notificationAlert;
    private Label transactionLabel;


    public DepositScene(Stage stage, Account atmAccount) {
//  Scene Design -------------------------------------------------------------------------------------------------------
        Pane layout = new Pane();
        Font buttonFont = new Font(17);
        notificationAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert = new Alert(Alert.AlertType.ERROR);
        scene = new Scene(layout, 600, 500);

        TextField depositField = new TextField();
        depositField.setEditable(false);
        depositField.setMaxWidth(134);
        depositField.setMaxHeight(30);
        depositField.setLayoutY(150);
        depositField.setLayoutX(scene.getWidth()/2 - depositField.getMaxWidth()/2);

        Button back = new Button("Back");
        back.setFont(Font.font(17));
        back.setLayoutX(10);
        back.setLayoutY(10);

        Button numOne = new Button("1");
        numOne.setLayoutX(depositField.getLayoutX());
        numOne.setLayoutY(depositField.getLayoutY()+40);
        numOne.setFont(buttonFont);

        Button numTwo = new Button("2");
        numTwo.setLayoutX(numOne.getLayoutX()+50);
        numTwo.setLayoutY(numOne.getLayoutY());
        numTwo.setFont(buttonFont);

        Button numThree = new Button("3");
        numThree.setLayoutX(numTwo.getLayoutX()+50);
        numThree.setLayoutY(numTwo.getLayoutY());
        numThree.setFont(buttonFont);

        Button numFour = new Button("4");
        numFour.setLayoutX(numOne.getLayoutX());
        numFour.setLayoutY(numOne.getLayoutY()+50);
        numFour.setFont(buttonFont);

        Button numFive = new Button("5");
        numFive.setLayoutX(numFour.getLayoutX()+50);
        numFive.setLayoutY(numFour.getLayoutY());
        numFive.setFont(buttonFont);

        Button numSix = new Button("6");
        numSix.setLayoutX(numFive.getLayoutX()+50);
        numSix.setLayoutY(numFive.getLayoutY());
        numSix.setFont(buttonFont);

        Button numSeven = new Button("7");
        numSeven.setLayoutX(numOne.getLayoutX());
        numSeven.setLayoutY(numFour.getLayoutY()+50);
        numSeven.setFont(buttonFont);

        Button numEight = new Button("8");
        numEight.setLayoutX(numSeven.getLayoutX()+50);
        numEight.setLayoutY(numSeven.getLayoutY());
        numEight.setFont(buttonFont);


        Button numNine = new Button("9");
        numNine.setLayoutX(numEight.getLayoutX()+50);
        numNine.setLayoutY(numEight.getLayoutY());
        numNine.setFont(buttonFont);


        Button numZero = new Button("0");
        numZero.setLayoutX(numSeven.getLayoutX());
        numZero.setLayoutY(numSeven.getLayoutY()+50);
        numZero.setFont(buttonFont);


        Button clear = new Button("Clear");
        clear.setLayoutX(numEight.getLayoutX());
        clear.setLayoutY(numEight.getLayoutY()+50);
        clear.setFont(buttonFont);

        Button backSpace = new Button("<--");
        backSpace.setLayoutX(numThree.getLayoutX()+50);
        backSpace.setLayoutY(numThree.getLayoutY());
        backSpace.setFont(buttonFont);

        Button withdraw = new Button("Deposit");
        withdraw.setLayoutX(depositField.getLayoutX()+depositField.getMaxWidth()+50);
        withdraw.setLayoutY(depositField.getLayoutY()-depositField.getMaxHeight()/2);
        withdraw.setFont(buttonFont);

        layout.getChildren().addAll(back,backSpace,clear,depositField,numOne,numTwo,numThree);
        layout.getChildren().addAll(withdraw,numFour,numFive,numSix);
        layout.getChildren().addAll(numSeven,numEight,numNine,numZero);

// End of Scene Design -------------------------------------------------------------------------------------------------

// Events --------------------------------------------------------------------------------------------------------------
        back.setOnAction(event -> {
            stage.setScene(homeScene);
        });
// Number Buttons ------------------------------------------------------------------------------------------------------
        numOne.setOnAction(event -> {
            depositField.setText(depositField.getText()+"1");
            checkTextFieldLimit(depositField);
        });
        numTwo.setOnAction(event -> {
            depositField.setText(depositField.getText()+"2");
            checkTextFieldLimit(depositField);
        });
        numThree.setOnAction(event -> {
            depositField.setText(depositField.getText()+"3");
            checkTextFieldLimit(depositField);
        });
        numFour.setOnAction(event -> {
            depositField.setText(depositField.getText()+"4");
            checkTextFieldLimit(depositField);
        });
        numFive.setOnAction(event -> {
            depositField.setText(depositField.getText()+"5");
            checkTextFieldLimit(depositField);
        });
        numSix.setOnAction(event -> {
            depositField.setText(depositField.getText()+"6");
            checkTextFieldLimit(depositField);
        });
        numSeven.setOnAction(event -> {
            depositField.setText(depositField.getText()+"7");
            checkTextFieldLimit(depositField);
        });
        numEight.setOnAction(event -> {
            depositField.setText(depositField.getText()+"8");
            checkTextFieldLimit(depositField);
        });
        numNine.setOnAction(event -> {
            depositField.setText(depositField.getText()+"9");
            checkTextFieldLimit(depositField);
        });
        numZero.setOnAction(event -> {
            depositField.setText(depositField.getText()+"0");
            checkTextFieldLimit(depositField);
        });
// End of Number Buttons -----------------------------------------------------------------------------------------------
// Withdraw Button -----------------------------------------------------------------------------------------------------
        withdraw.setOnAction(event -> {
            if(depositField.getText().isEmpty()){
                errorAlert.setContentText("Nothing to deposit");
                errorAlert.show();
                return;
            }
            int depositNum = Integer.parseInt(depositField.getText());
            atmAccount.addBalance(depositNum);
            transactionLabel.setText(atmAccount.addTransactionHistory("Deposited",depositField.getText()));
            notificationAlert.setContentText("Deposited $"+depositField.getText());
            notificationAlert.setHeaderText("Deposit Successful");
            notificationAlert.setTitle("Deposit");
            notificationAlert.show();
            depositField.setText("");
        });
// End of Withdraw Button ----------------------------------------------------------------------------------------------
// Clear Button --------------------------------------------------------------------------------------------------------
        clear.setOnAction(event -> {
            depositField.setText("");

        });
// End of Clear Button -------------------------------------------------------------------------------------------------
// Back Space Button ---------------------------------------------------------------------------------------------------
        backSpace.setOnAction(event -> {
            int num;
            if(depositField.getText().isEmpty()) return;
            num = Integer.parseInt(depositField.getText());
            num/=10;
            if(num<1) depositField.setText("");
            else depositField.setText(Integer.toString(num));

        });
// End of Back Space Button --------------------------------------------------------------------------------------------
// End of Events -------------------------------------------------------------------------------------------------------
    }

    public Scene getScene() {
        return scene;
    }

    public void setHomeScene(Scene homeScene) {
        this.homeScene = homeScene;
    }

    public void setTransactionLabel(Label transactionLabel) {
        this.transactionLabel = transactionLabel;
    }

    void checkTextFieldLimit(TextField field) {
        if (field.getText().isEmpty()) return;
        else if (Integer.parseInt(field.getText()) > 5000) {
            field.setText(Integer.toString(Integer.parseInt(field.getText()) / 10));
            errorAlert.setContentText("Limit is $5000");
            errorAlert.show();

        }
    }
}
