/*
    Cette classe permet de controller ce qui se passe dans la fenêtre d'acceuil :
    les eventListner.
    Le link de cette classe avec la scène controlée se fait dans le .fxml, ce qui nous permet
    de déclarer les eventHandlers sans préciser à quoi ils sont liés dans le code, vu que c'est
    précisé dans .fxml généré par SceneBuilder.
 */
package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.FirstWindow;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ControllerFirstWindow  {
    //méthode qui se lance lorsque l'utilisateur clique sur "jouer"
    @FXML
    public void playGame(ActionEvent event) throws IOException {
        System.out.println("CLICKED");
        //charger la deuxième scène
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/selectDifficulty.fxml"));

        Scene scene = new Scene(root);
        //ouvrir la deuxième scène dans la même fenêtre
        Stage window = FirstWindow.getStage();
        window.setScene(scene);
        window.show();
    }

    //méthode qui se lance lorsque l'utilisateur clique sur l'Hyperlink "noouveau à sudoku"
    public void learnSudoku(ActionEvent event) throws IOException{
        //ouvrir un lien sur le navigateur montrant les bases du Sudoku (la gestion d'erreur est obligatoire
        //pour pouvoir utiliser cette méthode)
            try{
                Desktop.getDesktop().browse(new URI("https://sudoku.com/how-to-play/sudoku-rules-for-complete-beginners/"));}
            catch (IOException e1){
                e1.printStackTrace();
            }catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
        }
}
