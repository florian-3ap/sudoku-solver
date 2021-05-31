package ch.juventus.view;

import javafx.scene.control.Alert;

public class AlertDialog {

  private AlertDialog() {}

  static void error(String title, String header, String content) {
    AlertDialog.create(Alert.AlertType.ERROR, title, header, content);
  }

  static void create(Alert.AlertType type, String title, String header, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    alert.setHeaderText(header);
    alert.showAndWait();
  }
}
