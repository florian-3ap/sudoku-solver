package ch.juventus;

import ch.juventus.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuSolverApplication extends Application {

  private final Logger logger = LoggerFactory.getLogger(SudokuSolverApplication.class);

  @Override
  public void start(Stage stage) {
    logger.info("Startup SudokuSolverApplication...");
    stage.setTitle("Sudoku Solver");
    stage.setScene(new MainView(stage).getScene());
    stage.show();
    logger.info("SudokuSolverApplication is successfully started.");
  }
}
