/**
 * Copyright @ 2013 Quan Nguyen
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.sourceforge.tessboxeditor;

import java.awt.Cursor;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.*;

public class TrainDialog extends javax.swing.JDialog {

    static final Preferences prefs = Preferences.userRoot().node("/net/sourceforge/tessboxeditor");
    private String tessDirectory;
    private String trainDataDirectory;
    TrainingWorker trainWorker;

    /**
     * Creates new form TrainDialog
     */
    public TrainDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tessDirectory = prefs.get("tessDirectory", null);
        this.jTextFieldTessDir.setText(tessDirectory);
        this.jFileChooser1.setCurrentDirectory(tessDirectory == null ? null : new File(tessDirectory));
        trainDataDirectory = prefs.get("trainDataDirectory", null);
        this.jTextFieldDataDir.setText(trainDataDirectory);
        this.jFileChooser2.setCurrentDirectory(trainDataDirectory == null ? null : new File(trainDataDirectory));
        this.jTextFieldLang.setText(prefs.get("trainnedLanguage", null));
        this.jTextFieldBootstrapLang.setText(prefs.get("bootstrapLanguage", null));
        this.jComboBoxOps.setSelectedIndex(prefs.getInt("trainingMode", 0));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quit();
            }
        });
        setLocationRelativeTo(getOwner());

        //  Handle escape key to hide the dialog
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction =
                new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTessDir = new javax.swing.JTextField();
        jButtonBrowseTess = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDataDir = new javax.swing.JTextField();
        jButtonBrowseData = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldLang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldBootstrapLang = new javax.swing.JTextField();
        jComboBoxOps = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jButtonRun = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonCancel.setEnabled(false);
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(15, 32767));
        jButtonClose = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar1.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Train Tesseract");

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Tesseract Executable:");
        jPanel1.add(jLabel4, new java.awt.GridBagConstraints());

        jTextFieldTessDir.setEnabled(false);
        jTextFieldTessDir.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 4);
        jPanel1.add(jTextFieldTessDir, gridBagConstraints);

        jButtonBrowseTess.setText("...");
        jButtonBrowseTess.setToolTipText("Browse");
        jButtonBrowseTess.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonBrowseTess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseTessActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jButtonBrowseTess, gridBagConstraints);

        jLabel3.setText("Training Data:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jLabel3, gridBagConstraints);

        jTextFieldDataDir.setEnabled(false);
        jTextFieldDataDir.setPreferredSize(new java.awt.Dimension(180, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jPanel1.add(jTextFieldDataDir, gridBagConstraints);

        jButtonBrowseData.setText("...");
        jButtonBrowseData.setToolTipText("Browse");
        jButtonBrowseData.setPreferredSize(new java.awt.Dimension(30, 23));
        jButtonBrowseData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBrowseDataActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        jPanel1.add(jButtonBrowseData, gridBagConstraints);

        jLabel1.setText("Language:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jLabel1, gridBagConstraints);

        jTextFieldLang.setPreferredSize(new java.awt.Dimension(34, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        jPanel1.add(jTextFieldLang, gridBagConstraints);

        jLabel2.setText("Bootstrap Language:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 38);
        jPanel1.add(jLabel2, gridBagConstraints);

        jTextFieldBootstrapLang.setPreferredSize(new java.awt.Dimension(34, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 0);
        jPanel1.add(jTextFieldBootstrapLang, gridBagConstraints);

        jComboBoxOps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Training Mode --", "Generate Boxes Only", "Train with Existing Boxes", "Train from Scratch" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(2, 3, 0, 0);
        jPanel1.add(jComboBoxOps, gridBagConstraints);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 3, 5));

        jButtonRun.setText("Run");
        jButtonRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRunActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonRun);

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonCancel);
        jPanel2.add(filler1);

        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonClose);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(13, 3, 0, 0);
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jProgressBar1.setStringPainted(true);
        jPanel3.add(jProgressBar1);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRunActionPerformed
        if (this.jComboBoxOps.getSelectedIndex() == 0 || this.jTextFieldTessDir.getText().length() == 0 || this.jTextFieldDataDir.getText().length() == 0 || this.jTextFieldLang.getText().trim().length() == 0) {
            JOptionPane.showMessageDialog(TrainDialog.this, "Input is not complete.");
            return;
        }

        this.jButtonRun.setEnabled(false);
        this.jButtonCancel.setEnabled(true);
        this.jProgressBar1.setIndeterminate(true);
        this.jProgressBar1.setString("Training...");
        this.jProgressBar1.setVisible(true);
        this.pack();
        getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        getGlassPane().setVisible(true);

        trainWorker = new TrainingWorker();
        trainWorker.execute();
    }//GEN-LAST:event_jButtonRunActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.setVisible(false);
        quit();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonBrowseDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseDataActionPerformed
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            trainDataDirectory = jFileChooser1.getCurrentDirectory().getPath();
            this.jTextFieldDataDir.setText(trainDataDirectory);
        }
    }//GEN-LAST:event_jButtonBrowseDataActionPerformed

    private void jButtonBrowseTessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBrowseTessActionPerformed
        if (jFileChooser2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tessDirectory = jFileChooser2.getCurrentDirectory().getPath();
            this.jTextFieldTessDir.setText(tessDirectory);
        }
    }//GEN-LAST:event_jButtonBrowseTessActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        if (trainWorker != null && !trainWorker.isDone()) {
            trainWorker.cancel(true);
        }
        this.jButtonCancel.setEnabled(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    void quit() {
        if (tessDirectory != null) {
            prefs.put("tessDirectory", tessDirectory);
        }
        if (trainDataDirectory != null) {
            prefs.put("trainDataDirectory", trainDataDirectory);
        }
        prefs.put("trainnedLanguage", this.jTextFieldLang.getText());
        prefs.put("bootstrapLanguage", this.jTextFieldBootstrapLang.getText());
        prefs.putInt("trainingMode", this.jComboBoxOps.getSelectedIndex());
    }

    /**
     * A worker class for training process.
     */
    class TrainingWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            TessTrainer trainer = new TessTrainer(tessDirectory, trainDataDirectory, jTextFieldLang.getText(), jTextFieldBootstrapLang.getText());
            trainer.generate(jComboBoxOps.getSelectedIndex());
            return null;
        }

        @Override
        protected void done() {
            jProgressBar1.setIndeterminate(false);

            try {
                get(); // dummy method            
                jProgressBar1.setString("Training completed.");
            } catch (InterruptedException ignore) {
                ignore.printStackTrace();
            } catch (java.util.concurrent.ExecutionException e) {
                String why = null;
                Throwable cause = e.getCause();
                if (cause != null) {
                    why = cause.getMessage();
                } else {
                    why = e.getMessage();
                }
//                    e.printStackTrace();
                JOptionPane.showMessageDialog(TrainDialog.this, why, "Train Tesseract", JOptionPane.ERROR_MESSAGE);
                jProgressBar1.setVisible(false);
                jProgressBar1.setString(null);
            } catch (java.util.concurrent.CancellationException e) {
                jProgressBar1.setString("Training cancelled.");
            } finally {
                jButtonRun.setEnabled(true);
                jButtonCancel.setEnabled(false);
                getGlassPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                getGlassPane().setVisible(false);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrainDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TrainDialog dialog = new TrainDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonBrowseData;
    private javax.swing.JButton jButtonBrowseTess;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JComboBox jComboBoxOps;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTextField jTextFieldBootstrapLang;
    private javax.swing.JTextField jTextFieldDataDir;
    private javax.swing.JTextField jTextFieldLang;
    private javax.swing.JTextField jTextFieldTessDir;
    // End of variables declaration//GEN-END:variables
}
