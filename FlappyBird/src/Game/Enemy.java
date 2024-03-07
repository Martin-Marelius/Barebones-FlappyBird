package Game;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

    private Random r = new Random();
    private double accelerate;
    private int low = 100;
    private int high = Game.HEIGHT-low;
    private int result = r.nextInt(high-low) + low;
    private boolean track = true;

    public Enemy(int x, int y, ID id) {
        super(x, y, id);


        velX = -4;
        velY = 0;
    }

    //bottom collision box
    public Rectangle getBounds() {

        return new Rectangle(x, result+85, 60, 600);

    }

    //top collision box
    public Rectangle getBounds1() {

        return new Rectangle(x, 0, 60, result-85);

    }


    public void tick() {
        x += velX;

        if(x <= 40 && track) {
            HUD.SCORE += 50;
            track = false;
        }

        if(x<=-60){
            x = Game.WIDTH+60;
            result = r.nextInt(high-low) + low;
            track = true;

        }

    }

    public void render(Graphics g) {

        //drawing pipe
        g.setColor(Color.red);
        g.fillRect(x, 0, 60, Game.HEIGHT);

        //drawing opening, and returning it as collision
        g.setColor(Color.BLACK);
        g.fillRect(x, result-85, 60, 170);

        g.setColor(Color.green);
        g.drawRect(x, 0, 60, result-85);
        g.drawRect(x, result+85, 60, 600);

    }

}