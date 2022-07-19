import java.lang.Math;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Board{

    public int size;

    public int depth;

    public int hvalue;


    public String sln;

    public class Cell {


        private int x;
        private int y;
        private char value;
        private int heuristic;
        private Cell(int tX,int tY, char val){
            x = tX;
            y = tY;
            value = val;
            heuristic = 0;
        }


    }

    private Cell[][] values;

    private void setValues(int x, int y, char value){
        values[x][y] = new Cell(x,y,value);
    }

    public Board(int thesize, Cell[][] theValues, String tsln){
        this.size = thesize;
        this.values = new Cell[size][size];
        for(int i = 0; i<size; i++){
            for( int j = 0; j< size; j++){
                values[i][j] = new Cell(i,j,theValues[i][j].value);
            }
        }
        GenerateHeuristics();
        sln = tsln;

    }

    public Board(int Size, String State){
        size = Size;
        hvalue= 0;

        values = new Cell[Size][Size];
        int counter = 0;
        for( int i = 0; i<size; i++){
            for( int j = 0; j< size; j++){
                values[i][j] = new Cell(i,j,State.charAt(counter));
//                System.out.println("here");
                counter ++;
            }
        }
        GenerateHeuristics();
         sln = "";

//        printBoard();



    }

    public String  getsignature(){

        String sg = "";

        for( int i = 0; i<size; i++){
            for( int j = 0; j<size;j++){
                sg = sg+values[i][j].value;
            }
        }
        return sg;
    }



    //board inf
    //parent info
    //node info

    // Generate heuristics?



    public List<Board> getMoves(){

        List<Board> moves = new ArrayList<Board>();

        int spacex = 0;
        int spacey = 0;

        for( int i = 0; i<size; i++){
            for( int j = 0; j< size; j++) {
                if (values[i][j].value == ' '){
                    spacex = i;
                    spacey = j;
                }
            }
        }

        //get i-1

        if( spacex > 0){
            String path = sln +" "+  values[spacex-1][spacey].value;
            Board bd1 = new Board( size,values,path);
            bd1.setValues(spacex,spacey,values[spacex-1][spacey].value);
            bd1.setValues(spacex-1,spacey,' ');
            bd1.GenerateHeuristics();
            moves.add(bd1);
        }




        // get i+1

        if(spacex < size-1){
            String path = sln +" "+ values[spacex+1][spacey].value;
            Board bd2 = new Board( size,values,path);
            bd2.setValues(spacex,spacey,values[spacex+1][spacey].value);
            bd2.setValues(spacex+1,spacey,' ');
            bd2.GenerateHeuristics();
            moves.add(bd2);
        }
//
//        // j-1
//
        if( spacey > 0){
            String path = sln +" "+  values[spacex][spacey-1].value;
            Board bd3 = new Board( size,values,path);
            bd3.setValues(spacex,spacey,values[spacex][spacey-1].value);
            bd3.setValues(spacex,spacey-1,' ');
            bd3.GenerateHeuristics();
            moves.add(bd3);
        }
//
//
//
//        // get j+1
//
        if(spacey < size-1){
            String path = sln +" "+ values[spacex][spacey+1].value;
            Board bd4 = new Board( size,values,path);
            bd4.setValues(spacex,spacey,values[spacex][spacey+1].value);
            bd4.setValues(spacex,spacey+1,' ');
            bd4.GenerateHeuristics();
            moves.add(bd4);
        }

//        System.out.println( "Boardchack: ");
//        printBoard();
//        System.out.println( " ");

        return moves;
    }

    public void GenerateHeuristics(){
        hvalue = 0;
        for( int i = 0; i<size; i++){
            for( int j = 0; j< size; j++){
                values[i][j].heuristic = md(i,j,values[i][j].value);
                hvalue += values[i][j].heuristic;
            }
        }
//        System.out.println(hvalue);
    }

    public int md(int x, int y, char val){

        char[][] goal2 = {{'2','1'},{'3',' '}};
        char[][] goal3 = {{' ','1','2'},{'3','4','5'},{'6','7','8'}};
        char[][] goal4 = {{'1','2','3','4'},{'5','6','7','8'},{'9','A','B','C'},{'D','E','F',' '}};

        for( int i = 0; i<size; i++){
            for( int j = 0; j<size;j++){

//                System.out.println("in: "+val + " " +i+" "+j+" "+x+" "+y+" ");
//4 "123456789AB DEFC" BFS
//                3 "47315862 " GBFS
//                2 "32 1" DFS

                if(size == 2){
                    if(goal2[i][j] == val){
//                        System.out.println("captured: "+val + " " +i+" "+j+" "+x+" "+y+" ");
                        return Math.abs(i-x)+Math.abs(j-y);
                    }

                } else if( size ==3){
                    if(goal3[i][j] == val){
//                        System.out.println("captured: "+val + " " +i+" "+j+" "+x+" "+y+" ");
                        return Math.abs(i-x)+Math.abs(j-y);
                    }
                } else {
                    if(goal4[i][j] == val){
//                        System.out.println("captured: "+val + " " +i+" "+j+" "+x+" "+y+" ");
                        return Math.abs(i-x)+Math.abs(j-y);
                    }
                }

            }
        }


        return 0;
    }

    public void printBoard(){
        for( int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                System.out.print("{"+values[i][j].value + ":"+values[i][j].heuristic+"} ");
            }
            System.out.println();
        }
    }





    //test if solvable (later)

}
