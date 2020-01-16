package view;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

import controller.CommBase;
import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: FENÊTRE PRINCIPALE
 * 
 * @Résumer: 
 * Cette classe représente la fenêtre principale de l'application
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 27 mai 2017 
 * 
 *******************************************************************/ 
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{

	/***************************
	 * Classes instanciées
	 ***************************/

	private MenuFenetre menuFenetre;
	private FenetreFormes fenetreFormes;

	/********************
	 * Constantes
	 ********************/

	private static final long serialVersionUID = -1210804336046370508L;

	//On donne les dimensions de la fenêtre. 
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

		//Donne une dimension de départ à la fenêtre
		this.setSize(WIDTH,HEIGHT);

		//Rend la fenetre principale visible.
		this.setVisible(true); 

		//Regroupement des composants graphiques  
		this.setLayout(new BorderLayout());

		//Ajout du menu à la fenêtre principale
		this.add(menuFenetre, BorderLayout.NORTH);

		//Ajout de la fenetre de formes à la fenêtre principale
		this.add(fenetreFormes, BorderLayout.CENTER); 

		//Fermeture de l'application à la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Donne un titre à la fenêtre
		this.setTitle("Fenêtre Principale");
	}

	/*******************************************************************
	 * @Titre: CHANGEMENT DE PROPRIÉTÉ
	 * 
	 * @Résumer:
	 * Appelée lorsque le sujet lance "firePropertyChanger"
	 * 
	 *******************************************************************/
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

		if(arg0.getPropertyName().equals("ENVOIE-TEST")){

			//Ajout d'une forme à la fenêtre en envoyant en paramètre la classe abstraite
			fenetreFormes.ajoutforme((FormesAbstraites) arg0.getNewValue());

			//Permet l'affichage des formes
			repaint();
		}
	}
}
