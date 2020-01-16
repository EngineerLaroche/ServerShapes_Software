package view;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: FENÊTRE FORMES
 * 
 * @Résumer: 
 * Cette fenêtre gère l'affichage des forme à l'écran.
 * La fenêtre possède une dimension fixe de 500 x 500 et 
 * supporte un maximum de 10 formes.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 26 mai 2017 
 * 
 *******************************************************************/ 
public class FenetreFormes extends JComponent{

	/***************************
	 * Classes instanciées
	 ***************************/

	private final Dimension dimension;
	private FormesAbstraites[] listeAbstraites;
	
	/********************
	 * Constantes
	 ********************/
	
	private static final long serialVersionUID = -2262235643903749505L;
	
	//On donne les dimensions de la fenêtre et la quantité maximale de formes 
	private static final int 
	WIDTH = 500,
	HEIGHT = 500,
	MAX_FORMES = 10;
	
	/********************
	 * Variable
	 ********************/
	
	//Utilisé pour compter le nombre de formes à l'affiche
	private int nombreFormes, quantiteFormes = 0;
	
	/********************
	 * Constructeur
	 ********************/
	public FenetreFormes(){
		
		//On donnes les dimensions de la fenêtre
		dimension = new Dimension(WIDTH, HEIGHT);

		//On créé une liste qui peut garder en mémoire au maximum 10 formes
		listeAbstraites = new FormesAbstraites[MAX_FORMES];
	}

	/*******************************************************************
	 * @Titre: AJOUTER FORME
	 * 
	 * @Résumer:
	 * Gère la capacité maximale de formes à afficher à l'écran avec une 
	 * limite de 10. Permet aussi d'afficher continuellement 10 formes
	 * avec un changement dynamique.
	 * 
	 *******************************************************************/
	public void ajoutforme(FormesAbstraites formesAbstraites){
		
		//Liste qui permet l'affichage au maximum de 10 formes
		listeAbstraites[quantiteFormes] = formesAbstraites;

		if(quantiteFormes <= MAX_FORMES){
			
			quantiteFormes++;
			
			//Si on atteint la capacité maximale de 10 formes
			if(quantiteFormes == MAX_FORMES){
				
					//on s'assure de recommencer le compte à 0
					quantiteFormes = 0;
			}
			
			//Si on reste sous la barre des 10 formes
			if(nombreFormes < MAX_FORMES){
				
				//Incrémente pour identifier le nombre de formes à imprimer
				nombreFormes++;
			}
		}
		else{
			
			System.out.println("Un problème est survenu avec la capacité maximal de formes à afficher.");
			
			//On s'assure d'arrêter le processus
			return;
		}
	}

	/*******************************************************************
	 * @Titre: AFFICHE FORMES
	 * 
	 * @Résumer:
	 * Affiche à l'écran les formes gardées en mémoire de la liste.
	 * 
	 *******************************************************************/
	@Override 
	public void paintComponent(Graphics g){
		
		//Boucle en fonction du nombre de formes
		for (int i = 0; i < nombreFormes; i++) {

			//Affiche à l'écran les formes de la liste
			listeAbstraites[i].graphiqueForme(g);
		}
	}

	/*******************************************************************
	 * @Titre: DIMENSION
	 * 
	 * @Résumer:
	 * Le Layout qui utilise (contient) FenetreFormes doit reserver 
	 * l'espace nécessaire a son affichage.
	 * 
	 *******************************************************************/
	@Override 
	public Dimension getPreferredSize(){

		return dimension;
	}
}