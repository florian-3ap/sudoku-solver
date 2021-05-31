package ch.juventus.view;

import ch.juventus.domain.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class SudokuBoard extends HBox {

  private Sudoku sudoku;
  private final GridPane gridPane;

  SudokuBoard() {
    gridPane = new GridPane();
    setAlignment(Pos.CENTER);
    gridPane.getStyleClass().add("sudokuPane");

    this.sudoku = new Sudoku();
    showSudoku();

    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(gridPane);
    getChildren().add(stackPane);
  }

  Sudoku getSudoku() {
    return sudoku;
  }

  void loadSudoku(Sudoku sudoku) {
    this.sudoku = sudoku;
    showSudoku();
  }

  void showSudoku() {
    gridPane.getChildren().clear();

    for (int row = 0; row < sudoku.getBoardSize(); row++) {
      for (int column = 0; column < sudoku.getBoardSize(); column++) {
        TextField textField = createTextField(sudoku.getNumber(row, column));
        gridPane.add(textField, column, row);
      }
    }
  }

  private TextField createTextField(int value) {
    TextField textField = new TextField(String.valueOf(value));
    textField.setEditable(false);
    textField.getStyleClass().add("textFields");
    return textField;
  }
}
