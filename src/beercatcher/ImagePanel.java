/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beercatcher;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author TuanFPT
 */
public class ImagePanel extends JPanel {
    
    private Image image;
    
    public ImagePanel(String pathImage, int x, int y) {
        this.setSize(x, y);
        this.setLayout(null);
        try {
            image = ImageIO.read(getClass().getResource(pathImage));
        } catch (IOException ex) {
            // handle exception...
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
    
}
