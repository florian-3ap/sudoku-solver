package ch.juventus.view.buttons;

import ch.juventus.controller.GameController;
import javafx.scene.control.Button;

public class SolveButton extends Button {

  private final GameController gameController;

  public SolveButton(GameController gameController) {
    super("Solve");
    this.gameController = gameController;

    setOnAction(actionEvent -> solveSudoku());
  }

  private void solveSudoku() {
    gameController.solveSudoku();
  }
}
