import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainGUI extends Application {
	int loginNum = 0;
	
	public static void main (String [] args) {
		launch(args);
	}
	
	public void start (Stage mainStage) throws IOException {
		// Setup
		mainStage.setTitle("Laundrify");
		StackPane root = new StackPane();
		root.setStyle("-fx-background-color: #BCDBF4");
		
		StackPane home = new StackPane();
		home.setStyle("-fx-background-color: #BCDBF4");
		
		Scene scene = new Scene(root, 320, 568);
		Scene scene2 = new Scene(home, 320, 568);
		
		// Login UI Elements
		TextField username = new TextField();
		username.setPromptText("Username");
		username.getText();
		username.setMaxWidth(160);
		username.setTranslateY(0);
		
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		password.getText();
		password.setMaxWidth(160);
		password.setTranslateY(40);
		
		Label loginError = new Label("INVALID USERNAME/PASSWORD. PLEASE TRY AGAIN");
		loginError.setVisible(false);
		loginError.setTranslateY(120);
		
		Label loginAttempt = new Label("Login attempt(s): " + loginNum);
		loginAttempt.setTranslateY(140);
		loginAttempt.setVisible(false);
		root.getChildren().add(loginAttempt);
		
		Button login = new Button();
		login.setText("Login");
		login.setTranslateY(80);
		login.setOnAction(e -> {
			String info = username.getText() + " " + password.getText();
			if (checkDB(info)) {
				mainStage.setScene(scene2);
			} else {
				if (loginNum > 4) { 
					System.exit(0);
				}
				loginError.setVisible(true);
				incrementLoginAttempts();
				loginAttempt.setText("Login attempt(s): " + loginNum + " out of 5");
				loginAttempt.setVisible(true);
			}
		});
		
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				String info = username.getText() + " " + password.getText();
				if (checkDB(info)) {
					mainStage.setScene(scene2);
				} else {
					loginError.setVisible(true);
					if (loginNum > 4) { 
						System.exit(0);
					}
					loginError.setVisible(true);
					incrementLoginAttempts();
					loginAttempt.setText("Login attempt(s): " + loginNum + " out of 5");
					loginAttempt.setVisible(true);
				}
			}
		});
		
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
		fb.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				scene.setCursor(Cursor.HAND);
			}
		});
		fb.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				scene.setCursor(Cursor.DEFAULT);
			}
		});
		fb.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("OPENING TAB FOR FACEBOOK!");
			}
		});
		
		Image googleImg = SwingFXUtils.toFXImage(ImageIO.read(new File("./img/google-login.png")), null);
		ImageView google = new ImageView(googleImg);
		google.setScaleX(0.12);
		google.setScaleY(0.12);
		google.setTranslateY(245);
		google.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				scene.setCursor(Cursor.HAND);
			}
		});
		google.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				scene.setCursor(Cursor.DEFAULT);
			}
		});
		google.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("OPENING TAB FOR GOOGLE!");
			}
		});
		
		root.getChildren().addAll(logo, icon, username, password, login, loginError, connect, fb, google);
		
		
		// Page 1 UI Elements
		Label welcome = new Label("WELCOME TO LAUNDRIFY!");
		welcome.setAlignment(Pos.CENTER);
		home.getChildren().add(welcome);
		
		// Display
		mainStage.setScene(scene);
		mainStage.show();
	}
	
	// Simple Database check
	// Checks if a string representing a username-password pair exists in a .txt file "database"
	public boolean checkDB(String info) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("database.txt")));
			String line = new String();
			while ( (line = br.readLine()) != null) {
				if (line.equals(info)) {
					br.close();
					return true;
				}
			}
		br.close();
		} catch (IOException e) {}
		
		return false;
	}
	
	public void incrementLoginAttempts() {
		loginNum++;
	}
}
