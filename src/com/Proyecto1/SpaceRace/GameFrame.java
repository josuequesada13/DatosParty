package com.Proyecto1.SpaceRace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GameFrame extends JFrame {
    public int players;
    public static GamePanel panel_1, panel_2 ,panel_3 ,panel_4;
    private JDesktopPane mainPane;
    public static JInternalFrame iframe1, iframe2, iframe3, iframe4;
    private boolean inGame;

    public GameFrame(int players) throws IOException {
        this.players = players;
        mainPane = new JDesktopPane();
        setFrameSize(players);
        setResizable(false);
        setTitle("Space Race");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        while(inGame){
            GamePanel panelarray[] = {panel_1, panel_2, panel_3, panel_4};
            boolean temp = true;
            while (temp){
                for(GamePanel panel: panelarray){
                    //System.out.println(panel.player);
                    panel.checkWin();
                    //System.out.println("hola");
                    if(panel.win){
                        //System.out.println("Player" + panel.getPlayer() + "won, congrats!");
                        panel.stopShip();
                        for(GamePanel panel1: panelarray){
                            stopMovement(panel1);
                        }
                        temp = false;
                        inGame = false;

                    }
                }
                /*for(GamePanel panel = null; panelarray) {
                    panel.shipStop();
                }*/
            }

            /*for(GamePanel panel: panelarray){
                panel.checkWin();
                if(panel.win){
                    System.out.println("Player" + panel.getPlayer() + "won, congrats!");
                    inGame = false;
                }*/
        }
    }

    public void start() throws IOException {
        pack();
        inGame = false;
        setVisible(true);
        setLocationRelativeTo(null);
        keyEvent(this);
        for(int i = 1; i<= players; i++){
            createGamePanel(i);
        }
    }

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

    public void createGamePanel(int player) throws IOException {
        if(player == 1){
            iframe1 = new JInternalFrame();
            iframe1.setVisible(true);
            iframe1.setSize(350,350);
            iframe1.setLocation(0,0);
            iFrameProps(iframe1, iframe1.getX(), iframe1.getY());
            mainPane.add(iframe1);
            panel_1 = new GamePanel(player);
            iframe1.add(panel_1);
        }if(player == 2){
            iframe2 = new JInternalFrame();
            iframe2.setVisible(true);
            iframe2.setSize(350,350);
            iframe2.setLocation(350,0);
            iFrameProps(iframe2, iframe2.getX(), iframe2.getY());
            mainPane.add(iframe2);
            panel_2 = new GamePanel(player);
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
            iframe3.add(panel_3);
        }if(player == 4) {
            iframe4 = new JInternalFrame();
            iframe4.setVisible(true);
            iframe4.setSize(350, 350);
            iframe4.setLocation(350, 350);
            iFrameProps(iframe4, iframe4.getX(), iframe4.getY());
            mainPane.add(iframe4);
            panel_4 = new GamePanel(player);
            iframe4.add(panel_4);
        }
        add(mainPane);
    }

    public void iFrameProps(JInternalFrame frame, int x, int y){
        frame.setClosable(false);
        frame.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE); //quita logo
        //frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
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

    public void stopMovement(GamePanel panel){
        panel.stopShip();
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
                    panel_1.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_S){//S1
                    panel_1.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_T){//T2
                    panel_2.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_G){//G2
                    panel_2.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_I){//I3
                    panel_3.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_K){//K3
                    panel_3.shipStop();
                } else if(e.getKeyCode() == KeyEvent.VK_UP) {//UP4
                    panel_4.shipStop();
                }  else if(e.getKeyCode() == KeyEvent.VK_DOWN){//DOWN4
                    panel_4.shipStop();
                }
            }
        });
    }

}

