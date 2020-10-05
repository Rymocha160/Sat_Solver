/**
 * 
 */
package alger1.isii.element;

import java.util.Vector;

/**
 * @author rym
 *
 */
public class Satisfaite implements Cloneable {
	
	//déclarations
	Vector<Clause>  Clauses;
	Vector<Litteral> Litteraux;
	
	int profondeur;
	
	//Constructeur
	public Satisfaite(Vector<Clause> clauses, Vector<Litteral> litteraux) {
		Clauses = clauses;
		Litteraux = litteraux;
	}
	//pour cloner les clauses et les litteraux 
	//cette est a verifie plus tard peut etre nahilha l clone tae litteraux
	@SuppressWarnings("unchecked")
	public Satisfaite(Satisfaite sat) throws CloneNotSupportedException
	{
		this.Litteraux=(Vector<Litteral> )sat.Litteraux.clone();
		this.Clauses=(Vector<Clause>) sat.Clauses.clone();
	}

	//getters et setters
	public Vector<Clause> getClauses() {
		return Clauses;
	}



	public Vector<Litteral> getLitteraux() {
		return Litteraux;
	}

	

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	
	//les fonctions
	
	//fonction qui verifie si on a le meme vecteur de litteraux
	public boolean MemeLitteraux(Satisfaite sat)
	{
		for(int i=0;i<sat.Litteraux.size();i++) //psq y a 75 litteraux et c exactement la taille de notre vecteur
		{
			if(this.Litteraux.get(i).getValeur()!=sat.Litteraux.get(i).getValeur())
				return false;
		}
		return true;
	}
	
	//fonction qui teste la satisfiabilitÃ© des clauses
	public boolean TestSatisfiabilite()
	{
		for(int i =0; i<Clauses.size();i++) //normalmnt c 325
		{
			if(this.Clauses.get(i).EstSat()==false) return false;
		}
		return true;
	}
	
	//fonction qui retourne nombre des clauses satisfaite
	public int NombreClauseSat()
	{
		int nombreClausesSat=0;
		for(int i =0; i<Clauses.size();i++) //normalmnt c 325
		{
			if(this.Clauses.get(i).EstSat()==true) nombreClausesSat++;
		}
		return nombreClausesSat;
		
	}
	
	//fonction qui retourne nombre de clauses non satisfaite
	public int NombreClauseNonSat()
	{
		int nombreClausesNonSat=0;
		for(int i =0; i<Clauses.size();i++) //normalmnt c 325
		{
			if(this.Clauses.get(i).EstSat()==false) nombreClausesNonSat++;
		}
		return nombreClausesNonSat;
		
	}
	
	
	//fonction qui retorne nombre de clause contenant un littéral L
	public int NombreClauseOntLitteral (int k)
	{
		int cpt = 0;
		for(int i=0; i<this.getClauses().size();i++)
		{
			for(int j=0;j<this.getClauses().get(i).getLitteraux().size();j++)
			{
				if((this.Clauses.get(i).getLitteraux().get(j).getNumvar()==k) || (- (this.Clauses.get(i).getLitteraux().get(j).getNumvar())==k))
					cpt++;
			}
		}
		return cpt;
	}

}
