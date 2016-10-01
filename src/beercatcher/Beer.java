/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beercatcher;

import java.awt.Image;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author TuanFPT
 */
public class Beer {

    private JLabel lb;
    private int speedIndex;

    public Beer(JLabel lb, int speedIndex) {
        this.lb = lb;
        this.speedIndex = speedIndex;
        lb.setSize(25, 87);
//        btn.setContentAreaFilled(false);
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("/images/beer.png"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        lb.setIcon(new ImageIcon(img));
    }

    public JLabel getLb() {
        return lb;
    }

    public int getSpeedIndex() {
        return speedIndex;
    }

    public void appear(JPanel pn) {
        Random r = new Random();
        pn.add(lb);
        lb.setLocation(r.nextInt(pn.getWidth() - lb.getSize().width), 0);
    }

    public void disappear(JPanel pn) {
        lb.setLocation(lb.getLocation().x, pn.getHeight());
    }

    public boolean collision(JLabel box) {
        int xBeer = lb.getLocation().x + lb.getWidth() / 2;
        int yBeer = lb.getLocation().y + lb.getHeight() * 2 / 3; // wtf
        // lay dien tich 1/3 phia tren cua box
        int xLeftBox = box.getLocation().x;
        int yUpBox = box.getLocation().y;
        int xRightBox = xLeftBox + box.getWidth();
        int yDownBox = yUpBox - box.getHeight() / 3;
        if (xBeer >= xLeftBox && xBeer <= xRightBox && yBeer >= yDownBox && yBeer <= yUpBox) {
            return true;
        }
        return false;
    }

    public boolean dead(JPanel pn) {
        if (lb.getLocation().getY() == pn.getHeight()) {
            return true;
        }
        return false;
    }

}
