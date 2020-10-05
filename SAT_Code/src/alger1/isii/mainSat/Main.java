/**
 * 
 */
package alger1.isii.mainSat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;

import alger1.isii.element.Clause;
import alger1.isii.element.Fourmi;
import alger1.isii.element.Litteral;
import alger1.isii.element.Pheromone_table;
import alger1.isii.element.Satisfaite;

/**
 * @author rym
 *
 */

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Vector <Litteral> ListeLitteral = new Vector<Litteral>();	
		Vector<Clause> clauses = new Vector<Clause>();
		//initialiser le vecteur des litteraux avec des -1 etles nommer surtt
		for(int i=-75;i<76;i++)//pour avoir des valeurs des litteraux entre -75 et 75
		{ 
			if(i!=0) ListeLitteral.add(new Litteral(i));//apart la valeur 0 qui signife un saut de ligne
			
		}
		//affichage
	/*	for(int i=0;i<ListeLitteral.size();i++)
		{ 
		System.out.println("valeur"+" "+ i+ " est "+ListeLitteral.get(i).getNumvar() );
		}*/
		//path de fichier sat
		String fichier = "C:/Users/Administrateur/Desktop/APK/src/test/eclipse/SAT/UUF75.325.100/uuf75-09.cnf";
		
		try
		{
			InputStream input=new FileInputStream(fichier); //@convertir le fichier en input stream flux d'octets
			InputStreamReader reader=new InputStreamReader(input);//lire les octets et les transfprme en caracteres
			//StringBuffer stringBuffer = new StringBuffer();
			BufferedReader br=new BufferedReader(reader);//lire le text avec un tampon
			
			//BufferedReader br = new BufferedReader(new FileReader(fichier)); 
			
			//comme si 3-SAT
			
			String litteral1="";
			String litteral2="";
			String litteral3="";
			int i = 0;
			String ligne=br.readLine();
			while ((ligne != null) && ligne.charAt(0)!='0')
			{
				
				//pour l'affichage
				/*stringBuffer.append(ligne);
                stringBuffer.append("\n");*/
				
                //initialiser tableau de Litteraux
                Vector<Litteral> litteraux = new Vector<Litteral>();
                //recupere que les clauses
                if(ligne.startsWith("c")||ligne.startsWith("p")||ligne.startsWith("%")||ligne.startsWith("0")){
                	//System.out.println("a sauter");
                	//on fait rien duuuuuu rien
                }
               
                else
                {
                	//recupere le 1er litteral
                
                	while((ligne.charAt(i)!=' ')&&(i<ligne.length()))
                	{
						litteral1+= ligne.charAt(i);
						i++;
					}
    
                	//System.out.println("1er "+a);
                	if(Integer.parseInt(litteral1)<0)//si le type de données primitif de la chaine (litteral1) est negative
                	{
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral1)+75));
						//System.out.println("1er "+Integer.parseInt(litteral1));
					}
					else{
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral1)+74));
					//	System.out.println("1er "+Integer.parseInt(litteral1));
					}
                	//litteraux.add(ListeLitteral.get(Integer.parseInt(litteral1)));
                	litteral1="";
					i++;
					
					//recupere le 2eme litteral
                	while(( ligne.charAt(i)!=' ') && ( i<ligne.length() ) )
                	{
						litteral2+= ligne.charAt(i);
						i++;
					}
                	int b=Integer.parseInt(litteral2);
                	//System.out.println("2eme "+b);
                	if(b<0){
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral2)+75));
					//	System.out.println("2eme "+b);
					}
					else{
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral2)+74));
					//	System.out.println("2eme "+b);
					}
                	//litteraux.add(ListeLitteral.get(Integer.parseInt(litteral2)));
                	litteral2="";
					i++;
					
					//recupere le 3eme litteral
                	while((ligne.charAt(i)!=' ')&&(i<ligne.length()))
                	{
						litteral3+= ligne.charAt(i);
						i++;
					}
                	int c=Integer.parseInt(litteral3);
                	//System.out.println("3eme "+c);
                	if(c<0){
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral3)+75));
					//	System.out.println("3eme "+c);
					}
					else{
						litteraux.add(ListeLitteral.get(Integer.parseInt(litteral3)+74));
						//System.out.println("3eme "+c);
					}
                	
                	litteral3="";
					//i++;
                	i=0;
					//ajouter Les litteraux pour reformuler un clause dans la liste des clauses
                	
					clauses.add(new Clause(litteraux));
				}
                ligne=br.readLine();
                //System.out.println("Contents of File: "+ligne.charAt(0));
			} 
			br.close();
			//pour l'affichage de fichier sur la console
		  /*System.out.println("Contents of File: ");
           System.out.println(stringBuffer.toString());*/
			}
		
		catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        } 
		
		
		//vecteur de litteraux avec des valeurs random
		Vector<Litteral> RandomLitteraux=new Vector<Litteral>();
		Litteral randomVar, contraire ;
	
		//tq la liste nest pas vide
		while(ListeLitteral.size()>0)
		{
		
			randomVar = Litteral.random(ListeLitteral); // une valeur random de ListeLitteral
			contraire = Litteral.Negation(ListeLitteral, randomVar); //si il onr des valeurs contaires sinn null
			Litteral.SupprimeLitteral(ListeLitteral, randomVar);//supp randomVar de ListeLitteral
			RandomLitteraux.add(0,randomVar);//a l'index 0 ajouter la valeur de randomLitteral
			RandomLitteraux.add(RandomLitteraux.size(), contraire);	
			
		}
		
		ListeLitteral = RandomLitteraux;
		
		
		//declarer objet Sat en utilisant les deux vcteurs precidents
		Satisfaite sat = new Satisfaite(clauses, ListeLitteral);
		LinkedList <Satisfaite> Ouvert = new LinkedList<Satisfaite>();
		LinkedList <Satisfaite> Ferme = new LinkedList<Satisfaite>();
		
		String input;
		int inputNumber;
		
		
		input = JOptionPane.showInputDialog(null, 
	    "Veuillez introduire un numéro pour votre Algo\n\n 1-BFS \n 2-DFS\n 3-HEURISTIQUE 01\n 4-HEURISTIQUE 02\n 5-ALGORITHME GENETIQUE\n 6-ACS");
		inputNumber = Integer.parseInt(input);
	
		while ((inputNumber<1) &&(inputNumber>6))
		{
			JOptionPane.showMessageDialog(null, "Choisissez un numéro correct  !!! -_-");
			input = JOptionPane.showInputDialog(null, 
				    "Veuillez introduire un numéro pour votre Algo\n\n 1-BFS \n 2-DFS\n 3-HEURISTIQUE 01\n 4-HEURISTIQUE 02\n 5-ALGORITHME GENETIQUE\n 6-ACS");
					inputNumber = Integer.parseInt(input);
			
		}
		switch(inputNumber)
		{
			case 1: System.out.println("******** BFS ********"); 
			long debut1 = System.currentTimeMillis();
		    alger1.isii.algo.BFS.BFS_Algo(sat, Ouvert, Ferme);
			//complixite temporelle
			float seconds1 = (System.currentTimeMillis() - debut1) / 1000F;
			System.out.println("Opération effectuée en:  "+Float.toString(seconds1) + " secondes.");
			break;
			
			
			case 2: System.out.println("******** DFS ********"); 
			long debut2 = System.currentTimeMillis();
		    alger1.isii.algo.DFS.DFS_Algo(sat, Ouvert, Ferme);
			//complixite temporelle
			float seconds2 = (System.currentTimeMillis() - debut2) / 1000F;
			System.out.println("Opération effectuée en:  "+Float.toString(seconds2) + " secondes.");
			break;
			
			case 3: System.out.println("******** HEURISTIQUES 01 ********"); 
			long debut3 = System.currentTimeMillis();
		    alger1.isii.algo.A_ETOILE_H1.A_ETOILE_Algo(sat, Ouvert, Ferme);
			//complixite temporelle
			float seconds3 = (System.currentTimeMillis() - debut3) / 1000F;
			System.out.println("Opération effectuée en:  "+Float.toString(seconds3) + " secondes.");
			break;
			
			case 4: System.out.println("******** HEURISTIQUES 02 ********"); 
			long debut4 = System.currentTimeMillis();
		    alger1.isii.algo.A_ETOILE_H2.A_ETOILE_Algo(sat, Ouvert, Ferme);
			//complixite temporelle
			float seconds4 = (System.currentTimeMillis() - debut4) / 1000F;
			System.out.println("Opération effectuée en:  "+Float.toString(seconds4) + " secondes.");
			break;
			
			
			
			
			case 5:System.out.println("******** LES ALGORITHMES GENETIQUES ********"); 
			
			// Création du cadre
	        JFrame frame = new JFrame ("ALGORITHME GENETIQUE");
	        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        frame.setSize (1000, 400);
	        JLabel affichage = new JLabel(" ");
	       
	       
	        
	        //Creating the panel at bottom and adding components
	        JPanel panel = new JPanel(); // the panel is not visible in output
	        panel.setBackground(new Color( 235,212,149));
	      // panel.setLayout(new GridLayout(3,2));
	       // JLabel label = new JLabel("<html><font color='white'>Taille de la population : </font></html>");
	        JLabel label = new JLabel("Taille de la population : ");

	        JTextField tf = new JTextField(10); // accepts upto 10 characters
	      //  JLabel label1 = new JLabel("<html><font color='white'>Taux de mutation : </font></html>");
	        JLabel label1 = new JLabel("Taux de mutation : ");

	        JTextField tf1 = new JTextField(10); // accepts upto 10 characters
	        JLabel label2 = new JLabel("Nombre des itération : ");

	        JTextField tf2 = new JTextField(10); // accepts upto 10 characters
	       
	        JButton send = new JButton("OK");
	        send.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt){
	            	//affichage.setText("en calcul...");
	                int num=Integer.parseInt(tf.getText());
	                int numIter=Integer.parseInt(tf2.getText());
	                Double mutat=Double.parseDouble(tf1.getText());
	             affichage.setVisible(false);
	              
	                
	                //tout le travail
	              //initialistion de la population
	    		    int tailleP=num;
	    		    int tailleBest;
	    		   
	    		    tailleBest=(tailleP)/2;
	    		    double tauxMutation;
	    		    tauxMutation=mutat;
	    		    int iter=numIter;
	    		    int bestFit=0;
	    		    //pour des raisons de croissement
	    		    if(tailleBest%2 !=0)
	    		    {
	    		    	tailleBest=tailleBest+1;
	    		    }
	    			int [] [] population =new int [tailleP][76];
	    			int [] [] bestPop =new int [tailleBest][76];
	    			int [] [] fils =new int [tailleBest][76];

	    			int [] tempo =new int [75];
	    			int [] parent1 =new int [75];
	    			int [] parent2 =new int [75];
	    			int [] fils1 =new int [75];
	    			int [] fils2 =new int [75];
	    			int [] mutate =new int [75];

	    			//génerer un population aléatoire
	    			population= alger1.isii.algo.GENETIQUE.Population(tailleP);
	    		/*	System.out.println("\nAffichage de la population\n ");
	    			for(int i=0;i<tailleP;i++) {
	    				for(int j=0;j<76;j++) {
	    				System.out.print(" "+population[i][j]);
	    				
	    			}
	    				System.out.println(" ");
	    			}*/

	    			
	    			
	    		for (int i=0;i<tailleP;i++)
	    		{
	    			for (int j=0;j<75;j++)
	    			{
	    				
	    				//System.out.print(" "+population[i][j]);

	    			tempo[j]=population[i][j];
	    			}
	    			//int l=i+1;
	    			//System.out.println("\n\n------ chromosome"+l+" -------");

	    			for (int k=0;k<75;k++)
	    			{
	    		//	System.out.print(" "+tempo[k]);
	    			}
	    			//calculer la fitness de chaque chromosome
	    			int fitness=alger1.isii.algo.GENETIQUE.Evaluation( sat, tempo);
	    			//inserer la fitnesse dans la derniere case de chaque ligne
	    			population[i][75]=fitness;
	    			//si parmis la population initiale existe une solution alors arreter la echerche et afficher
	    			if(fitness==sat.getClauses().size())
	    			{
	    				System.out.println("YES SAT");
	    				break;
	    			}
	    		}
	    			//affichage de la population initiale
	    		System.out.print(" \n la population initiale \n");	
	    		for (int i=0;i<tailleP;i++)
	    		{

	    			for (int j=0;j<76;j++)
	    			{
	    				
	    				System.out.print(" "+population[i][j]);
	    			}
	    			System.out.print(" \n");
	    			}
	    			
	    	
	    		//trier la matrice de population selon les valeurs de fitness
	    		//pour la sélection classée
	    	    int tampon = 0;
	    		boolean permut;
	     
	    		  do {
	    			permut = false;
	    			for (int i = 0; i < (tailleP-1); i++) 
	    			{
	    				// Teste si 2 éléments successifs sont dans le bon ordre ou non
	    				if (population[i][75]< population[i+1][75]) 
	    				{
	    					// s'ils ne le sont pas, on échange leurs positions
	    					for(int l=0;l<76;l++) {
	    					tampon = population[i][l];
	    					population[i][l] = population[i + 1][l];
	    					population[i + 1][l] = tampon;
	    					}
	    					permut = true;
	    				}
	    			}
	    		} while (permut);
	    			
	    		  
	    		  //affichage de la population apres le trie
	    		System.out.print("\n\nLa population en ordre decroissant selon la fonction fitness \n");
	    		for (int i=0;i<tailleP;i++)
	    		{

	    			for (int j=0;j<76;j++)
	    			{
	    				
	    				System.out.print(" "+population[i][j]);
	    			}
	    			System.out.print(" \n");
	    		
	    			}
	    		
	    		//selection classé 50%
	    		for (int i=0;i<tailleBest;i++)
	    		{
	    			for (int j=0;j<76;j++)
	    			{
	    				bestPop[i][j]=population [i][j];
	    			}
	    		}
	    		
	    		//affichage des peres selectionnés
	    		System.out.print("\n\nLa poplation initiale sélectionée \n");
	           for (int i=0;i<tailleBest;i++)
	    		{
	    			for (int j=0;j<76;j++)
	    			{
	    				
	    				System.out.print(" "+bestPop[i][j]);
	    			}
	    			System.out.print(" \n");
	    		
	    			}
	           
	           
	    	bestFit=population[0][75]; int maxim;
	       // System.out.println("taille best "+tailleBest);
	    for(int r=0; r<iter;r++)
	    	//int cpt=1; 
	   
	    //	while(bestFit!=325)
	    {
	    	//System.out.println(" L'itération "+cpt);
	    	System.out.println(" fitness "+bestFit);
	    	if(fils[0][75]>bestFit) {
	    		maxim=fils[0][75];
	    		System.out.println(" MAXXXXXX "+maxim);

	    	}

	    	//cpt++;
	    		//initialiser la matice fils avec des valeurs -1
	    	for(int i=0;i<tailleBest;i++) {
	    		for(int j=0;j<76;j++) {
	    			fils[i][j]=-1;
	    		}
	    	}
	    	
	    	
	    		//selectionner les parents pour le croissement
	    	for(int i=0;i<tailleBest-1;i++)
	          {
	    		for(int j=0;j<75;j++)
	    		{
	    			
	    			parent1[j]=bestPop[i][j];
	    			//System.out.println("parent 1"+parent1[j]);
	    			parent2[j]=bestPop[i+1][j];
	    		}
	    		//point de croisement
	    		Random rand=new Random();
	    		int x=rand.nextInt(75);//generer un nombre aléatoire entre 0 à et 74
	    		fils1=alger1.isii.algo.GENETIQUE.croisement1(parent1, parent2,x);
	    		fils2=alger1.isii.algo.GENETIQUE.croisement2(parent1, parent2,x);

	    	//remplire matrice des fils

	    			for(int j=0;j<75;j++) {
	    				fils[i][j]=fils1[j];
	    				fils[i+1][j]=fils2[j];
	    			}
	    			i++;
	    }
	    	//tester apres le croissement
	    for (int i=0; i<tailleBest;i++)
	    {
	    	for (int j=0;j<75;j++)
	    	{
	    		

	    	tempo[j]=fils[i][j];
	    	}
	    	
	    	int fitness=alger1.isii.algo.GENETIQUE.Evaluation( sat, tempo);	
	    	
	    	if(fitness==sat.getClauses().size())
	    	{
	    		System.out.println("YES SAT");
	    		break;
	    	}
	    	if(fitness>bestFit)	
	    	{
	    		System.out.println(" "+fitness);
	    	}
	    	
	    }
	    	/*System.out.println("\n\n fils apres croisement\n");

	    	for(int e=0;e<tailleBest;e++) {

	    		for(int j=0;j<76;j++) {
	    			System.out.print(" "+fils[e][j]);
	    		}
	    		System.out.println(" ");
	    	}	
	    		*/	
	    		//mutation
	    	Random rand=new Random();
	    	int x=rand.nextInt(3);

	    	for(int i=0;i<x;i++)
	    	{
	    		for(int j=0;j<tailleBest;j++)
	    		{
	    		    for(int e=0;e<mutate.length;e++) 
	    		      {
	    		      	mutate[e]=fils[j][e];

	    		       }
	    		 	mutate=alger1.isii.algo.GENETIQUE.mutation(mutate,tauxMutation);
	    		
	    		 	for(int a=0;a<75;a++) {
	    				fils[j][a]=mutate[a];
	    				
	    			}
	    			
	    			
	    		}
	    		}
	    	//afichage
	    /*	System.out.println("fils apres mutation");

	    	for(int e=0;e<tailleBest;e++) {

	    		for(int j=0;j<76;j++) {
	    			System.out.print(" "+fils[e][j]);
	    		}
	    		System.out.println(" ");
	    	}*/
	    	//evaluer les fils
	    	//System.out.println("\n\n fils apres croisement et mutation\n");
	    	for (int i=0;i<tailleBest;i++)
	    	{

	    		for (int j=0;j<75;j++)
	    		{
	    			

	    		tempo[j]=fils[i][j];
	    		}
	    		/*int l=i+1;
	    		System.out.println("\n\n------ chromosome fils "+l+" -------");

	    		for (int k=0;k<75;k++)
	    		{
	    		System.out.print(" "+tempo[k]);
	    		}*/
	    		int fitness=alger1.isii.algo.GENETIQUE.Evaluation( sat, tempo);	
	    		fils[i][75]=fitness;
	    		/*for (int m=0;m<76;m++)
	    		{
	    			

	    		System.out.print(" "+fils[i][m]);
	    		}
	    		System.out.print("\n");
	    */
	    		
	    		if(fitness==sat.getClauses().size())
	    		{
	    			System.out.println("YES SAT");
	    			break;
	    		}
	    	}
	    	
	    	
	    	
	    	
	    	//trier la matrice de fils selon les valeurs de fitness
	    			int tampo = 0;
	    			boolean permuter;
	    	 
	    			do {// hypothèse : le tableau est trié
	    				permuter = false;
	    				for (int i = 0; i < (tailleBest-1); i++) 
	    				{
	    					// Teste si 2 éléments successifs sont dans le bon ordre ou non
	    					if (fils[i][75]< fils[i+1][75]) 
	    					{
	    						// s'ils ne le sont pas, on échange leurs positions
	    						for(int l=0;l<76;l++) {
	    						tampo = fils[i][l];
	    						fils[i][l] = fils[i + 1][l];
	    						fils[i + 1][l] = tampo;
	    						}
	    						permuter = true;
	    					}
	    				}
	    			} while (permuter);
	    			if(fils[0][75]>bestFit)	
	    			{
	    				bestFit=fils[0][75];
	    			}
	    			/*System.out.print("decroissant \n");
	    			for (int i=0;i<tailleBest;i++)
	    			{

	    				for (int j=0;j<76;j++)
	    				{
	    					
	    					System.out.print(" "+fils[i][j]);
	    				}
	    				System.out.print(" \n");
	    			
	    				}*/
	    			
	    		
	    }		
	    	System.out.println("nombre max de clauses satisfaites retenu est "+bestFit);
	    	 affichage.setVisible(true);
	    	affichage.setFont(new Font("Serif", Font.PLAIN, 22));
	    	 affichage.setText("<html><br/><br/><br/>Nombre max de clauses satisfaites retenu est : "+Integer.toString(bestFit)+"</html>");       
	            }
	        });
	        JButton stop = new JButton("STOP");
	        stop.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt){
	            	System.exit(0);
	            }
	        });
	        panel.add(label); // Components Added using Flow Layout
	        panel.add(tf);
	        panel.add(label1); // Components Added using Flow Layout
	        panel.add(tf1);
	        panel.add(label2); // Components Added using Flow Layout
	        panel.add(tf2);
	        panel.add(send);
	       panel.add(stop);
	        panel.add(affichage);
	   

	      

	        //Adding Components to the frame.
	        frame.getContentPane().add(BorderLayout.CENTER, panel);
	      //  frame.getContentPane().add(BorderLayout.CENTER, ta);
	        frame.setVisible(true);
			
			
	        break;
				
			
			case 6:System.out.println("******** ACS ********"); 
			// Création du cadre
	        JFrame frameA = new JFrame ("ACS");
	        frameA.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	        frameA.setSize (1000, 400);
	        JLabel affichageA = new JLabel(" ");
	       
	       
	        
	        //Creating the panel at bottom and adding components
	        JPanel panelA = new JPanel(); // the panel is not visible in output
	        panelA.setBackground(new Color( 235,212,149));
	      // panel.setLayout(new GridLayout(3,2));
	       // JLabel label = new JLabel("<html><font color='white'>Taille de la population : </font></html>");
	        JLabel labelA = new JLabel("Alpha : ");

	        JTextField alffa = new JTextField(10); // accepts upto 10 characters
	      //  JLabel label1 = new JLabel("<html><font color='white'>Taux de mutation : </font></html>");
	        JLabel label1A = new JLabel("Beta");

	        JTextField tf1A = new JTextField(10); // accepts upto 10 characters
	        
	        JLabel label2A = new JLabel("q0 : ");
	        JTextField tf2A = new JTextField(10); // accepts upto 10 characters
	       
	        JLabel roo = new JLabel("Ro : ");
	        JTextField troo = new JTextField(10); // accepts upto 10 characters
	        
	        JLabel Lpherp = new JLabel("Nombre d'itérations : ");
	        JTextField Tphero = new JTextField(10); // accepts upto 10 characters
	        
	        JButton sendA = new JButton("OK");
	        sendA.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent evt){
	            	//affichage.setText("en calcul...");
	            	float alfa=Float.parseFloat(alffa.getText());
	                float qZero=Float.parseFloat(tf2A.getText());
                    float bita=Float.parseFloat(tf1A.getText());
	                float Row=Float.parseFloat(troo.getText());
	   
	                int pheroo=Integer.parseInt(Tphero.getText());
			//initialisation des paramètres empiriques
			 float alpha =  alfa , beta =  bita , q0 =  qZero , Ro = Row , pheromone0 = 0.1f; 
			//Vecteur des fourmis.
			Vector<Fourmi> fourmis = new Vector<>();  //vecteur des fourmis
			Vector<Pheromone_table> litterauxF = new Vector<>();  //vecteur des litteraux avec son pheromone
			ArrayList<int []> clauses_bechmark = new ArrayList<int []> (); //vecteur des clauses
			
			
			
			InputStream ips = null;
			try {
				//1ere instance
				ips = new FileInputStream("UF75.325.100/uf75-01.cnf");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			InputStreamReader ipsr=new InputStreamReader(ips);
			
			BufferedReader br=new BufferedReader(ipsr);
			String ligne ;
			String [] espace;//definir les espaces lors la recuperaton des litteraux
			
			//Les paramettres initiales 
			
			int nb_fourmis =60 ;//nombre de fourmis
			
			int max_iter = pheroo ;//nombre max d'itérations
			
			
			
			//recuperer les clauses des fichiers de benchmark
			int i_clause=0;
			try {
				while ((ligne=br.readLine())!=null)//tant que pas la fin de fichier
				{
					i_clause++; //augmenter nombre de points
					if((9<=i_clause)&&(i_clause<=333)) //pour chaque clause de fichier
					  {
						int [] tab_litteraux = new  int [3];//initialiser un tableau de taille 3 pour recupere les litteraux
						 espace = ligne.split(" ");//les espaces
						for (int k = 0; k < 3; k++) {
							 tab_litteraux[k] = Integer.parseInt(espace[k]);
							}
						 clauses_bechmark.add( tab_litteraux); //ajouter la clause au vector es clauses
						}
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			

			//initialiser le vecteur des litteraux avec de taux de phermone initial 0.1
			
				for(int i=-75;i<76;i++)//pour avoir des valeurs des litteraux entre -75 et 75
				{ 
					if(i!=0) litterauxF.addElement(new Pheromone_table (i , pheromone0));
					
				}
				
				
			
			//initialiser les the fourmis
			for (int i = 0; i < nb_fourmis; i++)//pour chaque fourmi
			{
				//ajouter la fourmi dans le vecteur des fourmis
				//initialiser la solution des fourmis null
				//avec une valeur d'evaluation a 0
				fourmis.addElement(new Fourmi(null, 0));
			}
			
			//Selectionner la meilleure fourmi
			Vector<Fourmi> meilleure_fourmi = new Vector<>();
			meilleure_fourmi.add(new Fourmi (null , 0));
			
			//Debut du travail
			for (int i = 0; i < max_iter; i++)
			{
				//pour chaque fourmi
				for (int j = 0; j < nb_fourmis; j++) 
				{
					//initialiser vecteur de solution pour la formi k
					 Vector<Integer> k = new Vector<>();
					//Construire la solution avec les paramètres 
					 k = alger1.isii.algo.ACS.Construire( litterauxF,q0,alpha,beta,clauses_bechmark); 
					//modifier les parametre de la fourmi
					fourmis.get(j).setSolution(k); //modifier la solution
					fourmis.get(j).setEvaluation(alger1.isii.algo.ACS.evaluation(k,clauses_bechmark)); //evaluer la solution 
				}
				
				//la mise à jour online "Online delayed update "
				for (int online = 0; online < fourmis.size(); online++) 
				{
					//initialiser vecteur de solution
					Vector<Integer> solution = new Vector<>();
					//recupere la solution de la fourmi
					solution = fourmis.get(online).getSolution();
					for (int l = 0; l < litterauxF.size(); l++) {
						if(litterauxF.get(l).getLiteral() == solution.get(online)){
							float p = litterauxF.get(l).getPheromone();
							//evaluer la solution
							float evaluat = alger1.isii.algo.ACS.evaluation(solution,clauses_bechmark)/325f;
							float update = (float) ((1-Ro)*p + Ro*evaluat);
							//mettre à jour le pheromone
							litterauxF.get(l).setPheromone(update);
						}
					}
				}
				
				//Choisir la meilleure solution pour cette itération
				int best_sol = fourmis.get(0).getEvaluation() , indice_best=0;
				for (int a = 1; a < fourmis.size(); a++)
				{
					//si la solution obtenu dans cette itération est meilleure que les autres on la definit comme best_sol
					if(fourmis.get(a).getEvaluation() > best_sol){
						//recupere l'evalution de cette solution
						best_sol = fourmis.get(a).getEvaluation();
						//recupere l'indice de cette solution
						indice_best = a;
					}
				}
				//deinir la solution obtenu comme meilleure et inserer ses info dans une meilleure définit comme meilleure fourmi
				if(best_sol > meilleure_fourmi.get(0).getEvaluation())
				{
					meilleure_fourmi.get(0).setEvaluation(best_sol);//attribuer l'evaluation a cette fourmi
					meilleure_fourmi.get(0).setSolution(fourmis.get(indice_best).getSolution());//attribuer la meilleure sol a la fourmi
				}
				
			
				//Mettre à jours le phéromone « Offline delayed update »
				//initialiser un vecteur de la meilleure solution
				Vector<Integer> offline = new Vector<Integer>();
				//ajouter la meilleure solution dans offline
				offline.addAll(fourmis.get(indice_best).getSolution());
				//parcourir le veceur offline
				for (int g = 0; g < offline.size(); g++) {
					//pour chaque litteral
					for (int j = 0; j < litterauxF.size(); j++) {
							if(offline.get(g) == litterauxF.get(j).getLiteral()){
								//recupere la quantité de phéromone
								float p = litterauxF.get(j).getPheromone();
								//evaluer le vecteur offline
								float eval = alger1.isii.algo.ACS.evaluation(offline,clauses_bechmark)/325f;
								//metter a jour
								float update = (float) ((1-Ro)*p + Ro*eval); //meilleure_fourmi-best/325
								//remplacer la valeur de phéromone
								litterauxF.get(j).setPheromone(2*update);
							}
					}
				}
				
				
		}
			System.out.println("Meilleure solution ------> "+meilleure_fourmi.get(0).getEvaluation()+"\n");
	    	affichageA.setFont(new Font("Serif", Font.PLAIN, 22));
	    	 affichageA.setText("<html><br/><br/><br/>Meilleure solution Construite ------> "+Integer.toString(meilleure_fourmi.get(0).getEvaluation())+"</html>"); 
	            }
	            });
		    panelA.add(labelA); // Components Added using Flow Layout
	        panelA.add(alffa);
	        panelA.add(label1A); // Components Added using Flow Layout
	        panelA.add(tf1A);
	        panelA.add(label2A); // Components Added using Flow Layout
	        panelA.add(tf2A);
	        panelA.add(roo); // Components Added using Flow Layout
	        panelA.add(troo);
	        panelA.add(Lpherp); // Components Added using Flow Layout
	        panelA.add(Tphero);
	        panelA.add(sendA);
	        panelA.add(affichageA);
	   

	      

	        //Adding Components to the frame.
	        frameA.getContentPane().add(BorderLayout.CENTER, panelA);
	      //  fraame.getContentPane().add(BorderLayout.CENTER, ta);
	        frameA.setVisible(true);
			break;	 
				    
		}//end switch

		
	}
	
		



}
