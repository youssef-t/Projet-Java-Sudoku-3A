/*
    Cette classe permet de controller ce qui se passe dans la fenêtre où on trouve la grille.
    Le link de cette classe avec la scène controlée se fait dans le .fxml.
    L'id de chaque bouton est défini dans le .fxml
 */

package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sample.CaseGrille;
import sample.FirstWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGridWindow implements Initializable {
    //les ids des boutons contenus dans .fxml
    @FXML private TextField difficulty;
    @FXML private GridPane grid;
    @FXML private Button button_retour_acceuil;
    @FXML private Button button_verification;
    @FXML private Button button_reinitialisation;
    private CaseGrille[][] cases_grille;
    private String[] sets_sudoku_easy;
    private String[] sets_sudoku_normal;
    private String[] sets_sudoku_hard;

    public ControllerGridWindow() {
        //Créer la grille et l'initialiser
        cases_grille = new CaseGrille[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                cases_grille[i][j] = new CaseGrille();

        //initialiser les combinaisons de sudoku
        sets_sudoku_easy = new String[4];
        sets_sudoku_normal = new String[4];
        sets_sudoku_hard = new String[4];

        sets_sudoku_easy[0] = "095074610041508023803002500500000000312000765000000004008200409120407350057690180";
        sets_sudoku_easy[1] = "050000960036000085982501004000002096309745008240090000810007009600980030094603800";
        sets_sudoku_easy[2] = "305070026726900001001600470003024008600510342200080107070200080160800734000400000";
        sets_sudoku_easy[3] = "038000254001002607050060903700010030500004102123600000817026000394800706205400000";

        sets_sudoku_normal[0] = "000059800000700409509200010002970030807000105050061700090008207608007000005320000";
        sets_sudoku_normal[1] = "308000000400105630060004000613820090200000010904500000002490001705000003000000842";
        sets_sudoku_normal[2] = "020001948810600000004027600170090003300000000048053000006100002000074006050900007";
        sets_sudoku_normal[3] = "010800009984000600000600030830000100000200096090750280400180000370902000001000920";

        sets_sudoku_hard[0] = "070900001000250040500000807000000005908060300005308002000000090096040000004100003";
        sets_sudoku_hard[1] = "360904100000000070004003060905010807000750009000000030700008090003540008000000000";
        sets_sudoku_hard[2] = "060005000070000001000063400003080000210090005400007800001600084000000050800040610";
        sets_sudoku_hard[3] = "490000000000040306750090000902000005000400060070001000500980600049007000020003500";

    }
    //remplir le texte difficulté en fonction de la difficulté
    //choisie par l'utilisateur
    public void setTextDifficulty(){
        //on récupère la diffculté choisie grâce au getter
        //String difficulty_help = new String(ControllerSecondWindowDifficulty.getDifficultySelected());
        //modifier la zone de texte d'id "difficulty" en utilisant le getter déclaré
        difficulty.setText("Difficulté: "+ ControllerSecondWindowDifficulty.getDifficultySelected());
    }

    //mettre toutes les cases de la grille à 0
    //0 représente une case vide
    public void initializeGrid(){
        //on utilise le getter déclaré dans ControllerSecondWindowDifficulty
        String difficulte = ControllerSecondWindowDifficulty.getDifficultySelected();
        //variable qui permettra de parcourir les combinaisons du sudoku
        int index = 0;
        char help_sudoku;
        int x = 0;
        //générer un nombre entre 0 et 3 pour prendre au hasard une combinaison de Sudoku
        x = (int)Math.round(Math.random()*3);
        //System.out.println("X: " +x);
        //Remplir la grille
        if(difficulte.equals("facile"))
            for(int i = 0; i<9; i++)
                for(int j = 0; j<9; j++) {
                    help_sudoku = sets_sudoku_easy[x].charAt(index);
                    //convertir le "char" en string car la méthode setValue prend en paramètre des String
                    cases_grille[i][j].setValue(String.valueOf(help_sudoku));
                    index++;
                }
        else if(difficulte.equals("normal"))
            for(int i = 0; i<9; i++)
                for(int j = 0; j<9; j++) {
                    help_sudoku = sets_sudoku_normal[x].charAt(index);
                    //convertir le "char" en string car la méthode setValue prend en paramètre des String
                    cases_grille[i][j].setValue(String.valueOf(help_sudoku));
                    index++;
                }
        else if(difficulte.equals("difficile"))
            for(int i = 0; i<9; i++)
                for(int j = 0; j<9; j++) {
                    help_sudoku = sets_sudoku_hard[x].charAt(index);
                    //convertir le "char" en string car la méthode setValue prend en paramètre des String
                    cases_grille[i][j].setValue(String.valueOf(help_sudoku));
                    index++;
                }


        //déterminer les cases modiables et non modifiables
        //et donner un style aux cases modifiables
        for(int i = 0; i<9 ; i++)
            for(int j = 0; j<9; j++){
                if(!cases_grille[i][j].getText().equals(" ")) {
                    cases_grille[i][j].setModifiable(false);
                    //System.out.println(i + ", " + j + " : non modifiable");
                }
                }

        //ajouter les cases à la grille
        for(int i = 0; i <9 ; i++)
            for(int j = 0; j <9; j++) {
                //on inverse i et j: dans GridPanel la colonne correspond au deuxième paramètre
                //et la ligne au troisème paramètre
                grid.add(cases_grille[i][j], j, i);
            }
    }


    //initialisation de notre scène en modifiant le texte "difficulté"
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setTextDifficulty();
        initializeGrid();

        //add CSS
        FirstWindow.getStage().getScene().getStylesheets().add("/sample/ressources/styles.css");
        for(int i = 0; i < 9; i++)
            for(int j = 0; j <9; j++)
                if(cases_grille[i][j].getModifiable()) {
                        cases_grille[i][j].getStyleClass().removeAll();
                        cases_grille[i][j].getStyleClass().add("CaseGrille_modifiable");
                }
    }


    //vérifier les valeurs saisies dans la grille
    public boolean verifyGrid(){
        //vérifier si les colonnes contiennent des doublons (chiffres seulement)
        for(int col = 0; col< 9;col++)
            for(int ligne = 0; ligne < 8 ; ligne++)
                for(int i = ligne +1; i < 9; i++){
                    if(cases_grille[ligne][col].getText().equals(cases_grille[i][col].getText())
                            && !cases_grille[ligne][col].getText().equals(" ")){
                        //System.out.println("Faux: colonne "+ligne+col+i);
                        return false;
                    }
                }

        //vérifier les lignes
        for(int ligne = 0; ligne< 9;ligne++)
            for(int col = 0; col < 8 ; col++)
                for(int i = col +1; i < 9; i++){
                    if(cases_grille[ligne][col].getText().equals(cases_grille[ligne][i].getText())
                            && !cases_grille[ligne][col].getText().equals(" ")){
                        //on peut améliorer le code en mettant la ligne qui se trouve juste au dessus
                        //dans la ligne 138 dans le if statement avec un "&&" (en permuttant ligne et col).
                        // Mais pour la clarté du code, on se met dans de nouvelles boucles
                        //System.out.println("Faux: lignes");
                        return false;}
                }


        //vérifier les carrés

        //On récupère les cases qui se trouvent au centre de chaque carré : cases_grille[1 + i*3][1 + j*3]
        //Ensuite,(pour simplifier la logique) on met toutes les cases contenues
        //dans un carré dans une ligne dansnotre nouvelle table
        //Enfin, on fait des comparaisons dans chaque ligne comme avant.
        //Pour des raisons de compatibalité, on déclare la nouvelle table comme String au lieu de char
        String[][] grille_transformee_carre_en_ligne;
        grille_transformee_carre_en_ligne = new String[9][9];
        int parcourir_ligne = 0;
        int parcourir_col = 0;
        //les deux premières boucles permettent de se mettre au centre des carrés
        for(int i =0; i<3;i++)
            for(int j = 0; j < 3; j++) {
                //parcourir le carré dont le centre est cases_grille[1+i*3 + a][1+j*3 +b]
                for (int a = -1; a < 2; a++)
                    for (int b = -1; b < 2; b++) {
                        //affecter le contenu du carré dans une ligne de la nouvelle table
                        grille_transformee_carre_en_ligne[parcourir_ligne][parcourir_col]=
                                cases_grille[1+i*3 + a][1+j*3 +b].getText();
                        parcourir_col++; //parcourir les colonnes
                    }
                parcourir_ligne++; //lorsqu'on passe au prochain carré, on passe vers la prochaine ligne
                                    //de la nouvelle table
                parcourir_col = 0; //se repositionner à la colonne 0
            }


        //vérifier les occurences dans les lignes de la nouvelle table
        for(int ligne = 0; ligne< 9;ligne++)
            for(int col = 0; col < 8 ; col++)
                for(int i = col +1; i < 9; i++){
                    if(grille_transformee_carre_en_ligne[ligne][col].equals(grille_transformee_carre_en_ligne[ligne][i])
                            && !grille_transformee_carre_en_ligne[ligne][col].equals(" "))
                        return false;
                }
        //Après toutes les vérifications, la méthode retourne "true"
        return true;
    }

    //EventHandler du bouton "vérifier"
    @FXML
    public void mouseClickVerify(ActionEvent event) {
        if(verifyGrid()) {
            //System.out.println("C'est bon");
            button_verification.setText("Vérifier ✔"); //changer le contenu du bouton
        }
        else {
            button_verification.setText("Vérifier ✘");
            //System.out.println("FAUX");
        }

    }

    //réinitialiser la grille
    @FXML
    public void mouseClickReinitialise(ActionEvent event) {
        button_verification.setText("Vérifier");
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                if(cases_grille[i][j].getModifiable())//les cases non modifiables sont celles qu'on a initialisées
                    cases_grille[i][j].setText(" ");
    }

    //retourner à la page d'acceuil
    @FXML
    public void mouseClickReturn(ActionEvent event) throws IOException{
        //System.out.println("CLICKED");
        //charger la page d'acceuil
        Parent root = FXMLLoader.load(getClass().getResource("../ressources/firstWindow.fxml"));

        //Afficher la page
        Scene scene = new Scene(root);

        FirstWindow.getStage().setScene(scene);
        FirstWindow.getStage().show();
    }

}
