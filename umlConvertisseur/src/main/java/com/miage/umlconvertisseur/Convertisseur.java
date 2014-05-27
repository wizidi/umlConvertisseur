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

    // initialise la liste des mesures depuis le fichier XML
    // initializes the list of measures from the XML file
    public static List<Mesure> listMeasure = initialiserListMesure();

    public static BigDecimal convertirDeuxUnite(String mesure, String nomUniteIn,
	    String nomUniteOut, BigDecimal valeurIn) {

	// On cherche la valeur de la l'unite OUT
	// The value of the unity we seek OUT
	BigDecimal valeurOut = new BigDecimal("0");

	// On initialise les variables qui vont nous servir a realiser les calculs
	// We initialize variables that will serve us realize calculations
	BigDecimal multiIn = new BigDecimal("0");
	BigDecimal multiOut = new BigDecimal("0");
	BigDecimal additionIn = new BigDecimal("0");
	BigDecimal additionOut = new BigDecimal("0");

	Boolean in = true;
	Boolean out = true;

	// On va se placer dans la bonne mesure pour recuperer multi et addition
	// It will be placed in good measure to recover the multiplier and adding
	for (int i = 0; i < listMeasure.size(); i++) {
	    // Si le nom de la mesure existe
	    if (listMeasure.get(i).getNomMesure().equals(mesure)) {
		// On va chercher les deux unites
		// If the name of the measure are
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    // IN
		    if (listMeasure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUniteIn)) {
			multiIn = listMeasure.get(i).getLstUnite().get(j)
				.getmultiParRapportDefaut();
			additionIn = listMeasure.get(i).getLstUnite().get(j)
				.getadditionParRapportDefaut();
			in = false;
		    }
		    // OUT
		    if (listMeasure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUniteOut)) {
			multiOut = listMeasure.get(i).getLstUnite().get(j)
				.getmultiParRapportDefaut();
			additionOut = listMeasure.get(i).getLstUnite().get(j)
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
	// It tests whether <> 0
	if (!in && !out) {

	    // Etape 1, tranformer en unite de base (exemple : 1km en m :  1 * 1000 (multi km)= 1000m)
	    // Step 2, tranformer in base unit (eg 1km m: 1 * 1000 (multi km) = 1000m)
	    BigDecimal valBase = valeurIn.multiply(multiIn).add(additionIn);

	    // Etape 2, transformer en unite out
	    // Step 2, into unit out
	    valeurOut = (valBase.subtract(additionOut)).divide(multiOut, 3, BigDecimal.ROUND_HALF_UP);

	    System.out.println("  " + valeurIn + " " + nomUniteIn + " = " + valeurOut + " " + nomUniteOut);
	}
	return valeurOut;
    }



    // ////////////////////////////
    // Initialiser list mesure //
    // ////////////////////////////
    private static List<Mesure> initialiserListMesure() {
	// Objectif, recuperer la liste des differentes mesures, contenant elles meme les differentes unites
	// Objective, retrieve a list of different measures, they even contain the different units
	List<Mesure> listeMesure = new ArrayList<Mesure>();

	// On recupere les donnees du fichier XML situe ici
	// The data from the XML file is recovered is here
	String fichierXml = "src/main/java/org/data/stockage.xml";

	// Creation d'un document xml (hors du try)
	// Creation of an xml documents (excluding try)
	Document documentXML = null;

	// On cherche a recuperer l'ensemble du contenu du fichier xml
	// It seeks to recover the entire contents of the xml file
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

	// On recupere la racine du fichier
	// The root of the file it recovers
	Element racineFichierXml = documentXML.getDocumentElement();

	// On recupere la liste des mesures
	// It recovers the list of measures
	NodeList lstMesureXml = racineFichierXml.getElementsByTagName("mesure");

	// Pour chaque mesure, on recupere l'ensemble des unit de la liste
	// For each measure, we recovered all the unit list
	int i = 0;
	int lstMesureLength = lstMesureXml.getLength();
	for (i = 0; i < lstMesureLength; i++) {

	    // on recupere notre mesure
	    // we recovered our measure
	    Element oneMesure = (Element) lstMesureXml.item(i);
	    
	    // On recupere le nom de la mesure
	    // We recovered the name of the measure
	    String attribut = lstMesureXml.item(i).getAttributes()
		    .getNamedItem("nomMesure").getTextContent();

	    // On recupere la liste des unites
	    // It recovers the list of units
	    NodeList lstUnit = oneMesure.getElementsByTagName("unite");

	    // on cree la liste des unites de la mesure
	    // we created a list of units of measurement
	    List<Unite> lstUnite = new ArrayList<Unite>();

	    // Pour chaque unite de la liste
	    // For each unit in the list
	    int lstUnitLength = lstUnit.getLength();
	    for (int j = 0; j < lstUnitLength; j++) {
		// on recupere le nom
		// we recovered the name
		String nomUnite = lstUnit.item(j).getAttributes()
			.getNamedItem("nomUnite").getTextContent();
		// on recupere le multi et l'addition, en string, donc le parse
		// multi and addition is recovered in string, so the parse
		String multi = lstUnit.item(j).getAttributes()
			.getNamedItem("multi").getTextContent();
		BigDecimal multiBigDecimal = new BigDecimal(multi);
		String addition = lstUnit.item(j).getAttributes()
			.getNamedItem("addition").getTextContent();
		BigDecimal additionBigDecimal = new BigDecimal(addition);

		// on cree un objet unite avec ces informations
		// we created a unit object with this information
		Unite unite = new Unite(nomUnite, multiBigDecimal,
			additionBigDecimal);

		// on ajoute l'unite a la liste de unite de la mesure
		// unit is added to the list of units of measurement
		lstUnite.add(unite);

	    }

	    // a chaque fois que l'on trouve une mesure on cree un objet mesure
	    // every time we find a measure a measure object is created
	    Mesure mesure = new Mesure();
	    mesure.setNomMesure(attribut);
	    mesure.setLstUnite(lstUnite);

	    // On ajoute cette mesure a notre liste de mesure
	    // This measurement is added to our list of measurement
	    listeMesure.add(mesure);
	}

	return listeMesure;
    }

    // /////////////////
    // Ajouter // ADD //
    // /////////////////
    public static void ajouterUnite(String nomMesure, String nomUnite,
	    BigDecimal multiUnite, BigDecimal additionUnite) {

	// on cree un objet unite avec ces informations
	// We created a unit object with this information
	Unite unite = new Unite(nomUnite, multiUnite, additionUnite);

	boolean doublon = false;
	boolean mesureExiste = false;

	for (int i = 0; i < listMeasure.size(); i++) {
	    if (listMeasure.get(i).getNomMesure().equals(nomMesure)) {
		mesureExiste = true;
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    if (listMeasure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUnite)) {
			doublon = true;
		    }
		}
		if (!doublon) {
		    listMeasure.get(i).getLstUnite().add(unite);
		} else {
		    System.out.print(" \n   Impossible d'ajouter " + nomUnite
			    + ", cette unite existe deja dans " + nomMesure
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
	    System.out.println(" \n  Ajout de l'unite " + nomUnite + " effectue ");
	    enregistrerInXML();
	}

    }

    // ////////////
    // Supprimer //
    // ////////////
    public static void supprimerUnite(String nomMesure, String nomUnite) {
	boolean validUnite = false;
	boolean validMesure = false;
	for (int i = 0; i < listMeasure.size(); i++) {
	    if (listMeasure.get(i).getNomMesure().equals(nomMesure)) {
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    if (listMeasure.get(i).getLstUnite().get(j).getNomUnite()
			    .equals(nomUnite)) {
			listMeasure.get(i).getLstUnite().remove(j);
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
	    .println("\n  Suppression de " + nomUnite + " effectue ");
	    enregistrerInXML();
	}
    }

    // /////////////////////////////////////////////////////// 
    // Enregistrer dans le fichier XML // Save the XML file // 
    // /////////////////////////////////////////////////////// 
    public static void enregistrerInXML() {
	String fichierXml = "src/stockage.xml";
	File fic = new File(fichierXml);
	try {
	    Writer w = new FileWriter(fic);
	    String reecritureDansLeFichier = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><umlConvertisseur>";
	    for (int i = 0; i < listMeasure.size(); i++) {
		reecritureDansLeFichier += "<mesure nomMesure=\""
			+ listMeasure.get(i).getNomMesure() + "\">";
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {

		    Unite unitCourant = listMeasure.get(i).getLstUnite().get(j);
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
