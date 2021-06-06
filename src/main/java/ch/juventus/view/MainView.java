package ch.juventus.view;

import ch.juventus.controller.GameController;
import ch.juventus.controller.SudokuController;
import ch.juventus.domain.Sudoku;
import ch.juventus.exception.SolvingException;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainView {

  private final BorderPane root;
  private final ButtonPane buttons;
  private final SudokuBoard sudokuBoard;
  private final FileChooser fileChooser;
  private final GameController sudokuController;

  public MainView() {
    this.root = new BorderPane();
    this.buttons = new ButtonPane();
    this.sudokuBoard = new SudokuBoard();
    this.fileChooser = new FileChooser();
    this.sudokuController = new SudokuController();
  }

  public Scene getScene() {
    root.setTop(buttons);
    root.setCenter(sudokuBoard);
    buttons.addButton(createLoadSudokuButton());
    buttons.addButton(createSolveButton());
    buttons.addButton(createResetButton());

    Scene scene = new Scene(root, 500, 500);
    scene
        .getStylesheets()
        .add(getClass().getClassLoader().getResource("style.css").toExternalForm());
    return scene;
  }

  private Button createLoadSudokuButton() {
    Button button = new Button("Load Sudoku");
    button.setOnAction(
        actionEvent -> {
          try {
            File sudokuFile = getSudokuFile();
            Sudoku sudoku = sudokuController.loadSudoku(sudokuFile);
            if (sudoku != null) {
              sudokuBoard.loadSudoku(sudoku);
            }
          } catch (Exception exception) {
            AlertDialog.error(
                "Loading Error", "Sudoku file could not be loaded.", exception.getMessage());
          }
        });
    return button;
  }

  private Button createSolveButton() {
    Button button = new Button("Solve");
    button.setOnAction(
        actionEvent -> {
          try {
            sudokuController.solveSudoku(sudokuBoard.getSudoku());
            sudokuBoard.update();
          } catch (SolvingException e) {
            AlertDialog.error("Solving Error", "The Sudoku could not be solved!", e.getMessage());
          }
        });
    return button;
  }

  private Button createResetButton() {
    Button button = new Button("Reset");
    button.setOnAction(actionEvent -> sudokuBoard.reset());
    return button;
  }

  private File getSudokuFile() {
    fileChooser.setTitle("Please chose a sudoku json file");
    fileChooser
        .getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Sudoku json files", "*.json"));
    return fileChooser.showOpenDialog(new Stage());
  }
}
