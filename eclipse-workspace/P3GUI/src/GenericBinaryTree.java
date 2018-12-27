/**Generic class for BST. Class requires method for: 
 * Insert new value into tree. Does not need to rebalance tree. It should allow duplicate entries. 
 *    Must be written using recursion 
 * Inorder Tree Traversal: performs an inorder tree traversal that generates and returns a 
 *    string containing tree elements in sorted order
 *    
 * @author Kelly M. McCutchen
 *
 */

//in this specific program, values entered should be: 
//Integer type or Fraction type
public class GenericBinaryTree<T extends Comparable <T>> {

	/* Class for left, right child node and key value*/
	private class Node {
		private T key; 
		private Node left, right; 
		
		private Node (T x) {
			key = x; 
			left = null; 
			right = null; 
		} //end: constructor (Node E) 
	} //end class:Node
	
	
	//root of BST
	private Node root;
	
	//evaluated tree
	private String myTreeString; 
	
	GenericBinaryTree() {
		root = null; 
	} //end GenericBinaryTree constructor
	
	//insert types for this project should Integer or Fraction types 
	//(both have Comparable (.compareTo) interfaces/methods
	public void insert (T x) {
		root = insertRecord (root, x); 
	} //end: insert
	
	/*function for inserting new keys into BST*/
	
	private Node insertRecord (Node root, T x) {
		
		//if tree is empty, return new node
		if (root == null) {
			root = new Node (x); 
			return root; 
		} //end: if root== null
		
		//else, init tree recursion/traversal
		//use .compareTo for evaluation
		//<= should be used for evaluation of equivalent values 
		if (x.compareTo(root.key)<=0) 
			root.left = insertRecord(root.left, x); 
		if (x.compareTo(root.key)>0)
			root.right = insertRecord(root.right, x); 
		
		//return node
		return root; 
		
	}
	
	
	public String inorderAscending() {
		myTreeString=""; 
		inorderRecordAscending (root);
		return myTreeString; 
	}
	
	private void inorderRecordAscending (Node root) {
		if (root != null) {
			inorderRecordAscending(root.left) ;
			//generate string from root
			myTreeString+= root.key.toString(); 
			myTreeString += " "; 
			inorderRecordAscending(root.right); 
		} //end: root!=null
	} //end fn: inorderRecordAscending (Node) 
	
	
	
	public String inorderDescending() {
		myTreeString=""; 
		inorderRecordDescending(root); 
		return myTreeString; 
	}
	
	private void inorderRecordDescending (Node root) {
		if (root != null) {
			inorderRecordDescending(root.right) ;
			//generate string from root
			myTreeString+= root.key.toString(); 
			myTreeString += " "; 
			inorderRecordDescending(root.left); 
		} //end: root!=null
	}
	
	
	
}
