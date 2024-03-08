/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartas;

import javax.swing.JOptionPane;

public class FrmJuego extends javax.swing.JFrame {

    private final Jugador jugador1 = new Jugador();
    private final Jugador jugador2 = new Jugador();
    // Declara una variable booleana para rastrear si btnRepartirActionPerformed se ha hecho clic
    private boolean repartirRealizado = false;
    private boolean verificarRealizado = false;

    public FrmJuego() {
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

        btnRepartir = new javax.swing.JButton();
        btnVerificar = new javax.swing.JButton();
        tpJugadores = new javax.swing.JTabbedPane();
        pnlJugador1 = new javax.swing.JPanel();
        pnlJugador2 = new javax.swing.JPanel();
        btnOrdenar = new javax.swing.JButton();
        bntPuntaje = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRepartir.setText("Repartir");
        btnRepartir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepartirActionPerformed(evt);
            }
        });

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        tpJugadores.setBackground(new java.awt.Color(51, 255, 204));

        pnlJugador1.setBackground(new java.awt.Color(102, 255, 51));

        javax.swing.GroupLayout pnlJugador1Layout = new javax.swing.GroupLayout(pnlJugador1);
        pnlJugador1.setLayout(pnlJugador1Layout);
        pnlJugador1Layout.setHorizontalGroup(
            pnlJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        pnlJugador1Layout.setVerticalGroup(
            pnlJugador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        tpJugadores.addTab("Martín Estrada Contreras", pnlJugador1);

        pnlJugador2.setBackground(new java.awt.Color(51, 255, 204));

        javax.swing.GroupLayout pnlJugador2Layout = new javax.swing.GroupLayout(pnlJugador2);
        pnlJugador2.setLayout(pnlJugador2Layout);
        pnlJugador2Layout.setHorizontalGroup(
            pnlJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
        pnlJugador2Layout.setVerticalGroup(
            pnlJugador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        tpJugadores.addTab("Raúl Vidal", pnlJugador2);

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });

        bntPuntaje.setText("Obtener Puntaje");
        bntPuntaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntPuntajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnRepartir)
                        .addGap(29, 29, 29)
                        .addComponent(btnVerificar)
                        .addGap(30, 30, 30)
                        .addComponent(btnOrdenar)
                        .addGap(38, 38, 38)
                        .addComponent(bntPuntaje))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tpJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRepartir)
                    .addComponent(btnVerificar)
                    .addComponent(btnOrdenar)
                    .addComponent(bntPuntaje))
                .addGap(18, 18, 18)
                .addComponent(tpJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRepartirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepartirActionPerformed

        jugador1.repartir();
        jugador2.repartir();

        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);

        // Marca repartirRealizado como true una vez que se haya realizado la acción de repartir
        repartirRealizado = true;

    }//GEN-LAST:event_btnRepartirActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        if (repartirRealizado) {

            int pestaña = tpJugadores.getSelectedIndex();
            String mensaje = "";
            switch (pestaña) {
                case 0:
                    mensaje = jugador1.getGrupos();
                    break;
                case 1:
                    mensaje = jugador2.getGrupos();
                    break;
            }
              verificarRealizado = true;
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debes hacer clic en 'Repartir' antes de verificar tus cartas.");
        }

    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed

        // Comprueba si btnRepartirActionPerformed se ha realizado antes de permitir que bntPuntajeActionPerformed se ejecute
        if (repartirRealizado) {
            jugador1.ordenarPorBurbuja();
            jugador2.ordenarPorBurbuja();

            jugador1.mostrar(pnlJugador1);
            jugador2.mostrar(pnlJugador2);
        } else {
            JOptionPane.showMessageDialog(null, "Primero debes hacer clic en 'Repartir' antes de ordenar las cartas.");
        }

    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void bntPuntajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntPuntajeActionPerformed
        if (repartirRealizado && verificarRealizado) {
            int pestaña = tpJugadores.getSelectedIndex();
            String mensaje = "";
            switch (pestaña) {
                case 0:
                    mensaje = jugador1.getPuntaje();
                    break;
                case 1:
                    mensaje = jugador2.getPuntaje();
                    break;
            }
            JOptionPane.showMessageDialog(null, mensaje);
    }//GEN-LAST:event_bntPuntajeActionPerformed
    else if (!repartirRealizado) {
            JOptionPane.showMessageDialog(null, "Primero debes hacer clic en 'Repartir' antes de verificar el puntaje.");
        } else {

            JOptionPane.showMessageDialog(null, "Primero debes hacer clic en 'Verificar' antes de verificar el puntaje.");
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
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntPuntaje;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnRepartir;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JPanel pnlJugador1;
    private javax.swing.JPanel pnlJugador2;
    private javax.swing.JTabbedPane tpJugadores;
    // End of variables declaration//GEN-END:variables
}
