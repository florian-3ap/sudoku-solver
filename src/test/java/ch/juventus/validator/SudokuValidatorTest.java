package ch.juventus.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ValidationException;
import ch.juventus.importer.SudokuFile;
import ch.juventus.importer.SudokuSquare;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class SudokuValidatorTest {

  SudokuValidator sudokuValidator = new SudokuValidator();

  @Test
  void testValidateInvalidSizeShouldThrowException() {
    SudokuFile sudokuFile = new SudokuFile(8, Collections.emptyList());

    Throwable exception =
        assertThrows(
            ValidationException.class, () -> sudokuValidator.validate(new Sudoku(sudokuFile)));
    assertEquals("8 is not a valid sudoku size!", exception.getMessage());
  }

  @Test
  void testValidateInvalidNumberShouldThrowException() {
    SudokuFile sudokuFile =
        new SudokuFile(9, Collections.singletonList(new SudokuSquare(0, 0, 10)));

    Throwable exception =
        assertThrows(
            ValidationException.class, () -> sudokuValidator.validate(new Sudoku(sudokuFile)));
    assertEquals("Invalid number in sudoku!", exception.getMessage());
  }
}
