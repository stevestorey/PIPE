package pipe.actions;

import java.awt.event.ActionEvent;

import pipe.views.PipeApplicationView;

public class ExitAction extends GuiAction {

    PipeApplicationView view;
    public ExitAction(PipeApplicationView view) {
        super("Exit", "Close the program", "ctrl Q");
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (view.checkForSaveAll()) {
            view.dispose();
            System.exit(0);
        }
    }
}
