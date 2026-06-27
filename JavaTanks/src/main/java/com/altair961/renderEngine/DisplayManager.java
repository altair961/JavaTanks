package com.altair961.renderEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.glfw.GLFWVidMode;

public class DisplayManager {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String TITLE = "Java Tanks";

    private static long window;

    private static double lastFrameTime;
    private static float delta;

    public static void createDisplay() {
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create window
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        window = GLFW.glfwCreateWindow(
                WIDTH,
                HEIGHT,
                TITLE,
                0,
                0);
        if(window == 0)
            throw new RuntimeException("Failed to create window.");

        // Configure OpenGL version
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE,
                GLFW.GLFW_OPENGL_CORE_PROFILE);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT,
                GLFW.GLFW_TRUE);

        // Center window
        GLFWVidMode vid =
                GLFW.glfwGetVideoMode(
                        GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(
                window,
                (vid.width()-WIDTH)/2,
                (vid.height()-HEIGHT)/2);

        // Make context current
        GLFW.glfwMakeContextCurrent(window);

        // Enable VSync
        GLFW.glfwSwapInterval(1);

        // Show window
        GLFW.glfwShowWindow(window);

        // Create OpenGL capabilities
        GL.createCapabilities();

        // Initialize timer
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay()
    {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
        double currentFrameTime = getCurrentTime();
        delta = (float)(currentFrameTime-lastFrameTime);
        lastFrameTime = currentFrameTime;
    }


    public static void closeDisplay() {

        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();

        GLFW.glfwSetErrorCallback(null).free();
    }

    public static boolean isCloseRequested() {

        return GLFW.glfwWindowShouldClose(window);
    }

    private static double getCurrentTime()
    {
        return GLFW.glfwGetTime();
    }

    public static float getFrameTimeSeconds()
    {
        return delta;
    }
}