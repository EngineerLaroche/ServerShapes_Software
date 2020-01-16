package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME OVALE
 * 
 * @Résumer:
 * Création d'un objet graphique de type ovale 2D. 
 * On identifie la forme à l'aide d'un remplissage coloré.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/
public class FormeOvale extends FormesAbstraites{

	/********************
	 * Variables
	 ********************/
	private int centreX, centreY, rayonH, rayonV;

	/********************
	 * Constructeur
	 ********************/
	public FormeOvale(int nseq, int centreX, int centreY, int rayonH, int rayonV){

		//Numéro de séquence de la forme
		super(nseq);

		this.centreX = centreX;
		this.centreY = centreY;
		this.rayonH = rayonH;
		this.rayonV = rayonV;
	}

	/********************
	 * Graphics 
	 ********************/
	@Override
	public void graphiqueForme(Graphics g){

		//Remplissage coloré de la forme
		g.setColor(Color.CYAN);
		g.fillOval(centreX, centreY, rayonH, rayonV);

		//Contour noir aide à identifier une forme qui emboite une autre.
		g.setColor(Color.BLACK);
		g.drawOval(centreX, centreY, rayonH, rayonV);
	}
}