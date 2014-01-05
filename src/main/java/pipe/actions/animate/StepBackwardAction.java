package pipe.actions.animate;

import java.awt.event.ActionEvent;

import pipe.controllers.PetriNetController;
import pipe.controllers.PipeApplicationController;
import pipe.gui.Animator;
import pipe.gui.ApplicationSettings;
import pipe.models.PipeApplicationModel;

public class StepBackwardAction extends AnimateAction {
    public StepBackwardAction(final String name, final String tooltip, final String keystroke) {
        super(name, tooltip, keystroke);
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        PipeApplicationModel applicationModel = ApplicationSettings.getApplicationModel();

        PipeApplicationController controller = ApplicationSettings.getApplicationController();
        PetriNetController petriNetController = controller.getActivePetriNetController();
        Animator animator = petriNetController.getAnimator();
        animator.stepBack();

//        applicationModel.stepforwardAction.setEnabled(animator.isStepForwardAllowed());
//        applicationModel.stepbackwardAction.setEnabled(animator.isStepBackAllowed());
    }
}
