package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME CERCLE
 * 
 * @Résumer: 
 * Création d'un objet graphique de type cercle 2D. 
 * On identifie la forme à l'aide d'un remplissage coloré.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017 
 *******************************************************************/

public class FormeCercle extends FormesAbstraites{

	/********************
	 * Variables
	 ********************/
	private int centreX, centreY, rayon;

	/********************
	 * Constructeur
	 ********************/
	public FormeCercle(int nseq, int centreX, int centreY, int rayon){

		//Numéro de séquence de la forme
		super(nseq);

		this.centreX = centreX;
		this.centreY = centreY;
		this.rayon = rayon;
	}

	/********************
	 * Graphics 
	 ********************/
	@Override
	public void graphiqueForme(Graphics g){

		//Remplissage coloré de la forme
		g.setColor(Color.BLUE);
		g.fillOval(centreX, centreY, (rayon * 2), (rayon * 2));

		//Contour noir aide à identifier une forme qui emboite une autre.
		g.setColor(Color.BLACK);
		g.drawOval(centreX, centreY, (rayon * 2), (rayon * 2));
	}
}