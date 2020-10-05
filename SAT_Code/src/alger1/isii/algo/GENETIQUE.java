/**
 * 
 */
package alger1.isii.algo;

import java.util.Random;
import java.util.Vector;

import alger1.isii.element.Clause;
import alger1.isii.element.Litteral;
import alger1.isii.element.Satisfaite;

/**
 * @author Rym
 *
 */
public class GENETIQUE {


	//definir un chromosome parent aleatoirement
	public static int [] Solution()
	
	{
		int []tabSol=new int [75];
		
		int solutionSize=75;
		for(int i=0;i<solutionSize;i++)
		{
			Random rand=new Random();
			int x=rand.nextInt(2);
			tabSol[i]=x;
			
		}
		return tabSol;
		
	}
	
	//definir une population de solutions
	public static int [][] Population (int taille)
	{
		
		int []tabSol=new int [75];//selon nombre de littéraux
		int [] [] population =new int [taille][76];
		
		for(int i=0;i<taille;i++)
		{
			for(int j=0;j<75;j++) {
			tabSol= alger1.isii.algo.GENETIQUE.Solution();
			population[i][j]=tabSol[j];
			
		}
			}
		return population;
	}

	
	
	
	
	

	
	
	public static int Evaluation(Satisfaite sat,int[]tempo) 
	{
		Vector<Litteral> Litteraux = new Vector<Litteral>();
		//initialiser le vecteur des litteraux avec des -1 et les nommer surtt
		for(int i=-75;i<76;i++)//pour avoir des valeurs des litteraux entre -75 et 75
		{ 
			if(i!=0) Litteraux.add(new Litteral(i));//apart la valeur 0 qui signife un saut de ligne
			
		}	
          int nom;
	     //cloner sat dans une variable formule	
		//cree un vecteur de clauses
		Vector<Clause> clause=new Vector<Clause>();
		for(int i=0;i<sat.getClauses().size();i++)//325
		{
			Vector <Litteral> Newlitteraux= new Vector<Litteral>();
		
			for(int j=0;j<sat.getClauses().get(i).getLitteraux().size();j++)//3  litteraux dans haque clause de 3SAT
			{
				if(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()<0){
					nom=-(sat.getClauses().get(i).getLitteraux().get(j).getNumvar());
					if(tempo[nom-1]==0)
						Litteraux.get(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()+75).setValeur(1);
					else
						Litteraux.get(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()+75).setValeur(0);
					    Newlitteraux.add(Litteraux.get(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()+75));
					
				}
				else{
				nom=sat.getClauses().get(i).getLitteraux().get(j).getNumvar();
				//donner une valeur a la variable x
				Litteraux.get(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()+74).setValeur(tempo[nom-1]);
					Newlitteraux.add(Litteraux.get(sat.getClauses().get(i).getLitteraux().get(j).getNumvar()+74));
					}
			}
			clause.add(new Clause(Newlitteraux));
		
		}
		//remplir les deux sat par les sat de sat
		Satisfaite formule = new Satisfaite(clause, Litteraux);

		//System.out.println("-------------------------");
		
		//System.out.println("nombre de clauses satisfaites "+formule.NombreClauseSat());
		return formule.NombreClauseSat();
		
	}//end evaluation
	
	
	//fonction de croissement
	public static int [] croisement1(int[]parent1, int[]parent2,int x)
	{
		
		int []child1=new int [75]; //nouveau chromosome
		for(int i=0;i<child1.length;i++)
		{
			if(i<x)
			{
				child1[i]=parent1[i];
				
			}
			else
				child1[i]=parent2[i];
		}
		return child1;
	}
	
	public static int [] croisement2(int[]parent1, int[]parent2,int x)
	{
		
		int []child2=new int [75];
		for(int i=0;i<child2.length;i++)
		{
			if(i<x)
			{
				child2[i]=parent2[i];
				
			}
			else
				child2[i]=parent1[i];
		}
		return child2;
	}
	//mutation sur un seul point qui sera appeler X fois
	public static int [] mutation(int[]tableau,double tauxMutation)
	{
		
		Random rand=new Random();
		int x=rand.nextInt(75);
		double prob=rand.nextDouble();
		//System.out.println("le point de mutation est "+x);
	//	System.out.println("le point de mutation est "+prob);
if(tauxMutation>=prob)
{
			if(tableau[x]==0)
			{
				tableau[x]=1;
			}
			else tableau[x]=0;
}	
			return tableau;
		
	}
	
	
	

	
}
