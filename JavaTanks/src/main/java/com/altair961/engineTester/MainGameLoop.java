package com.altair961.engineTester;

import com.altair961.renderEngine.DisplayManager;
import com.altair961.renderEngine.Loader;
import com.altair961.renderEngine.RawModel;
import com.altair961.renderEngine.Renderer;
import com.altair961.shaders.StaticShader;

public class MainGameLoop {
    public static void main(String[] args) {
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

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
            shader.start();
            // game logic
            renderer.render(model);
            shader.stop();
            DisplayManager.updateDisplay();;
        }

        shader.cleanUp();
        loader.cleanUP();
        DisplayManager.closeDisplay();
    }
}
