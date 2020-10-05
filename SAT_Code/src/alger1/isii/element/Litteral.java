/**
 * 
 */
package alger1.isii.element;

import java.util.Random;
import java.util.Vector;

/**
 * @author rym
 *
 */
public class Litteral {

	//déclaration
	private int Numvar;//de 1 a 75
	private int valeur;//negative ou positive boolean
	
	//Constructeurs
	//initialisation
	//pour initialiser les noms des litearaux ici c entre -75 et 75
	public Litteral(int nom){
		Numvar = nom;
		valeur = -1;
	}
	
	public Litteral(Litteral l){
		this.Numvar=l.Numvar;
		this.valeur=l.valeur;
	}
	//getters et setters

	public int getNumvar() {
		return Numvar;
	}


	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	//les fonctions
	
	//recupere les litteraux qui la non valeur d'une valeur donnnÃ©e
	public static Litteral Negation(Vector<Litteral> litteral, Litteral l){
		for(int i=0;i<litteral.size();i++){
			if(litteral.get(i).getNumvar() == -l.getNumvar()) 
				return litteral.get(i);
		}
		return null;
	}
	
	
	//initialiser le vecteur de litteraux avec des valeurs random en utilisant un autre litteral
	public static Litteral random (Vector<Litteral> Vlitteral){
		// cree un objet random
		Random rand = new Random();
		// Retourner un num sup a 0  et inferieur a la taille de vecteur
		//retourner la nieme valeur de vecteur
		//l enregistrer dans un object litteral
		Litteral litteral = Vlitteral.get(rand.nextInt(Vlitteral.size()));
		return litteral;	
	}
	
	
	//supprimer une valeur introduite d'un vecteur de litteraux 
	public static void  SupprimeLitteral(Vector<Litteral> Litteral, Litteral x){
		int cpt =0;
		while (cpt!=2)
		{
		for(int i=0;i<Litteral.size();i++)
		{
			if((Litteral.get(i).getNumvar()==x.getNumvar())||(Litteral.get(i).getNumvar()==-x.getNumvar()))
			{
				Litteral.remove(i);
				cpt++;
			}
		}	
		}
	}

}
