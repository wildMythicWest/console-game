package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game extends JFrame {
    private String player = "(*.*)";
    private Point position = new Point(14, 4);
    private int width = 40;
    private int height = 41;
    private char input = '0';
    private String[] grid = new String[height+1];

    public Game() {
        StringBuilder borderBuilder = new StringBuilder("+");
        for(int i = 1;i < width; i++) {
            borderBuilder.append("=");
        }
        borderBuilder.append("+");
        String border = borderBuilder.toString();
        grid[0] = border;
        grid[height] = border;

        StringBuilder rowBuilder = new StringBuilder("|");
        for(int i=1; i<width; i++) {
            rowBuilder.append(" ");
        }
        rowBuilder.append("|");
        String row = rowBuilder.toString();

        for(int i=1; i< height; i++) {
            grid[i] = row;
        }

        this.setSize(250, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                input = e.getKeyChar();
                switch (input) {
                    case 'w' : position.move(position.x, position.y-1); break;
                    case 'a' : position.move(position.x-1, position.y); break;
                    case 's' : position.move(position.x, position.y+1); break;
                    case 'd' : position.move(position.x+1, position.y); break;
                }
            }
        });
    }

    public void start() throws InterruptedException {
        while(input != 'c') {
            drawScreen();
            Thread.sleep(17);
        }
        stop();
    }

    private void stop() {
        System.exit(0);
    }

    private String[] screen;

    private void drawScreen() {
        clearScreen();
        screen = grid.clone();
        StringBuilder locationBuilder = new StringBuilder("|");
        for(int pos = 0; pos < position.x; pos++) {
            locationBuilder.append(" ");
        }
        locationBuilder.append(player);
        for(int pos = position.x+player.length()+1; pos < grid[0].length()-1; pos++) {
            locationBuilder.append(" ");
        }
        locationBuilder.append("|");
        String locationOnLine = locationBuilder.toString();
        screen[position.y] = locationOnLine;


        for (String s : screen) {
            System.out.println(s);
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
