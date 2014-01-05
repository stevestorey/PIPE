package pipe.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import pipe.views.PipeApplicationView;


public class WindowHandler extends WindowAdapter
{

    PipeApplicationView view;
    public WindowHandler(PipeApplicationView view)
    {
        this.view = view;
    }

    // Handler for window closing event
    public void windowClosing(WindowEvent e)
    {
        view.close();
    }
}
