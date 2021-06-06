package ch.juventus.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.juventus.importer.SudokuFile;
import ch.juventus.importer.SudokuSquare;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class SudokuTest {

  @Test
  void testSetNumber() {
    Sudoku sudoku = new Sudoku();
    sudoku.setNumber(4, 4, 8);

    int[][] board = sudoku.getBoard();

    assertEquals(8, board[4][4]);
  }

  @Test
  void testGetNumber() {
    Sudoku sudoku = new Sudoku();
    sudoku.setNumber(3, 7, 3);

    assertEquals(3, sudoku.getNumber(3, 7));
  }

  @Test
  void testSetEmptyNumber() {
    SudokuFile sudokuFile = new SudokuFile(4, Collections.singletonList(new SudokuSquare(2, 3, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertEquals(3, sudoku.getNumber(3, 2));
    sudoku.setEmptyNumber(3, 2);
    assertEquals(0, sudoku.getNumber(3, 2));
  }

  @Test
  void testIsEmpty() {
    SudokuFile sudokuFile =
        new SudokuFile(
            2,
            List.of(
                new SudokuSquare(0, 0, 3),
                new SudokuSquare(0, 1, 3),
                new SudokuSquare(1, 0, 0),
                new SudokuSquare(1, 1, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertEquals(0, sudoku.getNumber(0, 1));
    assertTrue(sudoku.isEmpty(0, 1));
  }

  @Test
  void testisOkShouldReturnTrue() {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(1, 0, 0),
                new SudokuSquare(2, 0, 2),
                new SudokuSquare(3, 0, 1),
                new SudokuSquare(0, 1, 2),
                new SudokuSquare(0, 2, 1),
                new SudokuSquare(0, 3, 4),
                new SudokuSquare(1, 0, 4),
                new SudokuSquare(1, 1, 1)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertTrue(sudoku.isOk(0, 0, 3));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2, 4})
  void testisOkShouldReturnFalse(int number) {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(1, 0, 0),
                new SudokuSquare(2, 0, 2),
                new SudokuSquare(3, 0, 1),
                new SudokuSquare(0, 1, 2),
                new SudokuSquare(0, 2, 1),
                new SudokuSquare(0, 3, 4),
                new SudokuSquare(1, 0, 4),
                new SudokuSquare(1, 1, 1)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertFalse(sudoku.isOk(0, 0, number));
  }

  @Test
  void testIsInRowShouldReturnTrue() {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(1, 0, 2),
                new SudokuSquare(2, 0, 0),
                new SudokuSquare(3, 0, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertTrue(sudoku.isInRow(0, 3));
  }

  @Test
  void testIsInRowShouldReturnFalse() {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(1, 0, 2),
                new SudokuSquare(2, 0, 0),
                new SudokuSquare(3, 0, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertFalse(sudoku.isInRow(0, 4));
  }

  @Test
  void testIsInColumnShouldReturnTrue() {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(0, 1, 2),
                new SudokuSquare(0, 2, 0),
                new SudokuSquare(0, 3, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertTrue(sudoku.isInColumn(0, 3));
  }

  @Test
  void testIsInColumnShouldReturnFalse() {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(0, 1, 2),
                new SudokuSquare(0, 2, 0),
                new SudokuSquare(0, 3, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertFalse(sudoku.isInColumn(0, 4));
  }

  @ParameterizedTest
  @CsvSource({"0,0", "0,1", "1,0", "1,1"})
  void testIsInBoxShouldReturnTrue(int row, int column) {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(1, 0, 2),
                new SudokuSquare(0, 1, 0),
                new SudokuSquare(1, 1, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertTrue(sudoku.isInBox(row, column, 3));
  }

  @ParameterizedTest
  @CsvSource({"0,0", "0,1", "1,0", "1,1"})
  void testIsInBoxShouldReturnFalse(int row, int column) {
    SudokuFile sudokuFile =
        new SudokuFile(
            4,
            List.of(
                new SudokuSquare(0, 0, 1),
                new SudokuSquare(1, 0, 2),
                new SudokuSquare(0, 1, 0),
                new SudokuSquare(1, 1, 3)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    assertFalse(sudoku.isInBox(row, column, 4));
  }
}
