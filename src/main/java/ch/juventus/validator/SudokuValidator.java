package ch.juventus.validator;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ValidationException;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuValidator {

  private final Logger logger = LoggerFactory.getLogger(SudokuValidator.class);
  private static final List<Integer> VALID_SUDOKU_SIZE = Arrays.asList(4, 9);

  public void validate(Sudoku sudoku) throws ValidationException {
    this.validateSize(sudoku);
    this.validateNumbers(sudoku);
  }

  private void validateSize(Sudoku sudoku) throws ValidationException {
    if (!VALID_SUDOKU_SIZE.contains(sudoku.getBoardSize())) {
      logger.error("{} is not a valid sudoku size!", sudoku.getBoardSize());
      throw new ValidationException(
          String.format("%d is not a valid sudoku size!", sudoku.getBoardSize()));
    }
  }

  private void validateNumbers(Sudoku sudoku) throws ValidationException {
    for (int row = 0; row < sudoku.getBoardSize(); row++) {
      for (int column = 0; column < sudoku.getBoardSize(); column++) {
        if (sudoku.getNumber(row, column) > sudoku.getBoardSize()) {
          logger.error("Invalid number in sudoku!");
          throw new ValidationException("Invalid number in sudoku!");
        }
      }
    }
  }
}
