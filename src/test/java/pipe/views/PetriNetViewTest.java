package pipe.views;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.xml.transform.TransformerFactoryConfigurationError;

import matchers.component.HasModel;
import org.junit.Before;
import org.junit.Test;

import pipe.models.PetriNet;
import pipe.models.component.Place;
import pipe.utilities.transformers.PNMLTransformer;
import pipe.utilities.transformers.PNMLTransformerTest;

import java.util.Observer;

public class PetriNetViewTest
{

	private PetriNetView petriNetView;
	private PlaceView placeView;
	private MarkingView markingView;

	@Before
	public void setUp() throws Exception
	{
		petriNetView = new PetriNetView(null, new PetriNet());
	}
	@Test
	public void verifyAnimationModePossibleForNewPetriNewViewWhenNetFileHasToken() throws Exception
	{
		checkAnimationModePossibleForNewPetriNet(PNMLTransformerTest.SIMPLE_NET_WITH_TOKEN); 
	}
	@Test
	public void verifyAnimationModePossibleForNewPetriNetViewWhenNetFileDoesNotHaveToken() throws Exception
	{
		checkAnimationModePossibleForNewPetriNet(PNMLTransformerTest.SIMPLE_NET_WITHOUT_TOKEN); 
	}
	protected void checkAnimationModePossibleForNewPetriNet(String net)
			throws TransformerFactoryConfigurationError
	{
//		assertNotNull("not null at creation",petriNetView.getTokenViews());
//		buildPetriNetViewFromXmlString(net);
//		assertNotNull("emptied, but token set controller re-created",petriNetView.getTokenViews());
//		assertNull("no matrices created yet",petriNetView.getTokenViews().get(0).getForwardsIncidenceMatrix());
//		petriNetView.setEnabledTransitions();
//		assertNotNull("matrices should be created now",petriNetView.getTokenViews().get(0).getForwardsIncidenceMatrix());
	}
	protected void buildPetriNetViewFromXmlString(String net)
			throws TransformerFactoryConfigurationError
	{
		PNMLTransformer transformer = new PNMLTransformer(); 
		petriNetView.createFromPNML(transformer.transformPNMLStreamSource(PNMLTransformerTest
				.getNetAsStreamSource(net)));
	}

    //TODO: THis logic should not be in here
	@Test
	public void verifyInitialPlaceMarkingIgnoredIfCorrespondingTokenDoesNotExist() throws Exception
	{
		buildPetriNetViewFromXmlString(PNMLTransformerTest.ONE_TOKEN_TWO_INITIAL_MARKINGS);
		assertEquals(1, petriNetView.getTokenViews().size());
		assertEquals(1, petriNetView.getPlacesArrayList().size());
		placeView = petriNetView.getPlacesArrayList().iterator().next();
		assertEquals("only one MarkingView created",1, placeView.getCurrentMarkingView().size());
		markingView = placeView.getCurrentMarkingView().get(0); 
		assertEquals(petriNetView.getTokenViews().get(0), markingView.getToken());
	}

    //TODO: This logic should not be in here, its tested elsewhere in the model
	@Test
	public void verifyNonzeroInitialMarkingLocksCorrespondingTokenView() throws Exception
	{
		buildPetriNetViewFromXmlString(PNMLTransformerTest.TWO_TOKENS_TWO_INITIAL_MARKINGS_ONE_NONZERO);
		assertEquals(2, petriNetView.getTokenViews().size());
		assertEquals(1, petriNetView.getPlacesArrayList().size());
		placeView = petriNetView.getPlacesArrayList().iterator().next();
		assertEquals("two MarkingViews",2, placeView.getCurrentMarkingView().size());
		markingView = placeView.getCurrentMarkingView().get(0);
		assertEquals(0, markingView.getCurrentMarking()); 
		assertFalse(markingView.getToken().isLocked());
		assertEquals(petriNetView.getTokenViews().get(0),markingView.getToken());
		markingView = placeView.getCurrentMarkingView().get(1);
		assertEquals(1, markingView.getCurrentMarking()); 
		assertTrue(markingView.getToken().isLocked());
		assertEquals(petriNetView.getTokenViews().get(1),markingView.getToken());
	}

	@Test
	public void verifyDefaultTokenViewCreatedDuringConstruction() throws Exception
	{
		assertEquals("Default created",1, petriNetView.getTokenViews().size());
		assertEquals("Default", petriNetView.getTokenViews().get(0).getID());
	}

    @Test
    public void displaysPlaceOnTab()
    {
        Place place = new Place("id", "name");
        PetriNet net = new PetriNet();
        net.addPlace(place);

        PetriNetView view = new PetriNetView(null, net);
        Observer mockObserver = mock(Observer.class);
        view.addObserver(mockObserver);

        view.update();
        verify(mockObserver).update(eq(view), argThat(new HasModel<Place, PlaceView>(place)));
    }
}
