package pipe.actions.type;



public class TimedTransitionAction extends TransitionAction {

    @Override
    protected boolean isTimed() {
        return true;
    }

    public TimedTransitionAction(final String name, final int typeID,
                                 final String tooltip,
                                 final String keystroke) {
        super(name, typeID, tooltip, keystroke);
    }
}
