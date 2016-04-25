// Author: Kateryna Chernega
// Course: ITI 1121-A
// Assignment: 1
// Question: 2
import java.util.Arrays;
import java.util.Random;


/** 
 * A <code>Population</code> is a collection of individuals (each one
 * representing a candidate solution for the n-queens problem).  To
 * facilitate the implementation of the various methods, <b>the
 * individuals will always be kept in increasing value of fitness</b>.
 */

public class Population {
    
    private int size; 
    private int dimension; 
    private Individual[] population; 
    private double MUTATION_RATE = 0.8; //constant 
    
    
    /**
     * A constructor of arity 2 to initialize the <b>Population</b>.
     * 
     * @param size is the number of individuals of this population
     * @param dimension is the size of the board and also the number of queens
     */

    public Population(int size, int dimension) {
    	//initializes the size and dimension of the board. 
    	this.size = size; 
    	this.dimension = dimension;
    	population = new Individual[size];
    	//creates an Individual with the dimension and fills it with queens. 
    	for (int n = 0; n < size; n++){
    		population[n] = new Individual(Util.getPermutation(dimension)); 
    		for(int i = 0; i < n-1; i++){
        		if(population[n-1-i].getFitness() < population[n-2-i].getFitness()){
        			Individual temp = population[n-2-i]; 
        			population[n-2-i] = population[n-1-i];
        			population[n-1-i] = temp; 
        		}
        		else{
        			break;
        		}
        	}
    	}
    }
   
    /**
     * The method <code>evolve</code> selects parent individuals. 
     * An offspring is then created from the two parents, using the method <code>crossover</code>. 
     * With probability <code>MUTATION_RATE</code>, the offspring is <code>mutated</code>. 
     * Use 0.8 as the default <code>MUTATION_RATE</code> 
     * The resulting child is inserted into the population. 
     * As a result, the least fitted individual will be eliminated from the population. 
     * Remember that the <code>population</code> is kept in increasing order of fitness. For the selection of the parents, you can
     * experiment with different scenarios. A possible scenario is to randomly
     * select two parents. Another possible one would be to select the most fit,
     * and a randomly selected one. Or else, select the two most fitted
     * individuals.
     */

    public void evolve() {
    	//randomly selects two parents     	
    	int parent1 = (int) (Math.random()*size); 
    	int parent2 = (int) (Math.random()*size);
    	//using method crossover generate an offspring from previously selected parents 
    	Individual offspring = population[parent1].recombine(population[parent2]); 
    	//mutate the offspring by no less then 80%
    	if(Math.random()<= MUTATION_RATE){
    		offspring.mutate();
    	}
    
    	population[size-1] = offspring;
    	//sort the arrays depending of the fitness such that individuals who have the least fitness are at the front
    	for(int i = 0; i < size-1; i++){
    		if(population[size-1-i].getFitness() < population[size-2-i].getFitness()){
    			Individual temp = population[size-2-i]; 
    			population[size-2-i] = population[size-1-i];
    			population[size-1-i] = temp; 
    		}
    		else{
    			break;
    		}
    	}
    	
    	
    }
    
    /**
     * The instance method <code>public Individual getFittest()</code> returns the
     * "best" individual of the population, i.e. the one that has the smallest
     * fitness value.
     * 
     * @return returns the currently best solution
     */
     
    public Individual getFittest() {
    	//returns the first individual in the array
    	return population[0]; 
	
    }
    
    /**
     * Returns a <code>String</code> representation of this <code>Population</code>.
     * 
     * @return the String representation of this Population
     */

    public String toString() {

	String str = ""; 
	str += "{";
	for(int i=0; i < size; i++){
		str += population[i].toString();
		str += ",";
	}
	str += "}";
	return str;

	}
}
