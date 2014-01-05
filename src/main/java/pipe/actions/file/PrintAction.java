package pipe.actions.file;

import java.awt.event.ActionEvent;

import pipe.gui.ApplicationSettings;
import pipe.gui.Export;
import pipe.gui.PetriNetTab;
import pipe.views.PipeApplicationView;

public class PrintAction extends FileAction {
    public PrintAction() {
        super("Print", "Print", "ctrl P");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PipeApplicationView view = ApplicationSettings.getApplicationView();
        PetriNetTab tab = view.getCurrentTab();
        Export.exportGuiView(tab, Export.PRINTER, null);
    }
}
