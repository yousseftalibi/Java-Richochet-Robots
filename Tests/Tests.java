import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    Robot RR = new Robot("Red");
    Board testBoard = new Board(RR, new Robot("G"), new Robot("Y"), new Robot("B"));

    @Test
    public void testSetBoard() {
        testBoard.setCases();
        testBoard.setBoard();
        assertNotNull(testBoard.playableRobots);
    }
    @Test
    public void testMoveRight(){
        RR.y=0;

        testBoard.setCases();
        testBoard.setBoard();
        for(int i=1; i<6; i++){
            testBoard.cases[RR.x][i].isConientObstacle = false;
        }
        testBoard.cases[RR.x][6].isConientObstacle = true;

        testBoard.MoveRobot(RR, "Right");
        assertEquals(RR.y, 5);
    }

    @Test
    public void testMoveLeft(){
        RR.y=3;
        testBoard.setCases();
        testBoard.setBoard();

        testBoard.cases[RR.x][1].isConientObstacle = false;
        testBoard.cases[RR.x][2].isConientObstacle = false;

        testBoard.MoveRobot(RR, "Left");

        assertEquals(RR.y, 0);
    }

    @Test
    public void testMoveUp(){
        RR.x=15;

        testBoard.setCases();
        testBoard.setBoard();
        for(int i=1; i<16; i++){
            testBoard.cases[i][RR.y].isConientObstacle = false;
            testBoard.cases[i][RR.y].isContientRobot = false;
        }

        testBoard.MoveRobot(RR, "Up");
        assertEquals(0, RR.x);
    }

    @Test
    public void testMoveDown(){
        RR.x=0;

        testBoard.setCases();
        testBoard.setBoard();

        for(int i=15; i>0; i--){
            testBoard.cases[i][RR.y].isConientObstacle = false;
            testBoard.cases[i][RR.y].isContientRobot = false;
        }

        testBoard.MoveRobot(RR, "Down");
        assertEquals(15, RR.x);
    }

    @Test
    public void testGetRobo() throws Exception {
       /* RR.x=0; RR.y=0;
        testBoard.getRobo(0, 0);
        assertThrows("Robot not found")*/

    }
    }