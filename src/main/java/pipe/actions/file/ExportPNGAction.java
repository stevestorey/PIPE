package pipe.actions.file;

import java.awt.event.ActionEvent;

import pipe.gui.ApplicationSettings;
import pipe.gui.Export;
import pipe.gui.PetriNetTab;
import pipe.views.PipeApplicationView;

public class ExportPNGAction extends FileAction {
    public ExportPNGAction() {
        super("PNG", "Export the net to PNG format", "ctrl G");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PipeApplicationView view = ApplicationSettings.getApplicationView();
        PetriNetTab tab = view.getCurrentTab();
        Export.exportGuiView(tab, Export.PNG, null);
    }
}
