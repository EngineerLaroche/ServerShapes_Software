package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME RECTANGLE
 * 
 * @Résumer:
 * Création d'un objet graphique de type rectangle 2D. 
 * On identifie la forme à l'aide d'un remplissage coloré.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/
public class FormeRectangle extends FormesAbstraites{

	/********************
	 * Variables
	 ********************/
	private int x1, y1, x2, y2;

	/********************
	 * Constructeur
	 ********************/
	public FormeRectangle(int nsec, int x1, int y1, int x2, int y2){

		//Numéro de séquence de la forme
		super(nsec);

		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	/********************
	 * Graphics 
	 ********************/
	@Override
	public void graphiqueForme(Graphics g){

		//Remplissage coloré de la forme
		g.setColor(Color.ORANGE);
		g.fillRect(x1, y1, (x2 - x1), (y2 - y1));

		//Contour noir aide à identifier une forme qui emboite une autre.
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, (x2 - x1), (y2 - y1));
	}
}