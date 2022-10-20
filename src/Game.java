public class Game {

   /*public static void main(String[] args) throws Exception {

        Robot robo1 = new Robot("Red");
        Robot robo2 = new Robot("Green");
        Robot robo3 = new Robot("Blue");
        Robot robo4 = new Robot("Yellow");

        robo1.x=15; robo1.y=0;

        Board board = new Board(robo1, robo2, robo3, robo4);

        board.setCases();
        board.setBoard();
        System.out.println("robot"+robo1.Color+": (x:"+ robo1.x + ", y:"+robo1.y+");");
        System.out.println("is moving to RIGHT");
        board.MoveRobot(robo1, "Right");
        System.out.println("robot"+robo1.Color+": (x:"+ robo1.x + ", y:"+robo1.y+");");


        board.afficherBoard();

    }*/


    public static Board getBoard(){
        Robot robo1 = new Robot("Red");
        Robot robo2 = new Robot("Green");
        Robot robo3 = new Robot("Blue");
        Robot robo4 = new Robot("Yellow");

        Board board = new Board(robo1, robo2, robo3, robo4);

        board.setCases();
        board.setBoard();

        return board;

    }


}
