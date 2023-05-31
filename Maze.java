
import java.util.Scanner;
import java.io.*;

public class Maze {

    public static void main(String[] args){
        createMaze();
        if(traverseMaze(maze, startRow, startCol)){
            System.out.println("Solved maze");
        }
        else{
            System.out.println("No solution");
        }
        printSolution();
    }

    public static char[][] maze;
    public static int numrow;
    public static int numcol;
    public static int startRow;
    public static int startCol;
    public static int rowFinal;
    public static int colFinal;

    //Read maze data
    public static void createMaze(){
        //number of rows and columns

        //read data from file to make maze
        try{
            File mazeFile = new File("maze.dat");
            Scanner scan = new Scanner(mazeFile);
            numrow = scan.nextInt();
            numcol = scan.nextInt();
            maze = new char[numrow][numcol];
            scan.nextLine(); //move cursor

            for(int i = 0; i<numrow; i++){
                String rowLine = scan.nextLine();
                for(int j = 0; j< numcol; j++){
                    maze[i][j] = rowLine.charAt(j);
                }
            }

        
            for(int row = 0; row < numrow; row++){
                for(int col = 0; col<numcol; col++){
                    if(maze[row][col] == '+'){
                        startRow = row;
                        startCol = col;
                    }
                }
            }
            for(int row = 0; row < numrow; row++){
                for(int col = 0; col<numcol; col++){
                    if(maze[row][col] == '-'){
                        rowFinal = row;
                        colFinal = col;
                    }
                }
            }
            
        }
        catch(Exception z){
            //z.printStackTrace();
            System.out.println("File not found error, please try again");
        }
    }
    

    public static boolean ifTraversable(char[][] mazeName, int row, int col){
        if(row>=0 && col>=0 && row <numrow && col <numcol){
            return (maze[row][col] == ' ' || (row == startRow && col == startCol));}
        return false;
    }
    
    //base case char at == -
    public static boolean traverseMaze(char[][] mazeName, int row, int col){
        if(row == rowFinal && col == colFinal){
            return true;
        }
        if(ifTraversable(maze, row, col)){
            
            if(maze[row][col] == '+'){
                maze[row][col] = '.';
            }
            maze[row][col] = '+';
            if(traverseMaze(maze, row+1, col)){
                return true;
            }
            if(traverseMaze(maze, row-1, col)){
                return true;
            }
            if(traverseMaze(maze, row, col+1)){
                return true;
            }
            if(traverseMaze(maze, row, col-1)){
                return true;
            }
            maze[row][col] = '.';
        }
        return false;
    }


    public static void printSolution(){
        for(int i=0;i<maze.length;i++){
            for(int j = 0; j<numcol; j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();}
    }


}

