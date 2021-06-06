package ch.juventus.solver;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.juventus.domain.Sudoku;
import ch.juventus.importer.SudokuFile;
import ch.juventus.importer.SudokuSquare;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class SudokuSolverTest {

  SudokuSolver sudokuSolver = new SudokuSolver();

  @Test
  void testSolvableSudokuShouldSucceed() {
    int[][] solution =
        new int[][] {
          {4, 1, 2, 3},
          {2, 3, 1, 4},
          {1, 4, 3, 2},
          {3, 2, 4, 1},
        };

    SudokuFile sudokuFile = new SudokuFile(4, Collections.singletonList(new SudokuSquare(0, 0, 4)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    final boolean solved = sudokuSolver.solve(sudoku);

    assertTrue(solved);
    assertArrayEquals(solution, sudoku.getBoard());
  }

  @Test
  void testUnsolvableSudokuShouldReturnFalse() {
    SudokuFile sudokuFile =
        new SudokuFile(4, List.of(new SudokuSquare(0, 0, 4), new SudokuSquare(1, 0, 4)));
    Sudoku sudoku = new Sudoku(sudokuFile);

    final boolean solved = sudokuSolver.solve(sudoku);

    assertFalse(solved);
  }
}
