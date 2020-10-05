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
public class A_ETOILE_H2 {
	
	//declarations
	//private long timeLimit;

   // private boolean timeExpired;

	public static  void A_ETOILE_Algo(Satisfaite s,   LinkedList<Satisfaite> Ouvert,   LinkedList<Satisfaite> Ferme)throws Exception 
	{
	int maxi=0;
		// long startTime = System.currentTimeMillis(); //calculer le temps
		 Litteral contraire=null, Vrai = null; //deux litteraux
		 Satisfaite pere =null; //sat c vecteur de litteraux et vecteur des clauses
		 Satisfaite tempo =null;
		 s.setProfondeur(0); //initialiser la profondeur a 0
		int [] occurence=new int [75];
		for(int i=0;i<75;i++)//
		{ 
			occurence[i]=s.NombreClauseOntLitteral(i+1);
			//System.out.println("lalalalal "+ i +" "+occurence[i]);
			
		}
		 //debut de l'algorithme
		Ouvert.add(s); //Inserer sat dans la file Ouvert
		
		
		 if(Ouvert.isEmpty())//si Ouvert est vide alors echec
		 {
				System.out.println("echec");
			}
		 while(!Ouvert.isEmpty())
		 {
			 int min=0;
			 int indice=0;
			for(int i=0;i<Ouvert.size();i++)
			{
				
				tempo=Ouvert.get(i);
				/*for(int k=0;k<tempo.getClauses().size();k++)//325
				{
				
					for(int j=0;j<tempo.getClauses().get(k).getLitteraux().size();j++)//3  litteraux dans haque clause de 3SAT
					{
						if(tempo.getClauses().get(k).getLitteraux().get(j).getNumvar()<0){
							
							if(((tempo.NombreClauseSat())+occurence[(tempo.getClauses().get(k).getLitteraux().get(j).getNumvar())+75])>max)
							{
								max=tempo.NombreClauseSat();
								   indice=k;
							}
						}
						else
						 if (((tempo.NombreClauseSat())+occurence[(tempo.getClauses().get(k).getLitteraux().get(j).getNumvar())-1])>max);
						{ max=tempo.NombreClauseSat();
						   indice=k;
					}
						}

				}*/
				if(tempo.NombreClauseNonSat()<min)
		
				   {
				   min=tempo.NombreClauseSat();
				   indice=i;
				   }
			  
			}
			//System.out.println("ouverrrrrrrrt "+tempo.getClauses().size());
		
			pere=Ouvert.remove(indice); //defiler le 1er noeud insere dans Ouvert
			Ferme.add(pere); //et l'enfiler a Ferme
			
			//cas de SAT
			//if((pere.getProfondeur()==75) && pere.TestSatisfiabilite())
			
			//System.out.println("profondeur "+pere.getProfondeur());
			if(maxi<pere.NombreClauseSat()+20)
			{
				maxi=pere.NombreClauseSat()+20;
				System.out.println("nombre de clauses satisfaites "+pere.NombreClauseSat());

			}				if(pere.getProfondeur()<s.getLitteraux().size()/2)//dans notre cas c 75
			{
				
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
				
				
				boolean ajout = true;
			//	System.out.println("vraiiiiiiiii "+Ferme.size());
				for(int i=0;i<Ferme.size();i++){
					if(fils2.MemeLitteraux(Ferme.get(i)) ){
						ajout = false;
						i=Ferme.size();
					}
				}
				if(ajout==true)Ouvert.addFirst(fils2);
				
				ajout = true;
				for(int i=0;i<Ferme.size();i++){
					if(fils1.MemeLitteraux(Ferme.get(i))){
						ajout = false;
						i=Ferme.size();
					}
				}
				if(ajout==true)Ouvert.addFirst(fils1);
			}
			if(pere.TestSatisfiabilite())
			{
				System.out.println("YES SAT");
				break;
			}
			
			
			
			
			
		 }//end while
	}
}
