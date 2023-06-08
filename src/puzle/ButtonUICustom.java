package puzle;

import javax.swing.*;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.text.View;
import java.awt.*;

public class ButtonUICustom extends MetalButtonUI {

    public ButtonUICustom() {

    }

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
