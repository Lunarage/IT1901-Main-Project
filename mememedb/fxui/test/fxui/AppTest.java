// package it1901.mememedb.fxui;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.scene.control.Button;
// import javafx.scene.layout.VBox;
// import javafx.scene.layout.HBox;
// import javafx.scene.Node;

// import javafx.collections.ObservableList;

// import it1901.mememedb.fxui.AppController;

// public class AppTest extends ApplicationTest {
//   private Parent parent;
//   private AppController controller;

//   @Override
//   public void start(final Stage stage) throws Exception {
//     final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
//     parent = fxmlLoader.load();
//     controller = fxmlLoader.getController();
//     stage.setScene(new Scene(parent));
//     stage.show();
//   }

//   @Test
//   public void testButtons() {
//     final Button logoutButton = (Button) parent.lookup("#logout");
//     final Button addContent = (Button) parent.lookup("#addContent");
//     clickOn(addContent);
//     clickOn(logoutButton);
//   }

// }