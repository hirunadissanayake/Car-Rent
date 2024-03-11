package lk.ijse.carrent.layerd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.carrent.layerd.util.SessionFactoryConfiguration;
import org.hibernate.Session;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

       Parent root = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
         stage.centerOnScreen();
         stage.setScene(scene);
         stage.show();


    }


}
