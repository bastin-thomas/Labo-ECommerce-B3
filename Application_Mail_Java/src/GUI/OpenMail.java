/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author student
 */
public class OpenMail extends javax.swing.JFrame {

    /**
     * Creates new form OpenMail
     */
    public OpenMail() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        From = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Subject = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Cc = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        To = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        Message = new javax.swing.JTextArea();

        jScrollPane6.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("From :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 20, -1, -1));

        jLabel2.setText("To :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 51, -1, -1));

        jLabel3.setText("Cc :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        From.setEditable(false);
        From.setFocusCycleRoot(false);
        From.setFocusable(false);
        jScrollPane1.setViewportView(From);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 16, 528, -1));

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Subject.setEditable(false);
        Subject.setFocusCycleRoot(false);
        Subject.setFocusable(false);
        jScrollPane3.setViewportView(Subject);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 126, 639, 36));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Cc.setEditable(false);
        Cc.setFocusCycleRoot(false);
        Cc.setFocusable(false);
        jScrollPane4.setViewportView(Cc);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 76, 528, -1));

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        To.setEditable(false);
        To.setFocusCycleRoot(false);
        To.setFocusable(false);
        jScrollPane5.setViewportView(To);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 46, 528, -1));

        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Message.setEditable(false);
        Message.setColumns(20);
        Message.setLineWrap(true);
        Message.setRows(5);
        Message.setWrapStyleWord(true);
        Message.setFocusable(false);
        jScrollPane8.setViewportView(Message);

        getContentPane().add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 180, 639, 356));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Cc;
    private javax.swing.JTextPane From;
    private javax.swing.JTextArea Message;
    private javax.swing.JTextPane Subject;
    private javax.swing.JTextPane To;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    // End of variables declaration//GEN-END:variables
}