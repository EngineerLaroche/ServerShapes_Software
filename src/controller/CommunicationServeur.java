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
 * @Résumer: 
 * Processus de validation et de création du socket TCP (connexion).
 * Utilisation des classes PrintWriter et BufferedReader pour 
 * capturer les requêtes de l'utilisateur pour les envoyer
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
	 * Classes instanciées
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

			//Création d'un nouveau socket à partir du nom d'hôte et du numéro de port donné
			socketTCP = new Socket(nomHote, numeroPort);	

			//Nouvelle écriture de caractère à partir du flux tamponné (socket).
			serviceOut = new PrintWriter(socketTCP.getOutputStream(), true);

			//Nouvelle lecture de caractères à partir du flux tamponné (socket).
			serviceIn = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));
			
		}
		//Attrape les problèmes associés à l'hôte. 
		catch (UnknownHostException e) {

			System.out.println("\n*****************************\nERREUR NOM HÔTE\n*****************************");
			System.out.println("Hôte " + nomHote + ". Code erreur: " + e + "\n");

		}
		//Attrape les problèmes associés au service TCP.
		catch (IOException e) {

			System.out.println("\n*****************************\nERREUR PORT TCP/IP\n*****************************");
			System.out.println("Socket TCP " + socketTCP + ". \nCode erreur: " + e + "\n");
		}
	}

	/******************************************************************************************************************
	 * @Titre: REQUÊTE FORME
	 * 
	 * @Résumer:
	 * Processus qui permet la requête d'une forme au serveur. 
	 * Si l'utilisateur décide d'activer une connexion, la commande "GET" est envoyé au serveur.
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

			//Réagit en fonction de la commande <GET>
			reponseRequeteForme = serviceIn.readLine();

			//Si l'utilisateur ne sélectionne pas d'autres options, on continue
			if(reponseRequeteForme.contains(PRINT_EMPTY)){ 

				//Imprime la commande <GET> 
				serviceOut.println(PRINT_GET);

				//Réagit en fonction de la commande <GET>
				reponseRequeteForme = serviceIn.readLine();
			}
		} 
		//Attrape les erreurs de la requête "GET"
		catch (IOException e) {
			
			//Si le serveur tombe en panne, l'utilisateur pourra procéder à un nouvelle tentive de connexion
			panneServeur = true;

			System.out.println("\n*****************************\nPANNE SERVEUR <GET>\n*****************************");
			System.out.println("Code erreur: " + e + "\n");
		}

		//Retourne l'information d'une forme générée par le serveur
		return reponseRequeteForme;
	}

	/******************************************************************************************************************
	 * @Titre: REQUÊTE ARRÊT
	 * 
	 * @Résumer:
	 * Processus qui permet l'arrêt des requêtes de formes vers le serveur. 
	 * Si l'utilisateur décide d'arrêter le connexion, la commande "END" est envoyé au serveur.
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

			//Réagit en fonction la commande <END>
			reponseRequeteArret = serviceIn.readLine();

			System.out.println("\nREQUÊTE ARRÊT CONNEXION");

		}
		//Attrape les erreurs de la requête "END"
		catch(IOException e){

			//Si le serveur tombe en panne, l'utilisateur pourra procéder à un nouvelle tentive de connexion
			panneServeur = true;
			
			System.out.println("\n*****************************\nPANNE SERVEUR <END>\n*****************************");
			System.out.println("Code erreur: " + e + "\n");
		}
		return reponseRequeteArret;
	}

	/**********************************************************
	 * @Titre: GET SOCKET
	 * 
	 * @Résumer:
	 * Retourne socketTCP pour aider à identifier les problèmes
	 * de connexions.
	 *
	 **********************************************************/
	public Socket getSocketTCP(){

		return socketTCP;
	}
	
	/**********************************************************
	 * @Titre: GET PANNE SERVEUR
	 * 
	 * @Résumer:
	 * Retourne une indication qui permet de repérer une
	 * panne au serveur.
	 *
	 **********************************************************/
	public boolean serveurActif() {

		return panneServeur;
	}
}