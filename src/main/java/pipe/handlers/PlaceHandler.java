package pipe.handlers;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import pipe.controllers.PetriNetController;
import pipe.models.component.Place;
import pipe.views.PlaceView;


/**
 * Handles place actions
 */
public class PlaceHandler
        extends ConnectableHandler<Place, PlaceView> {


    public PlaceHandler(PlaceView view, Container contentpane, Place place,
                        PetriNetController controller) {
        super(view, contentpane, place, controller);
    }

    protected JPopupMenu getPopup(MouseEvent e) {
        int index = 0;
        JPopupMenu popup = super.getPopup(e);

        JMenuItem menuItem = new JMenuItem("Edit Place");
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewComponent.showEditor();
            }
        };
        menuItem.addActionListener(actionListener);
        popup.insert(menuItem, index++);
        popup.insert(new JPopupMenu.Separator(), index);

        return popup;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        //
        //        if(!ApplicationSettings.getApplicationModel().isEditionAllowed() || e.isControlDown())
        //        {
        //            return;
        //        }
        //
        //        HistoryManager historyManager = ApplicationSettings.getApplicationView().getCurrentTab().getHistoryManager();
        //        if(e.isShiftDown())
        //        {
        //            double oldCapacity = component.getCapacity();
        //            LinkedList<MarkingView> oldMarkingViews = Copier.mediumCopy(((PlaceView) component).getCurrentMarkingView());
        //
        //            double newCapacity = oldCapacity - e.getWheelRotation();
        //            if(newCapacity < 0)
        //            {
        //                newCapacity = 0;
        //            }
        //
        //            historyManager.newEdit(); // new "transaction""
        //            for(MarkingView m : oldMarkingViews)
        //            {
        //                if(m.getToken().hasSameId(
        //                        ((PlaceView) component).getActiveTokenView()))
        //                {
        //                    if((newCapacity > 0)
        //                            && (m.getCurrentMarking() > newCapacity))
        //                    {
        //                        historyManager.addEdit(((PlaceView) component)
        //                                                    .setCurrentMarking(oldMarkingViews));
        //                    }
        //                    updateArcAndTran();
        //                }
        //            }
        //
        //            historyManager.addEdit(((PlaceView) component).setCapacity(newCapacity));
        //        }
        //        else
        //        {
        //            LinkedList<MarkingView> oldMarkingViews = Copier.mediumCopy(((PlaceView) component).getCurrentMarkingView());
        //            int markingChange = e.getWheelRotation();
        //            for(MarkingView m : oldMarkingViews)
        //            {
        //                if(m.getToken().hasSameId(
        //                        ((PlaceView) component).getActiveTokenView()))
        //                {
        //                    //m.setToken(Pipe.getCurrentPetriNetView().getTokenClassFromID(m.getToken().getID()));
        //                    int oldMarking = m.getCurrentMarking();
        //                    int newMarking = m.getCurrentMarking() - markingChange;
        //                    if(newMarking < 0)
        //                    {
        //                        newMarking = 0;
        //                    }
        //                    if(oldMarking != newMarking)
        //                    {
        //                        m.setCurrentMarking(newMarking);
        //                        historyManager.addNewEdit(((PlaceView) component)
        //                                                       .setCurrentMarking(oldMarkingViews));
        //                    }
        //                    updateArcAndTran();
        //                    break;
        //                }
        //
        //            }
        //        }
    }
}
