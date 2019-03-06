import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WithdrawalScene {
    private Scene scene;
    private Scene homeScene;
    private Alert errorAlert;
    private Alert notificationAlert;
    private Label transactionLabel;

    public WithdrawalScene(Stage stage, Account atmAccount) {
//  Scene Design -------------------------------------------------------------------------------------------------------
        Pane layout = new Pane();
        Font buttonFont = new Font(17);
        notificationAlert = new Alert(Alert.AlertType.INFORMATION);
        errorAlert = new Alert(Alert.AlertType.ERROR);
        scene = new Scene(layout, 600, 500);

        TextField withdrawField = new TextField();
        withdrawField.setEditable(false);
        withdrawField.setMaxWidth(134);
        withdrawField.setMaxHeight(30);
        withdrawField.setLayoutY(150);
        withdrawField.setLayoutX(scene.getWidth()/2 - withdrawField.getMaxWidth()/2);

        Button back = new Button("Back");
        back.setFont(Font.font(17));
        back.setLayoutX(10);
        back.setLayoutY(10);

        Button numOne = new Button("1");
        numOne.setLayoutX(withdrawField.getLayoutX());
        numOne.setLayoutY(withdrawField.getLayoutY()+40);
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

        Button withdraw = new Button("Withdraw");
        withdraw.setLayoutX(withdrawField.getLayoutX()+withdrawField.getMaxWidth()+50);
        withdraw.setLayoutY(withdrawField.getLayoutY()-withdrawField.getMaxHeight()/2);
        withdraw.setFont(buttonFont);

        layout.getChildren().addAll(back,backSpace,clear,withdrawField,numOne,numTwo,numThree);
        layout.getChildren().addAll(withdraw,numFour,numFive,numSix);
        layout.getChildren().addAll(numSeven,numEight,numNine,numZero);

// End of Scene Design -------------------------------------------------------------------------------------------------

// Events --------------------------------------------------------------------------------------------------------------
        back.setOnAction(event -> {
            stage.setScene(homeScene);
        });
// Number Buttons ------------------------------------------------------------------------------------------------------
        numOne.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"1");
            checkTextFieldLimit(withdrawField);
        });
        numTwo.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"2");
            checkTextFieldLimit(withdrawField);
        });
        numThree.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"3");
            checkTextFieldLimit(withdrawField);
        });
        numFour.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"4");
            checkTextFieldLimit(withdrawField);
        });
        numFive.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"5");
            checkTextFieldLimit(withdrawField);
        });
        numSix.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"6");
            checkTextFieldLimit(withdrawField);
        });
        numSeven.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"7");
            checkTextFieldLimit(withdrawField);
        });
        numEight.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"8");
            checkTextFieldLimit(withdrawField);
        });
        numNine.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"9");
            checkTextFieldLimit(withdrawField);
        });
        numZero.setOnAction(event -> {
            withdrawField.setText(withdrawField.getText()+"0");
            checkTextFieldLimit(withdrawField);
        });
// End of Number Buttons -----------------------------------------------------------------------------------------------
// Withdraw Button -----------------------------------------------------------------------------------------------------
        withdraw.setOnAction(event -> {
            if(withdrawField.getText().isEmpty()){
                errorAlert.setContentText("Nothing to withdraw");
                errorAlert.show();
                return;
            }
            int withdrawNum = Integer.parseInt(withdrawField.getText());
            if(atmAccount.getBalance()>=withdrawNum){
                atmAccount.subBalance(withdrawNum);
                notificationAlert.setContentText("Withdrawed $"+withdrawNum);
                transactionLabel.setText(atmAccount.addTransactionHistory("Withdrawed",withdrawField.getText()));
                notificationAlert.setHeaderText("Withdraw Successful");
                notificationAlert.setTitle("Withdraw");
                notificationAlert.show();
            }
            else {
                errorAlert.setContentText("Insufficient balance");
                errorAlert.show();
            }
            withdrawField.setText("");
        });
// End of Withdraw Button ----------------------------------------------------------------------------------------------
// Clear Button --------------------------------------------------------------------------------------------------------
        clear.setOnAction(event -> {
            withdrawField.setText("");

        });
// End of Clear Button -------------------------------------------------------------------------------------------------
// Back Space Button ---------------------------------------------------------------------------------------------------
        backSpace.setOnAction(event -> {
            int num;
            if(withdrawField.getText().isEmpty()) return;
            num = Integer.parseInt(withdrawField.getText());
            num/=10;
            if(num<1) withdrawField.setText("");
            else withdrawField.setText(Integer.toString(num));

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
