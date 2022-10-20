import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Solver {

    /*
     * 0 = unvisited
     1 = obstacle
     2 = visited
     9 = target
     */


    private static int [][] matrix =
                    { {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 0,0},
                    {7,0,7,0,7,0,7,0,0,0,0,0,7, 7, 7, 7},
                    {7,0,7,0,0,0,7,0,7,7,7,0,7, 7, 0,7},
                    {7,0,0,0,7,7,7,0,0,0,0,0,7, 0, 0, 7},
                    {7,0,7,0,0,0,0,0,7,7,7,0,7, 7, 0, 7},
                    {7,0,7,0,7,7,7,0,7,0,0,0,7, 7, 7, 7},
                    {7,0,7,0,7,0,0,0,7,7,7,0,7, 7, 7, 0},
                    {7,0,7,0,7,7,7,0,7,0,7,0,7, 0, 0, 0},
                    {7,0,0,0,0,0,0,0,0,0,7,9,7, 0, 7, 0},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7},
                    {7,7,7,7,7,7,7,7,7,7,7,7,7, 7, 7, 7}
            };


    private static final List<Integer> path = new ArrayList<Integer>();
    private static int pathIndex;
    static Robot robo1 = new Robot("R");
    static Robot robo2 = new Robot("G");
    static Robot robo3 = new Robot("B");
    static Robot robo4 = new Robot("Y");

    static Board board = new Board(robo1, robo2, robo3, robo4);



    public static int[][] AddObstacles(){
        board.setCases();
        board.setBoard();
        for(int i=0; i<14; i++) {
            for (int j = 0; j < 14; j++) {
                if (board.cases[i][j].isConientObstacle || board.cases[i][j].isContientRobot ) {
                    matrix[i][j]=1;
                }

                if(i==board.TargetCell.x && j ==board.TargetCell.y){
                    matrix[i][j] = 9;
                }
            }
        }
        return matrix;
    }


    public void go(int x, int y){
        DepthFirst.searchPath(AddObstacles(), x, y, path);

    }

    public static Boolean Solution(int x, int y){
        DepthFirst.searchPath(AddObstacles(), x, y, path);
        pathIndex = path.size() - 2;
        Boolean arrived = false;
        for (int p = 0; p < path.size(); p += 2) {
            int pathX = path.get(p);
            int pathY = path.get(p + 1);


            System.out.println("path (X : "+ pathX+ "  , Y : "+pathY+");");
            arrived = true;

        }
        if(arrived){
            return true;
        }
        else{
        return false;
        }
    }



    public static void main(String[] args) {
        Solver s = new Solver();
        System.out.println("enter start x & y  ");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        if(!Solution(x, y)){
            System.out.println("Too complicated, I am a simple AI :( ");
        }

    }

}