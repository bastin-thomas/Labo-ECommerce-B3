/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfreechart;

/**
 *
 * @author Arkios
 */
public class MyRow {
    public Float Hauteur;
    public Float Masse;
    public Float NbGrains;
    public Float MasseGrains;
    public String Couleur;
    public String GerminationEpi;
    public String Enracinement;
    public String Verse;
    public String Attaque;
    public String Parcelle;
    public Float HauteurJ7;
    public String VerseTraitement;
    
    public MyRow(){
        Hauteur = null;
        Masse = null;
        NbGrains = null;
        MasseGrains = null;
        Couleur = null;
        GerminationEpi = null;
        Enracinement = null;
        Verse = null;
        Attaque = null;
        Parcelle = null;
        HauteurJ7 = null;
        VerseTraitement = null;
    }
    
    public MyRow(String hauteur, String masse, String nbgrains, String massegrain, 
        String couleur, String germninationepis, String enracinement, 
        String verse, String attaque, String parcelle, 
        String hauteurj7, String versetraitement) throws Exception{
        
        if(hauteur.equals("NA")) throw new Exception("NaN Value Error");
        Hauteur = Float.parseFloat(hauteur);
        
        if(masse.equals("NA")) throw new Exception("NaN Value Error");
        Masse = Float.parseFloat(masse);
        
        if(nbgrains.equals("NA")) throw new Exception("NaN Value Error");
        NbGrains = Float.parseFloat(nbgrains);
        
        if(massegrain.equals("NA")) throw new Exception("NaN Value Error");
        MasseGrains = Float.parseFloat(massegrain);
        
        if(couleur.equals("NA")) throw new Exception("NaN Value Error");
        Couleur = couleur;
        
        if(germninationepis.equals("NA")) throw new Exception("NaN Value Error");
        GerminationEpi = germninationepis;
        
        if(enracinement.equals("NA")) throw new Exception("NaN Value Error");
        Enracinement = enracinement;
        
        if(verse.equals("NA")) throw new Exception("NaN Value Error");
        Verse = verse;
        
        if(attaque.equals("NA")) throw new Exception("NaN Value Error");
        Attaque = attaque;
        
        if(parcelle.equals("NA")) throw new Exception("NaN Value Error");
        Parcelle = parcelle;
        
        if(hauteurj7.equals("NA")) throw new Exception("NaN Value Error");
        HauteurJ7 = Float.parseFloat(hauteurj7);
        
        if(versetraitement.equals("NA")) throw new Exception("NaN Value Error");
        VerseTraitement = versetraitement;
    }

    @Override
    public String toString() {
        return "MyRow{" + "Hauteur=" + Hauteur + ", Masse=" + Masse + ", NbGrains=" + NbGrains + ", MasseGrains=" + MasseGrains + ", Couleur=" + Couleur + ", GerminationEpi=" + GerminationEpi + ", Enracinement=" + Enracinement + ", Verse=" + Verse + ", Attaque=" + Attaque + ", Parcelle=" + Parcelle + ", HauteurJ7=" + HauteurJ7 + ", VerseTraitement=" + VerseTraitement + '}';
    }
}
