/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author andres
 */
public class Frames {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Marco marco = new Marco();

    }

}

class Marco extends JFrame {

    public Marco() {
        setSize(650, 520);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Computer Graphics");

        Panel panel = new Panel();

        Panel2 panel2 = new Panel2();
        panel2.setSize(150, 520);
        add(panel);
        add(panel2);

        setLocationRelativeTo(null);
    }

}

class Panel extends JPanel {

    public Panel() {
        setBackground(Color.WHITE);
        setSize(500, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // size es el tamaño de la ventana.
        Dimension size = getSize();

        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        g2d.setColor(Color.black);

        int xmin = w / 2 - 100;
        int xmax = w / 2 + 100;
        int ymin = h / 2 - 100;
        int ymax = h / 2 + 100;
        
        g2d.drawLine(xmin, ymax, xmin, ymin);   // xmin
        g2d.drawLine(xmax, ymin, xmax, ymax);   // xmax
        g2d.drawLine(xmin, ymin, xmax, ymin);   // ymin
        g2d.drawLine(xmax, ymax, xmin, ymax);   // ymax

        coordinates(g2d, 0, w/2, h/2, xmin, ymin, xmax, ymax);
    }
    
    // This function is to calculate the points in which each segment line, starts and end.
    private void coordinates(Graphics2D g2d, int angle, int w, int h, int xmin, int ymin, int xmax, int ymax) {
        Point points[] = new Point[4];
        for (int i = 0; i < 2; i++) {
            int x_pos = (int) Math.round(141 * Math.cos(Math.toRadians(angle)));
            int y_pos = (int) Math.round(141 * Math.sin(Math.toRadians(angle)));
            points[i] = new Point(w + x_pos, h + y_pos);
            angle += 90;
        }

        for (int i = 0; i < 1; i++) {
            int n1 = getCode(points[i].x, points[i].y, xmin, ymin, xmax, ymax);
            int n2 = getCode(points[(i + 1)%4].x, points[(i + 1)%4].y, xmin, ymin, xmax, ymax);
            if (((n1 | n2) == 0)) {
                g2d.setColor(Color.green);
                g2d.drawLine(points[i].x, points[i].y, points[(i + 1)%4].x, points[(i + 1)%4].y);
            }else{
                g2d.setColor(Color.blue);
                g2d.drawLine(points[i].x, points[i].y, points[(i + 1)%4].x, points[(i + 1)%4].y);
            }
        }
    }
    
    private int getCode(int x, int y, int xmin, int ymin, int xmax, int ymax) {
        int res = 0; // Inside   0000
        if (x < xmin) {
            res = 1; // Left     0001
        } else if (x > xmax) {
            res = 2; // Right    0010
        }
        if (y > xmax) {
            res = 4; // Bottom   0100
        } else if (y < ymin) {
            res = 8; // Top      1000
        }
        return res;
    }
    
}

class Panel2 extends JPanel {

    public Panel2() {
        setBackground(Color.BLUE);
    }
}
