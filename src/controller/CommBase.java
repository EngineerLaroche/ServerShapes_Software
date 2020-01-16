package controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import model.FormesAbstraites;

/*******************************************************************
 * @Titre_Classe: BASE COMMUNICATION
 * 
 * @Résumer: 
 * Fourni les outils au support de la communication entre le client et 
 * le serveur via un fil d'exécution parallèle.
 * 
 * La création d'un pont (nom Hote et numero port), le démarrage, l'arrêt 
 * et l'activité de la communication sont au rendez-vous.
 * 
 * @Auteur: Alexandre Laroche
 * @Date: 22 mai 2017 
 *******************************************************************/ 
public class CommBase implements WindowListener {

	/***************************
	 * Classes instanciées
	 ***************************/

	private SwingWorker threadComm;
	private PropertyChangeListener listener;
	private CommunicationServeur communicationServeur; 

	/********************
	 * Constantes
	 ********************/

	//Donne un délai de 1000 ms = 1 sec
	private final int DELAI = 1000; 

	/********************
	 * Variables
	 ********************/ 
	
	//Garde en mémoire la sélection du bouton JOptionPane
	private int option;

	//Garde en mémoire le nom qui identifie l'hote et numéro de port de la connexion TCP/IP
	private String nomHote, numeroPort;

	//Démontre l'activité de l'application (actif ou non)
	private boolean estActif = false; 

	/********************
	 * Constructeur
	 ********************/ 
	public CommBase(){

		nomHote = null;
		listener = null;
		numeroPort = null;
		threadComm = null;
		communicationServeur = null; 
	}

	/*************************************************************************
	 * @Titre: ECOUTEUR CHANGEMENT
	 * 
	 * @Résumer:
	 * Definir le recepteur de l'information recue dans la communication 
	 * avec le serveur. listener sera alertee lors de l'appel de 
	 * "firePropertyChanger" par le SwingWorker.
	 * 
	 *************************************************************************/
	public void setPropertyChangeListener(PropertyChangeListener listener){

		this.listener = listener;
	}

	/*************************************************************************
	 * @Titre: DÉMARRAGE COMMUNICATION
	 * 
	 * @Résumer:
	 * On demande à l'utilisateur d'entrer les paramètres de connexion et 
	 * on vérifie ensuite le numéro de port ainsi que l'activité du ServeurForme
	 * pour s'assurer que tout est en ordre. On procède ensuite à la 
	 * création d'un pond de communication avec le ServeurForme.
	 * 
	 * @Source 
	 * JOptionPane Alerte:	https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
	 * 
	 *************************************************************************/
	public void start(){

		//Démarrage de la fenêtre de demande d'information de connexion
		fenetreInputConnexion();
	
		
		//Si l'utilisateur sélectionne le bouton "Cancel"
		if (option == JOptionPane.CANCEL_OPTION) {
			
			//Met fin à la fenêtre JOptionPane
			return;
		}
		
		//Création d'un pond de communication avec le ServeurForme à partir des paramètres entrées
		communicationServeur = new CommunicationServeur(nomHote, Integer.parseInt(numeroPort));
	
		//Tant que la communication avec le serveur échoue (null)
		while(communicationServeur.getSocketTCP() == null){

			//On averti l'utilisateur que le port entré ne concorde avec aucuns ServeurForme
			JOptionPane.showMessageDialog(null, "<html><b style='color:red'>Tentative de connexion au ServeurForme impossible.</b><br>"
					+ "\n\n<html><b style='color:orange'>Veuillez vous assurer de la conformité du </b><br>"
					+ "<html><b style='color:orange'>numéro de port et de l'activité du serveur.</b><br>"
					+ "\n\nAppuyez sur <OK> pour une nouvelle tentative de connexion. ", "Avertissement", 
					JOptionPane.WARNING_MESSAGE);
			
			//Nouvelle tentative de connexion pour l'utilisateur
			fenetreInputConnexion();
			
			//Si l'utilisateur sélectionne le bouton "Cancel"
			if (option == JOptionPane.CANCEL_OPTION) {
				
				//Met fin à la fenêtre JOptionPane
				return;
			}

			//Création d'un pond de communication avec le ServeurForme à partir des paramètres entrées
			communicationServeur = new CommunicationServeur(nomHote, Integer.parseInt(numeroPort));
		}

		System.out.println("\n*****************************\nPARAMÈTREs DE CONNEXION\n*****************************");
		System.out.println("Nom de l'hôte: " + nomHote);	
		System.out.println("Numéro de port: " + numeroPort);

		//Démarrage de la communication entre le client et le serveur
		creerCommunication();
	}

	/*************************************************************************
	 * @Titre: FENÊTRE INPUT CONNEXION
	 * 
	 * @Résumer:
	 * Demande à l'utilisateur d'inscrire un nom d'hôte et un 
	 * numero de port pour créer une connexion au serveur. 
	 * S'assusre que l'utilisateur entre les bons caractères 
	 * dans les bonnes cases pour éviter d'éventuels problèmes de connexions.
	 * 
	 * @Source 
	 * JOptionPane Erreurs: 	https://stackoverflow.com/questions/34391402/show-input-dialog-box-after-error-message-dialog-box
	 * JOptionPane Inputs: 		https://stackoverflow.com/questions/27207887/java-jbutton-opening-another-jframe-that-i-can-input-into
	 * 
	 *************************************************************************/
	public void fenetreInputConnexion(){

		//Espaces pour écrire le nom de l'hôte et le numéro de port
		JTextField hote = new JTextField();
		JTextField port = new JTextField();

		//Affichent les messages d'erreurs du nom de l'hôte et du numéro de 
		JLabel erreurHote = new JLabel();
		JLabel erreurPort = new JLabel();

		//Regroupement des messages d'erreurs et des zones d'écritures pour le JOptionPane
		Object[] message = {erreurHote, "Nom Hote:", hote, 
				erreurPort, "Numero Port:", port};

		//On procède à des vérifications pour éviter les erreurs d'inscriptions
		do{

			//Fenêtre qui demande le nom d'hôte et le numéro de port du serveur formes
			option = JOptionPane.showConfirmDialog(null, message, "Paramètres de connexion", JOptionPane.OK_CANCEL_OPTION);

			//Garde en mémoire le nom de l'hôte entré
			nomHote = hote.getText();

			//Garde en mémoire le numéro de port entré
			numeroPort = port.getText();

			//Si l'utilisateur sélectionne le bouton "Ok"
			if (option == JOptionPane.OK_OPTION) {

				//Si l'utilisateur entre autre chose que des lettres ou laisse un espace vide
				if(!nomHote.matches("[a-zA-Z]+") || nomHote.equals("")){ 

					//Message d'avertissement en rouge
					erreurHote.setText("<html><b style='color:red'>Entrez uniquement des lettres</b><br>");
				}

				//Si l'utilisateur entre des lettres et qu'il n'y pas d'espace vide, on retire le message d'erreur
				if(nomHote.matches("[a-zA-Z]+") && !nomHote.equals("")){ 

					//Retire message d'avertissement 
					erreurHote.setText("");
				}

				//Si l'utilisateur entre autre chose que des nombres ou laisse un espace vide
				if(!numeroPort.matches("[0-9]+") || numeroPort.equals("")){ 

					//Message d'avertissement en rouge
					erreurPort.setText("<html><b style='color:red'>Entrez uniquement des nombres</b><br>");
				}

				//Si l'utilisateur entre des nombres et qu'il n'y pas d'espace vide, on retire le message d'erreur
				if(numeroPort.matches("[0-9]+") && !numeroPort.equals("")){ 

					//Retire message d'avertissement 
					erreurPort.setText("");
				}
			}
			//Si l'utilisateur sélectionne le bouton "Cancel"
			if (option == JOptionPane.CANCEL_OPTION) {
				
				//Met fin à la fenêtre JOptionPane
				return;
			}
		}
		//Tant qu'il n'y a pas d'erreurs d'inscription pour la création d'une connexion TCP/IP, on procède.
		while(nomHote != "" && !nomHote.matches("[a-zA-Z]+") || numeroPort != "" && !numeroPort.matches("[0-9]+")); 

	}

	/*************************************************************************
	 * @Titre: ARRÊT COMMUNICATION
	 * 
	 * @Résumer:
	 * Lorsque l'application est ouverte et que la méthode est appelée, 
	 * on procède à l'arrête de la communication.
	 * 
	 *************************************************************************/
	public void stop(){

		if(threadComm != null)

			threadComm.cancel(true); 

		estActif = false;

		communicationServeur.requeteArret();

		System.out.println("\n*****************************\nFERMETURE CONNEXION\n*****************************");
		System.out.println("Arrêt complète de la communication entre client et serveur "
				+ "\npour <" + nomHote + "> sur le port " + numeroPort);
	}

	/*************************************************************************
	 * @Titre: CREATION COMMUNICATION
	 * 
	 * @Résumer:
	 * Utilisation du SwingWorker.
	 * Créer le nécessaire pour la communication au serveur
	 * 
	 *************************************************************************/
	protected void creerCommunication(){		

		// Cree un fil d'execusion parallele au fil courant
		threadComm = new SwingWorker(){

			@Override
			protected Object doInBackground() throws Exception {

				System.out.println("\n*****************************\nCOMMUNICATION CLIENT-SERVEUR\n*****************************");
				System.out.println("Le fils d'execution parallele est lancé");

				//On communique avec le ServeurFormes dans cette boucle
				while(true){

					Thread.sleep(DELAI);

					//On alerte l'observateur 
					if(listener != null) {

						//Garde en mémoire une forme identifié par TagParser
						String[] reponse = TagParser.main(communicationServeur.requeteForme());

						//On déclanche le processus de création de formes avec du data formes en paramètres
						ConceptionFormes.structureForme(reponse);

						//On reçoit la conception d'une nouvelle forme 
						FormesAbstraites formesAbstraites = ConceptionFormes.formationFormes();

						firePropertyChange("ENVOIE-TEST", null, (Object) formesAbstraites);
					}
				}
			}
		};

		if(listener != null)

			// "propertyChange" sera appelé lorsque  le SwinkWorker invoque "firePropertyChanger" 		
			threadComm.addPropertyChangeListener(listener); 

		// Lance le fil d'execution parallele.
		threadComm.execute(); 

		estActif = true;
	}

	/*************************************************************************
	 * @Titre: EST ACTIF
	 * 
	 * @Résumer:
	 * Lorsque la méthode est appelée, on rourne le fil d'execution 
	 * parallele de façon à être actif.
	 * 
	 *************************************************************************/
	public boolean isActif(){

		return estActif;
	}

	/*************************************************************************
	 * @Titre: LISTENER FERMETURE FENÊTRE
	 * 
	 * @Résumer:
	 * Lorsque l'utilisateur ferme la fenêtre, on procède à l'arrêt de la 
	 * communication entre le client et le serveur pour éviter les problèmes
	 * de connexions.
	 * 
	 *************************************************************************/
	@Override
	public void windowClosing(WindowEvent e) {

		//Si la communication avec le serveur est en cours
		if (communicationServeur != null) {

			//On ferme l'activité (false) par précaution
			estActif = false;
			
			//On demande l'arrêt de la communication
			communicationServeur.requeteArret();

			System.out.println("\n*****************************\nFERMETURE FENÊTRE\n*****************************");
			System.out.println("Interruption de la connexion pour <" + nomHote + "> sur le port " + numeroPort + "\n");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
	}
}