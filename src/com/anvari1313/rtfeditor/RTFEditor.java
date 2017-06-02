package com.anvari1313.rtfeditor;

import com.anvari1313.rtfeditor.frame.MainFrame;
import com.anvari1313.rtfeditor.util.App;

import javax.swing.*;

/**
 * @author Ahmad Anvari (https://github.com/anvari1313)
 */
public class RTFEditor {
    public static void main(String[] args) {
        App.loadConfigs();                              // Load the config of the app from the configfile

        //  Run the app in the separated thread from the main thread
        SwingUtilities.invokeLater(()->{
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });


    }
}
