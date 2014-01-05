package pipe.models.visitor;

import pipe.common.dataLayer.StateGroup;
import pipe.models.PetriNet;
import pipe.models.component.Annotation;
import pipe.models.component.Arc;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.views.viewComponents.RateParameter;

public class PetriNetComponentAddVisitor implements PetriNetComponentVisitor {
    private PetriNet petriNet;

    public PetriNetComponentAddVisitor(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    @Override
    public void visit(Arc<?, ?> arc) {
       petriNet.addArc(arc);
    }

    @Override
    public void visit(Place place) {
        petriNet.addPlace(place);
    }

    @Override
    public void visit(Transition transition) {
        petriNet.addTransition(transition);
    }

    @Override
    public void visit(Token token) {
        petriNet.addToken(token);
    }

    @Override
    public void visit(RateParameter parameter) {
        petriNet.addRate(parameter);
    }

    @Override
    public void visit(StateGroup group) {
        petriNet.addStateGroup(group);
    }

    @Override
    public void visit(Annotation annotation) {
        petriNet.addAnnotaiton(annotation);
    }
}
