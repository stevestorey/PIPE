package pipe.models.visitor.connectable.arc;

import pipe.models.component.ConditionalPlace;
import pipe.models.component.Connectable;
import pipe.models.component.Place;
import pipe.models.component.TemporaryArcTarget;
import pipe.models.component.Transition;

/**
 * A class to determine if an inhibitor arc can be built from the source
 */
public class InhibitorSourceVisitor implements ArcSourceVisitor {

    boolean canCreate = false;

    @Override
    public void visit(final Place place) {
        canCreate = true;
    }

    @Override
    public void visit(final Transition transition) {
        canCreate = false;
    }

    @Override
    public void visit(final TemporaryArcTarget arcTarget) {
        canCreate = false;
    }

    @Override
    public void visit(final ConditionalPlace conditionalPlace) {
        canCreate = false;
    }

    /**
     *
     * @return the result of the last item visited
     */
    @Override
    public boolean canCreate(Connectable<?> connectable) {
        connectable.accept(this);
        return canCreate;
    }
}
