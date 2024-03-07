package Game;

import java.awt.*;

public class HUD {

    public static int SCORE = 0;
    public int counter;

    public void tick() {


        if(counter == 10) {
            SCORE += 1;
            counter = 0;
        }
        counter++;


    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);

        g.setFont(new Font("Trebuchet", Font.BOLD, 30));
        g.drawString(String.valueOf(SCORE), Game.WIDTH/2 - 30, 30);

    }
}
