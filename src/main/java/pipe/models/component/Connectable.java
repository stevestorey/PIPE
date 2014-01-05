package pipe.models.component;

import pipe.models.visitor.connectable.ConnectableVisitor;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
/**
 *
 * @param <S> class that T connects to type
 * @param <T> current class type
 */
public abstract class Connectable<T extends Connectable<T>> extends AbstractPetriNetComponent {
    protected final Collection<Arc<?, T>> inboundArcs = new HashSet<Arc<?, T>>();
    protected final Collection<Arc<T, ?>> outboundArcs = new HashSet<Arc<T, ?>>();

    /**
     * Connectable position x
     */
    @Pnml("positionX")
    protected double x = 0;

    /**
     * Connectable position y
     */
    @Pnml("positionY")
    protected double y = 0;

    /**
     * Connectable id
     */
    @Pnml("id")
    protected String id;

    /**
     * Connectable name
     */
    @Pnml("name")
    protected String name;

    /**
     * Connectable name x offset relative to its x coordinate
     */
    @Pnml("nameOffsetX")
    protected double nameXOffset = -5;

    /**
     * Connectable name y offset relative to its y coordinate
     */
    @Pnml("nameOffsetY")
    protected double nameYOffset = 35;

    protected Connectable(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public double getNameXOffset() {
        return nameXOffset;
    }

    public double getNameYOffset() {
        return nameYOffset;
    }

    public void setNameXOffset(double nameXOffset) {
        this.nameXOffset = nameXOffset;
        notifyObservers();
    }

    public void setNameYOffset(double nameYOffset) {
        this.nameYOffset = nameYOffset;
        notifyObservers();
    }

    public Collection<Arc<T, ?>> outboundArcs() {
        return outboundArcs;
    }

    public Collection<Arc<?, T>> inboundArcs() {
        return inboundArcs;
    }

    public void addInbound(Arc<?, T> arc) {
        inboundArcs.add(arc);
    }

    public void addOutbound(Arc<T, ?> arc) {
        outboundArcs.add(arc);
    }

//    public void addInboundOrOutbound(ArcView newArcView) {
//        if (newArcView.getSource().getModel() == this) {
//            outboundArcs.add(newArcView);
//        } else {
//            inboundArcs.add(newArcView);
//        }
//    }

    public void removeOutboundArc(Arc<T, ?> arc) {
        outboundArcs.remove(arc);
    }

    public void removeInboundArc(Arc<?, T> arc) {
        inboundArcs.remove(arc);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        notifyObservers();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        notifyObservers();
    }

    public void setCentre(double x, double y) {
        setX(x - (getWidth() / 2.0));
        setY(y - (getHeight() / 2.0));
    }

    public abstract int getHeight();

    public abstract int getWidth();

    public abstract double getCentreX();

    public abstract double getCentreY();

    /**
     * @return coords for an arc to connect to
     *         <p/>
     *         x, y are the top left corner so A
     *         would return (4, 1) and B would
     *         return (14, 1)
     *         <p/>
     *         +---+         +---+
     *         | A |-------->| B |
     *         +---+         +---+
     */
    public abstract Point2D.Double getArcEdgePoint(double angle);

    /**
     * @return true if the arc can finish at this point.
     *         I.e it is not a temporary connectable
     */
    public abstract boolean isEndPoint();

    public void setId(String id) {
        this.id = id;
        notifyObservers();
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public abstract void accept(ConnectableVisitor visitor);

    @Override
    public String toString() {
        return id;
    }

}
