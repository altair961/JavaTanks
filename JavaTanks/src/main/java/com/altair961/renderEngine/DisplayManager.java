package com.altair961.renderEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class DisplayManager {
    private static long window;

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;

    public static void createWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        window = GLFW.glfwCreateWindow(
                WIDTH,
                HEIGHT,
                "Java Tanks",
                0,
                0);

        if (window == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);

        GL.createCapabilities();
    }

    public static void updateDisplay() {

        GLFW.glfwSwapBuffers(window);

        GLFW.glfwPollEvents();
    }

    public static void closeDisplay() {

        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();

        GLFW.glfwSetErrorCallback(null).free();
    }

    public static boolean isCloseRequested() {

        return GLFW.glfwWindowShouldClose(window);
    }
}
