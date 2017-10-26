package gr.teicrete.ie.oop2.rb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author labuser
 */
public class RoundButton extends JButton {

    public RoundButton(String label) {
        super(label);

        setBackground(Color.lightGray);
        setFocusable(true);
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

        setContentAreaFilled(false);
        setBorderPainted(true);

    }
    
    public RoundButton(){
        this("");
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.drawOval(0, 0, getWidth(), getHeight());
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        g.drawString(getText(), getWidth(), getHeight());

        super.paintComponent(g);
    }

    // Hit detection.
    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

}
