/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem.compands;

/**
 *
 * @author gajen
 */
public class LinkedList<ListType> {
    private Node<ListType> root;
    private int lenght;
    private int maxLenght;
    
    private static class Node<ListType> {
        ListType data;
        Node<ListType> next;
        
        Node(ListType data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public LinkedList(int maxLenght) {
        this.root = null;
        this.lenght = 0;
        this.maxLenght = maxLenght;
    }
    
    public void push(ListType value){
        
        if (lenght > maxLenght) throw new IllegalStateException("Connot push " + value + ", list is full.");
        
        Node<ListType> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
        } else {
            Node<ListType> current = root;
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        lenght ++;
    }
    
    public ListType get(int index) {
        checkIndex(index);
        Node<ListType> current = root;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public void set(int index, ListType value) {
        checkIndex(index);
        Node<ListType> current = root;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = value;
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= lenght) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + lenght);
        }
    }
    
    public int length() {return lenght;}
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<ListType> current = root;
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
    
    public ListType pop(int index) {
        checkIndex(index);
        ListType data = null;
        
        for (int i = 0; i < lenght; i ++) {
            if (i <= index) {
                data = root.data;
                root = root.next;
            }
        }
        lenght --;
        return data;
    }
}
