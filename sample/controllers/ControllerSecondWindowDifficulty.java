/*
    Cette classe permet de controller ce qui se passe dans la fenêtre où on choisit la difficulté.
    Le link de cette classe avec la scène controlée se fait dans le .fxml.
    L'id de chaque bouton est défini dans le .fxml
 */

package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import sample.FirstWindow;

import java.io.IOException;

public class ControllerSecondWindowDifficulty {
    //variable qui stocke le niveau de difficulté choisie
    private static String difficulty_selected;
    //getter pour la variable difficulty_selected
    //on le déclare static pour ne pas devoir déclarer une instance de la classe
    //et parce qu'on s'en sert dans d'autres fichiers sources
    public static String getDifficultySelected() {
        return difficulty_selected;
    }

    //on doit spécifier @FXML avant nos variables pour pouvoir les utiliser comme "id" dans .fxml
    //sinon on peut les déclarer puclic sauf qu'il est mieux d'éviter de faire cela
    @FXML private Button button_facile;
    @FXML private Button button_normal;
    @FXML private Button button_difficile;
    @FXML private Button button_retour;

    //méthode qui charge une nouvelle scène à la fenêtre "Stage"
    //pour éviter la duplication du code
    private void loadNewScene(String path, ActionEvent event) throws IOException{
        //charger le .fxml en précisant son chemin
        Parent root = FXMLLoader.load(getClass().getResource(path));
        //créer une nouvelle scène qui prend l'apparence du .fxml
        Scene scene = new Scene(root);
        //afficher le scène sur notre fenêtre
        FirstWindow.getStage().setScene(scene);
        FirstWindow.getStage().show();
    }

    //méthode qui s'exécute quand l'utilisateur clique sur le bouton Facile
    public void buttonFacileClick(ActionEvent event) throws IOException {
        difficulty_selected = "facile";
        System.out.println("facile CLICKED");
        //charger une nouvelle scène
        loadNewScene("../ressources/gridWindow.fxml", event);
    }

    //méthode qui s'exécute quand l'utilisateur clique sur le bouton Normal
    public void buttonNormalClick(ActionEvent event) throws IOException{
        difficulty_selected = "normal";
        System.out.println("normal CLICKED");
        //charger une nouvelle scène
        loadNewScene("../ressources/gridWindow.fxml", event);
    }

    //méthode qui s'exécute quand l'utilisateur clique sur le bouton Difficile
    public void buttonHardClick(ActionEvent event) throws IOException{
        difficulty_selected = "difficile";
        loadNewScene("../ressources/gridWindow.fxml",event);
    }

    //méthode qui s'exécute quand l'utilisateur clique sur le bouton retour
    public void buttonRetourClick(ActionEvent event) throws IOException{
        //retour à la page d'acceuil
        loadNewScene("../ressources/firstWindow.fxml", event);
    }
}
