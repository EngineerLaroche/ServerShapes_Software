package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME LIGNE
 * 
 * @Résumer:
 * Création d'un objet graphique de type ligne 2D. 
 * On identifie la forme à l'aide d'un remplissage coloré.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017 
 *******************************************************************/
public class FormeLigne extends FormesAbstraites{
	
	/********************
	 * Variables
	 ********************/
	private int x1, y1, x2, y2;
	
	/********************
	 * Constructeur
	 ********************/
	public FormeLigne(int nseq, int x1, int y1, int x2, int y2){
	
		//NUméro de séquence de la fgorme
		super(nseq);
		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/********************
	 * Graphics 
	 ********************/
	@Override
	public void graphiqueForme(Graphics g) {
		
		//Remplissage coloré de la forme
		g.setColor(Color.BLACK); 
		g.drawLine(x1, y1, x2, y2);
	}
}