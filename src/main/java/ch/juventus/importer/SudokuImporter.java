package ch.juventus.importer;

import ch.juventus.domain.Sudoku;
import ch.juventus.exception.ImportException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileReader;

public class SudokuImporter {

  private final Gson gson;

  public SudokuImporter() {
    this.gson = new GsonBuilder().create();
  }

  public Sudoku importPuzzle(File file) throws ImportException {
    try {
      JsonReader jsonReader = new JsonReader(new FileReader(file));

      SudokuFile sudokuFile = gson.fromJson(jsonReader, SudokuFile.class);

      return new Sudoku(sudokuFile);
    } catch (Exception e) {
      throw new ImportException("Sudoku could not be imported!", e);
    }
  }
}
