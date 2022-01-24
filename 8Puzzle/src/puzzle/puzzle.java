package puzzle;
import java.util.*;


public class puzzle {

    //enum ALGO  {BFS, DFS, UFS, GBF, Astar};
    //enum LEVEL {EASY, MED, HARD};
    //private ALGO algo;
    //private LEVEL diff;

    // final goal board
    int[][] goal =  {{1,2,3}, 
                    {8,0,4},
                    {7,6,5}};

    public static void main(String[] args) throws Exception {
        int[][] easy =  {{1,3,4}, 
                        {8,6,2},
                        {7,0,5}};

         int[][] med =  {{2,8,1}, 
                        {0,4,3},
                        {7,6,5}};

        int[][] hard =  {{5,6,7}, 
                        {4,0,8},
                        {3,2,1}};
        // User prompt
        //Scanner prompt = new Scanner(System.in);
        System.out.println("Please choose your desired algorithm.");
        System.out.println("BFS, DFS, UFS, GBF, Astar");
        System.out.println("---------------------");
        System.out.print("Algo input: ");
        Scanner prompt = new Scanner(System.in);
        String search = prompt.nextLine().toLowerCase();


        System.out.println("Choose the level of difficulty");
        System.out.println("EASY, MED, HARD");
        System.out.println("---------------------");
        System.out.print("Level input: ");

        String level = prompt.nextLine().toLowerCase();
       // String algo = prompt.nextLine().toLowerCase();

        GameBoard gbe = new GameBoard(easy);
        GameBoard gbm = new GameBoard(med);
        GameBoard gbh = new GameBoard(hard);
        Algo algo = new Algo();
        List<GameBoard> solution;


        switch(search){
            case "bfs":
                switch(level){
                    case "easy":
                        solution = algo.BFSearch(gbe);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());
                        //gbe.getLength();
                    break;
                    case "med":
                        solution = algo.BFSearch(gbm);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());
                                     
                    break;
                    case "hard":
                        //gbh.printBoard();
                        solution = algo.BFSearch(gbh);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());
                    break;
                }
                break;

            case "dfs":
                switch(level){
                    case "easy":
                        //System.out.println("here");
                        solution = algo.DFSearch(gbe);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());   
                    break;
                    case "med":
                        solution = algo.DFSearch(gbm);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());
                                               
                    break;
                    case "hard":
                        solution = algo.DFSearch(gbh);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getSize());
                    break;
                }
                break;

            case "ufs":
                switch(level){
                    case "easy":
                        solution = algo.UFSearch(gbe);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());                
                        break;
                    case "med":
                        solution = algo.UFSearch(gbm);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());              
                        break;
                    case "hard":
                        solution = algo.UFSearch(gbh);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());  
                        break;
                }
                break;

            case "gbf":
                switch(level){
                    case "easy":
                        solution = algo.GBFSearch(gbe);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());                
                        break;
                    case "med":
                        solution = algo.GBFSearch(gbm);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());              
                        break;
                    case "hard":
                        solution = algo.GBFSearch(gbh);
                        sol(solution);
                        System.out.println("Timer: " + algo.getTimer());
                        System.out.println("Size: " + algo.getPQSize());  
                        break;
                }
                break;
            
            case "astar":

                System.out.print("Choose a Heuristic (1-3)\n" +
                                    "1. Misplaced Position\n" +
                                    "2. Manhattan Distance\n" +
                                    "3. Cost of tile * Manhattan\n");
                int h = prompt.nextInt();
                switch(h){
                    case 1:
                        switch(level){
                            case "easy":
                                solution = algo.Astar(gbe, 1);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());                
                                break;
                            case "med":
                                solution = algo.Astar(gbm, 1);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());                   
                                break;
                            case "hard":
                                solution = algo.Astar(gbh, 1);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());
                                break;
                        }
                        break;
                    case 2:
                        switch(level){
                            case "easy":
                            solution = algo.Astar(gbe, 2);
                            sol(solution);
                            System.out.println("Timer: " + algo.getTimer());
                            System.out.println("Size: " + algo.getPQSize());                
                            break;
                        case "med":
                            solution = algo.Astar(gbm, 2);
                            sol(solution);
                            System.out.println("Timer: " + algo.getTimer());
                            System.out.println("Size: " + algo.getPQSize());                   
                            break;
                        case "hard":
                            solution = algo.Astar(gbh, 2);
                            sol(solution);
                            System.out.println("Timer: " + algo.getTimer());
                            System.out.println("Size: " + algo.getPQSize());
                            break;
                        }
                    case 3:
                        switch(level){
                            case "easy":
                                solution = algo.Astar(gbe, 3);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());                
                                break;
                            case "med":
                                solution = algo.Astar(gbm, 3);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());                   
                                break;
                            case "hard":
                                solution = algo.Astar(gbh, 3);
                                sol(solution);
                                System.out.println("Timer: " + algo.getTimer());
                                System.out.println("Size: " + algo.getPQSize());
                                break;
                        }
                }
        }

       // System.out.println(algo);
        prompt.close();
    }


    // method to go over the list provided by the searches
    // Prints from goal to initial 
    public static void sol(List<GameBoard> s){

        if(!s.isEmpty()){
            for (int i = 0; i < s.size(); i++){
                s.get(i).printBoard();
               // System.out.println("Total Cost: " + total);
               // System.out.println("Total Cost: " + s.get(i).getTotal());
                System.out.println("Direction: " + s.get(i).getMove());
                System.out.println("Depth: " + s.get(i).getLength());
                //System.out.println("Timerdfdfdf: " + s.get(i).getTimer());
            }
            System.out.println();
            System.out.println("Total Cost: " + s.get(0).getTotal());
            System.out.println("Total Depth: " + s.get(0).getLength());
        }else{
            System.out.println("Not found");
        }
    }
}
