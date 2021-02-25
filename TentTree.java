/**************************************************************************
  * @author Niha Imam
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: TentTree.java
  *
  * Description: a generic hash table using separate chaining to resolve
  * collision, table is initialized as an array of 11 spots
  ***************************************************************************/

class TentTree{
  
  private int numRows, numCols; // size of the 2D board
  private HashMap<Position, String> grid; // the board stored in a hash table
  private String treeSymbol, tentSymbol;  // the string representing tree/tent on board
  
  /**
   * constructor to initialize the hash map/grid
   * @param number of rows, number of columns, string tent and string tree
   */
  public TentTree(int numRows, int numCols, String tent, String tree){
    this.numRows = numRows;
    this.numCols = numCols;
    this.tentSymbol = tent;
    this.treeSymbol = tree;
    this.grid = new HashMap<Position, String>();
  }
  
  /**
   * overloaded constructor that initializes tree and tent with X an O
   * @param number of rows, number of columns
   */
  public TentTree(int numRows, int numCols){
    this.numRows = numRows;
    this.numCols = numCols;
    this.tentSymbol = "X";
    this.treeSymbol = "O";
    this.grid = new HashMap<Position, String>();
  }
  
  /**
   * accessors
   * @returns tent symbol
   */
  public String getTentSymbol(){ 
    return tentSymbol;
  }
  
  /**
   * accessors
   * @returns tree symbol
   */
  public String getTreeSymbol(){ 
    return treeSymbol;
  }
  
  /**
   * accessors
   * @returns number of rows
   */
  public int numRows(){
    return numRows;
  }
  
  /**
   * accessor
   * @returns number of columns
   */
  public int numCols(){
    return numCols;
  }
  
  /**
   * check whether the given position is valid or not
   * @param position to check if valid or not
   * @returns true if position is valid else false
   */
  public boolean isValidPosition(Position pos){
    if (pos.getRow() >= 0 && pos.getRow() < numRows && pos.getCol() >= 0 && pos.getCol() < numCols)
      return true;
    return false;
  }
  
  /**
   * check whether the given string is valid or not
   * @param string to check if valid or not
   * @returns true if string is valid else false
   */
  public boolean isValidSymbol(String s){
    if (s.equals(treeSymbol) || s.equals(tentSymbol))
      return true;
    return false;
  }
  
  /**
   * set the cell at position pos to be string s
   * @param position to set and the string s to set
   * @returns true if position has been set else false
   */
  public boolean set(Position pos, String s){
    if (!isValidPosition(pos))
      return false; //if positon not valid return false
    if (!isValidSymbol(s))
      return false; //if symbol not valid return false
    if (grid.getValue(pos) != null)
      return false; //if position is alrady taken return false
    return grid.add(pos,s); 
  }
  
  /**
   * gets/accesses the cell at the position pos
   * @param position to get the string
   * @returns the cell at the specified at position pos
   */
  public String get(Position pos){
    if (!isValidPosition(pos))
      return null;
    if (grid.getValue(pos) != null)
      return grid.getValue(pos);
    return null;
  }
  
  /**
   * add a tent at the position
   * @param position to add tent st
   * @return true if tent has been added else false
   */
  public boolean addTent(Position pos){
    if (!isValidPosition(pos))
      return false;
    if (grid.getValue(pos) != null)
      return false;
    if (grid.add(pos,tentSymbol))
      return true;
    return false;
  }
  
  /**
   * remove a tent form the position
   * @param position to remove a tree from
   * @returns true if tent has been removed else false
   */
  public boolean removeTent(Position pos){
    if (!isValidPosition(pos))
      return false;
    return grid.remove(pos);
  }
  
  /**
   * add a tree at the position
   * @param position to add a tree on
   * @returns true if tree has been removed else false
   */
  public boolean addTree(Position pos){
    if (!isValidPosition(pos))
      return false;
    if (grid.getValue(pos) != null)
      return false;
    if (grid.add(pos,treeSymbol))
      return true;
    return false;
  }
  
  /**
   * check if the position has a tent
   * @param position to check if it has a tent
   * @return true if position has tent else false
   */
  public boolean hasTent(Position pos){
    if (!isValidPosition(pos))
      return false;
    if (grid.getValue(pos) == tentSymbol)
      return true;
    return false;
  }
  
  /**
   * check if position has a neighbor above, below, left or right that has the same string
   * @param position to check neighbors and string to compare
   * @returns true if position has neighbor else false
   */
  public boolean posHasNbr(Position pos, String s){
    //create new positions and check whether any neighbor has the same string
    Position newpos1 = new Position(pos.getRow()-1,pos.getCol()); 
    if (isValidPosition(newpos1)){
      if (grid.getValue(newpos1) == s){
        return true;
      }
    }
    Position newpos2 = new Position(pos.getRow()+1,pos.getCol());
    if (isValidPosition(newpos2)){
      if (grid.getValue(newpos2) == s){
        return true;
      }
    }
    Position newpos3 = new Position(pos.getRow(),pos.getCol()-1);
    if (isValidPosition(newpos3)){
      if (grid.getValue(newpos3) == s){
        return true;
      }
    }
    Position newpos4 = new Position(pos.getRow(),pos.getCol()+1);
    if (isValidPosition(newpos4)){
      if (grid.getValue(newpos4) == s){
        return true;
      }
    }
    return false;
  }
  
  /**
   * check if position has any immediate neighbor that has the same string as s
   * @param position to check neighbors and string to compare
   * @returns true if position has neighbor else false
   */
  public boolean posTouching(Position pos, String s){
    //reuse code from poshasnbr method
    if (posHasNbr(pos, s))
      return true;
    //create new positions and check whether any neighbor has the same string
    Position newpos1 = new Position(pos.getRow()-1,pos.getCol()-1);
    if (isValidPosition(newpos1)){
      if (grid.getValue(newpos1) == s){
        return true;
      }
    }
    Position newpos2 = new Position(pos.getRow()-1,pos.getCol()+1);
    if (isValidPosition(newpos2)){
      if (grid.getValue(newpos2) == s){
        return true;
      }
    }
    Position newpos3 = new Position(pos.getRow()+1,pos.getCol()-1);
    if (isValidPosition(newpos3)){
      if (grid.getValue(newpos3) == s){
        return true;
      }
    }
    Position newpos4 = new Position(pos.getRow()+1,pos.getCol()+1);
    if (isValidPosition(newpos4)){
      if (grid.getValue(newpos4) == s){
        return true;
      }
    }
    return false;
  }
  
  /**
   * methods that return a string of the board representation
   * this has been implemented for you: DO NOT CHANGE
   * @param no parameters
   * @return a string
   */
  @Override
  public String toString(){
    // return a string of the board representation following these rules:
    // - if printed, it shows the board in R rows and C cols
    // - every cell should be represented as a 5-character long right-aligned string
    // - there should be one space between columns
    // - use "-" for empty cells
    // - every row ends with a new line "\n"
    StringBuilder sb = new StringBuilder("");
    for (int i=0; i<numRows; i++){
      for (int j =0; j<numCols; j++){
        Position pos = new Position(i,j);
        // use the hash table to get the symbol at Position(i,j)
        if (grid.contains(pos))
          sb.append(String.format("%5s ",this.get(pos)));
        else
          sb.append(String.format("%5s ","-")); //empty cell
      }
      sb.append("\n");
    }
    return sb.toString();
  }
  
  /**
   * EXTRA CREDIT:
   * methods that checks the status of the grid and returns:
   * 0: if the board is empty or with invalid symbols
   * 1: if the board is a valid and finished puzzle
   * 2: if the board is valid but not finished
   *     - should only return 2 if the grid missing some tent but otherwise valid
   *       i.e. no tent touching other tents; no 'orphan' tents not attached to any tree, etc. 
   * 3: if the board is invalid
   *     - note: only one issue need to be reported when the grid is invalid with multiple issues
   * @param no parameters
   * @return an integer to indicate the status
   * 
   * assuming HashMap overhead constant, O(R*C) 
   * where R is the number of rows and C is the number of columns
   * Note: feel free to add additional output to help the user locate the issue
   */
  public int checkStatus(){
    return 2;
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  // Note: you will need working Position and SimpleMap classes to make this class working
  
  public static void main(String[] args){
    
    TentTree g1 = new TentTree(4,5,"Tent","Tree");
    if (g1.numRows()==4 && g1.numCols()==5 && g1.getTentSymbol().equals("Tent")
          && g1.getTreeSymbol().equals("Tree")){
      System.out.println("Yay 1");
    }
    
    TentTree g2 = new TentTree(3,3);
    
    if (g2.set(new Position(1,0), "O") && !g2.set(new Position(1,0),"O") &&
        g2.addTree(new Position(1,2)) && !g2.addTree(new Position(1,5))){
      System.out.println("Yay 2");
    }
    
    if (g2.addTent((new Position(0,0))) && g2.addTent(new Position(0,1)) &&
        !g2.addTent((new Position(1,0))) && g2.get((new Position(0,0))).equals("X")){
      System.out.println("Yay 3");
      
    }
    
    if (g2.hasTent(new Position(0,0)) && g2.posHasNbr((new Position(0,0)),"O") &&
        g2.posTouching((new Position(1,2)),"X") && !g2.posHasNbr((new Position(1,2)),"X")){
      System.out.println("Yay 4");
      
    }
    if (g2.removeTent(new Position(0,1)) && !g2.removeTent(new Position(2,1))
          && g2.get(new Position(2,2))==null){
      System.out.println("Yay 5");
    }
    
  }
  
}