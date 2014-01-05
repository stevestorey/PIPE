/*
 * ArcWeightEdit.java
 */

package pipe.historyActions;

import pipe.models.component.Arc;
import pipe.models.component.Token;

/**
 * @author Alex Charalambous
 */
public class ArcWeight extends HistoryItem {

    private final Arc arc;
    private final Token token;
    private final String newWeight;
    private final String oldWeight;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ArcWeight arcWeight = (ArcWeight) o;

        if (!arc.equals(arcWeight.arc)) {
            return false;
        }
        if (!newWeight.equals(arcWeight.newWeight)) {
            return false;
        }
        if (!oldWeight.equals(arcWeight.oldWeight)) {
            return false;
        }
        if (!token.equals(arcWeight.token)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = arc.hashCode();
        result = 31 * result + token.hashCode();
        result = 31 * result + newWeight.hashCode();
        result = 31 * result + oldWeight.hashCode();
        return result;
    }

    public ArcWeight(final Arc arc, final Token token,
                     final String oldWeight, final String newWeight) {

        this.arc = arc;
        this.token = token;
        this.oldWeight = oldWeight;
        this.newWeight = newWeight;
    }

    /** */
    public void undo() {
        arc.setWeight(token, oldWeight);
    }

    /** */
    public void redo() {
        arc.setWeight(token, newWeight);
    }

}
