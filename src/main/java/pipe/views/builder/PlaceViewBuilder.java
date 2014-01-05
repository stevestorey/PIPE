package pipe.views.builder;

import java.util.LinkedList;

import pipe.controllers.PetriNetController;
import pipe.models.component.Place;
import pipe.views.MarkingView;
import pipe.views.PlaceView;

public class PlaceViewBuilder {
    private final Place place;
    private final PetriNetController controller;

    public PlaceViewBuilder(Place place, PetriNetController controller) {
        this.place = place;
        this.controller = controller;
    }

    public PlaceView build() {
        PlaceView view =
                new PlaceView(place.getId(), place.getName(), new LinkedList<MarkingView>(), place, controller);
        return view;
    }

}
