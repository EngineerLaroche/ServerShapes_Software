package model;

import java.awt.Color;
import java.awt.Graphics;

/*******************************************************************
 * @Titre: FORME LIGNE
 * 
 * @R�sumer:
 * Cr�ation d'un objet graphique de type ligne 2D. 
 * On identifie la forme � l'aide d'un remplissage color�.
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
	
		//NUm�ro de s�quence de la fgorme
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
		
		//Remplissage color� de la forme
		g.setColor(Color.BLACK); 
		g.drawLine(x1, y1, x2, y2);
	}
}