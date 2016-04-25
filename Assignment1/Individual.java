// Author: Kateryna Chernega
// Course: ITI 1121-A
// Assignment: 1
// Question: 3
import java.util.Random;



/**
 * An <code>Individual</code> (a chromosome) is a candidate solution for a given
 * problem. Its representation depends on the specific problem to be solved. Two
 * individuals can be combined (see method crossover) to produce a new
 * offspring. As with natural chromosomes, these artificial ones suffer
 * mutations. Each chromosome has a fitness value that indicates the quality of
 * this solution.
 * <p/>
 * 
 * A <code>Population</code> is a collection of chromosomes. At each iteration
 * (generation), the genetic algorithm selects chromosomes for reproduction. The
 * offsprings are inserted into the population, and the least fitted individuals
 * are eliminated. Thus, the size of the population is fixed.
 * <p/>
 * 
 * For this assignment, an <code>Individual</code> represents a solution to the
 * <code>n</code>-Queens problem. As introduced in the assignment description, a
 * candidate solution is represented by a permutation of size <code>n</code>,
 * such that attribute <code>i</code> represents the row for the queen found at
 * column <code>i</code>.
 * <p/>
 * 
 * Not all permutations are valid solutions to <code>n</code>-Queens problem. A
 * permutation is a valid solution if no two queens can attack each other. Two
 * queens are attacking each other if they are on the same row or column, which
 * is impossible given this representation, but also if they are found on the
 * same minor or major diagonal.
 * <p/>
 *
 * Herein, we define the fitness value of an individual as the number of pairs
 * of queens attacking each other.
 * <p/>
 * You must complete the implementation of the class <code>Individual</code>
 * following all the directives.
 *
 * @author Marcel Turcotte (turcotte@eecs.uottawa.ca)
 */




public class Individual implements Comparable<Individual> {
	private int[] board;
	private int size; 
	
    //initializes the board and its size
    public Individual(int size) {
    	
    this.size = size; 
    board = new int[size]; 
    board = Util.getPermutation(size);
    }

    /**
     * Creates an <code>Individual</code> using the provided permutation. The method
     * must copy the values of the permutation into a new array. This constructor
     * is primarily used for testing.
     * 
     * @param permutation used to initialize the attributes of this <code>Individual</code>
     */
 
    public Individual(int[] permutation) {
    	size = permutation.length;
    	board = new int[size];    
    	for(int i=0; i < size; i++){ //copies the values of the permutation into a new array 
    		board[i] = permutation[i];
    	}
    	
    }

    /**
     * Returns the offspring resulting from the crossover of <code>this</code>
     * <code>Individual</code> and <code>other</code>. The result must be a valid
     * permutation!
     * <p/>
     * 
     * In particular, the naive solution consisting of taking the first
     * <code>position-1</code> attributes of this <code>Individual</code> and
     * the last <code>size-position</code> attributes of <code>other</code> would
     * not generate a valid permutation in most cases.
     * <p/>
     *  
     * Instead, we are proposing that the first <code>position-1</code> attributes 
     * of this <code>Individual</code> are copied to the offspring, then the
     * missing values will be selected from <code>other</code>, whilst preserving
     * their order of appearance in <code>other</code>.
     * <p/>
     * 
     * This method is primarily used for testing.
     * 
     * @param other a reference to an <code>Individual</code>
     * @param position the location of the crossover
     * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
     */
    
    public Individual crossover(Individual other, int position) {
    	// create the temporary variable that holds the given position of some value inside the board 
    	int temp = board[position]; 
    	//overrides the value in this board with the one in other at the specified position
    	board[position] = other.board[position]; 
    	Individual offspring = new Individual(board);
    	//makes sure that after the offspring is created, parent stays the same as it was at the beginning
    	board[position] = temp; 
    	return offspring; 
    }

    /**
     * Returns the offspring resulting from the crossover of <code>this</code>
     * <code>Individual</code> and <code>other</code>. The method randomly selects the
     * position of the crossover. The result must be a valid permutation!
     * <p/>
     * 
     * In particular, the naive solution consisting of taking the first
     * <code>position-1</code> attributes of this <code>Individual</code> and the last
     * <code>size-position</code> attributes of <code>other</code> would not generate a
     * valid permutation in most cases.
     * <p/>
     * 
     * Instead, we are proposing that the first <code>position-1</code> attributes
     * of this <code>Individual</code> are copied to the offspring, then the missing
     * values will be selected from <code>other</code>, whilst preserving their
     * order of appearance in <code>other</code>.
     * <p/>
     * 
     * This method is used by <code>Population</code>.
     * 
     * @param other a reference to an <code>Individual</code>
     * @return the offspring resulting from the crossover of <code>this</code> and <code>other</code>
     */
    
    public Individual recombine(Individual other) {
    	//creates a new variable that selects a random position in the board.  
    	int position;
    	position = (int) (size*Math.random());
    	//changes the value found at random position in this board with the value in other board.
    	int temp = board[position];
    	board[position] = other.board[position];
    	//creates a new Individual, offspring, to store changed board
    	Individual offspring = new Individual(board);
    	//return parent back to the values it had before changes 
    	board[position] = temp;
    	return offspring; 
    	    	
    

    }
    
    /**
     * Returns the offspring resulting from applying a mutation
     * to this <code>Individual</code>. In order to make sure that 
     * the result is valid permutation, the method exchanges
     * the value of two attributes, those found at positions
     * <code>i</code> and <code>j</code>.
     * 
     * This method is primarily used for testing.
     * 
     * @param i the first attribute 
     * @param j the second attribute
     * @return the offspring resulting from exchanging attributes <code>i</code> and <code>j</code>
     */

    public Individual mutate(int i, int j) {
 
        	int n = board[i];
        	board[i] = board[j];
        	board[j] = n; 
        	Individual offspring = new Individual(board);
        	board[j] = board[i];
        	board[i] = n;
        	return offspring; 
	

    }

    /**
     * Returns the offspring resulting from applying a mutation
     * to this <code>Individual</code>. In order to make sure that 
     * the result is valid permutation, the method exchanges
     * the value of two randomly selected attributes.
     * <p/>
     * 
     * This method is called by <code>Population</code>.
     * 
     * @return the offspring resulting from exchanging two randomly selected attributes
     */

    public Individual mutate() {
    	int a = (int) (Math.random()*size);//generates first random number of the position1
    	int b;
        	do {
    	b= (int) (Math.random()*size);//generates second random number of the position2
    	}
    	//generate again when the value at position2 the same as position 1
    	while(a==b);
        	//exchanged the values of selected positions 
    	int i = board[a];
    	board[a] = board[b];
    	board[b] = i; 
    	Individual offspring = new Individual(board);
    	board[b] = board[a];
    	board[a] = i;
    	return offspring; 

    }
    
    /**
	 * Returns the fitness value of <code>this Individual</code>, which
	 * is defined as the number of pairs of queens attacking each
	 * other.
	 * 
	 * @return the fitness value of <code>this Individual</code>. 
	 */
	
	public int getFitness() {
		int fitness = 0; 
		for(int start = 0; start < size; start++){
			int n = 1;
			//checks if there are any queens hitting each other 
			//below the first line, and on the diagonals: left and right from where the queen is positioned. 
			for(int next = start+1; next < size; next ++){
				if((board[next] == board[start] - n) 
				|| (board[next] == board[start])
				|| (board[next] == board[start] + n))
				{
					fitness++; 
				}
				n++;
			}
		}
		return fitness; 
	
	}

	/**
     * Returns a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> is
     * less than, equal to, or greater than the fitness of the specified <code>Individual</code>. 
     * 
     * @param other <code>Individual</code> to be compared
     * @return a negative integer, zero, or a positive integer as the fitness of this <code>Individual</code> 
     *         is less than, equal to, or greater than the fitness of the specified <code>Individual</code>.
     */
    
    public int compareTo(Individual other) {

    	return (this.getFitness() - other.getFitness());
    }

    /**
     * Returns a string representation of this <code>Individual</code>.
     * 
     * @return a string representation of this <code>Individual</code>
     */
    
    public String toString() {
    	String str="";
    	str += "{";
    	for(int i=0; i < size; i++){
    		str += board[i];
    		str += ",";
    	}
    	str += "}";
    	return str;
   
    	}

    
    /**
     * Runs a series of tests.
     * 
     * @param args command line parameters of the program
     */

    public static void main(String[] args) {
    	
    	int[] a= {1,2,3,2};
    	Individual i = new Individual(a);
    	System.out.println(i.getFitness());
	
    }
}
