import java.util.Iterator;
import java.util.List;


public class Frequency {

	public static void frequency(List<Tuple> l) {
		//create an iterator
		Iterator<Tuple> iterator = l.iterator(); 
		
		int count = 0; 
		//check if there is an element 
		while(iterator.hasNext()){
			//if so, make iterator point to it 
			Tuple elem = iterator.next();
			//if element haven't been visited yet 
			if(!elem.visited()){
				//create another iterator
				Iterator<Tuple> itr = l.iterator(); 
				//while the element exists
				while(itr.hasNext()){
					//point iterator to the element
					Tuple nextElem = itr.next();
					//if the element has the same character as specified character
					if(nextElem.getChar() == elem.getChar()){
						//set the boolean value to true
						nextElem.toggle();
						//increment counter
						count++;
					}
				}
				//print the elements 
				System.out.println("The frequence of " + elem.getChar() + " is " + count);
				count = 0;
			}
			
			
		}
		
		
	}

}
