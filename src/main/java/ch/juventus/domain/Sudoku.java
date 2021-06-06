package ch.juventus.domain;

import ch.juventus.importer.SudokuFile;
import java.util.stream.IntStream;

public class Sudoku {

  private static final int EMPTY_CELL_NUMBER = 0;
  private static final int BOARD_START_INDEX = 0;

  private final int boxSize;
  private final int boardSize;
  private final int[][] board;

  public Sudoku() {
    this.boardSize = 9;
    this.board = new int[boardSize][boardSize];
    this.boxSize = 3;
  }

  public Sudoku(SudokuFile sudokuFile) {
    this.boardSize = sudokuFile.getSize();
    this.board = new int[boardSize][boardSize];
    this.boxSize = (int) Math.sqrt(boardSize);

    sudokuFile
        .getSquares()
        .forEach(value -> this.board[value.getY()][value.getX()] = value.getValue());
  }

  public int[][] getBoard() {
    return board;
  }

  public int getBoardSize() {
    return boardSize;
  }

  public void setNumber(int row, int column, int number) {
    this.board[row][column] = number;
  }

  public int getNumber(int row, int column) {
    return this.board[row][column];
  }

  public void setEmptyNumber(int row, int column) {
    this.board[row][column] = EMPTY_CELL_NUMBER;
  }

  public boolean isEmpty(int row, int column) {
    return this.board[row][column] == EMPTY_CELL_NUMBER;
  }

  public boolean isOk(int row, int column, int number) {
    return !isInRow(row, number) && !isInColumn(column, number) && !isInBox(row, column, number);
  }

  public boolean isInRow(int row, int number) {
    return IntStream.range(BOARD_START_INDEX, boardSize).anyMatch(i -> board[row][i] == number);
  }

  public boolean isInColumn(int column, int number) {
    return IntStream.range(BOARD_START_INDEX, boardSize).anyMatch(i -> board[i][column] == number);
  }

  public boolean isInBox(int row, int column, int number) {
    int boxRow = row - row % boxSize;
    int boxColumn = column - column % boxSize;

    for (int i = boxRow; i < boxRow + boxSize; i++) {
      for (int j = boxColumn; j < boxColumn + boxSize; j++) {
        if (board[i][j] == number) {
          return true;
        }
      }
    }

    return false;
  }
}
