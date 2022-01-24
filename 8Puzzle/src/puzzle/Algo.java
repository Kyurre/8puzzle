package puzzle;
import java.util.*;

public class Algo {
    public List<GameBoard> soluList;
    public List<GameBoard> currQueue; 
    public List<GameBoard> popList;
    public int timer;
    public int size;
    PriorityQueue<GameBoard> prioQueue; 
    public int prioQueueSize;

    // initialize timers 
    public Algo(){
        timer = 0;
        size = 0;
        prioQueueSize = 0;
    }

    // set and get size
    public void setSize(){
        if(currQueue.size() > size){
            size = currQueue.size();
        }
    }
    public int getSize(){
        return size;
    }
    // get and set time for priority queue

    public void setPQSize(){
        if(prioQueue.size() > prioQueueSize){
            prioQueueSize = prioQueue.size();
        }
    }

    public int getPQSize(){
        return prioQueueSize;
    }


    // get and set time
    public void setTimer(){
        timer += 1;
    }
    
    public int getTimer(){
        return timer;
    }


    // to check if there aren't any duplicates in the list
    public boolean hasBoard(List<GameBoard> list, GameBoard b){
            GameBoard temp;
            for (int i = 0; i < list.size(); i++){
                temp = list.get(i);
                if (temp.contains(b.board))
                    return true;
            }
            return false;
        }

    // traces a patch back to the root node
    public void path(List<GameBoard> route, GameBoard board){
        GameBoard curr = board;
            route.add(curr);

        while(curr.getParent() != null){
            
            curr = curr.getParent();
            route.add(curr);
        }
    }

    // Breadth First Search
    public List<GameBoard> BFSearch (GameBoard root){
        soluList = new ArrayList<GameBoard>();
        currQueue = new ArrayList<GameBoard>();
        popList = new ArrayList<GameBoard>();
        currQueue.add(root);
        boolean goal = false;


        while(!currQueue.isEmpty() && !goal){
            GameBoard current = currQueue.get(0);
            setTimer();
            //System.out.println(getTimer());
            popList.add(current);
            currQueue.remove(0);

            if(current.equalsGoal()){
                path(soluList, current);
                goal = true;
            }
            current.expandNode(); // create childs of current node
            for(int i = 0; i < current.nodeChildren.size(); i++){   // go through the current list of childs in the queue
                GameBoard currChild = current.nodeChildren.get(i);
               // setSize();
                    if (currChild.equalsGoal()){
                        goal = true;
                        path(soluList, currChild);
                    }
                // add child to current queue if it isnt in either queue
                if(!hasBoard(currQueue, currChild) && !hasBoard(popList, currChild)){ 
                    currQueue.add(currChild);
                    setSize();
                }
            }
        }
        return soluList;
    }

    // Depth First Search
    public List<GameBoard> DFSearch(GameBoard root) {
        soluList = new ArrayList<GameBoard>();
        popList = new ArrayList<GameBoard>();
        currQueue = new ArrayList<GameBoard>();
        currQueue.add(root); 
        boolean goal = false;

        while(!currQueue.isEmpty() && !goal){
            GameBoard current = currQueue.get(currQueue.size()-1);
            setTimer();
            popList.add(current);
            currQueue.remove(currQueue.size()-1);
        
            if(current.equalsGoal()){
                path(soluList, current);
                goal = true;
            }

            current.expandNode();
            for(int i = current.nodeChildren.size()-1; i >= 0; i--){
                GameBoard currChild = current.nodeChildren.get(i);
                //setSize();
                if(currChild.equalsGoal()){
                    goal = true;
                    path(soluList, currChild);
                }

                if(!hasBoard(currQueue, currChild) && !hasBoard(popList, currChild)){
                    currQueue.add(currChild);
                    setSize();
                }
            }

        }
        return soluList;
    }

    // Uniform Cost Search
    // sort the nodes based on g(n) so we can pop them based on highest prio
    public class UniCompare implements Comparator<GameBoard>{

        @Override
        public int compare(GameBoard i , GameBoard j ) {
            return i.getTotal() - j.getTotal();
        }
    }


    public List<GameBoard> UFSearch(GameBoard root){
        soluList = new ArrayList<GameBoard>();
        prioQueue = new PriorityQueue<GameBoard>(new UniCompare());
        popList = new ArrayList<GameBoard>();
        boolean goal = false;
        GameBoard current = root;
        prioQueue.add(current);

        while(!(prioQueue.isEmpty()) && !goal){
            current = prioQueue.poll();
            setTimer();
            popList.add(current);

            // check if we are already at goal
            if(current.equalsGoal()){
                goal = true;
                path(soluList, current);
            }

            current.expandNode();

            for(int i= 0 ; i < current.nodeChildren.size(); i++){
                GameBoard currChild = current.nodeChildren.get(i);
                //setPQSize();
               // System.out.println(getPQSize());
                if(currChild.equalsGoal()){
                    goal = true;
                    path(soluList, currChild);
                }

                if(!hasBoard(popList, currChild)){
                    prioQueue.add(currChild);
                    setPQSize();
                }
            }

        }
        return soluList;
    }


    // Greedy Best first search
    // this is for h(n)
    public class GBFComp implements Comparator<GameBoard>{

        @Override
        public int compare(GameBoard i , GameBoard j ) {
            return i.h1(i) - j.h1(j);
        }
    }

    public List<GameBoard> GBFSearch(GameBoard root){
        soluList = new ArrayList<GameBoard>();
        prioQueue = new PriorityQueue<GameBoard>(new GBFComp());
        popList = new ArrayList<>();
        boolean goal = false;
        GameBoard current = root;
        prioQueue.add(current);

        while(!(prioQueue.isEmpty()) && !goal){
            current = prioQueue.poll();
            setTimer();
            popList.add(current);

            // check if we are already at goal
            if(current.equalsGoal()){ 
                goal = true;
                path(soluList, current);
            }

            current.expandNode();

            for(int i= 0 ; i < current.nodeChildren.size(); i++){
                GameBoard currChild = current.nodeChildren.get(i);
                //setPQSize();
                // System.out.println(getPQSize());
                if(currChild.equalsGoal()){ // check to see if solution i found 
                    goal = true;
                    path(soluList, currChild);
                }

                if(!hasBoard(popList, currChild)){ // add child if not in queue
                    prioQueue.add(currChild);
                    setPQSize();
                }
            }

        }

        return soluList;
    }


    // Astar

    //based on h1
    public class Asf1 implements Comparator<GameBoard>{

        @Override
        public int compare(GameBoard i , GameBoard j ) {
            return (i.getTotal() + i.h1(i)) - (j.getTotal() + j.h1(j));
        }
    }

    // based on h2
    public class Asf2 implements Comparator<GameBoard>{

        @Override
        public int compare(GameBoard i , GameBoard j ) {
            return (i.getTotal() + i.h2(i)) - (j.getTotal() + j.h2(j));
        }
    }

    // based on h3
    public class Asf3 implements Comparator<GameBoard>{

        @Override
        public int compare(GameBoard i , GameBoard j ) {
            return i.h3(i) - j.h3(j);
        }
    }

    public List<GameBoard> Astar(GameBoard root, int f){
        soluList = new ArrayList<GameBoard>();

        switch(f){
            case 1:
                prioQueue = new PriorityQueue<>(new Asf1());
                break;
            case 2:
                prioQueue = new PriorityQueue<GameBoard>(new Asf2());
                break;
            case 3:
                prioQueue = new PriorityQueue<GameBoard>(new Asf2());
                break;
        }

        popList = new ArrayList<>();
        boolean goal = false;
        GameBoard current = root;
        prioQueue.add(current);

        while(!(prioQueue.isEmpty()) && !goal){
            current = prioQueue.poll();
            setTimer();
            popList.add(current);

            // check if we are already at goal
            if(current.equalsGoal()){
                goal = true;
                path(soluList, current);
            }

            current.expandNode();

            for(int i= 0 ; i < current.nodeChildren.size(); i++){
                GameBoard currChild = current.nodeChildren.get(i);
                //setPQSize();
                // System.out.println(getPQSize());
                if(currChild.equalsGoal()){
                    goal = true;
                    path(soluList, currChild);
                }
                if(!hasBoard(popList, currChild)){
                    prioQueue.add(currChild);
                    setPQSize();
                    //System.out.println(getPQSize());
                }
            }
        }

        return soluList;
    }

}

 