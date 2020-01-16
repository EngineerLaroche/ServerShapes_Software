package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

import controller.CommBase;
import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: FEN�TRE PRINCIPALE
 * 
 * @R�sumer: 
 * Cette classe repr�sente la fen�tre principale de l'application
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 27 mai 2017 
 * 
 *******************************************************************/ 
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{

	/***************************
	 * Classes instanci�es
	 ***************************/

	private MenuFenetre menuFenetre;
	private FenetreFormes fenetreFormes;

	/********************
	 * Constantes
	 ********************/

	private static final long serialVersionUID = -1210804336046370508L;

	//On donne les dimensions de la fen�tre. 
	private static final int 
	WIDTH = 500,
	HEIGHT = 500;

	/********************
	 * Constructeur
	 ********************/
	public FenetrePrincipale(CommBase commBase){

		menuFenetre = new MenuFenetre(commBase);
		fenetreFormes = new FenetreFormes();

		//Ajuste la dimension de la fenetre principale selon ses composants
		this.pack();

		//Donne une dimension de d�part � la fen�tre
		this.setSize(WIDTH,HEIGHT);

		//Rend la fenetre principale visible.
		this.setVisible(true); 

		//Regroupement des composants graphiques  
		this.setLayout(new BorderLayout());

		//Ajout du menu � la fen�tre principale
		this.add(menuFenetre, BorderLayout.NORTH);

		//Ajout de la fenetre de formes � la fen�tre principale
		this.add(fenetreFormes, BorderLayout.CENTER); 

		//Fermeture de l'application � la fermeture de la fen�tre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Donne un titre � la fen�tre
		this.setTitle("Fen�tre Principale");
	}

	/*******************************************************************
	 * @Titre: CHANGEMENT DE PROPRI�T�
	 * 
	 * @R�sumer:
	 * Appel�e lorsque le sujet lance "firePropertyChanger"
	 * 
	 *******************************************************************/
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

		if(arg0.getPropertyName().equals("ENVOIE-TEST")){

			//Ajout d'une forme � la fen�tre en envoyant en param�tre la classe abstraite
			fenetreFormes.ajoutforme((FormesAbstraites) arg0.getNewValue());

			//Permet l'affichage des formes
			repaint();
		}
	}
}
