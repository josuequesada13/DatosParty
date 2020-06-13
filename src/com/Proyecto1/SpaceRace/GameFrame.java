package com.Proyecto1.SpaceRace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    /**
     * Class that creates the main frame for the space race game to run
     * @author Josue Quesada
     * @version 1.0
     *
     */

    public int players;
    public static GamePanel panel_1, panel_2 ,panel_3 ,panel_4;
    private JDesktopPane mainPane;
    public static JInternalFrame iframe1, iframe2, iframe3, iframe4;
    private boolean inGame;
    private ArrayList<GamePanel> panelArrayList;

    /**
     * This method creates the main frame where inner panels will be placed
     * to play the game
     * @param players
     */
    public GameFrame(int players) throws IOException {
        this.players = players;
        mainPane = new JDesktopPane();
        setFrameSize(players);
        setResizable(false);
        setTitle("Space Race");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        start();

    }

    public void start() throws IOException {
        pack();
        this.panelArrayList = new ArrayList<>();
        inGame = false;
        setVisible(true);
        setLocationRelativeTo(null);
        keyEvent(this);
        for(int i = 1; i<= players; i++){
            createGamePanel(i);
        }
        stopPanels();
    }

    /**
     * Sets the size of the main frame depending on the quantity of players
     * @param players
     */
    public void setFrameSize(int players){
        if(players == 1) {
            this.setPreferredSize(new Dimension(350, 350 + 30));
        }else if(players == 2){
            this.setPreferredSize(new Dimension(350*2 + 10,  350 + 30));
        }else if(players == 3) {
            this.setPreferredSize(new Dimension(350*2 + 10, 350*2 + 30));
        }else if(players == 4) {
            this.setPreferredSize(new Dimension(350*2  + 10, 350*2 + 30));
        }
    }

    /**
     * Creates different panels and adjusts its location and size
     * depending on the quantity of players
     * @author Pedro Morales
     * @param player
     * @throws IOException
     */
    public void createGamePanel(int player) throws IOException {
        if(player == 1){
            iframe1 = new JInternalFrame();
            iframe1.setVisible(true);
            iframe1.setSize(350,350);
            iframe1.setLocation(0,0);
            iFrameProps(iframe1, iframe1.getX(), iframe1.getY());
            mainPane.add(iframe1);
            panel_1 = new GamePanel(player);
            this.panelArrayList.add(panel_1);
            iframe1.add(panel_1);
        }if(player == 2){
            iframe2 = new JInternalFrame();
            iframe2.setVisible(true);
            iframe2.setSize(350,350);
            iframe2.setLocation(350,0);
            iFrameProps(iframe2, iframe2.getX(), iframe2.getY());
            mainPane.add(iframe2);
            panel_2 = new GamePanel(player);
            this.panelArrayList.add(panel_2);
            iframe2.add(panel_2);
        }if(player == 3) {
            iframe3 = new JInternalFrame();
            iframe3.setVisible(true);
            iframe3.setSize(350, 350);
            if(players == 3){
                iframe3.setLocation(350/2, 350);
            }else if(players == 4){
                iframe3.setLocation(0,350);
            }
            iFrameProps(iframe3, iframe3.getX(), iframe3.getY());
            mainPane.add(iframe3);
            panel_3 = new GamePanel(player);
            this.panelArrayList.add(panel_3);
            iframe3.add(panel_3);
        }if(player == 4) {
            iframe4 = new JInternalFrame();
            iframe4.setVisible(true);
            iframe4.setSize(350, 350);
            iframe4.setLocation(350, 350);
            iFrameProps(iframe4, iframe4.getX(), iframe4.getY());
            mainPane.add(iframe4);
            panel_4 = new GamePanel(player);
            this.panelArrayList.add(panel_4);
            iframe4.add(panel_4);
        }
        add(mainPane);
    }

    /**
     * This method sets different parameters for each JInternalframe
     * so that it can`t be moved or resized
     * @param frame
     * @param x
     * @param y
     */
    public void iFrameProps(JInternalFrame frame, int x, int y){
        frame.setClosable(false);
        frame.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE); //quita logo
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                formComponentMoved(evt);
            }
            private void formComponentMoved(ComponentEvent evt) {
                // TODO Auto-generated method stub
                frame.setLocation(x, y);
            }
        });

    }

    /**
     *This method constatly checks if any player has scored X points to determine
     * who won, also, stops the movement for every other player
     */
    public void stopPanels(){
        boolean game = true;
        while(game){
            for(GamePanel panel: panelArrayList){
                if(panel.checkWin()){
                    System.out.println("Player " + panel.player + " won!");
                    for(GamePanel panel1: panelArrayList){
                        panel1.stopShip();
                    }
                    game = false;

                }
            }
        }
    }

    // arreglar para cauando juegan menos asjdaejidcnwejfebfijwenbfjwebfhuwe
    public void keyEvent(JFrame frame){
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){ //W1
                    panel_1.moveUp();
                } else if(e.getKeyCode() == KeyEvent.VK_S){//S1
                    panel_1.moveDown();
                } else if(e.getKeyCode() == KeyEvent.VK_T){//T2
                    panel_2.moveUp();
                } else if(e.getKeyCode() == KeyEvent.VK_G){//G2
                    panel_2.moveDown();
                } else if(e.getKeyCode() == KeyEvent.VK_I){//I3
                    panel_3.moveUp();
                } else if(e.getKeyCode() == KeyEvent.VK_K){//K3
                    panel_3.moveDown();
                } else if(e.getKeyCode() == KeyEvent.VK_UP) {//UP4
                    panel_4.moveUp();
                }  else if(e.getKeyCode() == KeyEvent.VK_DOWN){//DOWN4
                    panel_4.moveDown();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W){ //W1
                    panel_1.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_S){//S1
                    panel_1.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_T){//T2
                    panel_2.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_G){//G2
                    panel_2.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_I){//I3
                    panel_3.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_K){//K3
                    panel_3.shipPause();
                } else if(e.getKeyCode() == KeyEvent.VK_UP) {//UP4
                    panel_4.shipPause();
                }  else if(e.getKeyCode() == KeyEvent.VK_DOWN){//DOWN4
                    panel_4.shipPause();
                }
            }
        });
    }

}

