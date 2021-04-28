package ch.juventus.view;

import ch.juventus.controller.GameController;
import ch.juventus.view.buttons.LoadSudokuButton;
import ch.juventus.view.buttons.SolveButton;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView {

  private final Stage stage;
  private final BorderPane root;
  private final ButtonPane buttons;
  private final GameController gameController;

  public MainView(Stage stage) {
    this.stage = stage;
    this.root = new BorderPane();
    this.buttons = new ButtonPane();
    this.gameController = new GameController();
  }

  public Scene getScene() {
    root.setTop(buttons);
    buttons.addButton(new LoadSudokuButton(stage, gameController));
    buttons.addButton(new SolveButton(gameController));
    return new Scene(root, 450, 450);
  }
}
