/**************************************************************************
  * @author Niha Imam
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: Position.java
  *
  * Description: class represents one cell position in a 2D grid
  ***************************************************************************/

class Position{
  
  //row and column
  private int row;
  private int col;
  
  /**
   * constructor to initialize row and column
   * @param row and column row
   */
  public Position(int row, int col){
    this.row = row;
    this.col = col;
  }
  
  /**
   * accessor/getter of row
   */
  public int getRow(){
    return row;
  }
  
  /**
   * accessor/getter of col
   */
  public int getCol(){
    return col;
  }
  
  /**
   * create a string representation of position
   * @returns string representation of a position
   */
  public String toString(){
    return "<" + row + "," + col + ">";
  }
  
  /**
   * check if two positions are the same
   * @param another position object
   * @returns true if position equals else false
   */
  @Override
  public boolean equals(Object obj){
    if (!(obj instanceof Position)) //if object not a position object
      return false; 
    Position other = (Position) obj; //create a new position object
    return other.getRow() == this.getRow() && other.getCol() == this.getCol();
  }
  
  /**
   * generic hash code method
   * compute the integer hash code of the object
   * @return the hash code
   */
  @Override
  public int hashCode(){
    int result = 17;
    result = 31 * result + row;//.hashCode();
    result = 31 * result + col;//.hashCode();
    return result;
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  
  public static void main(String[] args){
    
    Position p1 = new Position(3,5);
    Position p2 = new Position(3,6);
    Position p3 = new Position(2,6);
    
    if (p1.getRow()==3 && p1.getCol()==5 && p1.toString().equals("<3,5>")){
      System.out.println("Yay 1");
    }
    
    if (!p1.equals(p2) && !p2.equals(p3) && p1.equals(new Position(3,5))){
      System.out.println("Yay 2");
    }
    
    if (p1.hashCode()!=p3.hashCode() && p1.hashCode()!=(new Position(5,3)).hashCode() &&
        p1.hashCode() == (new Position(3,5)).hashCode()){
      System.out.println("Yay 3");   
    }
    
  }
  
}