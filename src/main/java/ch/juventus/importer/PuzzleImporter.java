package ch.juventus.importer;

import ch.juventus.domain.Puzzle;
import ch.juventus.exception.ImportException;
import java.io.File;
import java.io.FileNotFoundException;

public interface PuzzleImporter<T extends Puzzle> {

  T importPuzzle(File file) throws FileNotFoundException, ImportException;
}
