package ch.juventus.controller;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import ch.juventus.exception.SolvingException;
import ch.juventus.exception.ValidationException;
import java.io.File;

public interface GameController {

  Sudoku loadSudoku(File sudokuFile) throws ImportException, ValidationException;

  void solveSudoku(Sudoku sudoku) throws SolvingException;
}
