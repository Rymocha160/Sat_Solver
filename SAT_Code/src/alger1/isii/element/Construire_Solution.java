/**
 * 
 */
package alger1.isii.element;

/**
 * @author Administrateur
 *
 */
public class Construire_Solution {
	
	int literal ;
	float val;
	
	public Construire_Solution (int l , float v){
		this.literal = l;
		this.val = v;
	}
//getters et setters
	public int getLiteral() {
		return literal;
	}

	public void setLiteral(int literal) {
		this.literal = literal;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
	
	

}
