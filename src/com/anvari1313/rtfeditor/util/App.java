package com.anvari1313.rtfeditor.util;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ahmad on 23/05/2017.
 */
public class App {
    public static final String APP_NAME = "RTF Editor";

    private static final String CONF_FILE_NAME = "data.conf";

    public static int APP_FRAME_WIDTH;
    public static int APP_FRAME_HEIGHT;
    public static int APP_FRAME_X;
    public static int APP_FRAME_Y;
    public static boolean APP_FRAME_IS_MAXIMIZED;


    private static Properties properties;           // A class for storing the config of our app to a file

    public static void loadConfigs(){
        properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(CONF_FILE_NAME);
            properties.load(in);

            APP_FRAME_WIDTH = Integer.parseInt(properties.getProperty("APP_FRAME_WIDTH"));
            APP_FRAME_HEIGHT = Integer.parseInt(properties.getProperty("APP_FRAME_HEIGHT"));
            APP_FRAME_X = Integer.parseInt(properties.getProperty("APP_FRAME_X"));
            APP_FRAME_Y = Integer.parseInt(properties.getProperty("APP_FRAME_Y"));
            APP_FRAME_IS_MAXIMIZED = Boolean.parseBoolean(properties.getProperty("APP_FRAME_IS_MAXIMIZED"));

            in.close();
        } catch (FileNotFoundException e) {
            setDefaultConfig();
        } catch (IOException e) {
            setDefaultConfig();
        }
    }

    public static void saveConfig(){

        //      Setting the current config of our app to the properties object
        properties.setProperty("APP_FRAME_WIDTH", "" + APP_FRAME_WIDTH);
        properties.setProperty("APP_FRAME_HEIGHT", "" + APP_FRAME_HEIGHT);
        properties.setProperty("APP_FRAME_X", "" + APP_FRAME_X);
        properties.setProperty("APP_FRAME_Y", "" + APP_FRAME_Y);
        properties.setProperty("APP_FRAME_IS_MAXIMIZED", "" + APP_FRAME_IS_MAXIMIZED);


        try {
            FileOutputStream out = new FileOutputStream(CONF_FILE_NAME);
            properties.store(out, APP_NAME);
            out.close();

        } catch (FileNotFoundException e) {
            // Do nothing if the file not found
        } catch (IOException e) {
            // Do nothing if the IOException happened
        }
    }

    private static void setDefaultConfig(){
        APP_FRAME_IS_MAXIMIZED = false;
        APP_FRAME_WIDTH = 640;
        APP_FRAME_HEIGHT = 480;

        //  Set the location of the Main Frame to the center of the screen
        APP_FRAME_X = (Toolkit.getDefaultToolkit().getScreenSize().width - APP_FRAME_WIDTH) / 2;
        APP_FRAME_Y = (Toolkit.getDefaultToolkit().getScreenSize().height - APP_FRAME_HEIGHT) / 2;
    }
}
