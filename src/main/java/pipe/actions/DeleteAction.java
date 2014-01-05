package pipe.actions;

import java.awt.event.ActionEvent;

import pipe.controllers.PetriNetController;
import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;

public class DeleteAction extends GuiAction
{

    public DeleteAction(String name, String tooltip, String keystroke)
    {
        super(name, tooltip, keystroke);
    }

    public void actionPerformed(ActionEvent e)
    {
        PipeApplicationController controller = ApplicationSettings.getApplicationController();
        PetriNetController petriNetController =  controller.getActivePetriNetController();
        petriNetController.deleteSelection();
    }
}
