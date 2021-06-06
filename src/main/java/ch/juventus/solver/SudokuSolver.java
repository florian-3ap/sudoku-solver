package ch.juventus.solver;

import ch.juventus.domain.Sudoku;

public class SudokuSolver {

  /**
   * Solves a sudoku using a recursive BackTracking algorithm.
   *
   * @param sudoku sudoku to be solved
   * @return boolean if the sudoku is solved
   */
  public boolean solve(Sudoku sudoku) {
    for (int row = 0; row < sudoku.getBoardSize(); row++) {
      for (int column = 0; column < sudoku.getBoardSize(); column++) {

        if (sudoku.isEmpty(row, column)) {
          for (int number = 1; number <= sudoku.getBoardSize(); number++) {
            if (sudoku.isOk(row, column, number)) {
              sudoku.setNumber(row, column, number);

              if (solve(sudoku)) {
                return true;
              } else {
                sudoku.setEmptyNumber(row, column);
              }
            }
          }

          return false;
        }
      }
    }

    return true;
  }
}
