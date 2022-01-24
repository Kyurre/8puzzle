package puzzle;
import java.util.*;


public class GameBoard{

    // storing structres
    public int[][] board;
    private int length;
    private int cost;
    private GameBoard parent;
    private int zeroCol;
    private int zeroRow;
    private int totCost;
    private posMoves moves;
    
    // public int timer;
    public List<GameBoard> nodeChildren;
    
    public int[][] goal = {{1,2,3}, 
                            {8,0,4},
                            {7,6,5}};
    public enum posMoves{RIGHT, LEFT, UP, DOWN};


    public GameBoard(int[][] board){
        setBoard(board);
    }

    // creating the board and initializing variables
    public void setBoard(int[][] b){
        this.length = 1;
        this.parent = null;
        this.cost = 0;
        this.totCost= 0;
        this.moves = null;
        this.board = b;
        this.nodeChildren = new ArrayList<GameBoard>();
        //timer = 0;
        findZero(board);
    }

    //check if we have reached goal state
    public boolean equalsGoal(){

        for (int i= 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(board[i][j] != goal[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // method to see if a given node already exists

    public boolean contains(int[][] b){

        for (int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if (board[i][j] != b[i][j] ){
                    return false;
                }
            }
        }
        return true;
    }

    // finding where the zero aka empty space is located
    public void findZero(int[][] board){
        for (int i = 0; i<board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(board[i][j] == 0){
                    this.zeroRow = i;
                    this.zeroCol = j;
                    break;
                }
            }
        }
    }
    // finding row and colum for a given value
    public int row(int num){
        int x = 0;
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(num == goal[i][j]){
                    x = i;
                }
            }
            
        }
        return x;
    }

    public int col(int num){
        int y = 0;
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if(num == goal[i][j]){
                    y = j;
                }
            }
            
        }
        return y;
    }

    //call all the possible moves from given position
    public void expandNode(){
        int col = zeroCol;
        int row = zeroRow;

        right(board, row, col);
        left(board, row, col);
        up(board, row, col);
        down(board, row, col);
    }

    // creates a secondary board which will be used when a move is possible 
    public void tempBoard(int[][] a, int [][]b){
        for (int i = 0; i < b.length; i++){
            for (int j = 0; j<b[i].length; j++){
                a[i][j] = b[i][j];
            }
        }
    }

    public void printBoard(){
        System.out.println();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    /*******************************************************************************************************************************
    All the possible move states right, left, up, down
    in each move, it will check if it can produce a child based on the move
    then it will add the child's data into the list and update cost and length of the tree the child was found i
    *******************************************************************************************************************************/
    public void right(int[][] board, int row, int col){
        if(col != 2 ){
            int[][] temp = new int[3][3];
            tempBoard(temp, board);

            int num = temp[row][col+1]; 
            temp[row][col+1] = temp[row][col];
            temp[row][col] = num;

            GameBoard child = new GameBoard(temp); // creating a child based on the possible move
            child.setParent(this);
            child.setCost(num);
            child.setMove(posMoves.RIGHT);
            child.setLength(this.getLength() +1);
            child.setTotal(child.getCost());
            this.nodeChildren.add(child);

        }
    }

    public void left(int[][] board, int row, int col){
        if(col != 0 ){
            int[][] temp = new int[3][3];
            tempBoard(temp, board);

            int num = temp[row][col-1]; 
            temp[row][col-1] = temp[row][col];
            temp[row][col] = num;

            GameBoard child = new GameBoard(temp);
            child.setParent(this);
            child.setCost(num);
            child.setMove(posMoves.LEFT);
            child.setLength(this.getLength() +1);
            child.setTotal(child.getCost());
            this.nodeChildren.add(child);


        }
    }

    public void up(int[][] board, int row, int col){
        if(row != 0 ){
            int[][] temp = new int[3][3];
            tempBoard(temp, board);

            int num = temp[row-1][col]; 
            temp[row-1][col] = temp[row][col];
            temp[row][col] = num;

            GameBoard child = new GameBoard(temp);
            child.setParent(this);
            child.setCost(num);
            child.setMove(posMoves.UP);
            child.setLength(this.getLength() +1);
            child.setTotal(child.getCost());
            this.nodeChildren.add(child);
        }
    }


    public void down(int[][] board, int row, int col){
        if(row != 2 ){
            int[][] temp = new int[3][3];
            tempBoard(temp, board);

            int num = temp[row+1][col]; 
            temp[row+1][col] = temp[row][col];
            temp[row][col] = num;

            GameBoard child = new GameBoard(temp);
            child.setParent(this);
            child.setCost(num);
            child.setMove(posMoves.DOWN);
            child.setLength(this.getLength() +1);
            child.setTotal(child.getCost());
            this.nodeChildren.add(child);

        }
    }
    /*************************************************************
     * Calculate the heuristics to be used in searches/ amount of misplaced pos
     * h1: amount of tiles in correct posistion
     * h2: total manhatten distance of each tile
     * h3: cost of tile * manhatten distance
     ****************************************************************/
    // first Heuristic (h1)
    public int h1(GameBoard b){
        int pos = 0;
        int[][] temp = board;

        for(int i = 0; i < temp.length; i++){
            for (int j = 0; j < temp[i].length; j++){
                if(temp[i][j] != goal[i][j]){
                    pos += 1;
                }
            }
        }
        return pos;
    }

    // Second Heuristic (h2)

    public int h2(GameBoard b){
        int dist = 0;
        int cost = 0;
        int[][] temp = board;

        for(int i = 0; i < temp.length; i++){
            for (int j = 0; j < temp[i].length; j++){
                if(temp[i][j] != goal[i][j]){
                    cost = temp[i][j]; 
                    dist += Math.abs(i - b.row(cost)) + Math.abs(j - b.col(cost));
                }
            }
        }
        return dist;
    }

    // third Heuristic (h3)
    // h3: based on cost of tile * manhatten distance
    public int h3(GameBoard b){
        int sum = 0;
        int dist = 0;
        int cost = 0;
        int[][] temp = board;

        for(int i = 0; i < temp.length; i++){
            for (int j = 0; j < temp[i].length; j++){
                if(temp[i][j] != goal[i][j]){
                    cost = temp[i][j]; // tile value
                    dist = Math.abs(i - b.row(cost)) + Math.abs(j - b.col(cost)); // manhattan distance

                    sum = cost * dist;
                }
            }
        }
        return sum;
    }


    /****************************************
    book keeping stuff getter and setter
    set and get length
    ****************************************/

    //set and get length of solution from initial node
    public void setLength(int d){
        this.length = d;
    }
    public int getLength(){
        return length;
    }



    // set and get node cost and total cost
    public void setCost (int c){
        this.cost = c;
    }

    public int getCost(){
        return cost;
    }

    public void setTotal(int tc){
        this.totCost = this.getParent().getTotal() + tc;
    }

    public int getTotal(){
        return totCost;
    }



    //set and get parent of child
    public void setParent(GameBoard p){
        this.parent = p;
    }
    
    public GameBoard getParent(){
        return parent;
    }



    // set and get movement of direction
    public void setMove(posMoves m){
        this.moves = m;
    }

    public posMoves getMove(){
        return moves;
    }


    /*
    //timer incrementer
    public void setTimer(){
        this.timer += 1;
    }

    public int getTimer(){
        return timer;

    }
    */
}
