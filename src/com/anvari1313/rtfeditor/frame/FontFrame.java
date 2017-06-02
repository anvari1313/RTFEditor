package com.anvari1313.rtfeditor.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ahmad Anvari (https://github.com/anvari1313)
 */
public class FontFrame extends JDialog {
    private JFrame parent;
    private JButton okJButton;
    private JButton cancelJButton;

    private JList fontList;
    private JCheckBox isBoldCheckBox;
    private JCheckBox isItalicCheckBox;
    private JCheckBox isUnderlineCheckBox;

    private String fonts[];

    private final int FRAME_WIDTH = 500;
    private final int FRAME_HEIGHT = 600;

    public FontFrame(JFrame parent){
        super(parent, "Font", true);    // Set the title and set the modality to true for the dialog

        setLocationRelativeTo(parent);            // Start location of the dialog should be relative to its parent(MainFrame)
        setSize(FRAME_WIDTH, FRAME_HEIGHT);


        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        this.parent = parent;
        setLayout(null);
        setResizable(false);
        setComponents();
    }

    /**
     * Every components in this frame is initialized in this method
     */
    private void setComponents(){
        // Size of the OK, Cancel Buttons
        int commandButtonsHeight = 30;
        int commandButtonsWidth = 100;


        int commandButtonSpaceToTheBottomOfFrame = 50;

        okJButton = new JButton("OK");
        cancelJButton = new JButton("Cancel");

        okJButton.setSize(commandButtonsWidth, commandButtonsHeight);
        cancelJButton.setSize(commandButtonsWidth, commandButtonsHeight);


        okJButton.setLocation(this.getWidth() - 2 * commandButtonsWidth - 30,                   // Set the X of OK Button
                this.getHeight() - commandButtonsHeight - commandButtonSpaceToTheBottomOfFrame);// Set the Y of OK Button
        cancelJButton.setLocation(this.getWidth() - 1 * commandButtonsWidth - 20,               // Set the X of Cancel Button
                this.getHeight() - commandButtonsHeight - commandButtonSpaceToTheBottomOfFrame);// Set the Y of Cancel Button

        // Add buttons to the frame
        add(okJButton);
        add(cancelJButton);

        fontList = new JList(fonts);
        JScrollPane scrollBar = new JScrollPane(fontList);
        scrollBar.setSize(this.getWidth() / 2, 2 * this.getHeight() / 3);
        scrollBar.setLocation(20, 20);
        add(scrollBar);


        int checkBoxHeight = 30;
        int checkBoxWidth = 100;
        int checkBoxX = 2 * getWidth() / 3;
        int checkBoxSpaceToTheTopOfFrame = 50;

        isBoldCheckBox = new JCheckBox("Bold");
        isBoldCheckBox.setLocation(checkBoxX, checkBoxHeight + checkBoxSpaceToTheTopOfFrame);
        isBoldCheckBox.setSize(checkBoxWidth, checkBoxHeight);
        add(isBoldCheckBox);
        isItalicCheckBox = new JCheckBox("Italic");
        isItalicCheckBox.setLocation(checkBoxX, checkBoxHeight + 2 * checkBoxSpaceToTheTopOfFrame);
        isItalicCheckBox.setSize(checkBoxWidth, checkBoxHeight);
        add(isItalicCheckBox);
        isUnderlineCheckBox = new JCheckBox("Underline");
        isUnderlineCheckBox.setLocation(checkBoxX, checkBoxHeight + 3 * checkBoxSpaceToTheTopOfFrame);
        isUnderlineCheckBox.setSize(checkBoxWidth, checkBoxHeight);
        add(isUnderlineCheckBox);
    }

    public void showDialog(){
        setVisible(true);
    }
}
