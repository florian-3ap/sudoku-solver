package ch.juventus.importer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import java.io.File;
import org.junit.jupiter.api.Test;

class SudokuImporterTest {

  SudokuImporter sudokuImporter = new SudokuImporter();

  @Test
  void testImport4x4PuzzleShouldSucceed() throws ImportException {
    File inputFile =
        new File(getClass().getClassLoader().getResource("sudoku/4x4_sudoku.json").getFile());

    Sudoku sudoku = sudokuImporter.importPuzzle(inputFile);
    assertNotNull(sudoku);
    assertEquals(4, sudoku.getBoardSize());
    assertEquals(0, sudoku.getNumber(0, 0));
    assertEquals(0, sudoku.getNumber(0, 1));
    assertEquals(0, sudoku.getNumber(0, 2));
    assertEquals(0, sudoku.getNumber(0, 3));
    assertEquals(0, sudoku.getNumber(1, 0));
    assertEquals(3, sudoku.getNumber(1, 1));
    assertEquals(4, sudoku.getNumber(1, 2));
    assertEquals(0, sudoku.getNumber(1, 3));
    assertEquals(0, sudoku.getNumber(2, 0));
    assertEquals(0, sudoku.getNumber(2, 1));
    assertEquals(0, sudoku.getNumber(2, 2));
    assertEquals(0, sudoku.getNumber(2, 3));
    assertEquals(4, sudoku.getNumber(3, 0));
    assertEquals(0, sudoku.getNumber(3, 1));
    assertEquals(0, sudoku.getNumber(3, 2));
    assertEquals(0, sudoku.getNumber(3, 3));
  }

  @Test
  void testImport4x4PuzzleShouldThrowException() {
    File inputFile =
        new File(
            getClass().getClassLoader().getResource("sudoku/4x4_sudoku_invalid.json").getFile());

    Throwable exception =
        assertThrows(ImportException.class, () -> sudokuImporter.importPuzzle(inputFile));
    assertEquals("Sudoku could not be imported!", exception.getMessage());
  }

  @Test
  void testImport9x9PuzzleShouldSucceed() throws ImportException {
    File inputFile =
        new File(getClass().getClassLoader().getResource("sudoku/9x9_sudoku.json").getFile());

    Sudoku sudoku = sudokuImporter.importPuzzle(inputFile);
    assertNotNull(sudoku);
    assertEquals(9, sudoku.getBoardSize());
    assertEquals(2, sudoku.getNumber(3, 0));
    assertEquals(3, sudoku.getNumber(0, 1));
    assertEquals(7, sudoku.getNumber(1, 2));
    assertEquals(8, sudoku.getNumber(3, 3));
  }
}
