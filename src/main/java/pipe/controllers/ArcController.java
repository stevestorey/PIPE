package pipe.controllers;

import java.util.Map;

import pipe.historyActions.ArcWeight;
import pipe.historyActions.HistoryManager;
import pipe.models.component.Arc;
import pipe.models.component.Connectable;
import pipe.models.component.Token;

public class ArcController<S extends Connectable<S>, T extends Connectable<T>> extends AbstractPetriNetComponentController<Arc<S, T>>
{
    private final Arc<S, T> arc;
    private final HistoryManager historyManager;

    ArcController(Arc<S, T> arc, HistoryManager historyManager) {

        super(arc, historyManager);
        this.arc = arc;
        this.historyManager = historyManager;
    }

    /**
     * Sets the weight for the current arc
     * @param token
     * @param expr
     */
    public void setWeight(final Token token, final String expr) {
        historyManager.newEdit();
        updateWeightForArc(token, expr);
    }

    /**
     * Creates a historyItem for updating weight and applies it
     * @param token
     * @param expr
     */
    private void updateWeightForArc(final Token token,
                                    final String expr) {
        String oldWeight = arc.getWeightForToken(token);
        ArcWeight weightAction = new ArcWeight(arc, token, oldWeight, expr);
        weightAction.redo();
        historyManager.addEdit(weightAction);
    }

    public void setWeights(final Map<Token, String> newWeights) {
        historyManager.newEdit();
        for (Map.Entry<Token, String> entry : newWeights.entrySet()) {
            updateWeightForArc(entry.getKey(), entry.getValue());
        }
    }

    public String getWeightForToken(final Token token) {
        return arc.getWeightForToken(token);
    }

    public boolean hasFunctionalWeight() {
        return arc.hasFunctionalWeight();
    }

    public T getTarget() {
        return arc.getTarget();
    }
}
