import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Board {
    public String[][] dimensions = new String[16][16];
    public Cell[][] cases = new Cell[16][16];
    public ArrayList<Robot> playableRobots = new ArrayList<>();
    Robot robo1, robo2, robo3, robo4;
    Cell TargetCell ;

    ArrayList<Cell> targetBlue = new ArrayList<>();
    ArrayList<Cell> targetGreen = new ArrayList<>();
    ArrayList<Cell> targetYellow = new ArrayList<>();
    ArrayList<Cell> targetRed = new ArrayList<>();


    public Board(Robot robo1, Robot robo2, Robot robo3, Robot robo4) {
        this.robo1 = robo1;
        this.robo2 = robo2;
        this.robo3 = robo3;
        this.robo4 = robo4;
        robo1.setColor("Red");
        robo2.setColor("Green");
        robo3.setColor("Blue");
        robo4.setColor("Yellow");

    }

    public void setBoard() {

        addTargetBlue();
        addTargetGreen();
        addTargetYellow();
        addTargetRed();

        RandomTargetChoice();
        setObstacle();
        addRobots(robo1, robo2, robo3, robo4);
    }

    public void addRobots(Robot robo1, Robot robo2, Robot robo3, Robot robo4) {
        this.playableRobots.add(robo1);
        this.playableRobots.add(robo2);
        this.playableRobots.add(robo3);
        this.playableRobots.add(robo4);

        ArrayList<Cell> RobotCase = new ArrayList<>();


        for (int i = 0; i < 4; i++) {
            RobotCase.add(cases[playableRobots.get(i).x][playableRobots.get(i).y]);
            if (playableRobots.get(i).x == 0 && playableRobots.get(i).y == 0) {
                while (!RobotCase.get(i).isEmpty() || cases[0][1].isContientRobot || cases[1][0].isContientRobot || cases[1][1].isContientRobot) {
                    int xyMax = 15;
                    Random rand = new Random();
                    RobotCase.get(i).x = rand.nextInt(xyMax);
                    RobotCase.get(i).y = rand.nextInt(xyMax);
                }
            } else {
                for (int k = -1; k <= 1; k++) {
                    for (int j = -1; j <= 1; j++) {
                        while ((!(RobotCase.get(i).x + k > 0 && RobotCase.get(i).y + j > 0)) && (!RobotCase.get(i).isEmpty())) {
                            int xyMax = 15;
                            Random rand = new Random();
                            RobotCase.get(i).x = rand.nextInt(xyMax);
                            RobotCase.get(i).y = rand.nextInt(xyMax);
                        }

                    }
                }

            }
            playableRobots.get(i).x = RobotCase.get(i).x;
            playableRobots.get(i).y = RobotCase.get(i).y;
            RobotCase.get(i).isContientRobot = true;

        }

    }

    public void setObstacle() {

        cases[2][5].Color = "Blue";
        cases[3][9].Color = "Blue";
        cases[9][13].Color = "Blue";
        cases[10][6].Color = "Blue";
        cases[4][2].Color = "Green";
        cases[1][13].Color = "Green";
        cases[14][3].Color = "Green";
        cases[14][10].Color = "Green";
        cases[6][1].Color = "Yellow";
        cases[6][12].Color = "Yellow";
        cases[9][4].Color = "Yellow";
        cases[11][9].Color = "Yellow";
        cases[4][14].Color = "Red";
        cases[5][7].Color = "Red";
        cases[13][1].Color = "Red";
        cases[13][14].Color = "Red";

        //Construction des obstacles sur le case 16x16

        cases[0][4].setMG(true);
        cases[0][10].setMG(true);
        cases[1][14].setMG(true);
        cases[2][13].setMH(true);
        cases[2][6].setMG(true);
        cases[2][15].setMH(true);
        cases[3][5].setMH(true);
        cases[3][9].setMH(true);
        cases[3][9].setMG(true);
        cases[4][2].setMH(true);
        cases[4][3].setMG(true);
        cases[4][14].setMH(true);
        cases[4][15].setMG(true);
        cases[5][0].setMH(true);
        cases[5][7].setMG(true);
        cases[6][7].setMH(true);
        cases[6][1].setMG(true);
        cases[6][1].setMH(true);
        cases[6][12].setMG(true);
        cases[7][12].setMH(true);
        cases[7][7].setMG(true);
        cases[8][8].setMH(true);
        cases[8][7].setMG(true);
        cases[9][8].setMH(true);
        cases[9][4].setMG(true);
        cases[9][4].setMG(true);
        cases[9][13].setMG(true);
        cases[10][13].setMH(true);
        cases[10][4].setMH(true);
        cases[10][6].setMG(true);
        cases[10][6].setMH(true);
        cases[11][10].setMG(true);
        cases[11][0].setMH(true);
        cases[12][9].setMH(true);
        cases[12][15].setMH(true);
        cases[12][7].setMH(true);
        cases[12][8].setMG(true);
        cases[13][1].setMH(true);
        cases[13][2].setMG(true);
        cases[13][14].setMH(true);
        cases[13][15].setMG(true);
        cases[14][4].setMG(true);
        cases[14][10].setMH(true);
        cases[14][10].setMG(true);
        cases[15][3].setMH(true);
        cases[15][5].setMG(true);
        cases[15][12].setMG(true);


        //set of center
        cases[7][7].setConientObstacle(true);
        cases[7][8].setConientObstacle(true);
        cases[8][7].setConientObstacle(true);
        cases[8][8].setConientObstacle(true);


        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Cell obstacleCase = cases[i][j];

                if (obstacleCase.isMG()) {
                    obstacleCase.obstacleDirection = "L";
                }
                if (obstacleCase.isMH()) {
                    obstacleCase.obstacleDirection = "U";
                }

            }

        }
    }


    public Robot getRobo(int _x, int _y) throws Exception {
        for (Robot robot : playableRobots) {
            if (robot.x == _x && robot.y == _y) {
                return robot;
            }
        }
        throw new Exception("Robot not found");
    }

    public void afficherBoard() throws Exception {

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.dimensions[i][j] = "  ◻  ";
                if (cases[i][j].isContientRobot) {

                    this.dimensions[i][j] = " R_" + getRobo(i, j).Color + " ";

                } else if (cases[i][j].isConientObstacle) {
                    this.dimensions[i][j] = "  " + cases[i][j].obstacleDirection + " ";
                }
            }
        }

        this.dimensions[4][14] = "RT1";
        this.dimensions[5][7] = "RT2";
        this.dimensions[13][1] = "RT3";
        this.dimensions[13][14] = "RT4";

        for (int i = 0; i < 16; i++) {
            System.out.println();
            for (int j = 0; j < 16; j++) {
                System.out.print(this.dimensions[i][j]);

            }
        }


        System.out.println();
    }

    public void setCases() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.dimensions[i][j] = "";
                cases[i][j] = new Cell();
                cases[i][j].x = i;
                cases[i][j].y = j;
            }
        }
    }

    public Map<Character, Integer> validMoveRobot(int x, int y, String direction, Robot r) {

        Map<Character, Integer> position = new HashMap<Character, Integer>();
        switch (direction) {
            case "Right":
                for (int i = y + 1; i < 16; i++) {
                    if (cases[x][i].isEmpty() && !cases[x][i].isMG() && !cases[x][i].isConientObstacle) {
                        cases[x][y].isContientRobot = false;
                        y++;
                        //check(x, y, r);
                    } else {
                        break;
                    }
                }
                position.put('x', x);
                position.put('y', y);
                cases[x][y].isContientRobot = true;

                break;

            case "Left":
                for (int i = y ; i > 0; i--) {
                    if (cases[x][i-1].isEmpty() && !cases[x][i].isMG() && !cases[x][i-1].isConientObstacle) {
                        cases[x][y].isContientRobot = false;
                        y--;
                        //check(x, y, r);
                    } else {
                        break;
                    }
                }
                position.put('x', x);
                position.put('y', y);
                cases[x][y].isContientRobot = true;
                break;
            case "Up":
                    for (int i = x;  i > 0; i--) {
                    if (cases[i-1][y].isEmpty() && !cases[i][y].isMH() && !cases[i-1][y].isConientObstacle) {
                        cases[x][y].isContientRobot = false;
                        x--;
                        //check(x, y, r);
                    } else {
                        break;

                    }
                }
                position.put('x', x);
                position.put('y', y);
                cases[x][y].isContientRobot = true;
                break;

            case "Down":
                for (int i = x; i < 15; i++) {
                    if (cases[i+1][y].isEmpty() && !cases[i+1][y].isMH() && !cases[i+1][y].isConientObstacle) {
                        cases[x][y].isContientRobot = false;
                        x++;
                        //check(x, y, r);
                    } else {
                        break;
                    }
                }
                position.put('x', x);
                position.put('y', y);
                cases[x][y].isContientRobot = true;
                break;
        }

        return position;
    }


    public void addTargetBlue() {

        targetBlue.add(cases[2][5]);
        targetBlue.add(cases[3][9]);
        targetBlue.add(cases[9][13]);
        targetBlue.add(cases[10][6]);
    }

    public void addTargetGreen() {

        targetGreen.add(cases[4][2]);
        targetGreen.add(cases[1][13]);
        targetGreen.add(cases[14][3]);
        targetGreen.add(cases[14][10]);
    }

    public void addTargetYellow() {
        targetYellow.add(cases[9][9]);

        targetYellow.add(cases[6][1]);
      targetYellow.add(cases[6][12]);
        targetYellow.add(cases[9][4]);
        targetYellow.add(cases[11][9]);
    }

    public void addTargetRed() {
       targetRed.add(cases[4][14]);

      targetRed.add(cases[5][7]);
        targetRed.add(cases[13][1]);
        targetRed.add(cases[13][14]);
    }
    int r=0;
    public void RandomTargetChoice(){

        ArrayList<Cell> AllTarget = new ArrayList<>();
        AllTarget.add(cases[4][14]);
        AllTarget.add(cases[5][7]);
        AllTarget.add(cases[13][1]);
        AllTarget.add(cases[13][14]);
        AllTarget.add(cases[6][1]);
        AllTarget.add(cases[6][12]);
        AllTarget.add(cases[9][4]);
        AllTarget.add(cases[11][9]);
        AllTarget.add(cases[4][2]);
        AllTarget.add(cases[1][13]);
        AllTarget.add(cases[14][3]);
        AllTarget.add(cases[14][10]);
        AllTarget.add(cases[2][5]);
        AllTarget.add(cases[3][9]);
        AllTarget.add(cases[9][13]);
        AllTarget.add(cases[10][6]);

        if (r==0){
            TargetCell = AllTarget.get(r);
        }
        Random rand = new Random();
        r = rand.nextInt(AllTarget.size());
        AllTarget.remove(TargetCell);
        TargetCell = AllTarget.get(r);
    }



    public ImageView linkGUITargets(){
        switch(TargetCell.Color){
            case "Red":
                return GUI.targetRedGUI;
            case "Blue":
                return GUI.targetBlueGUI;
            case "Green":
                return GUI.targetGreenGUI;
            default:
                return GUI.targetYellowGUI;
        }
    }

    public void MoveRobot(Robot r, String direction) {

        Map<Character, Integer> position = this.validMoveRobot(r.x, r.y, direction, r);
        r.x = position.get('x');
        r.y = position.get('y');


        if(r.x == TargetCell.x && r.y == TargetCell.y && r.Color== TargetCell.Color){
            System.out.println("TargetCell is (x,y) : ("+TargetCell.x+", "+TargetCell.y+");");
            GUI.group.getChildren().remove(linkGUITargets());
            RandomTargetChoice();
            GUI.group.getChildren().add(linkGUITargets());

            linkGUITargets().setX(14+(TargetCell.y*37));
            linkGUITargets().setY(38+(TargetCell.x*37));

        }


    }
}





