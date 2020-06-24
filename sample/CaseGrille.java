/*
    Cette classe définit chaque case qui compose la grille du Sudoku:
    elle hérite de la classe Button de Javafx et on lui ajoute quelques méthodes
    et des EventHandlers.
    Notons qu'on utilise un fichier .css pour définir la mise en forme des boutons,
    surtout pour prendre en charger la fonctionnalité "focused" de CSS

    Réalisé par: Taleb Youssef
 */

package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CaseGrille extends Button {
    //variable qui détermine si l'utilisateur peut modifier la case
    private boolean m_modifiable;

    //constructeur
    public CaseGrille() {
        super();
        m_modifiable = true;
        this.setValue("0");
        this.setVisible(true);
        //définir le style des cases qui vont composer la grille
        this.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        //charger le fichier .css
        FirstWindow.getStage().getScene().getStylesheets().add("/sample/ressources/styles.css");
        //Affecter la case à la classe CaseGrille définie dans .css
        this.getStyleClass().add("CaseGrille");

        //EventHandler quand l'utilisateur saisie un chiffre
        EventHandler<KeyEvent> eventHandlerKey = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                //saisir la valeur tapée au clavier dans le bouton
                //s'il est un chiffre
                if(m_modifiable) {
                    //On s'assure que l'utilisateur n'utilise pas les flèches
                    //Notons qu'on peut se déplacer dans la grille en les utilisant
                    if(keyEvent.getCode() != KeyCode.UP && keyEvent.getCode()!= KeyCode.DOWN
                     && keyEvent.getCode() != KeyCode.RIGHT && keyEvent.getCode() != KeyCode.LEFT) {
                        setValue(keyEvent.getText());
                    }
                }
                //System.out.println(keyEvent.getText());
            }
        };

        //ajouter l'eventHandler à notre objet
        this.addEventHandler(KeyEvent.KEY_PRESSED, eventHandlerKey);
    }

    //s'assurer que l'utilisateur a saisi un chiffre
    private boolean verifyInput(String string){
        if(string.length() != 1)
            return false;
        else
            return (string.charAt(0) <= '9' && string.charAt(0) >= '0');
    }

    //mettre la valeur saisie dans la case (le bouton)
    public void setValue(String string){
        //vérifier la valeur saisie
        if(verifyInput(string)) {
            if(string.charAt(0) == '0')
                this.setText(" ");
            else
                this.setText(string);

        }
    }

    //Changer la valeur de m_modifiable
    //Notons qu'en fonction de "m_modifiable", l'apparence de la case change
    public void setModifiable(boolean bool){
        this.m_modifiable = bool;
        if(bool){
            //enlever tous les styles
            this.getStyleClass().removeAll();
            //affecter l'objet à une nouvelle classe CSS
            this.getStyleClass().add("CaseGrille_modifiable");
        }
        else {
            this.getStyleClass().removeAll();
            this.getStyleClass().add("CaseGrille");
        }
    }

    //getter de la variable m_modifiable
    public boolean getModifiable(){
        return m_modifiable;
    }

}
