package model;

import ca.etsmtl.log.util.IDLogger;

/*******************************************************************
 * @Titre: FORMES ABSTRAITES
 * 
 * @Résumer:
 * La classe abstraite est conçue de façon à être minimaliste. 
 * L'idée est de permettre l'ajout d'options dans le cas où l'évolution 
 * du Travail_Pratique_01 prend une tournure différente.
 * 
 * Pour l'instant, on retrouve seulement une variable qui garde en 
 * mémoire le numéro de séquence associé à une forme. 
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 15 mai 2017
 *******************************************************************/
public abstract class FormesAbstraites implements InterfaceFormes{

	//Numéro de séquence d'une forme
	protected int nseq;

	//Méthode statique qui permet l'ajout d'un numéro de séquence au Journal
	IDLogger logger = IDLogger.getInstance();

	/********************
	 * Constructeurs
	 ********************/
	public FormesAbstraites(int nseq){

		this.nseq = nseq;
		
		//Utilisé pour la validation 
		logger.logID(nseq);
	}
}