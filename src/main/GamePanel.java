package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize=16; //16x16pixels tile
    final int scale=3;
    public final int tileSize = originalTileSize * scale; //16*3=48 so 48x48pixels
    final int maxScreenCol = 16; // 16 tiles horizontally in total in a game screen
    final int maxScreenRow = 12; // 12 tiles vertically in total in a game screen
    final int screenWidth = tileSize * maxScreenCol; //48*16=768pixels as width
    final int screenHeight = tileSize * maxScreenRow; //48*12=576pixels as height

    int FPS = 60;
    double drawInterval = 1000000000.0/FPS;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this,keyH);

    // set player's default X, Y position and speed.
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*@Override
    public void run(){
        double nextDrawTime = System.nanoTime() + drawInterval;
        //System.out.println("Starting Next Draw time: "+nextDrawTime);
        while(gameThread != null){
            //System.out.println("This is the game loop");
            update();
            repaint();
            try {
                //System.out.println(gameThread);
                double remainigTime = nextDrawTime - System.nanoTime();
                //System.out.println("Sleep time time: "+nextDrawTime);
                if(remainigTime < 0 )
                    remainigTime = 0;
                Thread.sleep((long) (remainigTime/1000000));
                nextDrawTime += drawInterval;
                //System.out.println("Next Draw time: "+nextDrawTime);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }*/

    @Override
    public void run(){
        double lastTime = System.nanoTime();
        double delta = 0;
        while(gameThread != null){
            double currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.repaint(g2);
        g2.dispose();
    }

}
