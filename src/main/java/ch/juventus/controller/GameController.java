package ch.juventus.controller;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import ch.juventus.exception.SolvingException;
import java.io.File;
import java.io.FileNotFoundException;

public interface GameController {

  Sudoku loadSudoku(File sudokuFile) throws ImportException, FileNotFoundException;

  void solveSudoku(Sudoku sudoku) throws SolvingException;
}
