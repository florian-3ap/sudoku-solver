package ch.juventus.importer;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class SudokuImporter {

  private static final List<Integer> VALID_SUDOKU_SIZE = Arrays.asList(2, 3, 4, 9);

  private final Gson gson;

  public SudokuImporter() {
    this.gson = new GsonBuilder().create();
  }

  public Sudoku importPuzzle(File file) throws FileNotFoundException, ImportException {
    JsonReader jsonReader = new JsonReader(new FileReader(file));

    SudokuFile sudokuFile = gson.fromJson(jsonReader, SudokuFile.class);

    if (!VALID_SUDOKU_SIZE.contains(sudokuFile.getSize())) {
      throw new ImportException(
          String.format("%d is not a valid sudoku size", sudokuFile.getSize()));
    }

    return new Sudoku(sudokuFile);
  }
}
