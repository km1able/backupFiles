#include <iostream>
#include <vector> 

using namespace std; 

class LinkedList {
    struct Node {
        int x; 
        Node* next; 
    };
    
    public: 
    LinkedList () { 
        head = NULL; 
    } //end constructor
        
    //destructor 
    ~LinkedList() {
        Node *next = head; 
        while (next ) {
            Node *deleteMe = next; 
            next = next->next; 
            delete deleteMe; 
        } //end while
    } //end ~LinkedList
    
   void addValue (int val) {
       Node *n = new Node(); 
       n->x = val; 
       n->next = head; 
       head = n; 
   }
   
   int popValue () {
       Node *n = head; 
       int ret = n-> x; 
       head = head-> next; 
       delete n; 
       return ret; 
   }

  
    private: 
    
    Node *head; 
}; //end class: LinkedList

int main()
{
	cout << "Hello World!" << endl;

    LinkedList list; 
    
    list.addValue(10); 
    list.addValue(3); 
    list.addValue(2); 
    list.addValue(4); 
    list.addValue(22); 
    
    cout<<list.popValue() <<endl; 
    cout<<list.popValue() <<endl; 
    cout<<list.popValue() <<endl; 
    cout<<list.popValue() <<endl; 
    cout<<list.popValue() <<endl; 
    cout<<list.popValue() <<endl; 
}