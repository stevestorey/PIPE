package pipe.actions.animate;

import java.awt.event.ActionEvent;

import pipe.actions.GuiAction;

public abstract class AnimateAction extends GuiAction
    {
        public AnimateAction(String name, String tooltip, String keystroke)
        {
            super(name, tooltip, keystroke);
        }

        public abstract void actionPerformed(ActionEvent event);
    }
