/**
 * 
 */
package alger1.isii.element;

import java.util.Vector;
/**
 * @author rym
 *
 */

//clause est un ensemble des litteraux
public class Clause {
	//déclaration
	private Vector <Litteral> Litteraux; //liste des litteraux
	
	
	//les constructeurs
	public Clause (Vector <Litteral> Litteraux) 
	{
		this.Litteraux = Litteraux;
	
	}

	
	//getters 
	public Vector<Litteral> getLitteraux() {
		return Litteraux;
	}


	//fonctions
	
	//verifier esq la clause est sat ou pas
	//faut l utiliser avec 3-SAT mais nous avons implementer un fonction generale
	public Boolean EstSat() 
	{
		int size=this.Litteraux.size();
		for(int i=0;i<size;i++)
		{
			if(this.Litteraux.get(i).getValeur()==1) return true ;
		}
		return false;	
	}


	
	}

