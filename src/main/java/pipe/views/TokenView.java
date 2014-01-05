package pipe.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.Serializable;
import java.util.Collection;
import java.util.Observable;

import pipe.controllers.TokenController;
import pipe.exceptions.TokenLockedException;
import pipe.models.component.Arc;
import pipe.models.component.Place;
import pipe.models.component.Token;
import pipe.models.component.Transition;
import pipe.models.interfaces.IObserver;
import pipe.utilities.math.Matrix;


public class TokenView extends Observable implements Serializable, IObserver {
    private Token _model;  // Steve Doubleday was final, but changed for replace(tokenView)
    private TokenController _controller;
    private Matrix previousIncidenceMatrix;

    public TokenView(TokenController controller, Token model) {
        _controller = controller;
        _model = model;
        _model.registerObserver(this);
    }

    public TokenView(boolean enabled, String id, Color color) {
        _model = new Token(id, enabled, 0, color);
    }

    public Color getColor() {
        return _model.getColor();
    }

    public void setColor(Color colour) {
        _model.setColor(colour);
    }

    void paint(Graphics canvas, Insets insets, int offset, int tempTotalMarking, int currentMarking) {
        if (tempTotalMarking > 5) {
            paintAsANumber(canvas, insets, offset, currentMarking);
        } else {
            paintAsAnOval(canvas, insets, tempTotalMarking, currentMarking);
        }
    }

    void paintAsAnOval(Graphics canvas, Insets insets, int tempTotalMarking, int currentMarking) {
        int x = insets.left;
        int y = insets.top;
        canvas.setColor(getColor());
        int WIDTH = 4;
        int HEIGHT = 4;
        for (int i = 0; i < currentMarking; i++) {

            switch (tempTotalMarking) {
                case 5:
                    canvas.drawOval(x + 6, y + 6, WIDTH, HEIGHT);
                    canvas.fillOval(x + 6, y + 6, WIDTH, HEIGHT);
                    break;
                case 4:
                    canvas.drawOval(x + 18, y + 20, WIDTH, HEIGHT);
                    canvas.fillOval(x + 18, y + 20, WIDTH, HEIGHT);
                    break;
                case 3:
                    canvas.drawOval(x + 6, y + 20, WIDTH, HEIGHT);
                    canvas.fillOval(x + 6, y + 20, WIDTH, HEIGHT);
                    break;
                case 2:
                    canvas.drawOval(x + 18, y + 6, WIDTH, HEIGHT);
                    canvas.fillOval(x + 18, y + 6, WIDTH, HEIGHT);
                    break;
                case 1:
                    canvas.drawOval(x + 12, y + 13, WIDTH, HEIGHT);
                    canvas.fillOval(x + 12, y + 13, WIDTH, HEIGHT);
                    break;
                case 0:
                    break;
                default:
                    break;
            }
            tempTotalMarking--;
        }
    }

    void paintAsANumber(Graphics canvas, Insets insets, int offset, int currentMarking) {
        int x = insets.left;
        int y = insets.top;
        canvas.setColor(getColor());
        if (currentMarking > 999) {
            canvas.drawString(String.valueOf(currentMarking), x, y + 10 + offset);
        } else if (currentMarking > 99) {
            canvas.drawString(String.valueOf(currentMarking), x + 3, y + 10 + offset);
        } else if (currentMarking > 9) {
            canvas.drawString(String.valueOf(currentMarking), x + 7, y + 10 + offset);
        } else if (currentMarking != 0) {
            canvas.drawString(String.valueOf(currentMarking), x + 12, y + 10 + offset);
        }
    }


    @Override
    public void update() {
        //paint
    }

    public void update(Graphics canvas, Insets insets, int offset, int tempTotalMarking, int currentMarking) {
        paint(canvas, insets, offset, tempTotalMarking, currentMarking);
    }

    public Token getModel() {
        return _model;
    }

    public void setCurrentMarking(int marking) {
        _model.setCurrentMarking(marking);
    }

    public int getCurrentMarking() {
        return _model.getCurrentMarking();
    }

    public Matrix getPreviousIncidenceMatrix() {
        return previousIncidenceMatrix;
    }

    public void incrementLock() {
        _model.incrementLock();
    }

    public void decrementLock() {
        _model.decrementLock();
    }

    public boolean isLocked() {
        return _model.isLocked();
    }

    public int getLockCount() {
        return _model.getLockCount();
    }

    public void setLockCount(int newLockCount) {
        _model.setLockCount(newLockCount);
    }

    public boolean isEnabled() {
        return _model.isEnabled();
    }

    /**
     * Disabling a TokenView should be done through disableAndNotifyObservers()
     *
     * @param enabled
     * @throws TokenLockedException
     */
    protected void setEnabled(boolean enabled) throws TokenLockedException {
        _model.setEnabled(enabled);
    }

    public String getID() {
        return _model.getId();
    }

    public void setID(String id) {
        _model.setId(id);
    }

    public boolean hasSameId(TokenView otherTokenView) {
        return otherTokenView.getID().equals(getID());
    }

    public void createIncidenceMatrix(Collection<ArcView> arcsArray, Collection<TransitionView> transitionsArray,
            Collection<PlaceView> placesArray) {
//        _model.createIncidenceMatrix(arcsArray, transitionsArray, placesArray);
    }

    public void createInhibitionMatrix(Collection<InhibitorArcView> inhibitorsArray,
            Collection<TransitionView> transitionsArray, Collection<PlaceView> placesArray) {
//        _model.createInhibitionMatrix(inhibitorsArray, transitionsArray, placesArray);
    }

    protected String getNormalizedID() {
        return normalize(getID());
    }

    private String normalize(String target) {
        if (target == null) {
            return "";
        } else {
            return target.trim().toLowerCase();
        }
    }

    /**
     * Sets enabled = false, and notifies any observers.  Observers should delete references to this TokenView.
     *
     * @throws TokenLockedException
     */
    public void disableAndNotifyObservers() throws TokenLockedException {
        setEnabled(false);
        setChanged();
        notifyObservers(null);
    }

    /**
     * Update the Token model in this TokenView from the argument tokenView.
     * <p/>
     * Used to preserve updates in this TokenView to ID and Color, while re-using the Token model from the argument tokenView.  Observers on the argument tokenView are notified so that they may update their reference to point to this tokenView.
     * <p/>
     * Use case:  If a set of new TokenViews replace existing TokenViews (TokenSetController#updateOrReplaceTokenViews(List<TokenView>)), each tokenView in the argument list will replace its counterpart in the original list.
     * <p/>
     * If this tokenView is disabled and the source TokenView is locked, TokenLockedException is thrown.
     * If this tokenView is disabled and the source TokenView is not locked, observers are notified with null, indicating that the argument tokenView is no longer valid and is not being replaced; observers should update their reference to null.
     *
     * @param tokenView
     * @throws TokenLockedException
     */
    //TODO consider use cases for this method; currently only used in tests, so should probably be deleted.
    public void updateModelFromPrevious(TokenView tokenView) throws TokenLockedException {
        boolean enabled = isEnabled();
        String ID = getID();
        Color color = getColor();
        this._model = tokenView._model;
        this.previousIncidenceMatrix = tokenView.previousIncidenceMatrix;
        setID(ID);
        setEnabled(enabled);
        setColor(color);
        tokenView.setChanged();
        if (enabled) {
            tokenView.notifyObservers(this);
        } else {
            tokenView.notifyObservers(null);
        }
    }

    /**
     * Returns false if this TokenView is invalid.
     * An invalid TokenView is disabled (isEnabled() == false), with a blank or null Id.
     * <p/>
     * All other TokenViews are valid.
     *
     * @return
     */
    public boolean isValid() {
        if ((getNormalizedID().equals("")) && (!isEnabled())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TokenView: Enabled=");
        builder.append(_model.isEnabled());
        builder.append(", Id=");
        builder.append(_model.getId());
        builder.append(", Color=");
        builder.append(_model.getColor());
        builder.append(", Lock count=");
        builder.append(_model.getLockCount());
        return builder.toString();
    }

    //TODO: DELETE STUB
    public int[][] getBackwardsIncidenceMatrix(final Collection<Arc<?, ?>> arcs, final Collection<Transition> transitions, final Collection<Place> places) {
        return new int[0][];  //To change body of created methods use File | Settings | File Templates.
    }

    //TODO: DELETE STUB
    public int[][] getForwardsIncidenceMatrix(final Collection<Arc<?, ?>> arcs, final Collection<Transition> transitions, final Collection<Place> places) {
        return new int[0][];  //To change body of created methods use File | Settings | File Templates.
    }

    //TODO: DELETE STUB
    public Matrix getInhibitionMatrix(final Collection<InhibitorArcView> inhibitorsArrayList, final Collection<TransitionView> transitionsArrayList, final Collection<PlaceView> placesArrayList) {
        return null;
    }

    //TODO: DELETE STUB
    public int[][] getIncidenceMatrix(final Collection<Arc<?, ?>> arcs, final Collection<Transition> transitions, final Collection<Place> places) {
        return new int[0][];  //To change body of created methods use File | Settings | File Templates.
    }
}
