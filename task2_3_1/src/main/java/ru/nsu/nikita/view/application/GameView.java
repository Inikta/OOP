package ru.nsu.nikita.view.application;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.nsu.nikita.backlogic.snake.Direction;
import ru.nsu.nikita.view.field_view.FieldView;
import ru.nsu.nikita.view.screens_controllers.LosingScreenViewController;
import ru.nsu.nikita.view.screens_controllers.WinningScreenViewController;
import ru.nsu.nikita.view.snake_view.SnakeHeadView;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static ru.nsu.nikita.backlogic.snake.Direction.*;

public class GameView {

    @FXML
    private Pane root;

    @FXML
    private Pane fieldPane;

    @FXML
    private Label currentScoreLabel;

    @FXML
    private Label goalScoreLabel;

    @FXML
    private Button restartButton;

    private SnakeHeadView snakeHeadView;
    private FieldView fieldView;

    private GameData gameData;

    private final Set<KeyCode> activeKeys = new HashSet<>();
    private Direction currentDirection = NONE;
    private Direction lastDirection = NONE;
    private AnimationTimer animationTimer;

    public BooleanProperty restartProperty;
    public BooleanProperty difficultyIncreaseProperty;

    public SimpleIntegerProperty currentScore;
    public SimpleIntegerProperty goalScore;

    public SimpleBooleanProperty winRestartProperty;
    public SimpleBooleanProperty loseRestartProperty;
    public SimpleBooleanProperty winQuitProperty;
    public SimpleBooleanProperty loseQuitProperty;

    private Stage loseWindow;
    private Stage winWindow;

    private Integer difficulty;
    protected int delay;

    @FXML
    public void onRestartPressed() {
        animationTimer.stop();
        restartProperty.setValue(true);
    }

    /**
     * Initialization of controller.
     * Also sets listeners for properties of restart and quit buttons of winning and losing screens.
     * @param gameData settings for the game
     * @throws IOException throws, if an error occurs during loading FXML markup
     */
    public void manualInitialization(GameData gameData) throws IOException {

        winRestartProperty = new SimpleBooleanProperty(false);
        loseRestartProperty = new SimpleBooleanProperty(false);
        winQuitProperty = new SimpleBooleanProperty(false);
        loseQuitProperty = new SimpleBooleanProperty(false);

        this.gameData = gameData;

        difficulty = gameData.getInitDifficulty();
        goalScoreLabel.setText(gameData.getGoalScore().toString());
        restartProperty = new SimpleBooleanProperty(false);
        goalScore = new SimpleIntegerProperty(gameData.getGoalScore());
        currentScore = new SimpleIntegerProperty(gameData.getCurrentScore());
        difficultyIncreaseProperty = new SimpleBooleanProperty(false);

        snakeHeadView = new SnakeHeadView(
                gameData.getSnakeHead(),
                gameData.getSnakeHeadViewSettingsContainer(),
                gameData.getSnakeBodyViewSettingsContainer());
        fieldView = new FieldView(gameData.getFieldViewSettingsContainer());

        fieldPane.getChildren().add(fieldView);
        fieldPane.getChildren().add(snakeHeadView);


        initializeEventHandler();

        delay = calculateInitDifficulty();
        initializeGameLoop();

        FXMLLoader loseLoader = new FXMLLoader(getClass().getResource("LosingScreenView.fxml"));
        loseWindow = new Stage();
        Scene loseScene = new Scene(loseLoader.load());
        loseWindow.setScene(loseScene);
        LosingScreenViewController loseController = loseLoader.getController();
        loseController.manualInit(currentScore, gameData.getGoalScore());

        loseController.restartProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                loseWindow.close();
                onRestartPressed();
            }
        });

        loseController.quitProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                loseWindow.close();
                loseQuitProperty.setValue(true);
            }
        });


        FXMLLoader winLoader = new FXMLLoader(getClass().getResource("WinningScreenView.fxml"));
        winWindow = new Stage();
        Scene winScene = new Scene(winLoader.load());
        winWindow.setScene(winScene);
        WinningScreenViewController winController = winLoader.getController();
        winController.manualInit();

        winController.restartProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                winWindow.close();
                onRestartPressed();
            }
        });

        winController.quitProperty.addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                winWindow.close();
                winQuitProperty.setValue(true);
            }
        });

        animationTimer.start();
    }

    /**
     * Update game view with new data.
     * @param now the timestamp of the current frame given in nanoseconds.
     * @param spawnRate number responsible for rate of food spawn
     */
    public void update(long now, int spawnRate) {
        inputUpdate();
        fieldView.update(snakeHeadView.getSnakeHead(), now, spawnRate);
        snakeHeadView.update();
        loadSnake();

        if (difficultyIncreaseProperty.get() & currentScore.get() < snakeHeadView.getSnakeHead().getLength()) {
            delay -= 2;
        }

        updateCurrentScore();

        if (!snakeHeadView.living.get()) {
            loseWindow.show();
            animationTimer.stop();
        }
    }

    /**
     * Load new body parts of snake.
     */
    private void loadSnake() {
        snakeHeadView.getTail().forEach(t -> {
            if (!fieldPane.getChildren().contains(t)) {
                fieldPane.getChildren().add(t);
            }
        });
    }

    /**
     * Update current score.
     */
    private void updateCurrentScore() {
        if (currentScore.get() + 1 == snakeHeadView.getSnakeHead().getLength()) {
            currentScore.set(currentScore.get() + 1);
        } else if (currentScore.get() > snakeHeadView.getSnakeHead().getLength()) {
            currentScore.set(currentScore.get());
        }

        currentScoreLabel.setText(currentScore.getValue().toString());


        if (currentScore.get() == goalScore.get()) {
            winWindow.show();
            animationTimer.stop();
        }
    }

    /**
     * Calculate initial difficulty.
     * @return delay between game updates
     */
    private int calculateInitDifficulty() {
        int base = 200;
        int initSub = difficulty * 20;
        if (initSub > 180) {
            initSub = 185;
        }

        return base - initSub;
    }

    /**
     * Initialize animation timer with update(now, spawnRate) method and set delay between updates.
     */
    private void initializeGameLoop() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(now, 2000);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * Move snake head according to current direction.
     */
    private void inputUpdate() {
        switch (currentDirection) {
            case LEFT -> snakeHeadView.getSnakeHead().move(LEFT);
            case RIGHT -> snakeHeadView.getSnakeHead().move(RIGHT);
            case UP -> snakeHeadView.getSnakeHead().move(UP);
            case DOWN -> snakeHeadView.getSnakeHead().move(DOWN);
            default -> snakeHeadView.getSnakeHead().move(lastDirection);
        }

    }

    /**
     * Initialize event handler for buttons pressing.
     */
    private void initializeEventHandler() {
        EventHandler<KeyEvent> inputHandler = event -> {
            if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
                activeKeys.add(event.getCode());
                if (activeKeys.contains(KeyCode.A)) {
                    currentDirection = LEFT;
                    lastDirection = LEFT;
                } else if (activeKeys.contains(KeyCode.D)) {
                    currentDirection = RIGHT;
                    lastDirection = RIGHT;
                } else if (activeKeys.contains(KeyCode.W)) {
                    currentDirection = UP;
                    lastDirection = UP;
                } else if (activeKeys.contains(KeyCode.S)) {
                    currentDirection = DOWN;
                    lastDirection = DOWN;
                } else {
                    currentDirection = lastDirection;
                }
            } else if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
                activeKeys.remove(event.getCode());
            } else {
                currentDirection = lastDirection;
            }
        };

        root.getScene().setOnKeyPressed(inputHandler);
        root.getScene().setOnKeyReleased(inputHandler);
    }


    public Pane getRoot() {
        return root;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public Pane getFieldPane() {
        return fieldPane;
    }

    public void setFieldPane(Pane fieldPane) {
        this.fieldPane = fieldPane;
    }

    public Label getCurrentScoreLabel() {
        return currentScoreLabel;
    }

    public void setCurrentScoreLabel(Label currentScoreLabel) {
        this.currentScoreLabel = currentScoreLabel;
    }

    public Label getGoalScoreLabel() {
        return goalScoreLabel;
    }

    public void setGoalScoreLabel(Label goalScoreLabel) {
        this.goalScoreLabel = goalScoreLabel;
    }

    public Button getRestartButton() {
        return restartButton;
    }

    public void setRestartButton(Button restartButton) {
        this.restartButton = restartButton;
    }

    public SnakeHeadView getSnakeHeadView() {
        return snakeHeadView;
    }

    public void setSnakeHeadView(SnakeHeadView snakeHeadView) {
        this.snakeHeadView = snakeHeadView;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }
}
