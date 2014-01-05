package pipe.actions.type;

import java.awt.Point;

import pipe.actions.TypeAction;
import pipe.controllers.PetriNetController;
import pipe.gui.Grid;
import pipe.historyActions.AddPetriNetObject;
import pipe.models.PetriNet;
import pipe.models.component.Connectable;
import pipe.models.component.Transition;

/**
 *  Abstract class to created timed/untimed transactions
 */
public abstract class TransitionAction extends TypeAction {

    protected abstract boolean isTimed();

    private String getNetTransitionName(PetriNetController petriNetController) {
        int number = petriNetController.getUniqueTransitionNumber();
        String id = "T" + number;
        return id;
    }

    private Transition newTransition(Point point, PetriNetController petriNetController)
    {
        //TODO: MOVE THIS OUT TO CONTROLLER, ALSO NEED TO ADD TO PETRINET MODEL...
        String id = getNetTransitionName(petriNetController);
        Transition transition = new Transition(id, id);
        transition.setX((double) Grid.getModifiedX(point.x));
        transition.setY((double) Grid.getModifiedY(point.y));
        transition.setTimed(isTimed());

        PetriNet petriNet = petriNetController.getPetriNet();
        petriNet.addTransition(transition);
        petriNet.notifyObservers();

        return transition;
    }

    @Override
    public void doAction(Point point, PetriNetController petriNetController) {

        Transition transition = newTransition(point, petriNetController);
        PetriNet net = petriNetController.getPetriNet();
        petriNetController.getHistoryManager().addNewEdit(new AddPetriNetObject(transition, net));
    }

    @Override
    public void doConnectableAction(Connectable connectable, PetriNetController petriNetController) {
        // Do nothing if clicked on existing connectable
    }

    public TransitionAction(final String name, final int typeID,
                            final String tooltip, final String keystroke) {
        super(name, typeID, tooltip, keystroke);
    }

}
