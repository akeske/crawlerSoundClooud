package org.jsoup.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Created by akeske
 */
public class Task extends SwingWorker<Void, Void>implements ActionListener,
        PropertyChangeListener, Runnable {

    private InitGUI initGUI;
    private JProgressBar progressBar;
    private JTextField txtDepth;
    private int maxDepth;
    private JTextField txtURL;
    private JLabel labelStatus;

    public Task(JProgressBar progressBar, JTextField txtDepth, JTextField txtURL, JLabel labelStatus) {
        this.progressBar = progressBar;
        this.progressBar.setValue(0);
        this.txtDepth = txtDepth;
        this.labelStatus = labelStatus;
        this.txtURL = txtURL;
    }

    @Override
    protected void process(List<Void> chunks) {
        super.process(chunks);
    }

    @Override
    protected Void doInBackground() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressBar.isVisible()) {
                    try {
                        InitGUI.repaintTable();
                        Thread.sleep(200);
                        labelStatus.setText("Added: " + Main.newUsersCounter + "     &&     " + "Duplicates: " + Main.duplicateUsersCounter);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }).start();
        Main.db = new DBConnection();

        try {
            maxDepth = Integer.valueOf(txtDepth.getText());
        }catch (NumberFormatException e) {
            maxDepth = 1;
        }
        new Crawler(txtURL.getText(), 0, this, maxDepth) ;
        Main.db.close();
        InitGUI.repaintTable();
        return null;
    }

    @Override
    protected void done() {
        super.done();
        progressBar.setVisible(false);
        progressBar.setValue(0);
        txtDepth.setEnabled(true);
        txtURL.setEnabled(true);
        initGUI.btnRun.setEnabled(true);
        System.out.println("\n\nAdded " + Main.newUsersCounter + " new users into database.");
        System.out.println("Also, there are " + Main.duplicateUsersCounter + " duplicate users that crawler did not add.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void publicSetProgress(int pro) {
        setProgress(pro);
    }

}
