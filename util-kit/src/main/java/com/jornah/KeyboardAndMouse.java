package com.jornah;

import org.apache.commons.lang3.RandomUtils;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeyboardAndMouse {
    private static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws AWTException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Callable<Object> MOUSE_MOVE_LISTENER = () -> {
            Point lastPoint = null;
            while (true) {
                Point point = MouseInfo.getPointerInfo().getLocation();
                if (!Objects.equals(lastPoint, point)) {
                    System.out.println(point);
                }
                lastPoint = point;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        };
        executorService.submit(MOUSE_MOVE_LISTENER);

        Thread.sleep(1000 * 3);
        for (int i = 0; i < 10; i++) {
            Point point = MouseInfo.getPointerInfo().getLocation();
            int x = (int) point.getX();
            int y = (int) point.getY();
            robot.mouseMove(x + 1, y + 1);
            inputMouse(robot, InputEvent.BUTTON3_MASK);
        }
        for (int i = 0; i < 50; i++) {
            pressKeepSecond(robot, KeyEvent.VK_7, 2);
            pressKeepSecond(robot, KeyEvent.VK_RIGHT, 2);
        }

    }

    private static void inputKey(int keyCode) {
        System.out.println("inputKey");
        try {
            robot.keyPress(keyCode);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(20, 40));
            robot.keyRelease(keyCode);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(20, 40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void inputMouse(Robot robot, int button) {
        System.out.println("inputMouse");
        try {
            robot.mousePress(button);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(20, 40));
            robot.mouseRelease(button);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(20, 40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void pressKeepSecond(Robot robot, int keyCode, int seconds) {
        System.out.println("pressKeepSecond");
        try {
            robot.keyPress(keyCode);
            TimeUnit.MILLISECONDS.sleep(seconds + RandomUtils.nextInt(20, 40));
            robot.keyRelease(keyCode);
            TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(20, 40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
