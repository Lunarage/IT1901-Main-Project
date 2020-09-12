package mememedb;

//Import javafx stuff
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;

//Java Utils
import java.util.HashMap;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;

public class AppController
{
	@FXML
	VBox content;
	Button addContent;



	//Initialize
	@FXML
	public void initialize()
	{

	}
	@FXML
	public void handleAddContent() throws FileNotFoundException {
		//get content from database
		System.out.println(App.class.getPackage());
		ImageView image = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("Grandma.png")));
		Label label2 = new Label("info and options");
		SplitPane listElement = new SplitPane();
		listElement.getItems().addAll(image, label2);
		listElement.prefWidthProperty().bind(content.prefWidthProperty());
		content.getChildren().addAll(listElement);
		
	}
	
	
}


