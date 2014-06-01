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

/**
 * @author Antoine
 * 
 *
 */
public class Converter {

    // initialise la liste des categorieUnites depuis le fichier XML
    // initializes the list of measures from the XML file
    public static List<CategorieUnite> listMeasure = initialiserListCategorieUnite();

    public static BigDecimal convertirDeuxUnite(String categorieUnite, String nameUniteIn,
	    String nameUniteOut, BigDecimal valueIn) {

	// On cherche la value de la l'unite OUT
	// The value of the unity we seek OUT
	BigDecimal valueOut = new BigDecimal("0");

	// On initialise les variables qui vont nous servir a realiser les calculs
	// We initialize variables that will serve us realize calculations
	BigDecimal multiIn = new BigDecimal("0");
	BigDecimal multiOut = new BigDecimal("0");
	BigDecimal additionIn = new BigDecimal("0");
	BigDecimal additionOut = new BigDecimal("0");

	Boolean in = true;
	Boolean out = true;

	// On va se placer dans la bonne categorieUnite pour recuperer multi et addition
	// It will be placed in good measure to recover the multiplier and adding
	for (int i = 0; i < listMeasure.size(); i++) {
	    // Si le name de la categorieUnite existe
	    if (listMeasure.get(i).getNameCategorieUnite().equals(categorieUnite)) {
		// On va chercher les deux unites
		// If the name of the measure are
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    // IN
		    if (listMeasure.get(i).getLstUnite().get(j).getNameUnite()
			    .equals(nameUniteIn)) {
			multiIn = listMeasure.get(i).getLstUnite().get(j)
				.getmultiParRapportDefaut();
			additionIn = listMeasure.get(i).getLstUnite().get(j)
				.getadditionParRapportDefaut();
			in = false;
		    }
		    // OUT
		    if (listMeasure.get(i).getLstUnite().get(j).getNameUnite()
			    .equals(nameUniteOut)) {
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
	    System.out.println("  \n   L'unite " + nameUniteIn + " est introuvable, la conversion est donc impossible");
	}

	if (out) {
	    System.out.println("  \n   L'unite " + nameUniteOut + " est introuvable, la conversion est donc impossible");
	}

	// On teste si <> de 0
	// It tests whether <> 0
	if (!in && !out) {

	    // Etape 1, tranformer en unite de base (exemple : 1km en m :  1 * 1000 (multi km)= 1000m)
	    // Step 2, tranformer in base unit (eg 1km m: 1 * 1000 (multi km) = 1000m)
	    BigDecimal valBase = valueIn.multiply(multiIn).add(additionIn);

	    // Etape 2, transformer en unite out
	    // Step 2, into unit out
	    valueOut = (valBase.subtract(additionOut)).divide(multiOut, 3, BigDecimal.ROUND_HALF_UP);

	    System.out.println("  " + valueIn + " " + nameUniteIn + " = " + valueOut + " " + nameUniteOut);
	}
	return valueOut;
    }



    // ////////////////////////////
    // Initialiser list categorieUnite //
    // ////////////////////////////
    private static List<CategorieUnite> initialiserListCategorieUnite() {
	// Objectif, recuperer la liste des differentes categorieUnites, contenant elles meme les differentes unites
	// Objective, retrieve a list of different measures, they even contain the different units
	List<CategorieUnite> listeCategorieUnite = new ArrayList<CategorieUnite>();

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
	} catch (SAXException | IOException | ParserConfigurationException e) {
	    e.printStackTrace();
	    System.exit(0);
	
	};

	// On recupere la racine du fichier
	// The root of the file it recovers
	Element racineFichierXml = documentXML.getDocumentElement();

	// On recupere la liste des categorieUnites
	// It recovers the list of measures
	NodeList lstCategorieUniteXml = racineFichierXml.getElementsByTagName("categorieUnit");

	// Pour chaque categorieUnite, on recupere l'ensemble des unit de la liste
	// For each measure, we recovered all the unit list
	int i = 0;
	int lstCategorieUniteLength = lstCategorieUniteXml.getLength();
	for (i = 0; i < lstCategorieUniteLength; i++) {

	    // on recupere notre categorieUnite
	    // we recovered our measure
	    Element oneCategorieUnite = (Element) lstCategorieUniteXml.item(i);
	    
	    // On recupere le name de la categorieUnite
	    // We recovered the name of the measure
	    String attribut = lstCategorieUniteXml.item(i).getAttributes()
		    .getNamedItem("nameCategorieUnit").getTextContent();

	    // On recupere la liste des unites
	    // It recovers the list of units
	    NodeList lstUnit = oneCategorieUnite.getElementsByTagName("unit");

	    // on cree la liste des unites de la categorieUnite
	    // we created a list of units of measurement
	    List<Unit> lstUnite = new ArrayList<Unit>();

	    // Pour chaque unite de la liste
	    // For each unit in the list
	    int lstUnitLength = lstUnit.getLength();
	    for (int j = 0; j < lstUnitLength; j++) {
		// on recupere le name
		// we recovered the name
		String nameUnite = lstUnit.item(j).getAttributes()
			.getNamedItem("nameUnit").getTextContent();
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
		Unit unit = new Unit(nameUnite, multiBigDecimal,
			additionBigDecimal);

		// on ajoute l'unite a la liste de unite de la categorieUnite
		// unit is added to the list of units of measurement
		lstUnite.add(unit);

	    }

	    // a chaque fois que l'on trouve une categorieUnite on cree un objet categorieUnite
	    // every time we find a measure a measure object is created
	    CategorieUnite categorieUnite = new CategorieUnite();
	    categorieUnite.setNameCategorieUnite(attribut);
	    categorieUnite.setLstUnite(lstUnite);

	    // On ajoute cette categorieUnite a notre liste de categorieUnite
	    // This measurement is added to our list of measurement
	    listeCategorieUnite.add(categorieUnite);
	}

	return listeCategorieUnite;
    }

    // /////////////////
    // Ajouter // ADD //
    // /////////////////
    public static void addUnit(String nameCategorieUnite, String nameUnite,
	    BigDecimal multiUnite, BigDecimal additionUnite) {

	// on cree un objet unite avec ces informations
	// We created a unit object with this information
	Unit unit = new Unit(nameUnite, multiUnite, additionUnite);

	boolean doublon = false;
	boolean categorieUniteExiste = false;

	for (int i = 0; i < listMeasure.size(); i++) {
	    if (listMeasure.get(i).getNameCategorieUnite().equals(nameCategorieUnite)) {
		categorieUniteExiste = true;
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    if (listMeasure.get(i).getLstUnite().get(j).getNameUnite()
			    .equals(nameUnite)) {
			doublon = true;
		    }
		}
		if (!doublon) {
		    listMeasure.get(i).getLstUnite().add(unit);
		} else {
		    System.out.print(" \n   Impossible d'ajouter " + nameUnite
			    + ", cette unite existe deja dans " + nameCategorieUnite
			    + " ! \n");
		}
	    }
	}
	if (!categorieUniteExiste) {
	    System.out
	    .print(" \n   La categorieUnite " + nameCategorieUnite
		    + " n'existe pas. Ajoute de " + nameUnite
		    + " impossible \n");
	}
	if (!doublon && categorieUniteExiste) {
	    System.out.println(" \n  Ajout de l'unite " + nameUnite + " effectue ");
	    enregistrerInXML();
	}

    }

    // ////////////
    // Supprimer //
    // ////////////
    public static void deleteUnit(String nameCategorieUnite, String nameUnite) {
	boolean validUnite = false;
	boolean validCategorieUnite = false;
	for (int i = 0; i < listMeasure.size(); i++) {
	    if (listMeasure.get(i).getNameCategorieUnite().equals(nameCategorieUnite)) {
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {
		    if (listMeasure.get(i).getLstUnite().get(j).getNameUnite()
			    .equals(nameUnite)) {
			listMeasure.get(i).getLstUnite().remove(j);
			validUnite = true;
		    }
		}
	    } else {
		validCategorieUnite = true;
	    }
	}
	if (!validUnite) {
	    System.out
	    .print("\n   Suppression de " + nameUnite + " impossible ");
	    if (!validCategorieUnite) {
		System.out.println("car la CategorieUnite " + nameCategorieUnite
			+ " n'existe pas ");
	    }
	} else {
	    System.out
	    .println("\n  Suppression de " + nameUnite + " effectue ");
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
		reecritureDansLeFichier += "<categorieUnite nameCategorieUnite=\""
			+ listMeasure.get(i).getNameCategorieUnite()+ "\">";
		for (int j = 0; j < listMeasure.get(i).getLstUnite().size(); j++) {

		    Unit unitCourant = listMeasure.get(i).getLstUnite().get(j);
		    reecritureDansLeFichier += "<unite nameUnit=\""
			    + unitCourant.getNameUnite() + "\" multi=\""
			    + unitCourant.getmultiParRapportDefaut()
			    + "\" addition=\""
			    + unitCourant.getadditionParRapportDefaut()
			    + "\" />";
		}
		reecritureDansLeFichier += "</categorieUnite>";
	    }
	    reecritureDansLeFichier += "</umlConvertisseur>";
	    w.write(reecritureDansLeFichier);
	    w.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}