package com.altair961.engineTester;

import com.altair961.renderEngine.DisplayManager;
import com.altair961.renderEngine.Loader;
import com.altair961.renderEngine.RawModel;
import com.altair961.renderEngine.Renderer;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,    //V0
                -0.5f, -0.5f, 0f,   //V1
                0.5f, -0.5f, 0f,    //V2
                0.5f, 0.5f, 0f      //V3
        };

        int[] indices = {
                0, 1, 3,    // Top left triangle (V0, V1, V3)
                3, 1, 2     // Bottom right triangle (V3, V1, V2)
        };

        RawModel model = loader.loadToVAO(vertices, indices);

        while(!DisplayManager.isCloseRequested()) {
            renderer.prepare();
            // game logic
            renderer.render(model);
            DisplayManager.updateDisplay();;
        }

        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
