/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beercatcher;

import java.io.File;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TuanFPT
 */
public class MoveBeerThread extends Thread {

    private boolean start = true;
    private ArrayList<Beer> beers;
    private JPanel pn;
    private JLabel box;
    private ScoreComponentsManager scm;
    private int score;
    private int numOfCaps;
    private CreatBeerThread creatBeer;
    private SoundPlayer collisionSound, deadSound;
    
    public MoveBeerThread(JPanel pn, JLabel box, ScoreComponentsManager scm, int score, int numOfCaps, CreatBeerThread creatBeer) {
        this.pn = pn;
        this.box = box;
        this.scm = scm;
        this.score = score;
        this.numOfCaps = numOfCaps;
        this.creatBeer = creatBeer;
        beers = creatBeer.getBeers();
        collisionSound = new SoundPlayer(new File("sound/collision.wav"));
        deadSound = new SoundPlayer(new File("sound/bup.wav"));
    }

    @Override
    public void run() {
        long timer;
        int level;
        while (true) {
            if (start) {
                deleteCollisionBeer();
                moveBeer();
                level = score / 1000;
                if (level < 8) {
                    timer = (long) 6.5 - level / 2;
                } else if(level < 10){
                    timer = (long) 2.5;
                }else if (level < 15) {
                    timer = 2;
                } else {
                    timer = (long) 1.5;
                }
            } else {
                timer = 500;
            }
            try {
                sleep(timer);
            } catch (InterruptedException ex) {
                Logger.getLogger(MoveBeerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void moveBeer() {
        if (beers.isEmpty()) {
            return;
        }
        for (int i = 0; i < beers.size(); i++) {

            int x = beers.get(i).getLb().getX();
            int y = beers.get(i).getLb().getY();
            beers.get(i).getLb().setLocation(x, y + beers.get(i).getSpeedIndex());
        }
    }

    private void deleteCollisionBeer() {
        if (beers.isEmpty()) {
            return;
        }
        for (int i = 0; i < beers.size(); i++) {
            if (beers.get(i).collision(box)) {
                collisionSound.play();
                beers.get(i).disappear(pn);
                beers.remove(i);
                score += 100;
                creatBeer.setScore(score);
                scm.updateScore(score);
                if (score % 2000 == 0) {
                    scm.updateCup(score);
                }
                if (score % 1000 == 0) {
                    scm.updateImageGirl(score);
                }
            } else if (beers.get(i).dead(pn)) {
                numOfCaps--;
                deadSound.play();
                beers.get(i).disappear(pn);
                beers.remove(i);
                scm.updateCaps(numOfCaps);
                if (numOfCaps <= 0) {
                    scm.updateNewGame(numOfCaps);
                    stopThread();
                    creatBeer.stopThread();
                }

            }
        }
    }

    public void stopThread() {
        start = false;
        for (int i = 0; i < beers.size(); i++) {
            beers.get(i).disappear(pn);
        }
    }

    public void startThread() {
        start = true;
        score = 0;
        numOfCaps = 3;
        beers = creatBeer.getBeers();
    }
}
