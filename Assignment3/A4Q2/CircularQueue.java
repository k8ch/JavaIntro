import java.util.LinkedList;
import java.util.List;

public class CircularQueue<E> implements Queue<E> {

    private int front, rear, size;
    private final E[] elems;

    @SuppressWarnings("unchecked")

    public CircularQueue(int capacity) {
        elems = (E[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void enqueue(E value) {
        rear = (rear + 1) % elems.length;
        elems[rear] = value;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        E savedValue = elems[front];
        elems[front] = null; // ``scrubbing''
        size--;
        front = (front + 1) % elems.length;
        return savedValue;
    }
    public List<E> dequeue(int toDelete){
    	//precondition: array is empty 
    	if(size == 0){
    		throw new EmptyQueueException();
    	}
    	//create a temporary array that saves the values that will be removed 
    	LinkedList<E> tempElems = new LinkedList<E>();
    	//loop through queue until values that needs to be delete are done
    	for(int i = toDelete; i > 0; i--){
    		//save all the values into temporary array
    		tempElems.addFirst(elems[front]);
    		//set the front element to null 
    		elems[front] = null; 
    		//the size is decreased
    		size--; 
    		//set the front to point to the next element
    		front = (front + 1)% elems.length; 
    	}
    	return tempElems; 
    }

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder(getClass().getName() + "[");

        if (size > 0) {

            int offset = 0;

            str.append(elems[front]);
            offset = offset + 1;

            while (offset < size) {

                str.append(", ");
                str.append(elems[(front + offset) % elems.length]);
                offset = offset + 1;

            }

        }

        str.append("]");

        return str.toString();

    }
}
