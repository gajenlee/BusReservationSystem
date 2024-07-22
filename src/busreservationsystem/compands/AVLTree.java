/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem.compands;

/**
 *
 * @author gajen
 */
public class AVLTree<ListType extends Comparable<ListType>> {
    
    //    Node Tree
    private class Node{
        ListType data;
        Node left, right;
        int height;
        int lenght;
        
        Node(ListType data) {
            this.data = data;
            this.height = 1;
            this.lenght = 1;
        }
    }
    
    //    Root of the tree
    private Node root;
    
    //    Insert a new value to the tree and get balanced
    private Node insert(Node node, ListType data) {
        if(node == null) {
            return new Node(data);
        }
        
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data);
        }else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data);
        } else {
            return node;
        }
        
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node.lenght = 1 + lenght(node.left) + lenght(node.right);
        return balance(node);
    }
    
    //    Delete the value and get balanced
    private Node delete(Node node, ListType data) {
        if(node == null) {
            return null;
        }
        
        if(data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if(node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node temp = findMin(node.right);
                node.data = temp.data;
                node.right = delete(node.right, temp.data);
            }
        }
        
        if (node == null) {
            return null;
        }
        
        node.height = 1 + Math.max(height(node.left), height(node.left));
        node.lenght = 1 + lenght(node.left) + lenght(node.right); 
        return balance(node);
    }
    
    //    Find the min value
    private Node findMin(Node node) {
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
    
    //    Get the height of the tree
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }
    
    //    Check the tree is balance or not
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }
    
    //    Balance the tree when the LL, RR, LR, and RL heavy 
    private Node balance(Node node) {
        int balance = getBalance(node);
        
        if (balance > 1) {
            if (getBalance(node.left) < 0){
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        
        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        
        return node;
    }
    
    
    //    Rotate Right u
    private Node rotateRight(Node y){
        Node x = y.left;
        Node T2 = x.right;
        
        x.right = y;
        y.left = T2;
        
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.lenght = 1 + lenght(y.left) + lenght(y.right);
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.lenght = 1 + lenght(x.left) + lenght(x.right);
        
        
        return x;
    }
    
    //    Rotate Left
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;
        x.right = T2;
        
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        x.lenght = 1 + lenght(x.left) + lenght(x.right);
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        y.lenght = 1 + lenght(y.left) + lenght(y.right); 
        
        return y;
    }
    
    //    In order traversal
    private void inOrderTraversal(Node node){
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }
    
    //    Get and access the value
    private ListType getByIndex(Node node, int index){
        int leftSize = lenght(node.left);
        if(index < leftSize){
            return getByIndex(node.left, index);
        } else if (index > leftSize){
            return getByIndex(node.right, index - leftSize -1);
        } else {
            return node.data;
        }
    }
    
    //    get the lenght of the tree
    private int lenght(Node node){
        return (node == null) ? 0 : node.lenght;
    }
    
    
    //    get lenght of the tree
    public int lenght(){
        return lenght(root);
    }
    
    //    get hight of the tree
    public int height() {
        return height(root);
    }
    
    //    call the get 
    public ListType getByIndex(int index){
        if (index < 0 || index >= lenght(root)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + lenght(root));
        }
        return getByIndex(root, index);
    }
    
    //    call the insert
    public void insert(ListType data) {
        root = insert(root, data);
    }
    
    //    call the delete 
    public void delete(ListType data) {
        root = delete(root, data);
    }
    
    //    call the in order traversal
    public void inOrderTraversal() {
        inOrderTraversal(root);
        System.out.println();
    }
    
}
