package pipe.models.visitor;

import pipe.common.dataLayer.StateGroup;
import pipe.models.component.Annotation;
import pipe.models.component.Arc;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.views.viewComponents.RateParameter;

public interface PetriNetComponentVisitor {
    public void visit(Arc<?, ?> arc);
    public void visit(Place place);
    public void visit(Transition transition);
    public void visit(Token token);
    public void visit(RateParameter parameter);
    public void visit(StateGroup group);
    public void visit(Annotation annotation);
}
