package pipe.models.strategy.arc;

import pipe.models.PetriNet;
import pipe.models.component.Arc;
import pipe.models.component.ArcType;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.utilities.math.IncidenceMatrix;

/**
 * Forwards strategy is for Transitions -> Places
 */
public class ForwardsNormalStrategy implements ArcStrategy<Transition, Place> {

    private final PetriNet petriNet;

    public ForwardsNormalStrategy(final PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    @Override
    public boolean canFire(Arc<Transition, Place> arc) {
        Place place = arc.getTarget();
        if (place.getCapacity() == 0) { // No capacity restrictions
            return true;
        }

        int totalTokensIn = 0;
        int totalTokensOut = 0;

        Transition transition = arc.getSource();
        for (Token token : arc.getTokenWeights().keySet()) {
            IncidenceMatrix forwardsIncidenceMatrix = petriNet.getForwardsIncidenceMatrix(token);
            IncidenceMatrix backwardsIncidenceMatrix = petriNet.getBackwardsIncidenceMatrix(token);

            totalTokensIn += forwardsIncidenceMatrix.get(place, transition);
            totalTokensOut += backwardsIncidenceMatrix.get(place, transition);
        }

        return (place.getNumberOfTokensStored() + totalTokensIn - totalTokensOut <= place.getCapacity());
    }


    @Override
    public ArcType getType() {
        return ArcType.NORMAL;
    }
}
