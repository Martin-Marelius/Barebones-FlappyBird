package Game;

import java.awt.*;

import static Game.Game.HEIGHT;
import static Game.Game.WIDTH;

public class Player extends GameObject {

    private double acc = 1.1;
    private Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y, 50, 50);
    }
    public Rectangle getBounds1() {
        return null;
    }

    public void tick() {
        y += velY;
        velY += acc;


        x = Game.clamp(x, 0, WIDTH-70);
        y = Game.clamp(y, 0, HEIGHT-70);

        collision();
    }

    private void collision() {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempOb = handler.object.get(i);
            if(tempOb.getId() == ID.PipeBot) {
                if(getBounds().intersects(tempOb.getBounds()) || getBounds().intersects(tempOb.getBounds1())) {
                    //collided
                    end();

                }
            }
        }
    }

    private void end() {

            System.out.println("You lost!");
            System.out.println("Final Score: " + HUD.SCORE);
            HUD.SCORE = 0;




    }

    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillOval(x+30,y,30,20);
        g.fillRect(x,y, 45, 35);

    }


}
