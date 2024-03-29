package fxui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import core.databases.LocalDatabase;

public class AppTest extends ApplicationTest {

  private LocalDatabase emptyDatabase = new LocalDatabase();
  private Parent parent;
  private AppController controller;
  private BrowserController browserController;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("App.fxml"));
    parent = fxmlLoader.load();
    controller = fxmlLoader.getController();
    controller.setDatabase(this.emptyDatabase);
    controller.handleLogOut();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @Test 
  public void switchPanesTest(){
    final Button registerButton = (Button) parent.lookup("#registerButton");
    final AnchorPane loginAnchorPane = (AnchorPane) parent.lookup("#loginAnchorPane");
    final AnchorPane registerAnchorPane = (AnchorPane) parent.lookup("#registerAnchorPane");
    clickOn(registerButton);
    assertFalse(loginAnchorPane.isVisible());
    assertTrue(registerAnchorPane.isVisible());
  }

  @Test
  public void appTest(){
    /* Test register */
    final Button registerButton = (Button) parent.lookup("#registerButton");
    clickOn(registerButton);

    final TextField nameTextField = (TextField) parent.lookup("#nameTextField");
    final TextField emailTextField = (TextField) parent.lookup("#emailTextField");
    final TextField nicknameTextField = (TextField) parent.lookup("#nicknameTextField");
    final PasswordField passwordTextField = (PasswordField) parent.lookup("#passwordTextField");

    final Button createUserButton = (Button) parent.lookup("#createUserButton");

    clickOn(nameTextField).write("Heststein Beststein");
    clickOn(emailTextField).write("heststein@ntnu.no");
    clickOn(nicknameTextField).write("HestStein");
    clickOn(passwordTextField).write("strawberryjam2");

    clickOn(createUserButton);
    sleep(1000);

    /*Test logout */ 
    
    final Button logoutButton = (Button) parent.lookup("#logoutButton");
    clickOn(logoutButton);
  
    /*Test login */

    final Button loginButton = (Button) parent.lookup("#loginButton");
    final TextField loginNickname = (TextField) parent.lookup("#loginNickname");
    final PasswordField loginPasswordText = (PasswordField) parent.lookup("#loginPasswordText");
    clickOn(loginNickname).write("HestStein");
    clickOn(loginPasswordText).write("strawberryjam2");
    clickOn(loginButton);

    /*Test add post */
    
    File inputFile = new File(getClass().getClassLoader().getResource("pangolin.jpg").getPath());
    System.out.println(inputFile.getAbsolutePath());
    browserController = ((BrowserController) controller.getChild());
    browserController.setSelectedImage(inputFile);
    clickOn("#inputTextField");
    write("This is a very nice animal");
    clickOn("#addContent");
    sleep(3000);

    /* Test comment */
    final Button viewPostButton  = (Button) parent.lookup("#viewPostButton");
    clickOn(viewPostButton);
    Parent parent2 = Window.getWindows().get(1).getScene().getRoot();
    final TextField commentInput = (TextField) parent2.lookup("#commentInput");
    final Button commentButton = (Button) parent2.lookup("#commentButton");
    clickOn(commentInput).write("Haha this is such a cool animal right??");
    clickOn(commentButton);

  }
}