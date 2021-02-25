/**************************************************************************
  * @author Niha Imam
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: SimpleList.java
  *
  * Description: a generic linked list class
  ***************************************************************************/

import java.util.Iterator;

class SimpleList<T> implements Iterable<T>{
  
  /**
   * private class node
   * objects of the linked list
   */
  private class Node<T>{
    T data;
    Node<T> next;
    
    public Node(T data){
      this.data = data;
      this.next = null;
    }
  }
  
  private Node<T> head;
  private Node<T> tail;
  private int size;
  
  /**
   * constructor to initialize list
   */
  public SimpleList(){
    this.head = null;
    this.tail = null;
    this.size = 0;
  }
  
  /**
   * add a new node to the end of the list
   * @param value to add to the list
   */
  public void add(T value){
    Node<T> node = new Node<T>(value);
    if (size == 0)
      head = node;
    else 
      tail.next = node;
    tail = node;
    size++;
  }
  
  /**
   * removes the first occurrence of the value
   * @param a value to be removed
   * @return true of value removed else false
   */
  public boolean remove(T value){
    if (head == null) //if linked list is empty
      return false;
    if (head.data.equals(value)){ //if head needs to be removed
      if (size == 1){ //if head is the only node in the list
        head = null;
        tail = null;
        size = 0;
      }
      else { //if head is not the only node in the list
        head = head.next; 
        size--;
      }
      return true;
    }
    //else
    //{
    Node<T> prev = head; //keep track of previous for easier removal
    //while its not at end and its not before the node we need to remove
    while (prev != tail && !prev.next.data.equals(value)) 
      prev = prev.next; //keep going
    //once out of loop find out why we are out of loop
    if (prev != tail){
      Node<T> nextnode = prev.next.next;
      prev.next = nextnode;
      size--;
      if (nextnode == null){ //gone too far
        tail = prev;
      }
      return true;
    }
    //if nothing worked than node doesnt exist
    return false;
  }
  
  /**
   * finds inded of the value
   * @param value that needs index
   * @returns index of value, if not present returns -1
   */
  public int indexOf(T value){
    Node<T> curr = head;
    int ctr = 0;
    while (curr != null){
      if (curr.data.equals(value))
        return ctr;
      curr = curr.next;
      ctr++;
    }
    return -1;
  }
  
  /**
   * checks to see if list has value
   * @param value to check
   * @returns true if list contains value else false
   */
  public boolean contains(T value){
    Node<T> curr = head;
    while (curr != null && !curr.data.equals(value)){
      curr = curr.next;
    }
    if (curr != null){
      return true;
    }
    else {
      return false;
    }
  }
  
  /**
   * search for the node with value
   * @param value to get
   * @returns the value found or null if not not found
   */
  public T get(T value){
    if (!contains(value))
      return null;
    Node<T> curr = head;
    T retval = null;
    while (curr != null){
      if (curr.data.equals(value))
        retval = curr.data;
      curr = curr.next;
    }
    return retval;
  }
  
  /**
   * size of list
   * @returns list size
   */
  public int size(){
    return size;
  }
  
  /**
   * iterator of the list to iterate through linked list
   * @returns iterator
   */
  public Iterator<T> iterator(){
    return new Iterator<T>(){
      Node<T> nextnode = head;
      
      public boolean hasNext(){
        return (nextnode != null);
      }
      
      public T next(){
        if (!hasNext()) return null;
        T retval = nextnode.data;
        nextnode = nextnode.next;
        return retval;
      }
    };
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  // also, consider add a toString() for your own debugging
  
  public static void main(String[] args){
    
    SimpleList<Integer> ilist = new SimpleList<Integer>();
    ilist.add(new Integer(11));
    ilist.add(new Integer(20));
    ilist.add(new Integer(5));
    
    if (ilist.size()==3 && ilist.contains(new Integer(5)) && 
        !ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1){
      System.out.println("Yay 1");
    }
    
    if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
        !ilist.contains(new Integer(11)) && ilist.get(20).equals(new Integer(20))){
      System.out.println("Yay 2");   
    }
    
    Iterator iter = ilist.iterator();
    if (iter.hasNext() && iter.next().equals(new Integer(20)) && iter.hasNext() &&
        iter.next().equals(new Integer(5)) && !iter.hasNext()){
      System.out.println("Yay 3");      
    }
    
  }
  
}