package pipe.actions.file;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import pipe.gui.ApplicationSettings;
import pipe.views.PipeApplicationView;

public class CloseAction extends FileAction {
    public CloseAction() {
        super("Close", "Close the current tab", "ctrl W");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final PipeApplicationView view = ApplicationSettings.getApplicationView();
        final JTabbedPane appTab = view.getFrameForPetriNetTabs();
        view.removeTab(appTab.getSelectedIndex());

        if( (appTab.getTabCount() > 0) ){
            appTab.remove(appTab.getSelectedIndex());
        }
    }
}
