package ch.juventus.exception;

public class ImportException extends Exception {

  public ImportException(String message) {
    super(message);
  }

  public ImportException(String message, Throwable cause) {
    super(message, cause);
  }
}
