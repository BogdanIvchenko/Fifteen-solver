
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class Tester {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        String input = "";
        //4 "123456789AB DEFC" BFS
//                3 "47315862 " GBFS
//                2 "32 1" DFS

        try {
            File myObj = new File("Readme.txt");
            myObj.createNewFile();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {

            FileWriter myWriter = new FileWriter("Readme.txt");


            while(!input.equals("stop")){
                input = in.nextLine();
                if (input.equals("stop")){
                    break;
                }
                String[] arr = input.split("\"");

                //size
                int size = Integer.parseInt(arr[0].replaceAll("\\s",""));

                // initial
                String initial = arr[1];


                //searchmethod
                String searchmethod = arr[2].replaceAll("\\s","");

                Board t = new Board(size,initial);

                Solver s = new Solver(t);

                String ret = "";

                if(searchmethod.equals("BFS")){
                    ret = s.solveBFS();
                } else if (searchmethod.equals("DFS")) {
                    ret = s.solveDFS();
                } else if (searchmethod.equals("A*")) {
                    ret = s.solveA();
                } else {
                    ret = s.solveGBFS();
                }
                myWriter.write("size:"+size);

                myWriter.write(System.getProperty("line.separator"));

                myWriter.write("initial:"+initial);
                myWriter.write(System.getProperty("line.separator"));

                if(size ==2){
                    myWriter.write("goal: \"213 \"");
                } else if (size ==3){
                    myWriter.write("goal: \" 12345678\"");
                } else {
                    myWriter.write("goal: \"123456789ABCDEF \"");
                }
                myWriter.write(System.getProperty("line.separator"));
                myWriter.write("searchmethod: "+searchmethod);

                myWriter.write(System.getProperty("line.separator"));
                myWriter.write(ret);
                myWriter.write(System.getProperty("line.separator"));
                myWriter.write("---------");

                myWriter.write(System.getProperty("line.separator"));
            }


            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }










    }
}
