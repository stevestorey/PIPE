package pipe.handlers.mouse;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

/**
 * A Swing implementation of MouseUtilities
 */
public class SwingMouseUtilities implements MouseUtilities {
    @Override
    public boolean isLeftMouse(MouseEvent event) {
        return SwingUtilities.isLeftMouseButton(event);
    }
}
