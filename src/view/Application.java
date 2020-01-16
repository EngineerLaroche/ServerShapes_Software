
package view;

import controller.CommBase;

/*******************************************************************

 * @Titre_Classe: DÉMARRAGE APPLICATION
 * 
 * @Résumer:
 * Représente l'application dans son ensemble.
 * On créé une nouvelle Base de Communication avec des listeners 
 * et une nouvelle fenêtre, qui est celle l'application. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 08 mai 2017 
 * 
 *******************************************************************/
public class Application{

	public static void main(String[] args) {

		//Démarrage d'une nouvelle application 
		new Application();
	}

	/********************
	 * Constructeur
	 ********************/
	public Application(){

		//Démarre un nouveau processus de communication
		CommBase comm = new CommBase();

		//Démarre une nouvelle fenêtre d'affichage
		FenetrePrincipale fenetre = new FenetrePrincipale(comm);

		//Interruption connexion si la fenêtre principale est fermé
		comm.setPropertyChangeListener(fenetre);
		
		//Permet d'envoyer un message si on ferme la fenêtre principale
		fenetre.addWindowListener(comm); 
	}
}