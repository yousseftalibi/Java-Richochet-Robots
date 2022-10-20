public class Cell {

    public int x,y;
    public String obstacleDirection;
    private boolean MH, MB, MG, MD;
    public boolean isConientObstacle, isContientRobot;
    public String Color;


    public void setMH(boolean MH) {
        this.MH = MH;
    }

    public void setMG(boolean MG) {
        this.MG = MG;
    }

    public void setConientObstacle(boolean conientObstacle) {
        isConientObstacle = true;
    }

    public boolean isMH() {
        return MH;
    }

    public boolean isMG() {
        return MG;
    }

    public boolean isEmpty(){
        return !isContientRobot ;
    }



}
