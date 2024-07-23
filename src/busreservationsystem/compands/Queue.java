/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem.compands;

/**
 *
 * @author gajen
 */
public class Queue<ListType> {
    private class Node {
        private ListType data;
        private Node next;
        
        Node(ListType data) {
            this.data = data;
            this.next = null;
        }
    }
    
    Node front;
    Node rear;
    int length;
    
    public Queue(){
        this.front = null;
        this.rear = null;
        this.length = 0;
    }
    
    private void enqueue_val(ListType data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }    
        length ++;
    }
    private ListType dequeue_val(){
        if (isEmpty()) {
            return (ListType) new IndexOutOfBoundsException("Size: " + length);
        }
        
        Node temp = front;
        front = temp.next;
        length ++;
        if (front == null) {
            rear = null;
        }
        return temp.data;
    }
    private boolean isEmpty() {return length == 0;}
    
    
    public ListType peek() {return isEmpty() ? null : front.data;}
    public int lenght() {return length;}
    public void enqueue(ListType data) {enqueue_val(data);}
    public ListType dequeue(){return dequeue_val();}
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = front;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    } 
}
