/**
 * 
 */
package alger1.isii.element;

/**
 * @author Administrateur
 *
 */

import java.util.Vector;

public class Fourmi {
//initialiser un vecteur qui represente une solution qu'une fourmi peut cinstruire
	Vector<Integer> solution = new Vector<>();
//Evaluation de cette solution
	int evaluation;

//constructeur
	public Fourmi(Vector<Integer> s , int v ){
		this.solution = s;
		this.evaluation = v;
	}
//getters et setters
	public Vector<Integer> getSolution() {
		return solution;
	}
	public void setSolution(Vector<Integer> solution) {
		this.solution = solution;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int valuation) {
		this.evaluation = valuation;
	}
		
}

