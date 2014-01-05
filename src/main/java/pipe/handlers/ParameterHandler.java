/*
 * ParameterHandler.java
 */
package pipe.handlers;

import java.awt.Container;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

import pipe.controllers.PetriNetController;
import pipe.views.viewComponents.Parameter;


public class ParameterHandler 
        extends NoteHandler
{
   
   
   public ParameterHandler(Container contentpane, Parameter parameter, PetriNetController controller) {
    //TODO: DONT PASS NULL FIX THIS
       super(null, contentpane, null, controller);
//      super(contentpane, parameter);
   }
   
   
   /** Creates the popup menu that the user will see when they right click on a
    * component */
   public JPopupMenu getPopup(MouseEvent e) {
      int index = 0;
      JPopupMenu popup = super.getPopup(e);
//      JMenuItem menuItem =
//               new JMenuItem(new EditNoteAction((Parameter) component));
//      menuItem.setText("Edit parameter");
//      popup.insert(menuItem, index++);
//
//      popup.insert(new JPopupMenu.Separator(),index);

      return popup;
   }
   
   
   public void mouseClicked(MouseEvent e) {
//      if ((e.getComponent() == component) || !e.getComponent().isEnabled()){
//         if ((SwingUtilities.isLeftMouseButton(e)) && (e.getClickCount() == 2)){
//            ((Parameter) component).enableEditMode();
//         }
//      }
   }

}
