package pipe.controllers;

import java.util.Collection;

import pipe.historyActions.HistoryManager;
import pipe.historyActions.TransitionInfiniteServer;
import pipe.historyActions.TransitionPriority;
import pipe.historyActions.TransitionRotation;
import pipe.historyActions.TransitionTiming;
import pipe.models.component.Arc;
import pipe.models.component.Transition;
import pipe.views.viewComponents.RateParameter;

public class TransitionController extends AbstractPetriNetComponentController<Transition>
{

    protected TransitionController(Transition component,
                                   HistoryManager historyManager) {
        super(component, historyManager);
    }

    public boolean isTimed() {
        return component.isTimed();
    }

    public boolean isInfiniteServer() {
        return component.isInfiniteServer();
    }

    public RateParameter getRateParameter() {
        return component.getRateParameter();
    }

    public String getName() {
        return component.getName();
    }

    public String getRateExpr() {
        return component.getRateExpr();
    }

    public int getPriority() {
        return component.getPriority();
    }

    public Collection<Arc<?, Transition>> inboundArcs() {
        return component.inboundArcs();
    }

    /**
     *
     * @param infiniteValue
     */
    public void setInfiniteServer(
                                  final boolean infiniteValue) {
        TransitionInfiniteServer infiniteAction = new TransitionInfiniteServer(component, infiniteValue);
        infiniteAction.redo();
        historyManager.addNewEdit(infiniteAction);
    }

    public void setTimed(final boolean timedValue) {
        TransitionTiming timedAction = new TransitionTiming(component, timedValue);
        timedAction.redo();
        historyManager.addNewEdit(timedAction);
    }

    public void setPriority(
                            final int priorityValue) {
        int oldPriority = component.getPriority();
        TransitionPriority priorityAction = new TransitionPriority(component, oldPriority, priorityValue);
        priorityAction.redo();
        historyManager.addNewEdit(priorityAction);
    }

    public void setAngle(final int angle) {
        int oldAngle = component.getAngle();
        TransitionRotation angleAction = new TransitionRotation(component, oldAngle, angle);
        angleAction.redo();
        historyManager.addNewEdit(angleAction);
    }
}
