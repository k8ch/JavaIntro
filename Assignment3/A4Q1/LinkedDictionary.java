/* ITI1121 A
 * Name: Kateryna Chernega
 * 
 */
import java.util.NoSuchElementException;




public class LinkedDictionary implements Map<String, Token>{

	//The elements of Dictionary are stored in this class. 
	private static class Elem{
		private String key; 
		private Token value; 
		private Elem next; 
		
		private Elem(String key, Token value, Elem next){
			this.key = key; 
			this.value = value; 
			this.next = next; 
		}
	}
	
 
	private Elem head; 
	private Elem tail; 
	
	public LinkedDictionary(){
		head = null;
		tail = null; 
	}
	
	/*
	 * Creates a new key-value association
	 *
	 */
	public void put(String key, Token value){
		//condition: if key is null
		if((key == null)||(value == null)){
			throw new NullPointerException();
		}
		//if there are no elements in the list
		if(head == null){
			head = new Elem(key, value, null);
			tail = head;
		} else {
		Elem newTail = new Elem(key, value, tail);
		tail = newTail; 
		}
	}
	/*
	 *  Returns the leftmost value associated with this specified key. 
	 *  The methods throws java.util.NoSuchElementException if the 
	 *  element being requested does not exist. 
	 *  It throws NullPointerException if the specified key is null.
	 * 
	 */
	public Token get(String key){
		//Set a node to reference to the head
		Elem current = tail;
		//if the key at this element is null, throw exception
		if(key == null){
			throw new NullPointerException();
		}
		//search through the list 
		while(current != null){
			//if current reference contains a key, that is the same to asked
			if(current.key.equals(key)){
			//return value that is contained together with this key
				return current.value;
			}
			//otherwise, keep searching
			current = current.next; 
		}
		
		//if nothing is found, exit the loop, throw the exception
		throw new NoSuchElementException();
	}
	/*
	 * returns true if an association exists for the specified key. 
	 * It throws NullPointerException if the specified key is null.
	 * 
	 */
	public boolean contains(String key) {
		//throw exception if the key is null
		if(key == null){
			throw new NullPointerException();
		}
		//check for empty list 
		if(head == null){
			return false;
		}
		//set up the current element to equal to the tail
		Elem current = tail;
		//traverse through the list
		while(current != null){
			//check if key at the current position is equivalent to the given key
			if(current.key == key){
				//if so, return true
				return true;
			}
			//otherwise, set current to next
			current = current.next;
		}
		
		return false;
	}
	/*
	 *replaces the value of the leftmost occurrence of the association for the specified key. 
	 *It throws NoSuchElementException if no such association exists. 
	 *It throws NullPointerException if the specified key or value is null.
	 *
	 */
	public void replace(String key, Token value) {
		//Throw exception if given key is null or value is null
		if((key == null)||(value == null)){
			throw new NullPointerException();
		}
		//set a new element to equal to tail
		Elem current = tail;
		//search the list
		while(current != null){
			//if key that is at the current node is equivalent to the given key
			if(current.key == key){
				//replace the value of the current with given value
				current.value = value; 
				return;
			}
			//keep searching through the list otherwise
			current = current.next;
		}
		//if no element found, throw the exception
		throw new NoSuchElementException();
	}
	
	/*
	 * removes the leftmost association for the specified key, and returns 
	 * the value that was associated with the key. 
	 * It throws java.util.NoSuchElementException if the element being requested
	 *  does not exist. It throws NullPointerException if the specified key is null.
	 */
	public Token remove(String key) {
		//throw exception if key is null
		if(key == null){
			throw new NullPointerException();
		}
		//create new element that point to tail
		Elem current = tail;
		Elem previous = null;
		//search the list
		while(current != null){
			//if key in the list is equivalent to the given key
			if(current.key == key){
				//save the value of the current
				Token temp = current.value;
				if(current == tail){
					tail = current.next;
					return temp;
					
				}
				previous.next = current.next;
				return temp;
			} 
			previous = current;
			current = current.next;
		}
		throw new NoSuchElementException();
	}
}


	