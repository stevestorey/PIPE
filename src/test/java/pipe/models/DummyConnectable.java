package pipe.models;

import java.awt.geom.Point2D;

import pipe.models.component.Connectable;
import pipe.models.visitor.PetriNetComponentVisitor;
import pipe.models.visitor.connectable.ConnectableVisitor;

public class DummyConnectable extends Connectable<DummyConnectable> {

    DummyConnectable(String id, String name) {
        super(id, name);
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public double getCentreX() {
        return 0;
    }

    @Override
    public double getCentreY() {
        return 0;
    }

    @Override
    public Point2D.Double getArcEdgePoint(double angle) {
        return new Point2D.Double(0,0);
    }

    @Override
    public boolean isEndPoint() {
        return true;
    }

    @Override
    public void accept(final ConnectableVisitor visitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public boolean isDraggable() {
        return false;
    }

    @Override
    public void accept(PetriNetComponentVisitor visitor) {

    }
}
