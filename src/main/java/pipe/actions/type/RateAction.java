package pipe.actions.type;

import pipe.actions.TypeAction;
import pipe.controllers.PetriNetController;
import pipe.models.component.Connectable;

import java.awt.event.MouseEvent;

public class RateAction extends TypeAction {


    @Override
    public void doAction(MouseEvent event, PetriNetController petriNetController) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doConnectableAction(Connectable connectable, PetriNetController petriNetController) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public RateAction(final String name, final int typeID,
                     final String tooltip, final String keystroke) {
        super(name, typeID, tooltip, keystroke);
    }


}
