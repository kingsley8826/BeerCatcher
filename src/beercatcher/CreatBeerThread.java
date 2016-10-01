/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beercatcher;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TuanFPT
 */
public class CreatBeerThread extends Thread {

    private boolean start = true;
    private JPanel pnMain;
    private int score;
    private ArrayList<Beer> beers;

    public CreatBeerThread(JPanel pnMain, int score) {
        this.pnMain = pnMain;
        this.score = score;
        beers = new ArrayList<>();
    }

    @Override
    public void run() {
        Random r = new Random();
        int timer;
        int level;
        int speedIndex;
        while (true) {
            if (start) {
                level = score / 1000 + 1;
                if (level < 5) {
                    timer = 500 + r.nextInt(1000 - 100 * level);
                } else if (level < 10) {
                    timer = 500 + r.nextInt(500);
                } else {
                    timer = 200 + r.nextInt(300);
                }
                speedIndex = randomSpeedIndex(level); // sometimes has a beer move faster orther beers
                Beer beer = new Beer(new JLabel(), speedIndex);
                beer.appear(pnMain);
                beers.add(beer);
            } else {
                timer = 500;
            }
            try {
                sleep(timer);
            } catch (InterruptedException ex) {
                Logger.getLogger(CreatBeerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private int randomSpeedIndex(int level) {
        Random r = new Random();
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += r.nextInt(2); // rately sum >= 6, so speed focus in 1
        }
        int speed;
        if(level < 4){
            speed = 1 + sum / 6;
        }else if (level < 10) {
            speed = 1 + sum / 7;
        } else {
            speed = 1 + sum / 8;
        }
        return speed;
    }

    public void stopThread() {
        start = false;
        score = 0;
        beers = new ArrayList<>();
    }

    public void startThread() {
        start = true;
        score = 0;
        beers = new ArrayList<>();
    }

    public ArrayList getBeers() {
        return beers;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
