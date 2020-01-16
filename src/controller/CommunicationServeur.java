package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*******************************************************************
 * @Titre_Classe: COMMUNICATION SERVEUR
 * 
 * @R�sumer: 
 * Processus de validation et de cr�ation du socket TCP (connexion).
 * Utilisation des classes PrintWriter et BufferedReader pour 
 * capturer les requ�tes de l'utilisateur pour les envoyer
 * vers le serveur formes.
 * 
 * @Source:
 * Socket: 			https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html 
 * Buffered Reader: https://stackoverflow.com/questions/6328125/java-reading-from-a-buffered-reader-from-a-socket-is-pausing-the-thread
 * PrintWriter: 	https://stackoverflow.com/questions/12306708/printwriter-in-java-socket-program
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 26 mai 2017 
 *******************************************************************/ 
public class CommunicationServeur{
	
	/***************************
	 * Classes instanci�es
	 ***************************/

	private Socket socketTCP;
	private PrintWriter serviceOut;
	private BufferedReader serviceIn;
	
	private boolean panneServeur = false;
	
	//Les messages 
	private static final String 
	PRINT_GET = "GET",
	PRINT_END = "END",
	PRINT_EMPTY = "";

	/********************
	 * Constructeur
	 ********************/ 
	public CommunicationServeur(){}
		
	public CommunicationServeur(String nomHote, int numeroPort) { 

		try{

			//Cr�ation d'un nouveau socket � partir du nom d'h�te et du num�ro de port donn�
			socketTCP = new Socket(nomHote, numeroPort);	

			//Nouvelle �criture de caract�re � partir du flux tamponn� (socket).
			serviceOut = new PrintWriter(socketTCP.getOutputStream(), true);

			//Nouvelle lecture de caract�res � partir du flux tamponn� (socket).
			serviceIn = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));
			
		}
		//Attrape les probl�mes associ�s � l'h�te. 
		catch (UnknownHostException e) {

			System.out.println("\n*****************************\nERREUR NOM H�TE\n*****************************");
			System.out.println("H�te " + nomHote + ". Code erreur: " + e + "\n");

		}
		//Attrape les probl�mes associ�s au service TCP.
		catch (IOException e) {

			System.out.println("\n*****************************\nERREUR PORT TCP/IP\n*****************************");
			System.out.println("Socket TCP " + socketTCP + ". \nCode erreur: " + e + "\n");
		}
	}

	/******************************************************************************************************************
	 * @Titre: REQU�TE FORME
	 * 
	 * @R�sumer:
	 * Processus qui permet la requ�te d'une forme au serveur. 
	 * Si l'utilisateur d�cide d'activer une connexion, la commande "GET" est envoy� au serveur.
	 * On s'assure d'offrir un processus de type Continue pour permettre l'affichage des formes en boucle.
	 * 
	 * @Source 
	 * readLine(): 	https://stackoverflow.com/questions/8560395/how-to-use-readline-method-in-java
	 * Exception: 	http://www.math.uni-hamburg.de/doc/java/tutorial/networking/sockets/readingWriting.html
	 *
	 ******************************************************************************************************************/
	public String requeteForme() {

		String reponseRequeteForme = null;

		try{

			//Imprime la commande <GET> 
			serviceOut.println(PRINT_GET);

			//R�agit en fonction de la commande <GET>
			reponseRequeteForme = serviceIn.readLine();

			//Si l'utilisateur ne s�lectionne pas d'autres options, on continue
			if(reponseRequeteForme.contains(PRINT_EMPTY)){ 

				//Imprime la commande <GET> 
				serviceOut.println(PRINT_GET);

				//R�agit en fonction de la commande <GET>
				reponseRequeteForme = serviceIn.readLine();
			}
		} 
		//Attrape les erreurs de la requ�te "GET"
		catch (IOException e) {
			
			//Si le serveur tombe en panne, l'utilisateur pourra proc�der � un nouvelle tentive de connexion
			panneServeur = true;

			System.out.println("\n*****************************\nPANNE SERVEUR <GET>\n*****************************");
			System.out.println("Code erreur: " + e + "\n");
		}

		//Retourne l'information d'une forme g�n�r�e par le serveur
		return reponseRequeteForme;
	}

	/******************************************************************************************************************
	 * @Titre: REQU�TE ARR�T
	 * 
	 * @R�sumer:
	 * Processus qui permet l'arr�t des requ�tes de formes vers le serveur. 
	 * Si l'utilisateur d�cide d'arr�ter le connexion, la commande "END" est envoy� au serveur.
	 * 
	 * @Source 
	 * readLine(): 	https://stackoverflow.com/questions/8560395/how-to-use-readline-method-in-java
	 * Exception: 	http://www.math.uni-hamburg.de/doc/java/tutorial/networking/sockets/readingWriting.html
	 *
	 ******************************************************************************************************************/
	public String requeteArret() {		

		String reponseRequeteArret = null;

		try{

			//Imprime la commande <END>
			serviceOut.println(PRINT_END);

			//R�agit en fonction la commande <END>
			reponseRequeteArret = serviceIn.readLine();

			System.out.println("\nREQU�TE ARR�T CONNEXION");

		}
		//Attrape les erreurs de la requ�te "END"
		catch(IOException e){

			//Si le serveur tombe en panne, l'utilisateur pourra proc�der � un nouvelle tentive de connexion
			panneServeur = true;
			
			System.out.println("\n*****************************\nPANNE SERVEUR <END>\n*****************************");
			System.out.println("Code erreur: " + e + "\n");
		}
		return reponseRequeteArret;
	}

	/**********************************************************
	 * @Titre: GET SOCKET
	 * 
	 * @R�sumer:
	 * Retourne socketTCP pour aider � identifier les probl�mes
	 * de connexions.
	 *
	 **********************************************************/
	public Socket getSocketTCP(){

		return socketTCP;
	}
	
	/**********************************************************
	 * @Titre: GET PANNE SERVEUR
	 * 
	 * @R�sumer:
	 * Retourne une indication qui permet de rep�rer une
	 * panne au serveur.
	 *
	 **********************************************************/
	public boolean serveurActif() {

		return panneServeur;
	}
}