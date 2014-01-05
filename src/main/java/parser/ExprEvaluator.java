package parser;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import pipe.models.PetriNet;
import pipe.models.component.Place;
import pipe.models.component.Token;

public class ExprEvaluator {

    private final PetriNet petriNet;

    public ExprEvaluator(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public Double parseAndEvalExprForTransition(String expr)
            throws EvaluationException {

        String lexpr = new String(expr.replaceAll("\\s", ""));

        for (Place place : petriNet.getPlaces()) {
            lexpr = findAndReplaceCapacity(lexpr, place);
            String name = getPlaceNameRepresentation(place);
            if (lexpr.toLowerCase().contains(name.toLowerCase())) {
                int numOfToken = place.getNumberOfTokensStored();
                lexpr = findAndReplaceTokens(lexpr, name, numOfToken);
            }
        }

        Evaluator evaluator = new Evaluator();
        String result = evaluator.evaluate(lexpr);
        Double dresult = Double.parseDouble(result);
        return dresult;
    }

    /**
     * @param expr
     * @param tokenId
     * @return -1 indicates the result value is not an integer
     * @throws Exception
     */
    public int parseAndEvalExpr(String expr, String tokenId) {

        if (!validFloatAndDivision(expr)) {
            return -2;
        }

        String lexpr = new String(expr.replaceAll("\\s", ""));

        Token token = petriNet.getToken(tokenId);
        for (Place place : petriNet.getPlaces()) {
            lexpr = findAndReplaceCapacity(lexpr, place);
            String name = getPlaceNameRepresentation(place);

            if (lexpr.toLowerCase().contains(name.toLowerCase())) {
                Map<Token, Integer> tokens = place.getTokenCounts();
                int numOfToken = tokens.get(token);
                lexpr = findAndReplaceTokens(lexpr, name, numOfToken);
            }
        }

        Evaluator evaluator = new Evaluator();
        try {

            String result = evaluator.evaluate(lexpr);
            Double dresult = Double.parseDouble(result);
            return (int) Math.round(dresult);
        } catch (EvaluationException e) {
            e.printStackTrace();
            System.err.println(e.getMessage() + " Expression was " + expr);
            return -1;
        }

    }


    /**
     * @param lexpr
     * @param name       place name
     * @param numOfToken
     * @return expression with place name replaced with its number of tokens
     */
    private String findAndReplaceTokens(String lexpr, final String name,
                                        final int numOfToken) {
        do {
            lexpr = lexpr.toLowerCase()
                    .replace(name.toLowerCase(), Integer.toString(numOfToken));
        } while (lexpr.toLowerCase().contains(name.toLowerCase()));
        return lexpr;
    }

    /**
     * @param place
     * @return name of place in format by #(<name>)
     */
    private String getPlaceNameRepresentation(final Place place) {
        String name = place.getName().replaceAll("\\s", "");
        name = ("#(" + name + ")");
        return name;
    }

    /**
     * F
     *
     * @param expr
     * @param place
     * @return String with place name replaced by it's capacity. That is
     *         cap(<name>) would be replaced by cap(<capacity>) = cap(10)
     */
    private String findAndReplaceCapacity(String expr, Place place) {
        String capacityWithPlaceName =
                "cap(" + place.getName().replaceAll("\\s", "") + ")";
        if (expr.toLowerCase().contains(capacityWithPlaceName.toLowerCase())) {
            double capacity = place.getCapacity();
            expr = expr.toLowerCase()
                    .replace(capacityWithPlaceName.toLowerCase(),
                            Double.toString(capacity));
        }
        return expr;
    }

    private boolean validFloatAndDivision(String raw) {

        Pattern p = Pattern.compile(".*ceil\\(.*[0-9]*\\.+[0-9]+.*\\).*");
        Pattern p2 = Pattern.compile(".*[0-9]*\\.+[0-9]+.*");
        Matcher m = p.matcher(raw);
        Matcher m1 = p2.matcher(raw);
        Pattern p3 = Pattern.compile(".*floor\\(.*[0-9]*\\.+[0-9]+.*\\).*");
        Matcher m3 = p3.matcher(raw);

        if ((m1.find() && !m.find() && !m3.find())) {
            return false;
        }

        Pattern p1 = Pattern.compile(".*ceil\\(.*/.*\\).*");
        m = p1.matcher(raw);
        Pattern p5 = Pattern.compile(".*floor\\(.*/.*\\).*");
        m1 = p5.matcher(raw);
        if (!m.find() && !m1.find() && raw.contains("/")) {
            return false;
        }

        return true;
    }

}
