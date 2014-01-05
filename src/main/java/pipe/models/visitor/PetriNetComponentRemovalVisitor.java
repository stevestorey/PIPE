package pipe.models.visitor;

import pipe.common.dataLayer.StateGroup;
import pipe.models.PetriNet;
import pipe.models.component.Annotation;
import pipe.models.component.Arc;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.views.viewComponents.RateParameter;

public class PetriNetComponentRemovalVisitor implements PetriNetComponentVisitor {
    private final PetriNet net;

    public PetriNetComponentRemovalVisitor(PetriNet net) {
        this.net = net;
    }

    @Override
    public void visit(Arc<?, ?> arc) {
        net.removeArc(arc);
    }

    @Override
    public void visit(Place place) {
        net.removePlace(place);
    }

    @Override
    public void visit(Transition transition) {
       net.removeTransition(transition);
    }

    @Override
    public void visit(Token token) {
       net.removeToken(token);
    }

    @Override
    public void visit(RateParameter parameter) {
        net.removeRateParameter(parameter);
    }

    @Override
    public void visit(StateGroup group) {
        net.removeStateGroup(group);
    }

    @Override
    public void visit(Annotation annotation) {
        net.removeAnnotaiton(annotation);
    }
}
