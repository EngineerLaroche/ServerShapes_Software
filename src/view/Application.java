
package view;

import controller.CommBase;

/*******************************************************************

 * @Titre_Classe: D�MARRAGE APPLICATION
 * 
 * @R�sumer:
 * Repr�sente l'application dans son ensemble.
 * On cr�� une nouvelle Base de Communication avec des listeners 
 * et une nouvelle fen�tre, qui est celle l'application. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 08 mai 2017 
 * 
 *******************************************************************/
public class Application{

	public static void main(String[] args) {

		//D�marrage d'une nouvelle application 
		new Application();
	}

	/********************
	 * Constructeur
	 ********************/
	public Application(){

		//D�marre un nouveau processus de communication
		CommBase comm = new CommBase();

		//D�marre une nouvelle fen�tre d'affichage
		FenetrePrincipale fenetre = new FenetrePrincipale(comm);

		//Interruption connexion si la fen�tre principale est ferm�
		comm.setPropertyChangeListener(fenetre);
		
		//Permet d'envoyer un message si on ferme la fen�tre principale
		fenetre.addWindowListener(comm); 
	}
}