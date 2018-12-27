import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;


 /** Main GUI aspect of program. Generates GUI. 
  *  Manages button events (Build Directed Graph, Topological Order) 
  *  As well as file input (name given from input text field) 
  *  And class recompilation (name given from input text field) 
  *  Recompilation Order prints in attached text panel 
  *  
  *  */ 
public class MainGUI extends JFrame implements ActionListener {

	//declaration of GenericDigraph class --> main programmatic data structure for list processing/sorting
	GenericDigraph<String> myDigraph;
	
	//GUI variables
	private JPanel input; 
	private JPanel output; 
	
	private JLabel userFileInDLabel; 
	private JLabel classToRecompileInDLabel; 
	private JLabel recompilationOutDLabel; 
	
	private JTextField userFileInText;
	private JTextField classToRecompileInText; 
	
	private JTextArea recompilationOutText; 
	
	JButton buildDirectedGraph = new JButton("Build Directed Graph");
	JButton topologicalOrder = new JButton ("Topological Order"); 

	
	//Constructor
	MainGUI () {
		
		//create panels, etc. 
		super ("Class Dependency Graph"); 
		input = new JPanel (new GridLayout(2,3, 20, 20)); 
		output= new JPanel (new FlowLayout()); 
	
		input.setBorder(BorderFactory.createLineBorder(Color.black));
		input.setLayout(new GridLayout(2, 3));
		
		TitledBorder border = new TitledBorder ("Recompilation Order"); 
		border.setTitleJustification(TitledBorder.LEFT);
		border.setTitlePosition(TitledBorder.TOP);
		
		output.setBorder(border);
		output.setLayout(new FlowLayout());
		
		input.setBackground(Color.LIGHT_GRAY);
		
		input.setPreferredSize(new Dimension (775, 70)); 
		output.setPreferredSize(new Dimension(780, 150));
		setLayout(new FlowLayout()); 
		setSize (800, 400); 
		setDefaultCloseOperation (EXIT_ON_CLOSE); 
		
		userFileInText = new JTextField (20); 
		classToRecompileInText = new JTextField (20); 
		recompilationOutText = new JTextArea(7, 63); 
		
		recompilationOutText.setEditable(false);
		
		userFileInDLabel = new JLabel ("Input File Name:", SwingConstants.LEFT); 
		classToRecompileInDLabel = new JLabel ("Class to Recompile:", SwingConstants.LEFT); 
		recompilationOutDLabel = new JLabel ("Recompilation Order", SwingConstants.LEFT);
		recompilationOutText.add(recompilationOutDLabel); 
		
		input.add(userFileInDLabel);
		input.add(userFileInText); 
		input.add(buildDirectedGraph); 
		input.add(classToRecompileInDLabel); 
		input.add(classToRecompileInText); 
		input.add(topologicalOrder); 
		
		output.add(recompilationOutText); 
		
		add (input); 
		add (output); 

		//action button listener
		
		buildDirectedGraph.addActionListener(this); 
		topologicalOrder.addActionListener(this);
		setVisible(true); 
	} //end constructor: MainGUI () 
	
	
	//evalButton 
		
		public void actionPerformed(ActionEvent e) {	
			Object source = e.getSource(); 
			if (source == buildDirectedGraph) {
				//enter buildDirectedGraph logic 
				String myFileName = userFileInText.getText(); 
				System.out.println("My File is: "  + myFileName);
				FileHandler myFileHandler = new FileHandler(myFileName); 
				if (myFileHandler.isSuccessful()) {
					myDigraph = new GenericDigraph<> (myFileHandler.getFile()); 
					myDigraph.printGraph(); 
				}
				//myDigraph.processDFSCycle(0);
			}
		
			if (source == topologicalOrder) {
				//enter topologicalOrder logic
				String receiveVal = classToRecompileInText.getText(); 
				//process receiveVal 
				String finalOutput = myDigraph.processDFSCycle(receiveVal);
				recompilationOutText.setText(finalOutput);
			}
			
		} //end: public actionPerformed 
	}