public class JStock {
	
	//creates a queue that stores transactions
	private LinkedQueue<Transaction> transaction;
	
	//constructor initializes the queue 
	public JStock(){
		transaction = new LinkedQueue<Transaction>();
		
	}
	
	/*
	 * Adds a new transaction at the rear of the queue. 
	 * The values of the parameters are used to create a new transaction
	 */
	public void buy(int num, float sharePrice){
		//create a new Transaction object with given values. 
		Transaction newTrans = new Transaction(num, sharePrice);
		//insert the transaction at the end of the list
		transaction.enqueue(newTrans);
	}
	/*
	 * Updates the queue of transaction so as to 
	 * reduce the total number of shares by num
	 * and returns the resulting capital gain or capital loss
	 * (a negative gain)
	 */
	public float sell(int num, float sharePrice){
		//Create initial gain 
		float gain = 0; 
		//case: given num is less then or equal to the given num 
		if(num < transaction.peek().getShares()){
			gain = (transaction.peek().getShares()*(transaction.peek().getSharePrice() - sharePrice));
			transaction.peek().sell(num);
			return gain; 
		}
		if(num >= transaction.peek().getShares()){
			//save gain from the first object into temporary float
			float tempGain = (transaction.peek().getShares()*(transaction.peek().getSharePrice() - sharePrice));
			//save the difference between share that is given and after substracting it from the transaction
			num = num - transaction.peek().getShares(); 
			//delete the transaction
			transaction.dequeue(); 
			
			
			//add amount to get the final gain value
			gain = tempGain + sell(num, sharePrice);
		}
		
		return gain; 
	}
	/*
	 * return the total value of the portfolio. This is the sum
	 * of the value of all the transactions. The value of a transaction 
	 * is simply the product of the number of shares by the share price. 
	 * 
	 */
	public float getValue(){
		float value = 0;
		LinkedQueue<Transaction> newTrans = new LinkedQueue<Transaction>();
		while(transaction.peek() != null){
			value = value + transaction.peek().getShares() * transaction.peek().getSharePrice();
			newTrans.enqueue(transaction.dequeue());
		}
		transaction = newTrans;
		return value; 
	}

}
