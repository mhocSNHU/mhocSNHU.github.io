/*****************************************************************
@author Matthew Hocking
@file TurnHolder.java
@purpose A simple linked list implementation used to create a simple
enviorment to print out every single move in a rolling list of a certain
size. Due to it only needing this print functionality, certain typical
linked list functions are excluded.
@version Fall 2023
@institution SNHU / GVSU
*****************************************************************/

public class TurnHolder {

    // Head of the linked list
    Node head;

    // Tail of the linked list
    Node tail;

    // Maximum size that can be reached before pushing head
    int max;

    // The current size of the linked list
    int size;

/*****************************************************************
Constructor for a new TurnHolder
@param maximumNodes The maximum amount of nodes to be held
*****************************************************************/
    TurnHolder(int maximumNodes) {
        this.head = null;
        this.tail = null;
        this.max=  maximumNodes;
        size = 0;
    }

/*****************************************************************
Adds a node at the back of the turn list
@param data The information to be held
*****************************************************************/
    public void insertNode(String data) {
        
        // Create a node to insert into the nist
        Node tempNode = new Node(data);

        // If there is no nodes yet - this is head and tail
        if (tail == null) {
            head = tempNode;
            tail = tempNode;
            size++;
        }

        // If there are nodes - simply connect to the tail
        else {
            tail.next = tempNode;
            tempNode.previous = tail;
            tail = tempNode;
            size++;

            // If the maximum size is reached - move the head down
            if (max > 0 && size > max) {
                head = head.next;
                head.previous = null;
                size--;
            }
        }
    }

/*****************************************************************
Returns a UI structures list of the turns
@return A string of all the turns in HTML format
*****************************************************************/
    public String returnTurns() {
        String concatString = "";
        Node heldNode = head;

        // While there are still nodes to read
        while (heldNode != null) {

            // Concat each nodes data onto the string with a line break
            concatString+= heldNode.data + "<br>";
            heldNode = heldNode.next;
        }

        // return the fully filled in string
        return concatString;
    }

/*****************************************************************
Internal class used for data storage
*****************************************************************/
    class Node {
        // The piece of data stored on the node
        public String data;

        // A connection to the next node
        public Node next;

        // A connection to the previous node
        public Node previous;

/*****************************************************************
Constructor for a new node
@param data The data to hold in the new node
*****************************************************************/ 
        public Node(String data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }

    }



}
