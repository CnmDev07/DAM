/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package actividad3;

import actividad3.Actividad3;
import actividad3.Formulario_frase;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author crixo
 */
public class Formulario_principal extends javax.swing.JFrame {

    /**
     * Creates new form Formulario_principal
     */
    public Formulario_principal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Bamistad = new javax.swing.JButton();
        Besfuerzo = new javax.swing.JButton();
        Bfuturo = new javax.swing.JButton();
        Bsalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        Bamistad.setText("Amistad");
        Bamistad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BamistadActionPerformed(evt);
            }
        });

        Besfuerzo.setText("Esfuerzo");
        Besfuerzo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BesfuerzoActionPerformed(evt);
            }
        });

        Bfuturo.setText("Futuro");

        Bsalir.setText("Salir");
        Bsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BsalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Frases");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(Bamistad)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Bsalir)
                            .addComponent(Besfuerzo))
                        .addGap(47, 47, 47)
                        .addComponent(Bfuturo)))
                .addGap(61, 61, 61))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bamistad)
                    .addComponent(Besfuerzo)
                    .addComponent(Bfuturo))
                .addGap(71, 71, 71)
                .addComponent(Bsalir)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BesfuerzoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BesfuerzoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BesfuerzoActionPerformed

    private void BsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BsalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BsalirActionPerformed

    private void BamistadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BamistadActionPerformed
      
          String fraseElegida = Actividad3.elegirFrase("/home/crixo/Documentos/DEV/DESIN/NetBeans/Actividad3/src/actividad3/amistad.txt");
        
    // Verifica que haya una frase válida y divídela en frase y autor
    if (!fraseElegida.isEmpty()) {
        String[] partes = fraseElegida.split(" - ");
        
        // Si la frase tiene una estructura válida, asigna los valores a los JLabel
        if (partes.length == 2) {
            String frase = partes[0];
            String autor = partes[1];

            // Crea y muestra el nuevo formulario
            Formulario_frase formulario = new Formulario_frase(autor, frase);
            this.setVisible(false); // Oculta la ventana actual
        }
    } else {
        // Manejo de error si no se obtiene una frase
        System.out.println("No se pudo obtener una frase.");
    }
    

        

    // Muestra el formulario con la nueva frase
    
        
        
        
    }//GEN-LAST:event_BamistadActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bamistad;
    private javax.swing.JButton Besfuerzo;
    private javax.swing.JButton Bfuturo;
    private javax.swing.JButton Bsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
