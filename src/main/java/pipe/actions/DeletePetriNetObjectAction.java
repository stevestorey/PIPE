/*
 * Created on 04-Mar-2004
 * Author is Michael Camacho
 *
 */
package pipe.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import pipe.controllers.PetriNetController;
import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;
import pipe.models.component.PetriNetComponent;


public class DeletePetriNetObjectAction extends AbstractAction {

    private final PetriNetComponent component;


    public DeletePetriNetObjectAction(PetriNetComponent component) {
        this.component = component;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        PipeApplicationController controller = ApplicationSettings.getApplicationController();
        PetriNetController petriNetController = controller.getActivePetriNetController();
        petriNetController.delete(component);
    }

}
