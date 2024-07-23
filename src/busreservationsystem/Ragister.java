/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package busreservationsystem;

import busreservationsystem.Bus;
import busreservationsystem.Customer;
import busreservationsystem.compands.AVLTree;
import busreservationsystem.compands.LinkedList;


/**
 *
 * @author gajen
 */
public class Ragister {
    
    private LinkedList<Bus> objects;
    private AVLTree<Bus> storeBus = new AVLTree<>();
    private AVLTree<Customer> storeCustomer = new AVLTree<>();
    
    public Ragister(LinkedList<Bus> obj){
        this.objects = obj;
        for(int i = 0; i < obj.length(); i++) {
            storeBusInfo(obj.get(i));
        }
    }
    
    private void storeBusInfo(Bus obj){
        storeBus.insert(obj);
    }
    
    public AVLTree<Bus> getBusStoredArray() {
        return storeBus;
    }
}
