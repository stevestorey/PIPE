package pipe.gui.widgets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import pipe.views.viewComponents.AnnotationNote;

/*
 * ParameterPanel.java
 *
 * Created on April 15, 2007, 9:25 AM
 */

/**
 * @author  Pere Bonet
 */
public class AnnotationPanel extends javax.swing.JPanel {
   
   private final AnnotationNote annotation;
   
   
   /**
    * Creates new form ParameterPanel
    * @param _annotation
    */
   public AnnotationPanel(AnnotationNote _annotation) {
      annotation = _annotation;
      initComponents();
      textArea.setText(annotation.getText());
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
   private void initComponents() {
      java.awt.GridBagConstraints gridBagConstraints;

       JPanel panel = new JPanel();
       JScrollPane jScrollPane1 = new JScrollPane();
      textArea = new javax.swing.JTextArea();
       JPanel buttonPanel = new JPanel();
       JButton okButton = new JButton();
       JButton cancelButton = new JButton();

      setLayout(new java.awt.GridBagLayout());

      setMaximumSize(new java.awt.Dimension(239, 208));
      setMinimumSize(new java.awt.Dimension(239, 208));
      panel.setLayout(new java.awt.GridLayout(1, 0));

      panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edit Annotation"));
      textArea.setColumns(20);
      textArea.setRows(5);
      jScrollPane1.setViewportView(textArea);

      panel.add(jScrollPane1);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
      gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
      add(panel, gridBagConstraints);

      buttonPanel.setLayout(new java.awt.GridBagLayout());

      okButton.setText("OK");
      okButton.setMaximumSize(new java.awt.Dimension(75, 25));
      okButton.setMinimumSize(new java.awt.Dimension(75, 25));
      okButton.setPreferredSize(new java.awt.Dimension(75, 25));
      okButton.addActionListener(new java.awt.event.ActionListener()
      {
          public void actionPerformed(java.awt.event.ActionEvent evt)
          {
              okButtonActionPerformed(evt);
          }
      });

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 6;
      gridBagConstraints.gridy = 0;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
      gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
      buttonPanel.add(okButton, gridBagConstraints);

      cancelButton.setText("Cancel");
      cancelButton.addActionListener(new java.awt.event.ActionListener()
      {
          public void actionPerformed(java.awt.event.ActionEvent evt)
          {
              cancelButtonActionPerformed(evt);
          }
      });

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
      gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
      buttonPanel.add(cancelButton, gridBagConstraints);

      gridBagConstraints = new java.awt.GridBagConstraints();
      gridBagConstraints.gridx = 0;
      gridBagConstraints.gridy = 1;
      gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
      add(buttonPanel, gridBagConstraints);

   }// </editor-fold>//GEN-END:initComponents

   private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
      exit();
   }//GEN-LAST:event_cancelButtonActionPerformed

   
   private void focusGained(javax.swing.JTextField textField){
      textField.setCaretPosition(0);
      textField.moveCaretPosition(textField.getText().length());
   }
   
   private void focusLost(javax.swing.JTextField textField){
      textField.setCaretPosition(0);
   }   
   
   private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
      annotation.setText(textArea.getText());
      annotation.repaint();
      exit();   
   }//GEN-LAST:event_okButtonActionPerformed
   
   
   private void exit() {
      //Provisional!
      this.getParent().getParent().getParent().getParent().setVisible(false);
   }


    private javax.swing.JTextArea textArea;
   // End of variables declaration//GEN-END:variables
   
}
