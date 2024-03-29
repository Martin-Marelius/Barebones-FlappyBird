package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 573860602378245302L;

    public static final int WIDTH = 600, HEIGHT = WIDTH / 9 * 12;
    private Thread thread;
    private boolean running = false;

    private Handler handler;

    private HUD hud;

    public Game() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(this.handler));

        new Window(WIDTH, HEIGHT,"Game", this);

        hud = new HUD();

        handler.addObject(new Enemy(WIDTH, HEIGHT/2-50, ID.PipeBot));
        handler.addObject(new Enemy(WIDTH+WIDTH/2 + 30, HEIGHT/2-50, ID.PipeBot));

        handler.addObject(new Player(60, HEIGHT/2-50, ID.Player, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
        stop();
    }

    private void tick() {
        handler.tick();
        hud.tick();

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs .getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if(var >= max) {
            return var = max;
        }
        else if(var < min) {
            return var = min;
        }
        return var;
    }

    public static void main (String args[] ) {
        new Game();
    }
}
