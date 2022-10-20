import java.util.Random;

public class Robot {
    public String Color;
    public int x,y;

    public Robot(String c ){
        int xy = 15;

       Random rand = new Random();
       do {
           this.x = rand.nextInt(xy);
           this.y = rand.nextInt(xy);
       }while ((this.x==7 && this.y==7) || (this.x==7 && this.y==8) || (this.x==8 && this.y==7) || (this.x==8 && this.y==8));

        this.Color = c;
    }

    public void setColor(String color) {
        Color = color;
    }

}
