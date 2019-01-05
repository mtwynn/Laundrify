import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainGUI extends Application {
	public static void main (String [] args) {
		launch(args);
	}
	
	public void start (Stage mainStage) throws IOException {
		// Setup
		mainStage.setTitle("Laundrify");
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: #BCDBF4");
		
		Scene scene = new Scene(root, 320, 568, Color.RED);
		
		// Login
		TextField username = new TextField();
		username.setPromptText("Username");
		username.getText();
		username.setMaxWidth(160);
		username.setTranslateY(0);
		
		TextField password = new TextField();
		password.setPromptText("Password");
		password.getText();
		password.setMaxWidth(160);
		password.setTranslateY(40);
		
		Button login = new Button();
		login.setText("Login");
		login.setTranslateY(80);
		
		Label connect = new Label("CONNECT WITH...");
		connect.setTranslateY(170);
		connect.setFont(Font.font("Lato", FontWeight.BOLD, 12));
		
		Image logoImg = SwingFXUtils.toFXImage(ImageIO.read(new File("./img/logo.jpg")), null);
		ImageView logo = new ImageView(logoImg);
		logo.setTranslateY(-230);
		
		Image iconImg = SwingFXUtils.toFXImage(ImageIO.read(new File("./img/icon.jpg")), null);
		ImageView icon = new ImageView(iconImg);
		icon.setTranslateY(-120);
		icon.setScaleX(0.6);
		icon.setScaleY(0.6);
		
		Image fbImg = SwingFXUtils.toFXImage(ImageIO.read(new File("./img/fb-login.png")), null);
		ImageView fb = new ImageView(fbImg);
		fb.setScaleX(0.2);
		fb.setScaleY(0.2);
		fb.setTranslateY(210);
		
		Image googleImg = SwingFXUtils.toFXImage(ImageIO.read(new File("./img/google-login.png")), null);
		ImageView google = new ImageView(googleImg);
		google.setScaleX(0.12);
		google.setScaleY(0.12);
		google.setTranslateY(245);
		
		root.getChildren().addAll(logo, icon, username, password, login, connect, fb, google);
		
		// Display
		mainStage.setScene(scene);
		mainStage.show();
		
		
	}
}
