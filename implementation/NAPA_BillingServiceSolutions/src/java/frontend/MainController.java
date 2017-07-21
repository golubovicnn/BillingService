package frontend;
/**
 * Created by golubovic on 12.06.17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    Stage primaryStage = new Stage();
    Parent root;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * show Welcom Screen
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
       showWelcomeScreen();
       // showProductScreen();
    }

    /**
     * Show Login Window
     * @throws IOException
     */

    public void showLogInScreen() throws IOException {

        root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("NAPA Billing Service Solutions");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    /**
     * Show Welcom Screen
     * @throws Exception
     */

    public void showWelcomeScreen() throws Exception {
        root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setTitle("NAPA Billing Service Solutions");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    /**
     * Schow Register Screen
     * @throws Exception
     */
    public void showRegisterScreen() throws Exception {
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        primaryStage.setTitle("NAPA Billing Service Solutions");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }

    /**
     * Show Product Screen
     * @throws Exception
     */

    public void showProductScreen() throws Exception {
        root = FXMLLoader.load(getClass().getResource("products.fxml"));
        primaryStage.setTitle("NAPA Billing Service Solutions");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}
