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

        // search an empty cell
        if (sudoku.isEmpty(row, column)) {

          // we try possible numbers
          for (int number = 1; number <= sudoku.getBoardSize(); number++) {
            if (sudoku.isOk(row, column, number)) {

              // number ok. it respects sudoku constraints
              sudoku.setNumber(row, column, number);

              if (solve(sudoku)) {
                return true;
              } else {
                // if not a solution, we empty the cell and we continue
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
