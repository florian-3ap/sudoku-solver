package ch.juventus.importer;

import java.util.List;

public class SudokuFile {
  private int size;
  private List<SudokuSquare> squares;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public List<SudokuSquare> getSquares() {
    return squares;
  }

  public void setSquares(List<SudokuSquare> squares) {
    this.squares = squares;
  }

  @Override
  public String toString() {
    return "SudokuFile{" + "size=" + size + ", squares=" + squares + '}';
  }
}
