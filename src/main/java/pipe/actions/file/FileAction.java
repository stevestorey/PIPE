package pipe.actions.file;

import java.awt.event.ActionEvent;

import pipe.actions.GuiAction;

public abstract class FileAction extends GuiAction
{
    public FileAction(String name, String tooltip, String keystroke)
    {
        super(name, tooltip, keystroke);
    }

    public abstract void actionPerformed(ActionEvent e);

}
