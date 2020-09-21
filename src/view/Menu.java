package view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocio.Produto;

public class Menu extends Application {
	
	public static List<Produto> listaProduto = new ArrayList<Produto>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Scene scene = new Scene(root,815,400);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}