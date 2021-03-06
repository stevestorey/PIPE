/*
 * Created on 07-Mar-2004
 * Author is Michael Camacho
 */
package pipe.actions;

import pipe.controllers.PetriNetController;
import pipe.controllers.PipeApplicationController;
import pipe.gui.ApplicationSettings;
import pipe.views.viewComponents.AnnotationNote;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class EditAnnotationBorderAction
        extends AbstractAction {

   private final AnnotationNote selected;
   

   public EditAnnotationBorderAction(AnnotationNote component) {
      selected = component;
   }

      
   /** Action for editing the text in an AnnotationNote */
   public void actionPerformed(ActionEvent e) {

       PipeApplicationController controller = ApplicationSettings.getApplicationController();
       PetriNetController petriNetController = controller.getActivePetriNetController();
       petriNetController.getHistoryManager().addNewEdit(
               selected.showBorder(!selected.isShowingBorder()));
   }

}
