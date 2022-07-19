import java.util.*;

public class Solver {

    public int numCreated;

    public int numExpanded;

    public int maxFringe;


    //define Fringe

//    Stack<Board> fringe;

    List<Board> fringe;

    List<String> visited;

    PriorityQueue<Board> pq;

    PriorityQueue<Board> apq;



//    Board root;
    public Solver(Board theroot ) {

        fringe = new ArrayList<Board>();
        fringe.add(theroot);
//        theroot.printBoard();
//        root = theroot;
        visited = new ArrayList<String>();

        Comparator<Board> myComparator = new Comparator<Board>() {
            @Override
            public int compare(Board firstBoard, Board secondBoard) {
//                System.out.println(firstBoard.hvalue+" "+secondBoard.hvalue);
                if (firstBoard.hvalue < secondBoard.hvalue) {
                    return -1;
                } else if (firstBoard.hvalue > secondBoard.hvalue) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        pq = new PriorityQueue<Board>(2,myComparator);

        pq.add(theroot);

        Comparator<Board> amyComparator = new Comparator<Board>() {
            @Override
            public int compare(Board firstBoard, Board secondBoard) {
//                System.out.println(firstBoard.hvalue+" "+secondBoard.hvalue);
                if (firstBoard.hvalue+ firstBoard.depth < secondBoard.hvalue+ secondBoard.depth) {
                    return -1;
                } else if (firstBoard.hvalue+ firstBoard.depth  > secondBoard.hvalue+ secondBoard.depth) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };

        apq = new PriorityQueue<Board>(2,amyComparator);

        apq.add(theroot);


        numCreated+=1;

        maxFringe = 1;




    }


    //BFS
    public String solveBFS(){

        while (!fringe.isEmpty()){

            Board node = fringe.remove(0);
            numExpanded +=1;

//            System.out.println("here");
//            System.out.println(visited.size());

            if(visited.contains(node.getsignature())){
                continue;
            }

            if(node.hvalue == 0){
                System.out.println(node.sln);
                String outp = node.depth+", "+ numCreated+", "+ numExpanded+", "+maxFringe;
                return outp;
            }

            List<Board> moves = node.getMoves();
            visited.add(node.getsignature());

            for(Board m :moves){
                fringe.add(m);
                numCreated+=1;
            }

            maxFringe = Math.max(maxFringe,fringe.size());


        }
        return "bfs";
    }

    //DFS
    public String solveDFS(){
        while (!fringe.isEmpty()){
            Board node = fringe.remove(0);
            numExpanded +=1;

//            System.out.println("here");
//            System.out.println(visited.size());

            if(visited.contains(node.getsignature())){
                continue;
            }

            if(node.hvalue == 0){
                System.out.println(node.sln);
                String outp = node.depth+", "+ numCreated+", "+ numExpanded+", "+maxFringe;
                return outp;
            }

            List<Board> moves = node.getMoves();
            visited.add(node.getsignature());

            for(Board m :moves){
                fringe.add(0,m);
                numCreated+=1;
            }
            maxFringe = Math.max(maxFringe,fringe.size());

        }
        return "DFS";

    }

    //GBFS

    public String solveGBFS(){

        String best = "A*";
        int bestValue = -1;

        while(!pq.isEmpty()) {

            Board node = pq.remove();
            numExpanded +=1;


            if (visited.contains(node.getsignature())) {
                continue;
            }
//            System.out.println(visited.size());

            if (node.hvalue == 0) {
                System.out.println(node.sln);
                String outp = node.depth+", "+ numCreated+", "+ numExpanded+", "+maxFringe;
                return outp;
            }

            List<Board> moves = node.getMoves();
            visited.add(node.getsignature());

            for (Board m : moves) {
                pq.add(m);
                numCreated+=1;
            }
            maxFringe = Math.max(maxFringe,fringe.size());
        }
        return "GBSF";

    }


    //A*
    public String solveA(){

        String best = "A*";
        int bestValue = -1;


        while(!apq.isEmpty()){
//            System.out.println("here");
            Board node = apq.remove();
            numExpanded +=1;
//            if(bestValue>0){
//                if(node.depth+node.hvalue>bestValue){
//                    System.out.println("here");
//                    break;
//                }
//            }

            if(visited.contains(node.getsignature())){
//                System.out.println("here2");
                continue; // make sure
            }

            if(node.hvalue == 0){
//                if (bestValue <0) {
//                    bestValue = node.depth;
//                    best = node.sln;
//
//                    System.out.println("best: "+best);
//                } else if(node.depth< bestValue){
//                    bestValue = node.depth;
//                    best = node.sln;
//                    System.out.println("best: "+best);
//                }
                System.out.println(node.sln);
                String outp = node.depth+", "+ numCreated+", "+ numExpanded+", "+maxFringe;
                return outp;
            }

            List<Board> moves = node.getMoves();
            visited.add(node.getsignature());

            for (Board m : moves) {
                apq.add(m);
                numCreated+=1;
            }
            maxFringe = Math.max(maxFringe,fringe.size());

        }

        return best;

    }




}
