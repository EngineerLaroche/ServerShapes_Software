package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************************************
 * @Titre_Classe: TAG PARSER
 * 
 * @Résumer: 
 * Permet de décomposer les chaînes de caractères reçue par le
 * ServeurForme à l'aide de certaines informations comme le numéro de 
 * séquence d'une forme par exemple. 
 * 
 * @SOurce:
 * Expressions rationnelles: https://cours.etsmtl.ca/log121/private/lab/lab1/expressionsRationnelles.shtml
 * Syntaxe: http://www.commentcamarche.net/php/phpreg.php3
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 31 mai 2017 
 *******************************************************************/ 
public final class TagParser {
	
	/********************
	 * Constante
	 ********************/

	//Représente un ensemble de caractère permettent d'identifer un argument
	private static String CARACTERE_PATTERN = "(.*) <(.*)> (.*) </\\2>";
	
	/*******************************************************************
	 * @Titre: STRUCTURE FORME
	 * 
	 * @Résumer:
	 * De façon générale, on converti les valeurs pour uniformiser le tout
	 * et l'information décrivant une forme est structurée dans un ordre précis.
	 * 
	 * @Source:
	 * array.split(): http://stackoverflow.com/questions/14414582/java-split-string-to-array
	 *
	 *******************************************************************/
	public static final String[] main(String informationForme) {

		Pattern pattern = Pattern.compile(CARACTERE_PATTERN); 
		
		//Retourne l'information complète d'une forme 
		return findTag(pattern, informationForme);
	}

	/*******************************************************************
	 * @Titre: STRUCTURE FORME
	 * 
	 * @Résumer:
	 * De façon générale, on converti les valeurs pour uniformiser le tout
	 * et l'information décrivant une forme est structurée dans un ordre précis.
	 * 
	 * @Source:
	 * array.split(): http://stackoverflow.com/questions/14414582/java-split-string-to-array
	 *
	 *******************************************************************/
	private static String[] findTag(Pattern pattern, String informationForme) {
		
		//Strings qui composent l'identité d'une forme
		String 
		numeroSeq = null,
		identiteForme = null,
		dimensionsForme = null;
		
		//Liste qui garde en mémoire toute l'information concernant une forme
		String[] descriptionCompleteForme = new String[3];
		
		//Identification correspondance avec une forme abstraite
		Matcher matcher = pattern.matcher(informationForme);
		
		//Si un résultat est identifié, la primitive "resultat" tombe à true.
		boolean resultat = matcher.find();

		//On valide le résultat (true)
		if (resultat == true) {

			numeroSeq = matcher.group(1);
			identiteForme = matcher.group(2);
			dimensionsForme = matcher.group(3);
			
			System.out.println("\n*****************************\nPARAMÈTRES FORME:  " + matcher.group(2) + "\n*****************************");
			System.out.println("Numéro de séquence: " + matcher.group(1) + "\nDimensions: " + matcher.group(3) + "\n");
		}
	
		//Insertition des éléments qui composent l'identté d'une forme dans une liste
		descriptionCompleteForme[0] = numeroSeq;
		descriptionCompleteForme[1] = identiteForme; 
		descriptionCompleteForme[2] = dimensionsForme;
		
		//Retourne l'identité d'une forme et ses paramètres
		return descriptionCompleteForme;
	}	
}