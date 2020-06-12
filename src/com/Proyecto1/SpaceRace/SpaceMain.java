package com.Proyecto1.SpaceRace;

import java.io.IOException;

public class SpaceMain {
    private static GameFrame myBoard;

    public static void main(String[] args) throws IOException {
        myBoard = new GameFrame(2);
        myBoard.start();
    }
}
