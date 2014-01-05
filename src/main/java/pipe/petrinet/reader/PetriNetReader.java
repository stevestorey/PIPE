package pipe.petrinet.reader;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pipe.common.dataLayer.StateGroup;
import pipe.models.PetriNet;
import pipe.models.component.Annotation;
import pipe.models.component.Arc;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.petrinet.reader.creator.CreatorStruct;
import pipe.petrinet.reader.creator.ElementParser;
import pipe.views.viewComponents.RateParameter;

public class PetriNetReader {

    /**
     * Map of token names to tokens. Used to find tokens for a place.
     */
    private Map<String, Token> tokens = new HashMap<String, Token>();

    private Map<String, Method> elementMethods = new HashMap<String, Method>();

    private Map<String, Transition> transitions = new HashMap<String, Transition>();
    private Map<String, Place> places = new HashMap<String, Place>();

    private Map<String, RateParameter> rates = new HashMap<String, RateParameter>();

    private final CreatorStruct creators;


    /**
     * Uses Reflection to add any methods with the ElementParser annotation
     * to the elementMethods token.
     * ElementParser.value() must equal the XML element label.
     */
    public PetriNetReader(CreatorStruct creators)
    {
        this.creators = creators;

        for (Method method : PetriNetReader.class.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(ElementParser.class))
            {
                ElementParser parser = method.getAnnotation(ElementParser.class);
                elementMethods.put(parser.value(), method);
            }
        }
    }

    public PetriNet createFromFile(PetriNet net, Document document)
    {
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getChildNodes();

        for (int i = 0; i < nodes.getLength(); i++)
        {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                String nodeName = node.getNodeName();
                if (elementMethods.containsKey(nodeName)) {
                    Method method = elementMethods.get(nodeName);
                    try {
                        method.invoke(this, (Element) node, net);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return net;
    }


    /**
     * If there are no tokens, it creates a default token for Places to use
     *
     * Then it creates a token from the element
     * @param element
     * @param net
     */
    @ElementParser("place")
    private void createPlaceAndAddToNet(Element element, PetriNet net)
    {
        if (tokens.isEmpty())
        {
            createDefaultToken(net);
        }
        creators.placeCreator.setTokens(tokens);
        Place place = creators.placeCreator.create(element);
        places.put(place.getId(), place);
        net.addPlace(place);
    }

    private void createDefaultToken(PetriNet net) {
        Token defaultToken = new Token("Default", true, 0, Color.BLACK);
        tokens.put(defaultToken.getId(), defaultToken);
        net.addToken(defaultToken);
    }

    @ElementParser("transition")
    private void createTransitionAndAddToNet(Element element, PetriNet net)
    {
        creators.transitionCreator.setRates(rates);
        Transition transition = creators.transitionCreator.create(element);
        transitions.put(transition.getId(), transition);
        net.addTransition(transition);
    }

    @ElementParser("arc")
    private void createArcAndAddToNet(Element element, PetriNet net)
    {
        creators.arcCreator.setPlaces(places);
        creators.arcCreator.setTransitions(transitions);
        creators.arcCreator.setTokens(tokens);
        Arc arc = creators.arcCreator.create(element);
        net.addArc(arc);
    }

    @ElementParser("labels")
    private void createAnnotation(Element element, PetriNet net)
    {
        Annotation annotation = creators.annotationCreator.create(element);
        net.addAnnotaiton(annotation);
    }

    @ElementParser("definition")
    private void createRateParameter(Element element, PetriNet net)
    {
        RateParameter parameter = creators.rateParameterCreator.create(element);
        rates.put(parameter.getId(), parameter);
        net.addRate(parameter);
    }

    @ElementParser("token")
    private void createToken(Element element, PetriNet net)
    {
        Token token = creators.tokenCreator.create(element);
        tokens.put(token.getId(), token);
        net.addToken(token);
    }

    @ElementParser("stategroup")
    private void createStateGroup(Element element, PetriNet net)
    {
        StateGroup group = creators.stateGroupCreator.create(element);
        net.addStateGroup(group);
    }


}
