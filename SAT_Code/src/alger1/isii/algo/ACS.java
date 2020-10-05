/**
 * 
 */
package alger1.isii.algo;

/**
 * @author Rym
 *
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import alger1.isii.element.Construire_Solution;
import alger1.isii.element.Pheromone_table;

public class ACS {

	
	
	
	//fonction d'évaluation
		public static int evaluation (Vector<Integer> s,ArrayList<int []> clauses_bechmark){
			int m = 0;
			//initialiser une liste TTT
			ArrayList<int []> TTT = new ArrayList<>();
			TTT.addAll(clauses_bechmark);
			//compter les clauses floatQui satisf le litteral
			for (int i = 0; i < s.size(); i++) {
			for (int k = 0; k < TTT.size(); k++) {
				if((s.get(i) == TTT.get(k)[0]) || (s.get(i) == TTT.get(k)[1]) || (s.get(i) == TTT.get(k)[2]) ){
					TTT.remove(k);
					m++;
				}
			}
			}
			return m;
		}
	
		//Fonction de satisfaction nombre de clauses sat
		public static int satisf(ArrayList<int []> clauses_bechmark,int l)
		{
			int n = 0;
			for (int i = 0; i < clauses_bechmark.size(); i++) {
				if( (clauses_bechmark.get(i)[0] == l) || (clauses_bechmark.get(i)[1] == l) || (clauses_bechmark.get(i)[2] == l)){
					n++;
				}
			}
			return n;
		}
	
	//fonctio de construction de solution
	public static Vector<Integer>  Construire (Vector<Pheromone_table> litteraux,float floatQ0, float alpha,float beta,ArrayList<int []> clauses_bechmark)
	{
		//definir le vecteur contenat la solution vide
		Vector<Integer> solution = new Vector<>();//vecteur d'entiers
		Vector<Pheromone_table> l = new Vector<>(); //tableau de litteraux avec leurs phéromones
		l.addAll(litteraux); //ajouter les litteraux au vecteur l
		
		 
		 Vector<Construire_Solution> Construire = new Vector<>(); //vecteur contient le literal et sa probabilité
			//pour tout les litteraux
			for (int i = 0; i < l.size(); i++) 
			{
				int literal =  l.get(i).getLiteral() ; 
				int satisf = satisf(clauses_bechmark,literal); //nombre de clauses satisfiables par c literal
				float a = satisf / 325f ;
				float c = (float) (Math.pow(l.get(i).getPheromone(), alpha) * Math.pow(a, beta));
				Construire.add(new Construire_Solution(literal, c)); 
			}
			
			while(!Construire.isEmpty()){
				Random rand = new Random();
				 float floatQ = rand.nextFloat() * (1 - 0) + 0;
				 @SuppressWarnings("unused")
				int num_boucle=0;
				 int maximum_L;
				 float val_max;
			if(floatQ < floatQ0){
				maximum_L = Construire.get(0).getLiteral();
				 val_max = Construire.get(0).getVal();
				//prendre le meilleur literal de plus grande probabilité
				for (int i = 1; i < Construire.size(); i++) {
					if(Construire.get(i).getVal() > val_max){
						maximum_L = Construire.get(i).getLiteral();
						val_max = Construire.get(i).getVal();
						num_boucle = 1;
					}
				} 
			}
			else{
				//regle de decision
					float global=0;
					num_boucle=2;
					//calculer la somme des probabilité
					for (int i = 0; i < Construire.size() ; i++) {
						global += Construire.get(i).getVal();
					}
					maximum_L = Construire.get(0).getLiteral();
					 val_max = Math.abs(Construire.get(0).getVal()/global);
					
					 
					//diviser la proba calculée sur la proba global
					for (int i = 1; i < Construire.size() ; i++) {
						float t =  Math.abs(Construire.get(i).getVal()/global);
						if(t < val_max ){
							maximum_L = Construire.get(i).getLiteral();
							val_max = t;
						
						}
					}
				}
			
			//ajouter literal de meilleur proba au solution
			solution.add(maximum_L);
			
			
			int k=0;
			while( k < Construire.size()){
				if((Construire.get(k).getLiteral() == maximum_L) || (Construire.get(k).getLiteral() == -maximum_L)){
					Construire.remove(k);
				}else{
					k++;
				}
			}
		}
			for (int i = 0; i < solution.size(); i++) {
				System.out.print(solution.get(i)+" ");
			}
			System.out.println("\n sat "+evaluation(solution,clauses_bechmark));
			//retourner la solution
		return solution;
	}
	
	
	
	
	
	
	
	
}


