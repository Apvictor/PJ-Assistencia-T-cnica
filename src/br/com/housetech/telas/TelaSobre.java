package br.com.housetech.telas;
import java.awt.Toolkit;
/*
 * @author Armando
 */
public class TelaSobre extends javax.swing.JFrame {

    public TelaSobre() {
        initComponents();
        setIcon();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Sobre");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Sistema para Controle de Assistência Técnica");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Desenvolvido por Armando Víctor Pereira");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel3.setText("Sob a Licença GPL");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/GNU.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, -1, -1));

        titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/reparar (1).png"))); // NOI18N
        titulo.setText("HouseTech");
        titulo.setToolTipText("");
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 200, 130));

        setSize(new java.awt.Dimension(314, 354));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaSobre().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
//ICONE DA JANELA
    private void setIcon(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/housetech/icones/reparar.png")));
    }
}