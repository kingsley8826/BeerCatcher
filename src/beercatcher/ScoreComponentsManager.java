/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beercatcher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author TuanFPT
 */
public class ScoreComponentsManager {

    private JLabel lbCup;
    private JButton btnNewGame;
    private JLabel lbGirl;
    private ArrayList<JLabel> lbCaps;
    private Graphics g;
    private SoundPlayer fillBeerSound, girlSound;
    public ScoreComponentsManager(JLabel lbCup, JButton btnNewGame, JLabel lbGirl, ArrayList<JLabel> lbCaps, Graphics g) {
        this.lbCup = lbCup;
        this.btnNewGame = btnNewGame;
        this.lbGirl = lbGirl;
        this.lbCaps = lbCaps;
        this.g = g;
        fillBeerSound = new SoundPlayer(new File("sound/fillBeer.wav"));
        girlSound = new SoundPlayer(new File("sound/girl.wav"));
    }

    public void update(int score, int numOfCaps) {
        updateNewGame(numOfCaps);
        updateCaps(numOfCaps);
        updateScore(score);
        updateCup(score);
        updateImageGirl(score);
    }

    public void updateNewGame(int numOfCaps) {
        if (numOfCaps < 0) {
            return;
        }
        String pathName = "/images/btnNewGame" + numOfCaps + ".png";
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(pathName));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (numOfCaps == 3) {
            btnNewGame.setContentAreaFilled(false);
            btnNewGame.setEnabled(false);
            btnNewGame.setIcon(new ImageIcon(img));
        } else if (numOfCaps == 0) {
            btnNewGame.setEnabled(true);
            btnNewGame.setIcon(new ImageIcon(img));
        }
    }

    public void updateCaps(int numOfCaps) {
        String pathName;
        Image img = null;
        if (numOfCaps == 3) {
            pathName = "/images/beerCapOn.png";
            try {
                img = ImageIO.read(getClass().getResource(pathName));
            } catch (IOException ex) {
                Logger.getLogger(ScoreComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < 3; i++) {
                lbCaps.get(i).setIcon(new ImageIcon(img));
            }
        } else if (numOfCaps >= 0) {
            pathName = "/images/beerCapOff.png";
            try {
                img = ImageIO.read(getClass().getResource(pathName));
            } catch (IOException ex) {
                Logger.getLogger(ScoreComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbCaps.get(numOfCaps).setIcon(new ImageIcon(img));
        }
    }

    public void updateScore(int score) {
        g.setColor(Color.black);
        g.fillRect(15, 200, 70, 40);
        g.setColor(Color.red);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        g.drawString(String.valueOf(score), 15, 240);
    }

    public void updateCup(int score) {
        if (score > 10000) {
            return;
        }
        if(score > 0){
            fillBeerSound.play();
        }
        String pathName = "/images/cup/" + score / 2000 + ".png";
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(pathName));
        } catch (IOException ex) {
            Logger.getLogger(ScoreComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbCup.setIcon(new ImageIcon(img));
    }

    public void updateImageGirl(int score) {
        if (score >= 9000 && score != 10000 && score != 12000 && score != 15000){
            return;
        }
        if(score > 0){
            girlSound.play();
        }
        String pathName = "/images/ngoctrinh/" + score / 1000 + ".png";
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource(pathName));
        } catch (IOException ex) {
            Logger.getLogger(ScoreComponentsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbGirl.setIcon(new ImageIcon(img));
    }

}
