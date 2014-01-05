package pipe.actions;

import java.awt.event.ActionEvent;

import pipe.views.PipeApplicationView;

class ValidateAction extends GuiAction
{
    private final PipeApplicationView _pipeApplicationView;

    ValidateAction(String name, String tooltip, String keystroke, PipeApplicationView pipeApplicationView)
    {
        super(name, tooltip, keystroke);
        _pipeApplicationView = pipeApplicationView;
    }

    public void actionPerformed(ActionEvent e)
    {
        _pipeApplicationView.getCurrentPetriNetView().validTagStructure();
    }
}
