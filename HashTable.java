/**************************************************************************
  * @author Niha Imam
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: HashTable.java
  *
  * Description: a generic hash table using separate chaining to resolve
  * collision, table is initialized as an array of 11 spots
  ***************************************************************************/

import java.util.Iterator;

class HashTable<T> {
  
  @SuppressWarnings("unchecked")
  private SimpleList<T>[] table = new SimpleList[11];
  int size = 0;
  
  /**
   * constructor to initialize table
   */
  public HashTable(){
    for (int i = 0; i < table.length; i++){
      table[i] = new SimpleList<T>();
    }
  }
  
  /**
   * adds an item to the hash table
   * @param value to add 
   * @returns true if add was successful else false
   */
  public boolean add(T value){
    int i = myhash(value, table.length); //find the hash val
    boolean answer = false;
    if (!table[i].contains(value)){
      table[i].add(value); //add on index hash val
      answer = true;
      size++;
    }
    if (getAvgChainLength() > 1.2){ //if avg chain len > 1.2
      rehash(nextPrime(2*table.length)); //rehash to next prime 2*table.length
    }
    return answer;
  }
  
  /**
   * helper method to find the hash value
   * @param value to hash and length of table
   * @returns index on which needs to be inserted
   */
  private int myhash(T x, int length){
    int val = x.hashCode();
    val %= length;
    if (val < 0)
      val +=length;
    return val;
  }
  
  /**
   * removes a value from the hash table
   * @param value to remove
   * @returns true if remove was successful else false
   */
  public boolean remove(T value){
    int i = myhash(value, table.length);
    if (table[i].contains(value)){
      table[i].remove(value);
      return true;
    }
    return false;
  }
  
  /**
   * checks if a value in in the table
   * @param value to check 
   * @returns true if value is in the hash table
   */
  public boolean contains(T value){
    for (int i = 0; i < table.length; i++){
      if (table[i].contains(value))
        return true;
    }
    return false;
  }
  
  /**
   * gets/accesses a value in in the table
   * @param value to get
   * @returns the value found in the hash table else null
   */
  public T get(T value){
    for (int i = 0; i < table.length; i++){
      if (table[i].contains(value)){
        return table[i].get(value);
      }
    }
    return null;
  }
  
  /**
   * create a new table of size capacity and rehash all the existing values to it
   * @param new capacity of the new table
   * @returns true if rehash successful else false
   */
  @SuppressWarnings("unchecked")
  public boolean rehash(int newCapacity){
    double load = (double) size()/ (double) newCapacity;
    if (load > 0.7) //if load will be > 0.7 do not rehas
      return false;
    SimpleList<T>[] oldtable = table; //create a old table
    table = new SimpleList[newCapacity]; //make the table into a new table
    for (int i = 0; i < table.length; i++)
      table[i] = new SimpleList<T>(); //initialize the table
    for (int i = 0; i < oldtable.length; i++){
      Iterator iter = oldtable[i].iterator(); //iterate through the itens
      while (iter.hasNext()){ 
        T x = (T) iter.next(); //while table has next
        int newHash = myhash(x,table.length); //find the new hash code for each value
        table[newHash].add(x); //add it to the new table
      }
    }
    return true;
  }
  
  /**
   * size of hashtable by adding all the sizes of each list
   * @returns hash table size
   */
  public int size(){
    int size = 0;
    for (int i = 0; i < table.length; i++){
      size += table[i].size();
    }
    return size;
  }
  
  /**
   * calculate the load of the table
   * @returns the load
   */
  public double getLoad(){
    double size = size();
    double len = table.length;
    return size/len;
  }
  
  /**
   * calculate the average chain length
   * @returns the average length of non-empty chains in the hash table
   * @returns the load
   */
  public double getAvgChainLength(){
    double avg = (double) size();
    double none = nonEmptyChains();
    return avg/none;
  }
  
  /**
   * helper method to calculate the number of non empty chains
   * @returns number of non empty chains
   */
  private double nonEmptyChains(){
    double none = 0.0;
    for (int i = 0; i < table.length; i++){
      if (table[i].size() > 0)
        none++;
    }
    return none;
  }
  
  /**
   * takes all values in hash tbale ins puts them in an array
   * @returns an array of values
   */
  public Object[] valuesToArray(){
    Object[] newarr = new Object[size()];
    int index = 0;
    for (int i = 0; i < table.length; i++){
      Iterator iter = table[i].iterator();
      while (iter.hasNext()){
        newarr[index] = iter.next();
        index++;
      }
    }
    return newarr;
  }
  
  // inefficiently finds the next prime number >= x
  // this is written for you
  public int nextPrime(int x) {
    while(true) {
      boolean isPrime = true;
      for(int i = 2; i <= Math.sqrt(x); i++) {
        if(x % i == 0) {
          isPrime = false;
          break;
        }
      }
      if(isPrime) return x;
      x++;
    }
  }
  
  //------------------------------------
  // example test code... edit this as much as you want!
  public static void main(String[] args) {
    HashTable<String> names = new HashTable<>();
    
    if(names.add("Alice") && names.add("Bob") && !names.add("Alice") 
         && names.size() == 2 && names.getAvgChainLength() == 1)  {
      System.out.println("Yay 1");
    }
    
    if(names.remove("Bob") && names.contains("Alice") && !names.contains("Bob")
         && names.valuesToArray()[0].equals("Alice")) {
      System.out.println("Yay 2");
    }
    
    boolean loadOk = true;
    if(names.getLoad() != 1/11.0 || !names.rehash(10) || names.getLoad() != 1/10.0 || names.rehash(1)) {
      loadOk = false;
    }
    
    boolean avgOk = true;
    HashTable<Integer> nums = new HashTable<>();
    for(int i = 1; i <= 70 && avgOk; i++) {
      nums.add(i);
      double avg = nums.getAvgChainLength();
      if(avg> 1.2 || (i < 12 && avg != 1) || (i >= 14 && i <= 23 && avg != 1) || 
         (i >= 28 && i <= 47 && avg != 1) || (i >= 57 && i <= 70 && avg!= 1)) {
        avgOk = false;
      }
    }
    
    if(loadOk && avgOk) {
      System.out.println("Yay 3");
    }
    
  }
  
}