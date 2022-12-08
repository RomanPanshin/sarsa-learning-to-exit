package base;

import java.awt.Color;
import java.awt.Frame;
import java.util.Random;
import java.awt.Graphics;

public class GUI extends Frame {
    private int gameNumber;
    private final int agentStartX = 0;
    private final int agentStartY = 5;
    private int[] agentPosition = {agentStartX,agentStartY};
    private final int tileWH = 50;
    private int startX = 20;
    private int startY = 20;
    private float sum = 1;

    private float[][] board = {};

    public void setBoard(float[][] board, float sum){
        this.sum = sum;
        this.board = board;
    }


    public GUI() {
        super("Q-Learning Maze");
        this.gameNumber = 0;
        setSize(600,350);
        setVisible(true);
    }


    /*
     *This function updates the agents position on the board.
     *@x : The x coordinate of the agent
     *@y : The y coordinate of the agent
     * Returns : The reward of the agent's new position
     */
    public void setAgentPosition(int x, int y){
        this.agentPosition[0] = x;
        this.agentPosition[1] = y;
        System.out.println(x + y + " X, Y");
        this.gameNumber += 1;
    }


    /*
     *@value : The reward of the position on the board
     *Returns : The appropriate color for the specific tile on the board
     */
    private Color lookupPaintColor(float value){
        if(Math.abs(value) > Math.abs(value*2/this.sum))
                return Color.RED;
            else
                return Color.YELLOW;
//        return Color.YELLOW;
    }


    /*
     *This method renders the graphics to the screen
     *None of the game logic occurs here
     */
    public void paint(Graphics g) {
        g.setColor(Color.red);
        for(float[] row: board) {
            for(float col: row) {
                g.setColor(lookupPaintColor(col));
                g.fill3DRect(startX, startY, tileWH, tileWH, true);
                this.startX += this.tileWH;
            }
            this.startX = 20;
            this.startY += this.tileWH;
        }
        this.startY = 20;
        this.startX = 20;
        g.setColor(Color.MAGENTA);
        g.fillOval(this.agentPosition[0]*tileWH + this.startY, this.agentPosition[1]*tileWH + this.startY,  50, 50);
        g.setColor(Color.BLACK);
        g.drawString("gui number : " + this.gameNumber, 230, 335);
        repaint();
    }


}