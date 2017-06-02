package com.anvari1313.rtfeditor.frame;

import com.anvari1313.rtfeditor.elements.TextEditor;
import com.anvari1313.rtfeditor.util.App;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;


/**
 * @author Ahmad Anvari (https://github.com/anvari1313)
 */
public class MainFrame extends JFrame {
    private TextEditor editorPane;


    private JPopupMenu popupMenu;
    private JMenu editMenu;
    private FontFrame fontFrame;

    public MainFrame(){
        setFrame();
        setMenus();
        setEditorPane();
        fontFrame = new FontFrame(this);
    }

    private void setEditorPane(){
        editorPane = new TextEditor();
        add(editorPane);
    }

    /**
     * Set the size and location of the frame
     */
    private void setFrame(){
        setSize(App.APP_FRAME_WIDTH, App.APP_FRAME_HEIGHT);                         // Set the size of the frame from the conf file
        setLocation(App.APP_FRAME_X, App.APP_FRAME_Y);                              // Set the location of the frame from the conf file
        if (App.APP_FRAME_IS_MAXIMIZED) setExtendedState(JFrame.MAXIMIZED_BOTH);    // Maximize the app if it was maximized
        setTitle(App.APP_NAME);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);                       // Frame should not be closed when the X button is clicked
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {                      // The handler for frame close operation
                attemptToClose();                                                   // attemptToClose method will be run when the X button is clicked
            }
        });

        addWindowStateListener((WindowEvent e) -> App.APP_FRAME_IS_MAXIMIZED = (e.getNewState() & JFrame.MAXIMIZED_BOTH) != 0);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    // Set the theme of the app to the default system theme
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construct the menu items and their handlers'
     */
    private void setMenus(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem newFileItem = new JMenuItem("New");
        newFileItem.setMnemonic(KeyEvent.VK_N);
        newFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newFileItem.addActionListener((ActionEvent e) ->{

        });

        JMenuItem openFileItem = new JMenuItem("Open...");
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        openFileItem.addActionListener((ActionEvent event) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showDialog(this, "Open it");
            if (result == JFileChooser.APPROVE_OPTION) {
                editorPane.openDoc(fileChooser.getSelectedFile());
            }
        });


        JMenuItem saveFileItem = new JMenuItem("Save");
        saveFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        saveFileItem.addActionListener((ActionEvent event)->{

        });

        JMenuItem saveAsFileItem = new JMenuItem("Save As...");
        saveAsFileItem.addActionListener((ActionEvent event)->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                editorPane.saveDoc(fileChooser.getSelectedFile());
            }

        });

        JMenuItem exitFileItem = new JMenuItem("Exit");
        exitFileItem.setMnemonic(KeyEvent.VK_X);
        exitFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK));
        exitFileItem.addActionListener((ActionEvent event)-> {attemptToClose(); });

        fileMenu.add(newFileItem);
        fileMenu.add(openFileItem);
        fileMenu.addSeparator();
        fileMenu.add(saveFileItem);
        fileMenu.add(saveAsFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitFileItem);
        menuBar.add(fileMenu);

        editMenu = new JMenu("Edit");
        JMenuItem cutEditItem = new JMenuItem("Cut");
        cutEditItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        cutEditItem.addActionListener((ActionEvent event) -> editorPane.cutSelectedText() );
        JMenuItem copyEditItem = new JMenuItem("Copy");
        copyEditItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        copyEditItem.addActionListener((ActionEvent event) -> editorPane.copySelectedText() );
        JMenuItem pasteEditItem = new JMenuItem("Paste");
        pasteEditItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        pasteEditItem.addActionListener((ActionEvent event) -> editorPane.pasteSelectedText() );
        JMenuItem gotoEditItem = new JMenuItem("Go to");

        editMenu.add(cutEditItem);
        editMenu.add(copyEditItem);
        editMenu.add(pasteEditItem);
        editMenu.addSeparator();
        editMenu.add(gotoEditItem);
        menuBar.add(editMenu);

        JMenu styleMenu = new JMenu("Style");
        JMenu setColorEditItem = new JMenu("Set Color");
        JMenuItem blackSetColorEditItem = new JMenuItem("Black");
        JMenuItem blueSetColorEditItem = new JMenuItem("Blue");

        JMenu fontSyleEdit = new JMenu("Style");
        JMenuItem boldFontSyleEdit = new JMenuItem("Bold");
        boldFontSyleEdit.addActionListener((ActionEvent event)->{
            fontFrame.showDialog();
        });

        JMenuItem regularFontSyleEdit = new JMenuItem("Italic");

        JMenuItem fontStyleEditMenuItem = new JMenuItem("Font...");
        fontStyleEditMenuItem.addActionListener((ActionEvent event)->
            fontFrame.showDialog()
        );

        styleMenu.add(setColorEditItem);
        setColorEditItem.add(blackSetColorEditItem);
        setColorEditItem.add(blueSetColorEditItem);
        styleMenu.add(fontStyleEditMenuItem);
        menuBar.add(styleMenu);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem topicHelpItem = new JMenuItem("Topic");
        JMenuItem aboutItem = new JMenuItem("About " + App.APP_NAME);
        helpMenu.add(topicHelpItem);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void attemptToClose(){
        if (editorPane.isChanged()){
            int dialogResult = JOptionPane.showConfirmDialog(this, "You have unsaved changes in the opened document.\nWould you like to save it before exit?",App.APP_NAME, JOptionPane.YES_NO_CANCEL_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                close();
            }else if (dialogResult == JOptionPane.NO_OPTION){
                close();
            }
        }else {
            close();
        }
    }

    private void close(){
        App.APP_FRAME_WIDTH = getWidth();
        App.APP_FRAME_HEIGHT = getHeight();
        App.APP_FRAME_X = getX();
        App.APP_FRAME_Y = getY();
        App.saveConfig();
        System.exit(0);
    }
}