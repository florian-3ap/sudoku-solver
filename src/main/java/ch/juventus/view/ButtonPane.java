package ch.juventus.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonPane extends HBox {

  ButtonPane() {
    super();
    setAlignment(Pos.CENTER);
    getStyleClass().add("buttonPane");
  }

  void addButton(Button button) {
    getChildren().add(button);
    button.getStyleClass().add("buttons");
  }
}
