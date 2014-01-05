package pipe.actions;

import java.awt.Point;
import java.awt.event.ActionEvent;

import pipe.controllers.PetriNetController;
import pipe.gui.ApplicationSettings;
import pipe.gui.Constants;
import pipe.gui.PetriNetTab;
import pipe.gui.StatusBar;
import pipe.models.PipeApplicationModel;
import pipe.models.component.Connectable;

public abstract class TypeAction extends GuiAction
{
    //TODO: Eventually a handler can tell the GUI Action to do its thing
    // for each type of type clicked on.
    public abstract void doAction(Point point, PetriNetController petriNetController);
    public abstract void doConnectableAction(Connectable connectable, PetriNetController petriNetController);

    private final int typeID;

    public TypeAction(String name, int typeID, String tooltip, String keystroke)
    {
        super(name, tooltip, keystroke);
        this.typeID = typeID;
    }

    public TypeAction(String name, int typeID, String tooltip, String keystroke, boolean toggleable)
    {
        super(name, tooltip, keystroke, toggleable);
        this.typeID = typeID;
    }

    public void actionPerformed(ActionEvent e)
    {
        PipeApplicationModel pipeApplicationView = ApplicationSettings.getApplicationModel();
        PipeApplicationModel model = ApplicationSettings.getApplicationModel();
        model.selectTypeAction(this);

        pipeApplicationView.setMode(typeID);
        StatusBar statusBar = ApplicationSettings.getApplicationView().statusBar;
        statusBar.changeText(typeID);

        PetriNetTab petriNetTab = ApplicationSettings.getApplicationView().getCurrentTab();
        if(petriNetTab == null)
        {
            return;
        }

        petriNetTab.getSelectionObject().disableSelection();
        // _petriNetTabView.getSelectionObject().clearSelection();

        PetriNetController petriNetController = petriNetTab.getPetriNetController();
        if((typeID != Constants.ARC) && (petriNetController.isCurrentlyCreatingArc()))
        {
            petriNetController.cancelArcCreation();
            petriNetTab.repaint();
        }

        if(typeID == Constants.SELECT)
        {
            // disable drawing to eliminate possiblity of connecting arc to
            // old coord of moved component
            statusBar.changeText(typeID);
            petriNetTab.getSelectionObject().enableSelection();
            petriNetTab.setCursorType("arrow");
        }
        else if(typeID == Constants.DRAG)
        {
            petriNetTab.setCursorType("move");
        }
        else
        {
            petriNetTab.setCursorType("crosshair");
        }
    }

}
