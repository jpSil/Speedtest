package org.jpsil.speedtest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;


public class App extends Application {

    // Score label
    private Label score;

    // Start button
    private Button newGame;

    // Buttons
    private GameButton redButton = new GameButton(Color.RED, Color.DARKRED);
    private GameButton blueButton = new GameButton(Color.CYAN, Color.DARKBLUE);
    private GameButton yellowButton = new GameButton(Color.YELLOW, Color.GOLDENROD);
    private GameButton greenButton = new GameButton(Color.LAWNGREEN, Color.DARKGREEN);

    // Main container
    private BorderPane container;

    // Game button container
    private HBox buttonContainer;

    // Random number generator
    private Random random = new Random();

    private int playerScore = 0;

    private long interval = 2000;

    private NewGameThread newGameThread;

    // Scene
    private Scene scene;

    public void handleButtonClick(GameButton button) {
        button.setOnMouseClicked(mouseEvent -> {
            if(button.isButtonOn()) {
                playerScore++;
                updateScore(score, playerScore);
                System.out.println("Player score is: " +playerScore);
                button.turnButtonOff();
            } else {
                System.out.println("Game over");
                newGameThread.setStopRun(true);

            }

            if(playerScore == 5) {
                lowerInterval();
                newGameThread.setStopRun(true);
                reactToIntervalChange();
            }

        });
    }

    public void handleStartGameClick(Button button) {
        button.setOnAction(actionEvent -> {
                newGameThread = new NewGameThread(redButton, blueButton, yellowButton, greenButton, interval);
                newGameThread.setDaemon(true);
                newGameThread.start();
        });
    }

    public void updateScore(Label score, int playerScore) {
        score.setText("Score: " + playerScore);
    }

    public void lowerInterval() {
        this.interval = interval / 2;
    }

    public void reactToIntervalChange() {
        newGameThread = new NewGameThread(redButton, blueButton, yellowButton, greenButton, interval);
        newGameThread.setDaemon(true);
        newGameThread.start();
    }

    @Override
    public void start(Stage stage) {

        // Player score
        score = new Label("Score: " + playerScore);
        score.setPadding(new Insets(15));
        score.setFont(Font.font("Verdana", 40));

        // New game button
        newGame = new Button("New Game");
        handleStartGameClick(newGame);

        // Red button

        handleButtonClick(redButton);

        // Blue button
        handleButtonClick(blueButton);

        // Yellow button
        handleButtonClick(yellowButton);

        // Green button
        handleButtonClick(greenButton);

        buttonContainer = new HBox();
        buttonContainer.setSpacing(10);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.setMaxSize(400, 200);
        buttonContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                                                    CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.getChildren().addAll(redButton, blueButton, yellowButton, greenButton);

        container = new BorderPane();
        container.setCenter(buttonContainer);
        container.setTop(score);
        container.setBottom(newGame);

        scene = new Scene(container, 800,600);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}