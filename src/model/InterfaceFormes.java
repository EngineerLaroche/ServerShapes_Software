package model;

import java.awt.Graphics;

/*******************************************************************
 * @Titre: INTERFACE FORME
 * 
 * @Résumer:
 * La classe Interface est conçue de façon à être minimaliste. 
 * L'idée est de permettre l'ajout d'options dans le cas où l'évolution 
 * du Travail_Pratique_01 prend une tournure différente.
 * 
 * Pour l'instant, on retrouve seulement une méthode qui permet d'associer
 * une couleur à une forme.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/
public interface InterfaceFormes {
	
	//Associe une couleur à une forme 
	public void graphiqueForme(Graphics g);
}