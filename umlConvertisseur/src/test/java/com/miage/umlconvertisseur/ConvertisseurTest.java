package com.miage.umlconvertisseur;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ConvertisseurTest {

    @Test
    public void test() {

        ///////////////////
        // TEST UNITAIRE //
        ///////////////////
        //////////////////////////////////////
        // Ajouter unit� qui existe deja //
        //////////////////////////////////////
        Convertisseur.ajouterUnite("distance", "cm", new BigDecimal("120"), new BigDecimal("120"));

        ////////////////////////////////////////////////////////
        // Ajouter une unit� dans une mesure qui n'�xiste pas //
        ////////////////////////////////////////////////////////
        Convertisseur.ajouterUnite("toto", "cm", new BigDecimal("120"), new BigDecimal("120"));

        //////////////////////////////////////
        // Convertir unit� qui n'�xiste pas //
        //////////////////////////////////////
        // distance : 1toto = 2tata
        @SuppressWarnings("unused")
        BigDecimal resultValeurOutTotoToTata = Convertisseur.convertirDeuxUnite("distance", "toto", "tata", new BigDecimal("0"));

        ///////////////
        // Convertir //
        ///////////////
        // distance, valeur par d�faut : m
        System.out.println("\n distance");
        // distance : 100cm = 1m
        BigDecimal valeurInCmToM = new BigDecimal("100");
        BigDecimal valeurOutAttenduCmToM = new BigDecimal("1.000");
        BigDecimal resultValeurOutCmToM = Convertisseur.convertirDeuxUnite("distance", "cm", "m", valeurInCmToM);
        assertEquals(valeurOutAttenduCmToM, resultValeurOutCmToM);

        // distance : 1km = 100000cm
        BigDecimal valeurInKmToCm = new BigDecimal("1");
        BigDecimal valeurOutAttenduKmToCm = new BigDecimal("100000.000");
        BigDecimal resultValeurOutKmToCm = Convertisseur.convertirDeuxUnite("distance", "km", "cm", valeurInKmToCm);
        assertEquals(valeurOutAttenduKmToCm, resultValeurOutKmToCm);

        // distance : 1m = 1000mm
        BigDecimal valeurInMToMm = new BigDecimal("1");
        BigDecimal valeurOutAttenduMToMm = new BigDecimal("1000.000");
        BigDecimal resultValeurOutMToMm = Convertisseur.convertirDeuxUnite("distance", "m", "mm", valeurInMToMm);
        assertEquals(valeurOutAttenduMToMm, resultValeurOutMToMm);

        // distance : 1miles = 1609,344m
        BigDecimal valeurInMilesToMm = new BigDecimal("1");
        BigDecimal valeurOutAttenduMilesToMm = new BigDecimal("1609.344");
        BigDecimal resultValeurOutMilesToMm = Convertisseur.convertirDeuxUnite("distance", "miles", "m", valeurInMilesToMm);
        assertEquals(valeurOutAttenduMilesToMm, resultValeurOutMilesToMm);

        // distance : 1km = 0.621 miles
        BigDecimal valeurInKmToMiles = new BigDecimal("1");
        BigDecimal valeurOutAttenduKmToMiles = new BigDecimal("0.621");
        BigDecimal resultValeurOutKmToMiles = Convertisseur.convertirDeuxUnite("distance", "km", "miles", valeurInKmToMiles);
        assertEquals(valeurOutAttenduKmToMiles, resultValeurOutKmToMiles);

        // distance : 1ft = 0,333yard
        BigDecimal valeurInFtToYard = new BigDecimal("1");
        BigDecimal valeurOutAttenduFtToYard = new BigDecimal("0.334");
        BigDecimal resultValeurOutFtToYard = Convertisseur.convertirDeuxUnite("distance", "ft", "yard", valeurInFtToYard);
        assertEquals(valeurOutAttenduFtToYard, resultValeurOutFtToYard);

        /////////////
        // Ajouter //
        /////////////
        // Cr�ation d'une nouvelle unit�e dans la mesure "distance" : le mille marin.
        // L'unit� de r�f�rence de Mesure c'est le metre
        // 1 mille marin est �gal � 1852m. Il n'y a pas de d�calage
        Convertisseur.ajouterUnite("distance", "milleMarin", new BigDecimal("1852.00"), new BigDecimal("0.0"));

        // Test de la convertion
        // distance : 1milleMarin = 1852.000m
        BigDecimal valeurMilleMarinToM = new BigDecimal("1");
        BigDecimal valeurOutAttenduMilleMarinToM = new BigDecimal("1852.000");
        BigDecimal resultValeurOutMilleMarinToM = Convertisseur.convertirDeuxUnite("distance", "milleMarin", "m", valeurMilleMarinToM);
        assertEquals(valeurOutAttenduMilleMarinToM, resultValeurOutMilleMarinToM);

        // distance : 1m = 0.001MilleMarin
        BigDecimal valeurMToMilleMarin = new BigDecimal("1");
        BigDecimal valeurOutAttenduMToMilleMarin = new BigDecimal("0.001");
        BigDecimal resultValeurOutMToMilleMarin = Convertisseur.convertirDeuxUnite("distance", "m", "milleMarin", valeurMToMilleMarin);
        assertEquals(valeurOutAttenduMToMilleMarin, resultValeurOutMToMilleMarin);

        ///////////////
        // Supprimer //
        ///////////////
        Convertisseur.supprimerUnite("distance", "milleMarin");

        System.out.println("\n temps");
	// temps, valeur par defaut J

        // temps : 1j = 24h
        BigDecimal valeurInJTOH = new BigDecimal("1");
        BigDecimal valeurOutAttenduJTOH = new BigDecimal("24.000");
        BigDecimal resultValeurOutJTOH = Convertisseur.convertirDeuxUnite("temps", "j", "h", valeurInJTOH);
        assertEquals(valeurOutAttenduJTOH, resultValeurOutJTOH);

        // temps : 1j = 1440m
        BigDecimal valeurInJToM = new BigDecimal("1");
        BigDecimal valeurOutAttenduJToM = new BigDecimal("1440.000");
        BigDecimal resultValeurOutJToM = Convertisseur.convertirDeuxUnite("temps", "j", "m", valeurInJToM);
        assertEquals(valeurOutAttenduJToM, resultValeurOutJToM);

        // temps : 1j = 0.143 semaine
        BigDecimal valeurInJToSem = new BigDecimal("1");
        BigDecimal valeurOutAttenduJToSem = new BigDecimal("0.143");
        BigDecimal resultValeurOutJToSem = Convertisseur.convertirDeuxUnite("temps", "j", "semaine", valeurInJToSem);
        assertEquals(valeurOutAttenduJToSem, resultValeurOutJToSem);

        // temps : 1h = 0.042j
        BigDecimal valeurInHToJ = new BigDecimal("1");
        BigDecimal valeurOutAttenduHToJ = new BigDecimal("0.042");
        BigDecimal resultValeurOutHToJ = Convertisseur.convertirDeuxUnite("temps", "h", "j", valeurInHToJ);
        assertEquals(valeurOutAttenduHToJ, resultValeurOutHToJ);

        // temps : 1semai = 168h
        BigDecimal valeurInSemToH = new BigDecimal("1");
        BigDecimal valeurOutAttenduSemToH = new BigDecimal("168.000");
        BigDecimal resultValeurOutSemToH = Convertisseur.convertirDeuxUnite("temps", "semaine", "h", valeurInSemToH);
        assertEquals(valeurOutAttenduSemToH, resultValeurOutSemToH);

        System.out.println("\n vitesse");
        // vitesse 1km/s = 0.278m/s
        BigDecimal valeurInKmhToMs = new BigDecimal("1");
        BigDecimal valeurOutAttenduKmhToMs = new BigDecimal("0.278");
        BigDecimal resultValeurOutKmhToMs = Convertisseur.convertirDeuxUnite("vitesse", "km/h", "m/s", valeurInKmhToMs);
        assertEquals(valeurOutAttenduKmhToMs, resultValeurOutKmhToMs);

        // vitesse 1m/s = 2.237mile/h
        BigDecimal valeurInMsToMileh = new BigDecimal("1");
        BigDecimal valeurOutAttenduMsToMileh = new BigDecimal("2.237");
        BigDecimal resultValeurOutMsToMileh = Convertisseur.convertirDeuxUnite("vitesse", "m/s", "mile/h", valeurInMsToMileh);
        assertEquals(valeurOutAttenduMsToMileh, resultValeurOutMsToMileh);

        // vitesse 1mile/h = 1m/s
        BigDecimal valeurInMilesToKms = new BigDecimal("1");
        BigDecimal valeurOutAttenduMilesToKms = new BigDecimal("0.447");
        BigDecimal resultValeurOutMilesToKms = Convertisseur.convertirDeuxUnite("vitesse", "mile/h", "m/s", valeurInMilesToKms);
        assertEquals(valeurOutAttenduMilesToKms, resultValeurOutMilesToKms);

        System.out.println("\n poids");
	// poids

        // poids 1t = 1g
        BigDecimal valeurInTToG = new BigDecimal("1");
        BigDecimal valeurOutAttenduTToG = new BigDecimal("1000000.000");
        BigDecimal resultValeurOutTToG = Convertisseur.convertirDeuxUnite("poids", "t", "g", valeurInTToG);
        assertEquals(valeurOutAttenduTToG, resultValeurOutTToG);

        Convertisseur.supprimerUnite("poids", "kg");

        // Cr�ation d'une nouvelle unit�e dans la mesure "poids" : Kilo.
        // L'unit� de r�f�rence de Mesure c'est le gramme
        // 1 kg = 1000.000 g. Il n'y a pas de d�calage
        // Si le kg �xite d�j�, message d'erreur
        Convertisseur.ajouterUnite("poids", "kg", new BigDecimal("1000.00"), new BigDecimal("0.0"));

        // poids 1t = 1g
        BigDecimal valeurInGToT = new BigDecimal("1");
        BigDecimal valeurOutAttenduGToT = new BigDecimal("0.001");
        BigDecimal resultValeurOutGToT = Convertisseur.convertirDeuxUnite("poids", "g", "kg", valeurInGToT);
        assertEquals(valeurOutAttenduGToT, resultValeurOutGToT);

        System.out.println("\n volume");
	// volume

        // volume 1l = 0.001m3
        BigDecimal valeurInLToM3 = new BigDecimal("1");
        BigDecimal valeurOutAttenduLToM3 = new BigDecimal("0.001");
        BigDecimal resultValeurOutLToM3 = Convertisseur.convertirDeuxUnite("volume", "l", "m3", valeurInLToM3);
        assertEquals(valeurOutAttenduLToM3, resultValeurOutLToM3);

        // volume 1yd3 = 0.765m3
        BigDecimal valeurInYdToM3 = new BigDecimal("1");
        BigDecimal valeurOutAttenduYdToM3 = new BigDecimal("0.765");
        BigDecimal resultValeurOutYdToM3 = Convertisseur.convertirDeuxUnite("volume", "yd3", "m3", valeurInYdToM3);
        assertEquals(valeurOutAttenduYdToM3, resultValeurOutYdToM3);

        // volume 1m3 = 1.308yd3
        BigDecimal valeurInM3ToYd3 = new BigDecimal("1");
        BigDecimal valeurOutAttenduM3ToYd3 = new BigDecimal("1.308");
        BigDecimal resultValeurOutM3ToYd3 = Convertisseur.convertirDeuxUnite("volume", "m3", "yd3", valeurInM3ToYd3);
        assertEquals(valeurOutAttenduM3ToYd3, resultValeurOutM3ToYd3);

        // volume 1l = 1.308ft3
        BigDecimal valeurInLToFt3 = new BigDecimal("1");
        BigDecimal valeurOutAttenduLToFt3 = new BigDecimal("0.035");
        BigDecimal resultValeurOutLToFt3 = Convertisseur.convertirDeuxUnite("volume", "l", "ft3", valeurInLToFt3);
        assertEquals(valeurOutAttenduLToFt3, resultValeurOutLToFt3);

        // surface
        System.out.println("\n surface");
        // surface 1m� = 1.196 yd�
        BigDecimal valeurInM2ToYd2 = new BigDecimal("1");
        BigDecimal valeurOutAttenduM2ToYd2 = new BigDecimal("1.196");
        BigDecimal resultValeurOutM2ToYd2 = Convertisseur.convertirDeuxUnite("surface", "m2", "yd2", valeurInM2ToYd2);
        assertEquals(valeurOutAttenduM2ToYd2, resultValeurOutM2ToYd2);

        // surface : 1 yd� = 0.836 m�
        BigDecimal valeurInYd2ToM2 = new BigDecimal("1");
        BigDecimal valeurOutAttenduYd2ToM2 = new BigDecimal("0.836");
        BigDecimal resultValeurOutYd2ToM2 = Convertisseur.convertirDeuxUnite("surface", "yd2", "m2", valeurInYd2ToM2);
        assertEquals(valeurOutAttenduYd2ToM2, resultValeurOutYd2ToM2);

        System.out.println("\n temperature");
        // temperature : 1celsius = 33,800farenheit
        BigDecimal valeurInCelToFar = new BigDecimal("1");
        BigDecimal valeurOutAttenduCelToFar = new BigDecimal("33.800");
        BigDecimal resultValeurOutCelToFar = Convertisseur.convertirDeuxUnite("temperature", "celsius", "farenheit", valeurInCelToFar);
        assertEquals(valeurOutAttenduCelToFar, resultValeurOutCelToFar);

        // temperature : 1farenheit = -17.22 celsius
        BigDecimal valeurInFarToCel = new BigDecimal("1");
        BigDecimal valeurOutAttenduFarToCel = new BigDecimal("-17.222");
        BigDecimal resultValeurOutFarToCel = Convertisseur.convertirDeuxUnite("temperature", "farenheit", "celsius", valeurInFarToCel);
        assertEquals(valeurOutAttenduFarToCel, resultValeurOutFarToCel);

        Convertisseur.supprimerUnite("temperature", "celsius");

        Convertisseur.ajouterUnite("temperature", "celsius", new BigDecimal("1.8"), new BigDecimal("32.0"));

        // temperature : 1farenheit = -17.22 celsius
        BigDecimal valeurInFarToCel2 = new BigDecimal("1");
        BigDecimal valeurOutAttenduFarToCel2 = new BigDecimal("-17.222");
        BigDecimal resultValeurOutFarToCel2 = Convertisseur.convertirDeuxUnite("temperature", "farenheit", "celsius", valeurInFarToCel2);
        assertEquals(valeurOutAttenduFarToCel2, resultValeurOutFarToCel2);

        // temperature : 1 farenheit = 255.928 kelvin
        BigDecimal valeurInFarToKel = new BigDecimal("1");
        BigDecimal valeurOutAttenduFarToKel = new BigDecimal("255.928");
        BigDecimal resultValeurOutFarToKel = Convertisseur.convertirDeuxUnite("temperature", "farenheit", "kelvin", valeurInFarToKel);
        assertEquals(valeurOutAttenduFarToKel, resultValeurOutFarToKel);

        // temperature : 1 kelvin = 255.928 farenheit
        BigDecimal valeurInKelToFar = new BigDecimal("1");
        BigDecimal valeurOutAttenduKelToFar = new BigDecimal("-457.870");
        BigDecimal resultValeurOutKelToFar = Convertisseur.convertirDeuxUnite("temperature", "kelvin", "farenheit", valeurInKelToFar);
        assertEquals(valeurOutAttenduKelToFar, resultValeurOutKelToFar);

        // temperature : 1 Kelvin  = -272.150 Celcius
        BigDecimal valeurInCelToKel = new BigDecimal("1");
        BigDecimal valeurOutAttenduCelToKel = new BigDecimal("-272.150");
        BigDecimal resultValeurOutCelToKel = Convertisseur.convertirDeuxUnite("temperature", "kelvin", "celsius", valeurInCelToKel);
        assertEquals(valeurOutAttenduCelToKel, resultValeurOutCelToKel);

        // temperature : 1 Celcius = 274.150 Kelvin
        BigDecimal valeurInKelToCel = new BigDecimal("1");
        BigDecimal valeurOutAttenduKelToCel = new BigDecimal("274.150");
        BigDecimal resultValeurOutKelToCel = Convertisseur.convertirDeuxUnite("temperature", "celsius", "kelvin", valeurInKelToCel);
        assertEquals(valeurOutAttenduKelToCel, resultValeurOutKelToCel);

    }

}
