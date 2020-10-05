/**
 * 
 */
package alger1.isii.algo;

import alger1.isii.element.Clause;
import alger1.isii.element.Litteral;
import alger1.isii.element.Satisfaite;

import java.util.LinkedList;
import java.util.Vector;




/**
 * @author rym
 *
 */
public class BFS   {
	int valeur;
	 
	//declarations
	//private long timeLimit;

   // private boolean timeExpired;

	public static  void BFS_Algo(Satisfaite s,   LinkedList<Satisfaite> Ouvert,   LinkedList<Satisfaite> Ferme)throws Exception 
	{
	int max=0;
		// long startTime = System.currentTimeMillis(); //calculer le temps
		 Litteral contraire=null, Vrai = null; //deux litteraux
		 Satisfaite pere =null; //sat c vecteur de litteraux et vecteur des clauses
		 s.setProfondeur(0); //initialiser la profondeur a 0
		
		 //debut de l'algorithme
		Ouvert.add(s); //Inserer sat dans la file Ouvert
		
		 if(Ouvert.isEmpty())//si Ouvert est vide alors echec
		 {
				System.out.println("echec");
			}
		 while(!Ouvert.isEmpty())//la file n'est pas vide
		 {
			pere=Ouvert.removeFirst(); //defiler le 1er noeud insere dans Ouvert
			
			Ferme.add(pere); //et l'enfiler a Ferme
			if(pere.TestSatisfiabilite())
			{
				System.out.println("YES SAT, il existe une instanciation qui satisfaite toutes les clause!");
				break;
			}
			if(max<pere.NombreClauseSat())
			{
				max=pere.NombreClauseSat();
				System.out.println("nombre de clauses satisfaites "+pere.NombreClauseSat());

			}
			 
			if(pere.getProfondeur()<s.getLitteraux().size()/2)//dans notre cas c 75
			{
		
			//faire le clonage	
			//cree deux vecteurs de clauses
				Vector<Clause> clause1=new Vector<Clause>();
				Vector<Clause> clause2= new Vector<Clause>();
			//cree vecteur de litteraux
				Vector<Litteral> Litteraux1 = new Vector<Litteral>();
				Vector<Litteral> Litteraux2 = new Vector<Litteral>();
				//remplire les deux vecteurs de litteraux par les litteraux de pere
				for(int i=0;i<pere.getLitteraux().size();i++)//150
				{
					Litteraux1.add(new Litteral(pere.getLitteraux().get(i)));
					Litteraux2.add(new Litteral(pere.getLitteraux().get(i)));
				}
				

				//System.out.println("pere Litteraux size  "+pere.getLitteraux().size());
				//System.out.println("pere clause size  "+pere.getClauses().size());
				
				for(int i=0;i<pere.getClauses().size();i++)//325
				{
					Vector <Litteral> Newlitteraux1= new Vector<Litteral>();
					Vector <Litteral> Newlitteraux2= new Vector<Litteral>();

					//Vector <Litteral> Newlitteraux2= new Vector<Litteral>();

					//System.out.println("pere clause size  "+pere.getClauses().get(i).getLitteraux().size());
					for(int j=0;j<pere.getClauses().get(i).getLitteraux().size();j++)//3  litteraux dans haque clause de 3SAT
					{
						
						if(pere.getClauses().get(i).getLitteraux().get(j).getNumvar()<0){
							

							Newlitteraux1.add(Litteraux1.get(pere.getClauses().get(i).getLitteraux().get(j).getNumvar()+75));
							Newlitteraux2.add(Litteraux2.get(pere.getClauses().get(i).getLitteraux().get(j).getNumvar()+75));
						}
						else{
							Newlitteraux1.add(Litteraux1.get(pere.getClauses().get(i).getLitteraux().get(j).getNumvar()+74));
							Newlitteraux2.add(Litteraux2.get(pere.getClauses().get(i).getLitteraux().get(j).getNumvar()+74));
						}
						
					}
					clause1.add(new Clause(Newlitteraux1));
					clause2.add(new Clause(Newlitteraux2));	
				}
				//remplir les deux sat par les sat de pere
				Satisfaite fils1 = new Satisfaite(clause1, Litteraux1);
				Satisfaite  fils2 = new Satisfaite(clause2, Litteraux2);
				//augmenter la profondeur
				fils1.setProfondeur(pere.getProfondeur()+1);
				fils2.setProfondeur(pere.getProfondeur()+1);
				
				Vrai = fils1.getLitteraux().get(fils1.getProfondeur()-1);
				//System.out.println("vraiiiiiiiii "+Vrai.getValeur());
				Vrai.setValeur(1);
				
				
				contraire = Litteral.Negation(fils1.getLitteraux(), Vrai);
				contraire.setValeur(0);
				
				
				Vrai= fils2.getLitteraux().get(fils2.getProfondeur()-1);
				Vrai.setValeur(0);
				
				contraire = Litteral.Negation(fils2.getLitteraux(), Vrai);
				contraire.setValeur(1);
				
				//si fils2 n'existe pas dans fermé  l'ajout dans Ouvert la file
				boolean ajout = true;
			//	System.out.println("vraiiiiiiiii "+Ferme.size());
				for(int i=0;i<Ferme.size();i++){
					if(fils2.MemeLitteraux(Ferme.get(i))){
						ajout = false;
						i=Ferme.size();
					}
				}
				//l'insertion de fils2 dans Ouvert
				if(ajout==true)Ouvert.addLast(fils2);
				//si fils1 n'existe pas dans fermé  l'ajout dans Ouvert la file
				ajout = true;
				for(int i=0;i<Ferme.size();i++){
					if(fils1.MemeLitteraux(Ferme.get(i))){
						ajout = false;
						i=Ferme.size();
					}
				}
				//l'insrsion de fils1 dans Ouvert
				if(ajout==true)Ouvert.addLast(fils1);
			}
			

		 }//end while
	}
	
	  
	 
	

}
