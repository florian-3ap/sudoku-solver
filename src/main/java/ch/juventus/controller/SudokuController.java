package ch.juventus.controller;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import ch.juventus.exception.SolvingException;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.solver.SudokuSolver;
import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuController implements GameController {

  private final Logger logger = LoggerFactory.getLogger(SudokuController.class);
  private final SudokuSolver puzzleSolver;
  private final SudokuImporter puzzleImporter;

  public SudokuController() {
    this.puzzleSolver = new SudokuSolver();
    this.puzzleImporter = new SudokuImporter();
  }

  @Override
  public Sudoku loadSudoku(File sudokuFile) throws ImportException, FileNotFoundException {
    if (sudokuFile == null) {
      logger.warn("File selection was canceled by the user.");
      return null;
    }

    return puzzleImporter.importPuzzle(sudokuFile);
  }

  @Override
  public void solveSudoku(Sudoku sudoku) throws SolvingException {
    if (sudoku == null) {
      logger.error("No Sudoku was loaded.");
      throw new SolvingException("No sudoku available to solve.");
    }

    boolean solved = puzzleSolver.solve(sudoku);

    if (!solved) {
      logger.error("The sudoku can't be solved!");
      throw new SolvingException("The sudoku can't be solved!");
    }
  }
}
