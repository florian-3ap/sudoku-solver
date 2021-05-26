package ch.juventus.view;

import ch.juventus.controller.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView {

  private final BorderPane root;
  private final ButtonPane buttons;
  private final SudokuBoard sudokuBoard;
  private final GameController gameController;

  public MainView(Stage stage) {
    this.root = new BorderPane();
    this.buttons = new ButtonPane();
    this.sudokuBoard = new SudokuBoard();
    this.gameController = new GameController(stage, sudokuBoard);
  }

  public Scene getScene() {
    root.setTop(buttons);
    root.setCenter(sudokuBoard);
    buttons.addButton(createLoadSudokuButton());
    buttons.addButton(createSolveButton());

    Scene scene = new Scene(root, 500, 500);
    scene
        .getStylesheets()
        .add(getClass().getClassLoader().getResource("style.css").toExternalForm());
    return scene;
  }

  private Button createLoadSudokuButton() {
    Button button = new Button("Load Sudoku");
    button.setOnAction(actionEvent -> gameController.loadSudoku());
    return button;
  }

  private Button createSolveButton() {
    Button button = new Button("Solve");
    button.setOnAction(actionEvent -> gameController.solveSudoku());
    return button;
  }
}
