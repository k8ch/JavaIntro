/* ITI1121 A
 * Name: Kateryna Chernega
 * 
 */
public class BinarySearchTree< E extends Comparable<E>> {

    private static class Node<F> {

        private final F value;
        private Node<F> left;
        private Node<F> right;

        private Node(F value) {
            this.value = value;
            left = null;
            right = null;
        }

    }

    private Node<E> root = null;

    /**
     * Inserts an object into this BinarySearchTree.
     *
     * @param obj item to be added
     * @return true if the object has been added and false otherwise
     */

    public boolean add(E obj) {

        // pre-condtion:
        if (obj == null) {
            throw new NullPointerException("Illegal Argument");
        }

        // special case:
        if (root == null) {
            root = new Node<E>(obj);
            return true;
        }

        // general case:
        return add(obj, root);
    }

    private boolean add(E obj, Node<E> current) {

        boolean result;
        int test = obj.compareTo(current.value);

        if (test == 0) {
            result = false; // already exists, not added
        } else if (test < 0) {
            if (current.left == null) {
                current.left = new Node<E>(obj);
                result = true;
            } else {
                result = add(obj, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = new Node<E>(obj);
                result = true;
            } else {
                result = add(obj, current.right);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return toString(root);
    }
    
    public int count(E low, E high){
    	return count(root, low, high);
    }
    
    private int count(Node<E> root, E low, E high){
    	int count = 0; 
    	//base case, when the left node exists. 
    	if(root.left != null){
    		count+=count(root.left, low, high);
    	}
    	//same for the right node 
    	
    	if(root.right != null){
    		count+=count(root.right, low, high);
    	}
    
    	//count the values between the low and high 
    	if((low.compareTo(root.value) <= 0)&&(high.compareTo(root.value) >= 0 )){
    		count++; 
    	}
    	
    	return count; 
    	
    	
    	
    	
    }

	private String toString(Node<E> p) {
        if (p == null) {
            return "null";
        } else {
            return "(" + toString(p.left) + "," + p.value + "," + toString(p.right) + ")";
        }
    }

}
