package pipe.actions.zoom;

import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import pipe.actions.GuiAction;
import pipe.gui.ApplicationSettings;
import pipe.gui.PetriNetTab;
import pipe.gui.ZoomController;
import pipe.views.PipeApplicationView;

public class ZoomInAction extends GuiAction {

    public ZoomInAction(final String name, final String tooltip, final String keystroke) {
        super(name, tooltip, keystroke);
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        PipeApplicationView pipeApplicationView = ApplicationSettings.getApplicationView();
        PetriNetTab appView = pipeApplicationView.getCurrentTab();
        ZoomController zoomer = appView.getZoomController();

        boolean zoomed = zoomer.zoomIn();

        if (zoomed) {
            JViewport currentView = ((JScrollPane) pipeApplicationView.getFrameForPetriNetTabs().getSelectedComponent()).getViewport();

            double midpointX = ZoomController.getUnzoomedValue(currentView
                    .getViewPosition().x
                    + (currentView.getWidth() * 0.5), zoomer.getPercent());
            double midpointY = ZoomController.getUnzoomedValue(currentView
                    .getViewPosition().y
                    + (currentView.getHeight() * 0.5), zoomer.getPercent());

            pipeApplicationView.updateZoomCombo();
            appView.zoomTo(new java.awt.Point((int) midpointX,
                    (int) midpointY));
        }
    }
}
