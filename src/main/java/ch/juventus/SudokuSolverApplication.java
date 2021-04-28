package ch.juventus;

import ch.juventus.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class SudokuSolverApplication extends Application {

  @Override
  public void start(Stage stage) {
    stage.setTitle("Sudoku Solver");
    stage.setScene(new MainView(stage).getScene());
    stage.show();
  }
}
