package pipe.actions.file;

import java.awt.event.ActionEvent;

import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;

public class CreateAction extends FileAction {

    public CreateAction() {
        super("New", "Create a new Petri net", "ctrl N");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PipeApplicationController controller = ApplicationSettings.getApplicationController();
        controller.createEmptyPetriNet();
    }
}
