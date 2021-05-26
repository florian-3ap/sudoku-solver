package ch.juventus.controller;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import ch.juventus.importer.PuzzleImporter;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.solver.PuzzleSolver;
import ch.juventus.solver.SudokuSolver;
import ch.juventus.view.AlertDialog;
import ch.juventus.view.SudokuBoard;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameController {

  private final Logger logger = LoggerFactory.getLogger(GameController.class);
  private final Stage stage;
  private final FileChooser fileChooser;
  private final SudokuBoard sudokuBoard;
  private final PuzzleSolver<Sudoku> puzzleSolver;
  private final PuzzleImporter<Sudoku> puzzleImporter;

  private Sudoku sudoku;

  public GameController(Stage stage, SudokuBoard sudokuBoard) {
    this.fileChooser = new FileChooser();
    this.sudokuBoard = sudokuBoard;
    this.puzzleSolver = new SudokuSolver();
    this.puzzleImporter = new SudokuImporter();
    this.stage = stage;
  }

  public void loadSudoku() {
    final File sudokuFile = getSudokuFile();

    if (sudokuFile == null) {
      logger.warn("File selection was canceled by the user.");
      return;
    }

    try {
      this.sudoku = puzzleImporter.importPuzzle(sudokuFile);
      sudokuBoard.showSudoku(sudoku);
    } catch (ImportException importException) {
      logger.error("Sudoku file contains invalid size!", importException);
      AlertDialog.error(
          "Loading Error", "Sudoku file could not be loaded.", importException.getMessage());
    } catch (Exception exception) {
      logger.error("Sudoku file could not be loaded.", exception);
      AlertDialog.error(
          "Loading Error", "Sudoku file could not be loaded.", exception.getMessage());
    }
  }

  public void solveSudoku() {
    if (sudoku == null) {
      logger.error("No Sudoku was loaded.");
      AlertDialog.error("Solving Error", "Please upload a file first!", "");
      return;
    }

    boolean solved = puzzleSolver.solve(sudoku);

    if (solved) {
      sudokuBoard.showSudoku(sudoku);
    } else {
      logger.error("The Sudoku could not be solved.");
      AlertDialog.error("Solving Error", "The Sudoku could not be solved.", "");
    }
  }

  private File getSudokuFile() {
    fileChooser.setTitle("Please chose a sudoku json file");
    fileChooser
        .getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Sudoku json files", "*.json"));
    return fileChooser.showOpenDialog(stage);
  }
}
