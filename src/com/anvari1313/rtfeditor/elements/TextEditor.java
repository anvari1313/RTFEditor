package com.anvari1313.rtfeditor.elements;

import com.anvari1313.rtfeditor.util.App;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * @author Ahmad Anvari (https://github.com/anvari1313)
 */
public class TextEditor extends JTextPane {

    private Clipboard clipboard;

    private boolean isChanged;
    private RTFEditorKit rtf;

    private JPopupMenu popupMenu;


    public TextEditor(){
        super();

        rtf = new RTFEditorKit();
        setEditorKit(rtf);

        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        isChanged = false;
//        getDocument().addDocumentListener(new MyListener());
        addChangeListener();
        setPopupMenu();
    }

    public void newDoc(){
        isChanged = false;
        setText("");
    }

    public void openDoc(File file){

        FileInputStream fi = null;


        try {
            fi = new FileInputStream(file);
            rtf.read(fi, getDocument(), 0);
            fi.close();
            addChangeListener();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), App.APP_NAME, JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), App.APP_NAME, JOptionPane.ERROR_MESSAGE);
        } catch (BadLocationException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), App.APP_NAME, JOptionPane.ERROR_MESSAGE);
        }

    }

    public void saveDoc(File file){
        StyledDocument doc = (StyledDocument)getDocument();


        BufferedOutputStream out;

        try {
            out = new BufferedOutputStream(new FileOutputStream(file));

            rtf.write(out, doc, doc.getStartPosition().getOffset(), doc.getLength());

        } catch (FileNotFoundException e) {

        } catch (IOException e){

        } catch (BadLocationException e){

        }
    }

    public boolean isChanged(){
        return this.isChanged;
    }

    private void addChangeListener(){
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isChanged = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isChanged = true;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isChanged = true;
            }
        });
    }

    private void setPopupMenu(){
        popupMenu = new JPopupMenu();

        popupMenu.add(new JMenuItem("Copy"));

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isPopupTrigger())
                    popupMenu.show(TextEditor.this, e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger())
                    popupMenu.show(TextEditor.this, e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger())
                    popupMenu.show(TextEditor.this, e.getX(), e.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.isPopupTrigger())
                    popupMenu.show(TextEditor.this, e.getX(), e.getY());
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void copySelectedText(){
        TransferHandler transferHandler = getTransferHandler();
        transferHandler.exportToClipboard(this, clipboard, TransferHandler.COPY);
    }

    public void cutSelectedText(){
        TransferHandler transferHandler = getTransferHandler();
        transferHandler.exportToClipboard(this, clipboard, TransferHandler.COPY);
    }

    public void pasteSelectedText(){
        TransferHandler transferHandler = getTransferHandler();
        transferHandler.importData(this, clipboard.getContents(null));
    }


}