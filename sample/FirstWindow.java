/*
    Initialisation de l'application (programme)
    et afficher la première scène d'acceuil

    Réalisé par: Taleb Youssef
 */

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FirstWindow extends Application {
    //pour pouvoir récupérer m_window dans toutes les classes
    //grâce au getter déclaré "static"
    private static Stage m_window;
    private Scene m_scene;

    //getter de la fenêtre "Stage" m_window
    //On s'en sert dans les autres pour changer scène
    public static Stage getStage(){
        return m_window;
    }

    //lancement de l'application
    @Override
    public void start(Stage window) throws Exception {
        m_window = window;
        //on charge le fichier FXML qui gère la mise en page
        //et les boutons qui existent sur la fenêtre
        //Le root est la racine de notre scène
        Parent root = FXMLLoader.load(getClass().getResource("ressources/firstWindow.fxml"));
        m_window.setTitle("Sudoku");
        //On crée une scène et la lie avec notre racine
        //pour que ça s'affiche
        m_scene = new Scene(root);
        m_window.setScene(m_scene);

        //régler la taille de la fenêtre
        m_window.setHeight(750);
        m_window.setMinHeight(750);
        m_window.setMaxHeight(750);
        m_window.setWidth(850);
        m_window.setMinWidth(850);
        m_window.setMaxWidth(850);


        //afficher notre application (fenêtre)
        window.show();
    }
}

