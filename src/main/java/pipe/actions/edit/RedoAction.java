package pipe.actions.edit;

import java.awt.event.ActionEvent;

import pipe.actions.GuiAction;
import pipe.controllers.PetriNetController;
import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;

public class RedoAction extends GuiAction {

    public RedoAction() {
        super("Redo", "Redo (Ctrl-Y)", "ctrl Y");
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        PipeApplicationController applicationController = ApplicationSettings
                .getApplicationController();
        PetriNetController controller = applicationController.getActivePetriNetController();
        controller.getHistoryManager().doRedo();
    }
}