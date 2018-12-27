import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
/**
 * 
 * Main data processing class in Project 4. 
 * Reads lists values from file generation and creates hash maps and and Integer list. 
 * Creates error messages at cycle and invalid class detection
 * Creates topographical representation of input data 
 *
 * @param <T>
 */
public class GenericDigraph<T>{

	private int numVertices;  
	private int _hashIndexCounter; 
	
	//main list of Integer stored values --> Integers map in hash to String value of input objects
	private ArrayList<LinkedList<Integer>> adjacencyList; 
	private HashMap <Integer, String> integerKey_HashMap = new HashMap<> (); 
	private HashMap<String,Integer> stringKey_HashMap = new HashMap<>(); 
	
	//constructor  (takes in array of array of given file strings 
	public GenericDigraph (ArrayList<ArrayList<T>> inputTextArray) {
		
		int hashIndexCounter = 0; //key mapping determination for hashMap
		adjacencyList = new ArrayList<LinkedList<Integer>>(); 
		System.out.println("---- Init GenericDigraph -----");
		
		//temp hash index list --> transform in second iteration to 
		//reflect model graph as shown (i.e. vertices for all input values with dependencies as given 
		ArrayList<LinkedList<Integer>> t_adjacencyList = new ArrayList<LinkedList<Integer>>(); 
		//HashMap <Key, Value) 

		//inputs input values from processed text file 
		for (ArrayList<T> thisLine: inputTextArray) {
			ArrayList<T> tempLine = thisLine; 
			//this line of Integer keys per table 
			LinkedList<Integer> tempLinkedList = new LinkedList<>();

			for (T tempWord : tempLine) {
				//add 'word' and hashIndex to hashMap 
				//if 'tempWord' does not exist in map --> add to map --> update counter --> else continue
				if (stringKey_HashMap.containsKey(tempWord)){
				}
				else {	
					integerKey_HashMap.put(hashIndexCounter, (String)tempWord);
					stringKey_HashMap.put((String) tempWord, hashIndexCounter); 
					//increase hashIndexCounter (
					hashIndexCounter ++; 
				}//end : else hashMap
				
				//add integer values to temp list for row 
				tempLinkedList.add(stringKey_HashMap.get(tempWord)); 
				
			}//end for: T tempWord
			
			//add index values to temp adjacency list 
			t_adjacencyList.add(tempLinkedList); 
		
		} //end: for : ArrayList<T> tempLine 
		//end of init list construction ---> take initial list given and process/hash 
		
		for (int i = 0; i<hashIndexCounter; i++) {  //-->iterates main listing for creation of main adjList
			boolean bAdded = false; //if value matches in hashMap, then add list from init list row 
									//else --> create new LinkedList<T> and add to adjacency 
			for (int j = 0; j<t_adjacencyList.size(); j++)  {
				
				//create list using integer key values 
				int valueofKey = t_adjacencyList.get(j).get(0); 

				if (valueofKey == i) {
					
					addVertex(t_adjacencyList.get(j));
					bAdded = true; 
				} //end if (valueOfKey) 
					
				} //end for :j
			if (bAdded == false ) {
				addVertex(new LinkedList<Integer>()); 
			//	addEdge(i,i); 
				}
			}//end for : i
	
		numVertices = inputTextArray.size(); 
		_hashIndexCounter = hashIndexCounter; 
		JOptionPane.showMessageDialog(null, "Graph Built Successfully");
	} //end constructor: GenericDigraph <>
			
	public void printGraph () {
		for (int i = 0; i<adjacencyList.size(); i++) {
			System.out.println(); 
			for (int j = 0; j<adjacencyList.get(i).size(); j++) {
				Integer tempAccessor = adjacencyList.get(i).get(j);
				String value = integerKey_HashMap.get(tempAccessor); 
				System.out.print(value + "[" + tempAccessor + "]  ");
			} //end for: j
		} //end for: i 
	} //end printGraph () 
	
	private void addEdge (int source, int destination) {
		adjacencyList.get(source).add(destination); 
	}
		
	private void addVertex (int destination) {
		LinkedList<Integer> tempList = new LinkedList<Integer>(); 
		tempList.add(destination); 
		adjacencyList.add(tempList);
	}
	
	private void addVertex (LinkedList<Integer> inputList) {
		adjacencyList.add(inputList); 
	}
	
	//returns class values for return to asking component 
	private String  DFS (String input) {
		Integer vertex = null; 
		try { 
			//check if input filename matches value in hashmap
			if (stringKey_HashMap.containsKey(input)) {
				 vertex = stringKey_HashMap.get(input); 
				System.out.println("Vertex found is: " + vertex);
			}
			else {
				//handle incorrect filename input
				//InvalidClassName error
				throw  new InvalidClassName("input"); 
			}
		}
		catch (InvalidClassName invalidClass) {
			//invalid class
			return "Invalid Class Name..." ;
		}
		
		//initialize data for sorting
		String returnValues = ""; 
		int size = adjacencyList.size(); 
		ArrayList<Integer> values = new ArrayList<Integer>(); 
		values.add(vertex); 
		boolean visited [] = new boolean [size]; 
		for (int i = 0; i<size; i++) 
			visited[i] = false; 
		boolean isCyclic [] = new boolean [1]; 
		isCyclic[0] = false; 
		
		for (int i =0; i<size; i++) {
			//detect cycles 
			detectCycle(i, i,visited, isCyclic); 
		}
		
		if (isCyclic[0] == true ) 
			//detected cycles
		{
			try {
				throw new CycleDetectedException (""); 
			}
			catch (CycleDetectedException cyclXpn) {
				return "Cycle Found...";
			}
			 
		}
		else {
			visited = new boolean [size]; 
			determineGraphValues (vertex, vertex, visited, values) ; 
			
			// debug code --> print values of initial search 
			//System.out.println("Values are: " );
			//for (int i = 0; i<values.size(); i++) 
			//	System.out.print(values.get(i) + " ");
			/* */
		
			Stack<Integer>  stack = new Stack<Integer>(); 
			visited = new boolean[size]; 
			for (int i = 0; i<size; i++) 
				visited[i] = false; 
			
			//topographical sort 
			for (int i = 0; i<size; i++ ) 
				if (visited[i] == false) 
					DFSUtil(i, visited, stack); 
			System.out.println("Printing stack values...");
			//create string values of input integer type for passing to MainGUI
			while (stack.empty() == false)  {
				Integer temp = stack.pop(); 
				String tempString = integerKey_HashMap.get(temp); 
				if (values.contains(temp)) {
					System.out.print("[" + temp+ "]"  + tempString+ " ");
					returnValues += tempString += " "; 
				}
			} //end: while 
			System.out.println("");
		System.out.println(returnValues);	
		}//end: else 
		//return values discovered from Topographical sort
		return returnValues; 
	} //end fn: DFS
	

	//main sort fn 
	private void DFSUtil (int v, boolean visited[], Stack<Integer> stack) {
		visited[v] = true; 
		Integer i; 
		
		Iterator<Integer> it = adjacencyList.get(v).iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i]) 
				DFSUtil (i, visited, stack); 
		}
		
		stack.push(v); 
	}
	//used for topo.graph filtering of values (when topo sort scans graph, removes entries not required) 
	private void determineGraphValues(int source, int vertex, boolean visited [], ArrayList<Integer> values ) 
	{
		visited[vertex] = true; 
		for (int i =1; i<adjacencyList.get(vertex).size(); i++) {
			Integer next = adjacencyList.get(vertex).get(i); 
			if (!values.contains(next))
				values.add(next); 
			if (!visited[next]) {
				determineGraphValues(source,next, visited, values ); 
			}
		} //end: while
	 
		
	} //end fn: DFSUtil 
	//detects cycles 
	private void detectCycle (int source, int vertex, boolean visited [], boolean isCyclic []) 
	{
		visited[vertex] = true; 
		//recur for adjacent vertices
		for (int i =0; i<adjacencyList.get(vertex).size(); i++) {
			Integer next = adjacencyList.get(vertex).get(i); 
			//if source found, there is a cycle
			if (source == next && i != 0)  {
				isCyclic[0] = true; 
				return; 
			}
			if (!visited[next]) {
				detectCycle(source,next, visited, isCyclic); 
			}
		} //end: while	
	} //end fn: DFSUtil 
	
	//entryway to digraph processing utilities 
	public String processDFSCycle (String input ) {
		
			//input vertex value: integer
			String returnVal = DFS(input);
			return returnVal; 
	} //end fn: processDFSCycle
	

	
} //end: GenericDigraph class def
