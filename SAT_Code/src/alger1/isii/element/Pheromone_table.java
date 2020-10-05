package alger1.isii.element;

public class Pheromone_table {

	

	int literal;
	float pheromone;
	//attribuer la phéromone dans littéral
	public Pheromone_table(int l , float p) {
	
		this.literal = l;
		this.pheromone = p;
		
	}
//getters et setters
	public int getLiteral() {
		return literal;
	}

	public void setLiteral(int literal) {
		this.literal = literal;
	}

	public float getPheromone() {
		return pheromone;
	}

	public void setPheromone(float pheromone) {
		this.pheromone = pheromone;
	}
	
	


}
