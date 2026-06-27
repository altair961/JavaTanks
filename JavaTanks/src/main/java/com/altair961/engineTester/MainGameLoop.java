package com.altair961.engineTester;

import com.altair961.renderEngine.DisplayManager;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while(!DisplayManager.isCloseRequested()) {
            // game logic
            // render
            DisplayManager.updateDisplay();;
        }

        DisplayManager.closeDisplay();
    }
}
