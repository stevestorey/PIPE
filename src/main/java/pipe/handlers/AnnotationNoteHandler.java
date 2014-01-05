 /*
 * Created on 05-Mar-2004
 * Author is Michael Camacho
 *
 */
 package pipe.handlers;

 import java.awt.Container;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import pipe.controllers.PetriNetController;
import pipe.models.component.Annotation;
import pipe.views.viewComponents.AnnotationNote;


 public class AnnotationNoteHandler
        extends NoteHandler
 {
   

   public AnnotationNoteHandler(AnnotationNote view, Container contentpane, Annotation note, PetriNetController controller) {
      super(view, contentpane, note, controller);
      enablePopup = true;
   }

   
   /** 
    * Creates the popup menu that the user will see when they right click on a 
    * component */
   public JPopupMenu getPopup(MouseEvent e) {
      int popupIndex = 0;
      JPopupMenu popup = super.getPopup(e);
      
//      JMenuItem menuItem =
//              new JMenuItem(new EditNoteAction((AnnotationNote) component));
//      menuItem.setText("Edit text");
//      popup.insert(menuItem, popupIndex++);
//
//      menuItem = new JMenuItem(
//              new EditAnnotationBorderAction((AnnotationNote) component));
//      if (((AnnotationNote) component).isShowingBorder()){
//         menuItem.setText("Disable Border");
//      } else{
//         menuItem.setText("Enable Border");
//      }
//      popup.insert(menuItem, popupIndex++);
//
//      menuItem = new JMenuItem(
//              new EditAnnotationBackgroundAction((AnnotationNote) component));
//      if (((AnnotationNote) component).isFilled()) {
//         menuItem.setText("Transparent");
//      } else {
//         menuItem.setText("Solid Background");
//      }
//      popup.insert(new JPopupMenu.Separator(), popupIndex++);
//      popup.insert(menuItem, popupIndex);

      return popup;
   }

   
   public void mouseClicked(MouseEvent e) {
//      if ((e.getComponent() == component || !e.getComponent().isEnabled()) &&
//              (SwingUtilities.isLeftMouseButton(e))) {
//         if (e.getClickCount() == 2){
//            ((AnnotationNote) component).enableEditMode();
//         }
//      }
   }
   
}
