package pipe.models.component;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Map;

import parser.ExprEvaluator;
import pipe.gui.Constants;
import pipe.models.visitor.PetriNetComponentVisitor;
import pipe.models.visitor.connectable.ConnectableVisitor;
import pipe.views.viewComponents.RateParameter;


public class Transition extends Connectable<Transition> implements Serializable
{

    @Pnml("priority")
    private int priority;

    @Pnml("rate")
	private String rateExpr;

    private int orientation = 0;

    @Pnml("timed")
    private boolean timed = false;

    @Pnml("infiniteServer")
    private boolean infiniteServer = false;

    @Pnml("angle")
    private int angle = 0;

    private RateParameter rateParameter;

    public static final int TRANSITION_HEIGHT = Constants.PLACE_TRANSITION_HEIGHT;
    public static final int TRANSITION_WIDTH = TRANSITION_HEIGHT / 3;
    private static final double ROOT_THREE_OVER_TWO = 0.5 * Math.sqrt(3);
    private boolean enabled = false;

    public Transition(String id, String name)
    {
        this(id, name, "1", 1);
    }

    @Override
    public int getHeight() {
        return TRANSITION_HEIGHT;
    }

    @Override
    public int getWidth() {
        return TRANSITION_WIDTH;
    }

    @Override
    public double getCentreX() {
        return getX() + getHeight()/2;
    }

    @Override
    public double getCentreY() {
        return getY() + getHeight()/2;
    }

    /**
     * Rotates point on transition around transition center
     * @param angle
     * @param point
     * @return
     */
    private Point2D.Double rotateAroundCenter(double angle, Point2D.Double point)
    {
        AffineTransform tx = new AffineTransform();
        tx.rotate(angle, getCentreX(), getCentreY());
        Point2D.Double rotatedPoint = new Point2D.Double();
        tx.transform(point, rotatedPoint);
        return rotatedPoint;
    }

    @Override
    public Point2D.Double getArcEdgePoint(double angle) {
        double half_height = getHeight()/2;
        double centre_x = x + half_height; //Use height since the actual object is a square, width is just the displayed width
        double centre_y = y + half_height;

        Point2D.Double connectionPoint = new Point2D.Double(centre_x, centre_y);

        double half_width = getWidth()/2;
        double rotatedAngle = angle + Math.toRadians(this.angle);
        if (connectToTop(rotatedAngle)) {
            connectionPoint.y -= half_height;
        } else if (connectToBottom(rotatedAngle)) {
            connectionPoint.y += half_height;
        } else if (connectToLeft(rotatedAngle)) {
            connectionPoint.x -= half_width;
        } else { //connectToRight
            connectionPoint.x += half_width;
        }

        return rotateAroundCenter(Math.toRadians(this.angle), connectionPoint);
    }

    @Override
    public boolean isEndPoint() {
        return true;
    }

    @Override
    public void accept(final ConnectableVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the left
     * @param angle in radians
     * @return
     */
    private boolean connectToLeft(double angle) {
        return (Math.sin(angle) > 0);
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the bottom
     * @param angle in radians
     * @return
     */
    private boolean connectToBottom(double angle) {
        return Math.cos(angle) < -ROOT_THREE_OVER_TWO;
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the top
     * @param angle in radians
     * @return
     */
    private boolean connectToTop(double angle) {
        return Math.cos(angle) > ROOT_THREE_OVER_TWO;
    }

    public Transition(String id, String name, String rateExpr, int priority)
    {
        super(id, name);
        this.rateExpr =rateExpr;
        this.priority = priority;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
        notifyObservers();
    }

	public String getRateExpr() {
		return rateExpr;
	}

	public void setRateExpr(String string) {
		rateExpr = string;
        notifyObservers();
	}
	public void setRateExpr(double expr) {
		rateExpr = Double.toString(expr);
        notifyObservers();
	}

    public int getAngle() {
        return angle;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean isTimed() {
        return timed;
    }

    public boolean isInfiniteServer() {
        return infiniteServer;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        notifyObservers();
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        notifyObservers();
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
        notifyObservers();
    }

    public void setInfiniteServer(boolean infiniteServer) {
        this.infiniteServer = infiniteServer;
        notifyObservers();
    }



    public void setAngle(int angle) {
        this.angle = angle;
        notifyObservers();
    }

    public RateParameter getRateParameter() {
        return rateParameter;
    }

    public void setRateParameter(RateParameter rateParameter) {
        this.rateParameter = rateParameter;
        notifyObservers();
    }

    @Override
    public boolean isSelectable() {
        return  true;
    }

    @Override
    public boolean isDraggable() {
        return true;
    }

    @Override
    public void accept(PetriNetComponentVisitor visitor) {
        visitor.visit(this);
    }

    public void enable() {
        enabled = true;
        notifyObservers();
    }

    public void disable() {
        enabled = false;
        notifyObservers();
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * A Transition is enabled if all its input places are marked with at least one token
     * This method calculates the minimium number of tokens needed in order for a transition to be enabeld
     *
     * @param evaluator used to evaluate an arcs expression in the petrinet the
     *                  transition belongs to.
     * @return
     */
    public int getEnablingDegree(ExprEvaluator evaluator) {

        int enablingDegree = Integer.MAX_VALUE;


        for (Arc<?, Transition> arc : inboundArcs()) {
            Place place = (Place) arc.getSource();
            Map<Token, String> arcWeights = arc.getTokenWeights();
            for (Map.Entry<Token, String> entry : arcWeights.entrySet()) {
                Token arcToken = entry.getKey();
                String arcTokenExpression = entry.getValue();

                int placeTokenCount = place.getTokenCount(arcToken);
                int requiredTokenCount = evaluator.parseAndEvalExpr(arcTokenExpression, arcToken.getId());

                if (requiredTokenCount == 0) {
                    enablingDegree = 0;
                } else {
                    //TODO: WHY DIVIDE?
                    int currentDegree = (int) Math.floor(placeTokenCount / requiredTokenCount);
                    if (currentDegree < enablingDegree) {
                        enablingDegree = currentDegree;
                    }

                }
            }
        }
        return enablingDegree;
    }
}
