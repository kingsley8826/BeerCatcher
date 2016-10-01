package beercatcher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TuanFPT
 */
public final class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    int score = 0;
    int numOfCaps = 3;
    JLabel box;
    JPanel pnMain;
    JPanel pnGirl;
    JLabel lbGirl;
    Container jFrame = this.getContentPane();
    ScoreComponentsManager scm;
    Graphics g = null;
    MoveBeerThread moveBeer;
    CreatBeerThread creatBeer;
    public Main() {
        initComponents();
        initPanel();
        createComponent();
        addMouseMove();
        startGame();
        this.setLocationRelativeTo(null);
        this.setSize(1014, 640);
    }

    public void initPanel() {
        pnMain = new ImagePanel("/images/background.png", 600, 600);
        pnGirl = new JPanel();
        lbGirl = new JLabel();
        pnGirl.setLayout(null);
        pnGirl.add(lbGirl);
        lbGirl.setBounds(0, 0, 300, 600); // important
        pnGirl.setSize(new Dimension(300, 600));
        jFrame.add(pnScore);
        pnScore.setLocation(0, 0);
        jFrame.add(pnMain);
        pnMain.setLocation(100, 0);
        jFrame.add(pnGirl);
        pnGirl.setLocation(700, 0);
        g = pnScore.getGraphics();
    }

    public void createComponent() {
        ArrayList<JLabel> lbCaps = new ArrayList<>();
        lbCaps.add(lbCap1);        
        lbCaps.add(lbCap2);
        lbCaps.add(lbCap3);
        scm = new ScoreComponentsManager(lbCup, btnNewGame, lbGirl, lbCaps, g);
        scm.update(score, numOfCaps);
    }

    public void addMouseMove() {
        box = new JLabel();
        pnMain.add(box);
        box.setBounds(250, 500, 150, 90);
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/images/box.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        box.setIcon(new ImageIcon(img));
        pnMain.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (pnMain.getMousePosition().x > pnMain.getWidth() - box.getWidth() / 2) { // right
                    box.setLocation(pnMain.getWidth() - box.getWidth(), pnMain.getHeight() - box.getHeight());
                } else if (pnMain.getMousePosition().x < box.getWidth() / 2) { // left
                    box.setLocation(0, pnMain.getHeight() - box.getHeight());
                } else {
                    box.setLocation(pnMain.getMousePosition().x - box.getWidth() / 2, pnMain.getHeight() - box.getHeight());
                }
            }
        });
    }

    public void startGame() {
        creatBeer = new CreatBeerThread(pnMain, score);
        creatBeer.start();
        moveBeer = new MoveBeerThread(pnMain, box, scm, score, numOfCaps, creatBeer);
        moveBeer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnScore = new javax.swing.JPanel();
        lbCup = new javax.swing.JLabel();
        lbCap3 = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        lbCap1 = new javax.swing.JLabel();
        lbCap2 = new javax.swing.JLabel();
        lbScoreLayout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(null);

        pnScore.setPreferredSize(new java.awt.Dimension(100, 600));
        pnScore.setLayout(null);
        pnScore.add(lbCup);
        lbCup.setBounds(30, 20, 60, 180);

        lbCap3.setMaximumSize(new java.awt.Dimension(34, 30));
        lbCap3.setMinimumSize(new java.awt.Dimension(10, 10));
        pnScore.add(lbCap3);
        lbCap3.setBounds(70, 500, 30, 30);

        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });
        pnScore.add(btnNewGame);
        btnNewGame.setBounds(20, 540, 80, 30);
        pnScore.add(lbCap1);
        lbCap1.setBounds(10, 500, 30, 30);
        pnScore.add(lbCap2);
        lbCap2.setBounds(40, 500, 30, 30);

        lbScoreLayout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/score.png"))); // NOI18N
        pnScore.add(lbScoreLayout);
        lbScoreLayout.setBounds(0, 0, 100, 600);

        getContentPane().add(pnScore);
        pnScore.setBounds(0, 0, 100, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        score = 0;
        numOfCaps = 3;
        scm.update(score, numOfCaps);
        creatBeer.startThread();
        moveBeer.startThread();
    }//GEN-LAST:event_btnNewGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewGame;
    private javax.swing.JLabel lbCap1;
    private javax.swing.JLabel lbCap2;
    private javax.swing.JLabel lbCap3;
    private javax.swing.JLabel lbCup;
    private javax.swing.JLabel lbScoreLayout;
    private javax.swing.JPanel pnScore;
    // End of variables declaration//GEN-END:variables
}
