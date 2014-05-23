package com.miage.umlconvertisseur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Convertisseur {

    // Permet d'initialiser la liste des mesures depuis le fichier XML
    public static List<Mesure> listeMesure = initialiserListMesure();



    public static BigDecimal convertirDeuxUnite(String mesure, String nomUniteIn,
	    String nomUniteOut, BigDecimal valeurIn) {
	//	    	context Main::convertir(mesure, uniteIn, uniteOut) pre
	//		 self->forAll(m:Mesure | m = mesure implies(m->forAll(u:Unite | u=uniteIn)))
	//		 self->forAll(m:Mesure | m = mesure implies(m->forAll(u:Unite | u=uniteOut)))

	// On cherche la valeur de la l'unit� OUT
	BigDecimal valeurOut = new BigDecimal("0");

	// On initialise les variables qui vont nous servir � r�aliser les
	// calculs
	BigDecimal multiIn = new BigDecimal("0");
	BigDecimal multiOut = new BigDecimal("0");
	BigDecimal additionIn = new BigDecimal("0");
	BigDecimal additionOut = new BigDecimal("0");

	Boolean in = true;
	Boolean out = true;

	// On va se placer dans la bonne mesure pour r�cup�rer multi et addition
	for (int i = 0; i < listeMesure.size(); i++) {
	    // Si le nom de la mesure existe
	    if (listeMesure.get(i).getNomMesure().equals(mesure)) {
		// On va chercher les deux unit�s
		for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {
		    // IN
		    if (listeMesure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUniteIn)) {
			multiIn = listeMesure.get(i).getLstUnite().get(j)
				.getmultiParRapportDefaut();
			additionIn = listeMesure.get(i).getLstUnite().get(j)
				.getadditionParRapportDefaut();
			in = false;
		    }
		    // OUT
		    if (listeMesure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUniteOut)) {
			multiOut = listeMesure.get(i).getLstUnite().get(j)
				.getmultiParRapportDefaut();
			additionOut = listeMesure.get(i).getLstUnite().get(j)
				.getadditionParRapportDefaut();
			out = false;
		    }
		}
	    }
	}

	if (in) {
	    System.out.println("  \n   L'unite " + nomUniteIn + " est introuvable, la conversion est donc impossible");
	}

	if (out) {
	    System.out.println("  \n   L'unite " + nomUniteOut + " est introuvable, la conversion est donc impossible");
	}

	// On teste si <> de 0
	if (!in && !out) {

	    // Etape 1, tranformer en unit� de base (exemple : 1km en m :  1 * 1000 (multi km)= 1000m)
	    BigDecimal valBase = valeurIn.multiply(multiIn).add(additionIn);

	    // Etape 2, transformer en unit� out
	    valeurOut = (valBase.subtract(additionOut)).divide(multiOut, 3, BigDecimal.ROUND_HALF_UP);

	    System.out.println("  " + valeurIn + " " + nomUniteIn + " = " + valeurOut + " " + nomUniteOut);
	}
	return valeurOut;
    }



    // ////////////////////////////
    // Initialiser list mesure //
    // ////////////////////////////
    private static List<Mesure> initialiserListMesure() {
	// Objectif, r�cup�rer la liste des diff�rentes mesures, contenant elles
	// m�me les diff�rentes unit�s
	List<Mesure> listeMesure = new ArrayList<Mesure>();

	// On r�cup�re les donn�es du fichier XML situ� ici
	String fichierXml = "src/main/java/org/data/stockage.xml";

	// Cr�ation d'un document xml (hors du try)
	Document documentXML = null;

	// On cherche � r�cup�rer l'ensemble du contenu du fichier xml
	try {
	    File donneeDufichier = new File(fichierXml);
	    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
		    .newInstance();
	    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    documentXML = docBuilder.parse(donneeDufichier);
	} catch (SAXException e) {
	    e.printStackTrace();
	    System.exit(0);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.exit(0);
	} catch (ParserConfigurationException e) {
	    e.printStackTrace();
	    System.exit(0);
	};

	// On r�cup�re la racine du fichier
	Element racineFichierXml = documentXML.getDocumentElement();

	// On r�cup�re la liste des mesures
	NodeList lstMesureXml = racineFichierXml.getElementsByTagName("mesure");

	// Pour chaque mesure, on r�cup�re l'ensemble des unit de la liste
	int i = 0;
	int lstMesureLength = lstMesureXml.getLength();
	for (i = 0; i < lstMesureLength; i++) {

	    // on r�cup�re notre mesure
	    Element oneMesure = (Element) lstMesureXml.item(i);
	    // On r�cup�re le nom de la mesure
	    String attribut = lstMesureXml.item(i).getAttributes()
		    .getNamedItem("nomMesure").getTextContent();

	    // On r�cup�re la liste des unit�s
	    NodeList lstUnit = oneMesure.getElementsByTagName("unite");

	    // on cr�� la liste des unit�s de la mesure
	    List<Unite> lstUnite = new ArrayList<Unite>();

	    // Pour chaque unit� de la liste
	    int lstUnitLength = lstUnit.getLength();
	    for (int j = 0; j < lstUnitLength; j++) {
		// on r�cupere le nom
		String nomUnite = lstUnit.item(j).getAttributes()
			.getNamedItem("nomUnite").getTextContent();
		// on r�cupere le multi et le d�calage, en string, donc le parse
		String multi = lstUnit.item(j).getAttributes()
			.getNamedItem("multi").getTextContent();
		BigDecimal multiBigDecimal = new BigDecimal(multi);
		String addition = lstUnit.item(j).getAttributes()
			.getNamedItem("addition").getTextContent();
		BigDecimal additionBigDecimal = new BigDecimal(addition);

		// on cr�� un objet unite avec ces informations
		Unite unite = new Unite(nomUnite, multiBigDecimal,
			additionBigDecimal);

		// on ajoute l'unite � la liste de unit� de la mesure
		lstUnite.add(unite);

	    }

	    // a chaque fois que l'on trouve une mesure on cr�e un objet mesure
	    Mesure mesure = new Mesure();
	    mesure.setNomMesure(attribut);
	    mesure.setLstUnite(lstUnite);

	    // On ajoute cette mesure � notre liste de mesure
	    listeMesure.add(mesure);
	}

	return listeMesure;
    }

    // ////////////
    // Ajouter //
    // ////////////
    public static void ajouterUnite(String nomMesure, String nomUnite,
	    BigDecimal multiUnite, BigDecimal additionUnite) {

	// on cr�� un objet unite avec ces informations
	Unite unite = new Unite(nomUnite, multiUnite, additionUnite);

	boolean doublon = false;
	boolean mesureExiste = false;

	for (int i = 0; i < listeMesure.size(); i++) {
	    if (listeMesure.get(i).getNomMesure().equals(nomMesure)) {
		mesureExiste = true;
		for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {
		    if (listeMesure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUnite)) {
			doublon = true;
		    }
		}
		if (!doublon) {
		    listeMesure.get(i).getLstUnite().add(unite);
		} else {
		    System.out.print(" \n   Impossible d'ajouter " + nomUnite
			    + ", cette unit� existe d�j� dans " + nomMesure
			    + " ! \n");
		}
	    }
	}
	if (!mesureExiste) {
	    System.out
	    .print(" \n   La mesure " + nomMesure
		    + " n'existe pas. Ajoute de " + nomUnite
		    + " impossible \n");
	}
	if (!doublon && mesureExiste) {
	    System.out.println(" \n  Ajout de l'unit� " + nomUnite + " effectu� ");
	    enregistrerInXML();
	}

    }

    // //////////////
    // Supprimer //
    // //////////////
    public static void supprimerUnite(String nomMesure, String nomUnite) {
	boolean validUnite = false;
	boolean validMesure = false;
	for (int i = 0; i < listeMesure.size(); i++) {
	    if (listeMesure.get(i).getNomMesure().equals(nomMesure)) {
		for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {
		    if (listeMesure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUnite)) {
			listeMesure.get(i).getLstUnite().remove(j);
			validUnite = true;
		    }
		}
	    } else {
		validMesure = true;
	    }
	}
	if (!validUnite) {
	    System.out
	    .print("\n   Suppression de " + nomUnite + " impossible ");
	    if (!validMesure) {
		System.out.println("car la Mesure " + nomMesure
			+ " n'existe pas ");
	    }
	} else {
	    System.out
	    .println("\n  Suppression de " + nomUnite + " effectu� ");
	    enregistrerInXML();
	}
    }

    // ///////////////////////////////////
    // Enregistrer dans le fichier XML //
    // ///////////////////////////////////
    public static void enregistrerInXML() {
	String fichierXml = "src/stockage.xml";
	File fic = new File(fichierXml);
	try {
	    Writer w = new FileWriter(fic);
	    String reecritureDansLeFichier = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><umlConvertisseur>";
	    for (int i = 0; i < listeMesure.size(); i++) {
		reecritureDansLeFichier += "<mesure nomMesure=\""
			+ listeMesure.get(i).getNomMesure() + "\">";
		for (int j = 0; j < listeMesure.get(i).getLstUnite().size(); j++) {

		    Unite unitCourant = listeMesure.get(i).getLstUnite().get(j);
		    reecritureDansLeFichier += "<unite nomUnite=\""
			    + unitCourant.getNomUnite() + "\" multi=\""
			    + unitCourant.getmultiParRapportDefaut()
			    + "\" addition=\""
			    + unitCourant.getadditionParRapportDefaut()
			    + "\" />";
		}
		reecritureDansLeFichier += "</mesure>";
	    }
	    reecritureDansLeFichier += "</umlConvertisseur>";
	    w.write(reecritureDansLeFichier);
	    w.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
