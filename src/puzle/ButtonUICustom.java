package puzle;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class ButtonUICustom extends MetalButtonUI {
    @Override
    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        if (b.isContentAreaFilled()) {
            Dimension size = b.getSize();
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, size.width, size.height);
        }
    }
}
