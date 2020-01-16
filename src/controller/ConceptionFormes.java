package controller;

import model.FormeCarre;
import model.FormeCercle;
import model.FormeLigne;
import model.FormeOvale;
import model.FormeRectangle;
import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: CONCEPTION FORMES
 * 
 * @Résumer: 
 * Identification du type de forme et enregistrement des paramètres
 * pour la création d'une nouvelle forme pour chaque demandes.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 18 mai 2017 
 *******************************************************************/ 
public class ConceptionFormes {

	/***************************
	 * Classes instanciées
	 ***************************/

	private static FormesAbstraites formesAbstraites;

	/********************
	 * Variables
	 ********************/

	private static int[] listeAbstraite;
	private static int numSeqForme;

	private static String[] coordonneeForme;
	private static String typeForme;
	
	/********************
	 * Constantes
	 ********************/
	
	private static final String
	FORME_CARRE = "CARRE",
	FORME_RECTANGLE = "RECTANGLE",
	FORME_CERCLE = "CERCLE",
	FORME_OVALE = "OVALE",
	FORME_LIGNE = "LIGNE";

	/********************
	 * Constructeur
	 ********************/
	public ConceptionFormes() {

		//On s'assure qu'il n'y a aucune références à des objets (formes)
		formesAbstraites = null;
		
	}

	/*******************************************************************
	 * @Titre: STRUCTURE FORME
	 * 
	 * @Résumer:
	 * De façon générale, on insère dans une liste le numéro de séquence,
	 * le nom et les coordonnées d'une forme
	 * 
	 * @Source:
	 * array.split(): http://stackoverflow.com/questions/14414582/java-split-string-to-array
	 *
	 *******************************************************************/
	public static void structureForme(String[] chaineForme) {
		
		//On associe la première position avec le numero de séquence de la forme
		numSeqForme = Integer.parseInt(chaineForme[0]);

		//On associe la deuxième position avec le nom (type) de la forme
		typeForme = chaineForme[1];

		//On associe la troisieme position avec les coordonnées de la forme et on sépare les valeurs
		coordonneeForme = chaineForme[2].split(" ");

		//Créé une nouvelle liste Integer en fonction du nombre de valeurs coordonnées
		listeAbstraite = new int[coordonneeForme.length];

		//Boucle en fonction du nombre de valeurs coordonnées d'une forme
		for (int i = 0; i < coordonneeForme.length; i++) { 

			//Pour toutes les valeurs coordonées, on les transforment en Integer
			listeAbstraite[i] = Integer.parseInt(coordonneeForme[i]); 
		}
	}

	/*******************************************************************
	 * @Titre: FORMATION D'UNE FORME
	 * 
	 * @Résumer:
	 * La configuration "switch" permet pour chaque demande la création 
	 * d'une nouvelle forme qui fait référence aux paramètres
	 * de la classe FormesAbstraites et permet d'associer un numéro de 
	 * séquence ainsi les dimensions de cette forme. 
	 * 
	 * @Source 
	 * Switch Case: https://openclassrooms.com/courses/apprenez-a-programmer-en-java/tp-une-calculatrice
	 * 
	 *******************************************************************/
	public static FormesAbstraites formationFormes(){

		//Identification du type de forme puis création d'une nouvelle forme avec ses paramètres
		switch (typeForme) {

		case FORME_CARRE:

			formesAbstraites = new FormeCarre(numSeqForme, listeAbstraite[0], listeAbstraite[1], listeAbstraite[2], listeAbstraite[3]);												
			break;

		case FORME_RECTANGLE:

			formesAbstraites = new FormeRectangle(numSeqForme, listeAbstraite[0], listeAbstraite[1], listeAbstraite[2], listeAbstraite[3]); 													
			break;

		case FORME_CERCLE:

			formesAbstraites = new FormeCercle(numSeqForme, listeAbstraite[0], listeAbstraite[1], listeAbstraite[2]);																
			break;

		case FORME_OVALE:

			formesAbstraites = new FormeOvale(numSeqForme, listeAbstraite[0], listeAbstraite[1], listeAbstraite[2], listeAbstraite[3]);												
			break;

		case FORME_LIGNE:

			formesAbstraites = new FormeLigne(numSeqForme, listeAbstraite[0], listeAbstraite[1], listeAbstraite[2], listeAbstraite[3]);													
			break;
		}

		//Retourne la nouvelle forme construite 
		return formesAbstraites;
	}
}