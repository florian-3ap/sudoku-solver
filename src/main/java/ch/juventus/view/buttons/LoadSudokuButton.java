package ch.juventus.view.buttons;

import ch.juventus.controller.GameController;
import java.io.File;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadSudokuButton extends Button {

  private final Stage stage;
  private final FileChooser fileChooser;
  private final GameController gameController;

  public LoadSudokuButton(Stage stage, GameController gameController) {
    super("Load Sudoku");
    this.stage = stage;
    this.fileChooser = new FileChooser();
    this.gameController = gameController;

    setOnAction(actionEvent -> getSudoku());
  }

  private void getSudoku() {
    File sudokuFile = getSudokuFile();

    // TODO: do something with the file

    gameController.setUploadedSudoku();
  }

  private File getSudokuFile() {
    fileChooser.setTitle("Please chose a sudoku json file");
    fileChooser
        .getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Sudoku json files", "*.json"));
    return fileChooser.showOpenDialog(stage);
  }
}
