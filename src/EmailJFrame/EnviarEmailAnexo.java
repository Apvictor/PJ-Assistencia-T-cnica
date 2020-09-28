package EmailJFrame;

import br.com.housetech.dal.ModuloConexao;
import br.com.housetech.telas.TelaOS;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class EnviarEmailAnexo extends javax.swing.JFrame {

    //Atributos
    //Caminho do arquivo
    String caminho;
    //Email de destino
    String destino;
    //Nome cliente
    String nome;
    File arquivo;

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Método Pesquisar
    private void pesquisar() {
        String sql = "SELECT idcli as ID, nomecli as NOME, emailcli as EMAIL FROM tab_clientes WHERE nomecli LIKE ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_Pesquisa.getText() + "%");
            rs = pst.executeQuery();
            tbl_Clientes.setModel(DbUtils.resultSetToTableModel(rs));
            DbUtils.resultSetToTableModel(rs);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(READ): " + erro.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Clica Tabela
    public void clicarTabela() {
        int setar = tbl_Clientes.getSelectedRow();
        txtNome.setText(tbl_Clientes.getModel().getValueAt(setar, 1).toString());
        txtEmail.setText(tbl_Clientes.getModel().getValueAt(setar, 2).toString());
    }

    public EnviarEmailAnexo() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCaminho = new javax.swing.JTextField();
        btnProcurar = new javax.swing.JButton();
        btnEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_Pesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Clientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Email");
        setBackground(new java.awt.Color(204, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Enviando Email com Anexo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Caminho:*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, -1, -1));

        txtCaminho.setEditable(false);
        getContentPane().add(txtCaminho, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 340, 40));

        btnProcurar.setText("Procurar");
        btnProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcurarActionPerformed(evt);
            }
        });
        getContentPane().add(btnProcurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 390, -1, 40));

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, -1, 30));

        jLabel3.setText("Cliente:*");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txt_Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisaKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 340, -1));

        jLabel4.setText("Email:*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        txtEmail.setEditable(false);
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 340, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/pesquisar_1.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, -1, -1));

        tbl_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Clientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 340, 200));

        jLabel6.setText("Nome:*");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtNome.setEditable(false);
        getContentPane().add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 340, -1));

        setSize(new java.awt.Dimension(556, 536));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//Botão Procurar
    private void btnProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcurarActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        arquivo = chooser.getSelectedFile().getAbsoluteFile();
        caminho = arquivo.getAbsolutePath();
        txtCaminho.setText(caminho);

    }//GEN-LAST:event_btnProcurarActionPerformed
//Botão Enviar
    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        TelaOS telaOS = new TelaOS();
        telaOS.flag = 0;

        Email email = new Email();
        //Validação
        if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor pesquisar cliente!");
            txtEmail.requestFocus();
        } else if (txtCaminho.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor Anexar Arquivo!");
        } else {
            email.enviar(txtNome.getText(), txtEmail.getText(), caminho, arquivo.getName());
            this.dispose();
        }

    }//GEN-LAST:event_btnEnviarActionPerformed
//Clicar na Tabela
    private void tbl_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClientesMouseClicked
        clicarTabela();
    }//GEN-LAST:event_tbl_ClientesMouseClicked
//Digitar no CAMPO DE TEXTO 
    private void txt_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_txt_PesquisaKeyReleased

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EnviarEmailAnexo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnProcurar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Clientes;
    private javax.swing.JTextField txtCaminho;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txt_Pesquisa;
    // End of variables declaration//GEN-END:variables
}
