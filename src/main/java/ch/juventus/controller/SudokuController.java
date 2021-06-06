package ch.juventus.controller;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import ch.juventus.exception.SolvingException;
import ch.juventus.exception.ValidationException;
import ch.juventus.importer.SudokuImporter;
import ch.juventus.solver.SudokuSolver;
import ch.juventus.validator.SudokuValidator;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuController implements GameController {

  private final Logger logger = LoggerFactory.getLogger(SudokuController.class);
  private final SudokuSolver sudokuSolver;
  private final SudokuImporter sudokuImporter;
  private final SudokuValidator sudokuValidator;

  public SudokuController() {
    this.sudokuSolver = new SudokuSolver();
    this.sudokuImporter = new SudokuImporter();
    this.sudokuValidator = new SudokuValidator();
  }

  @Override
  public Sudoku loadSudoku(File sudokuFile) throws ImportException, ValidationException {
    if (sudokuFile == null) {
      logger.warn("File selection was canceled by the user.");
      return null;
    }

    Sudoku sudoku = sudokuImporter.importPuzzle(sudokuFile);
    sudokuValidator.validate(sudoku);

    return sudoku;
  }

  @Override
  public void solveSudoku(Sudoku sudoku) throws SolvingException {
    if (sudoku == null) {
      logger.error("No Sudoku was loaded.");
      throw new SolvingException("No sudoku available to solve.");
    }

    boolean solved = sudokuSolver.solve(sudoku);

    if (!solved) {
      logger.error("The sudoku can't be solved!");
      throw new SolvingException("The sudoku can't be solved!");
    }
  }
}
