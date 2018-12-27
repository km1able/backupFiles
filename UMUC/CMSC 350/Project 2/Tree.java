/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package posttoinfixtree;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * Author: suzy
 * Date: Feb 2, 2017
 * Purpose:
 * @author suzy
 */

// Java program for expression tree


public class PostToInfixTree {

    static private boolean isOperator(char c) {
        return c == '+' ||  c == '-' || c == '*' ||  c == '/';
    }
    
    static private boolean isNumber(char ch)
    {
        return ch >= '0' && ch <= '9';
    }
    
    // public String Operator(char)

   /* //method to do inorder traversal
    public void inorder(Node t) {
        if (t != null) {
            inorder(t.left);
            System.out.print(t.ch + " ");
            inorder(t.right);
        }
    }
*/
    // Returns root of constructed tree for given
    // postfix expression
    public Node constructTree(char postfix[]) {
        Stack<Node> st = new Stack();
        Node t, t1, t2;
        int count =0;

        //loops through input
        for (int i = 0; i < postfix.length; i++) {
            
            // If operand, push into stack
            if (!isOperator(postfix[i]) && isNumber(postfix[i])) {
                t = new Node(postfix[i]);
                st.push(t);
              
            } else if (isOperator(postfix[i])){ 
               
                t = new Node(postfix[i]);

                // Pop two top nodes
                // LIFO
                t1 = st.pop();      
                t2 = st.pop();
                  
                //make them children
                t.right = t1;
                t.left = t2;
                 
                switch (postfix[i]){
                    case '+':
                        System.out.print("Add ");
                        break;
                    case '-':
                        System.out.print("Sub ");
                        break;
                    case '*':
                        System.out.print("Mul ");
                        break;
                    case '/':
                        System.out.print("Div ");
                        if(t1.toString().contentEquals("0")){
                            JOptionPane.showMessageDialog(null, "Can't divide by 0");
                        }
                        break;
                }
//t1 = leftNode
                System.out.print("R" + count+ " ");
                count++;
                if (!t1.toString().startsWith("(") && !t2.toString().startsWith("(")){
                    System.out.println(t2 + " " + t1);
                } 
                else if (t1.toString().startsWith("(") && t2.toString().startsWith("(")){
                    System.out.println("R" + (count -3)+ " " + "R" + (count -2) );
                }
                else if(t1.toString().startsWith("(") && !t2.toString().startsWith("(")){
                    System.out.println(t2 + " " + "R" + (count -2));
                }
                else if(!t1.toString().startsWith("(") && t2.toString().startsWith("(")){
                    System.out.println("R"+ (count-2)+" " + t1);
                }
              
                st.push(t);  
               
            }else{
                JOptionPane.showMessageDialog(null,"Invalid character input");
            }
        }
        System.out.println(" ");
        
        //root of expression
        t = st.peek();
        st.pop();

        System.out.println("Infix expression: " + t);
        System.out.println(" ");
        return t;
    } 
}

