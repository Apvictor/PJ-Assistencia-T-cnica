package br.com.housetech.telas;

import java.sql.*;
import br.com.housetech.dal.ModuloConexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/*
 * @author Armando
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
        pesquisar();
    }

    //Método Adicionar
    private void adicionar() {
        String sql = "INSERT INTO tab_Clientes(nomecli,endcli,fonecli,emailcli) VALUES(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_nome.getText());
            pst.setString(2, txt_Endereco.getText());
            pst.setString(3, txt_fone.getText());
            pst.setString(4, txt_Email.getText());

            //Validações
            if (txt_nome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Nome!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_nome.requestFocus();
            } else if (txt_fone.getText().equals("(  )      -    ")) {
                JOptionPane.showMessageDialog(null, "Preencher a Telefone!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_fone.requestFocus();
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    txt_id.setText(null);
                    txt_nome.setText(null);
                    txt_Endereco.setText(null);
                    txt_fone.setText(null);
                    txt_Email.setText(null);
                    JOptionPane.showMessageDialog(null, "Adicionado com Sucesso!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Create): " + erro.getMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Pesquisar
    private void pesquisar() {
        String sql = "SELECT idcli as ID, nomecli as NOME, endcli as ENDEREÇO, fonecli as FONE, emailcli as EMAIL FROM tab_clientes WHERE nomecli LIKE ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_Pesquisar.getText() + "%");
            rs = pst.executeQuery();
            tbl_Clientes.setModel(DbUtils.resultSetToTableModel(rs));
            DbUtils.resultSetToTableModel(rs);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Read): " + erro.getMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Clica Tabela
    public void clicarTabela() {
        int setar = tbl_Clientes.getSelectedRow();
        txt_id.setText(tbl_Clientes.getModel().getValueAt(setar, 0).toString());
        txt_nome.setText(tbl_Clientes.getModel().getValueAt(setar, 1).toString());
        txt_Endereco.setText(tbl_Clientes.getModel().getValueAt(setar, 2).toString());
        txt_fone.setText(tbl_Clientes.getModel().getValueAt(setar, 3).toString());
        txt_Email.setText(tbl_Clientes.getModel().getValueAt(setar, 4).toString());
        btn_Create.setEnabled(false);
    }

    //Método Alterar
    private void alterar() {
        String sql = "UPDATE tab_clientes SET nomecli=?,endcli=?,fonecli=?,emailcli=? WHERE idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_nome.getText());
            pst.setString(2, txt_Endereco.getText());
            pst.setString(3, txt_fone.getText());
            pst.setString(4, txt_Email.getText());
            pst.setString(5, txt_id.getText());

            //Validações
            if (txt_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_id.requestFocus();
            } else if (txt_nome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Nome!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_nome.requestFocus();
            } else if (txt_fone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Telefone!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_fone.requestFocus();
            } else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    txt_id.setText(null);
                    txt_nome.setText(null);
                    txt_fone.setText(null);
                    txt_Endereco.setText(null);
                    txt_Email.setText(null);
                    JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                    btn_Create.setEnabled(true);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Update): " + erro.getMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Método Remover
    private void remover() {

        String sql = "DELETE FROM tab_clientes WHERE idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            if (txt_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                txt_id.requestFocus();
            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Remover este Usuário?", "Clientes", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    int remover = pst.executeUpdate();
                    if (remover > 0) {
                        txt_id.setText(null);
                        txt_nome.setText(null);
                        txt_fone.setText(null);
                        txt_Email.setText(null);
                        txt_Endereco.setText(null);
                        JOptionPane.showMessageDialog(null, "Removido com Sucesso!", "Clientes", JOptionPane.INFORMATION_MESSAGE);
                        btn_Create.setEnabled(true);
                    }
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Delete): " + erro.getMessage(), "Clientes", JOptionPane.ERROR_MESSAGE);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_fone = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_Endereco = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_Email = new javax.swing.JTextField();
        btn_Create = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        txt_Pesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Clientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        fundo = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Tela Clientes");
        setPreferredSize(new java.awt.Dimension(592, 431));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/reparar (1).png"))); // NOI18N
        titulo.setText("Tela Clientes");
        titulo.setToolTipText("");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel7.setText("*Campos obrigatórios");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("*NOME");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        txt_nome.setName(""); // NOI18N
        getContentPane().add(txt_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 280, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("*FONE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        try {
            txt_fone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txt_fone, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("ENDEREÇO");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, -1, -1));

        txt_Endereco.setName(""); // NOI18N
        getContentPane().add(txt_Endereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 280, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("E-MAIL");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

        txt_Email.setName(""); // NOI18N
        getContentPane().add(txt_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 280, -1));

        btn_Create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/create.png"))); // NOI18N
        btn_Create.setToolTipText("Adicionar");
        btn_Create.setContentAreaFilled(false);
        btn_Create.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Create, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 40, -1));

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/update.png"))); // NOI18N
        btn_Update.setToolTipText("Alterar");
        btn_Update.setContentAreaFilled(false);
        btn_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 40, -1));

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/delete.png"))); // NOI18N
        btn_Delete.setToolTipText("Remover");
        btn_Delete.setContentAreaFilled(false);
        btn_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 40, -1));

        txt_Pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisarKeyReleased(evt);
            }
        });
        getContentPane().add(txt_Pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 150, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/pesquisar_1.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

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
        tbl_Clientes.setOpaque(false);
        tbl_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Clientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 330, 100));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("*ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        txt_id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_id.setEnabled(false);
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 50, -1));

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/capa.png"))); // NOI18N
        getContentPane().add(fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setBounds(0, 0, 592, 431);
    }// </editor-fold>//GEN-END:initComponents
//Botão Adicionar
    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        adicionar();
        pesquisar();
    }//GEN-LAST:event_btn_CreateActionPerformed
//Botão Alterar
    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        alterar();
        pesquisar();
    }//GEN-LAST:event_btn_UpdateActionPerformed
//Botão Remover
    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        remover();
        pesquisar();
    }//GEN-LAST:event_btn_DeleteActionPerformed
//Campos Pesquisar
    private void txt_PesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisarKeyReleased
        pesquisar();
    }//GEN-LAST:event_txt_PesquisarKeyReleased
//Clicar Tabela
    private void tbl_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClientesMouseClicked
        clicarTabela();
    }//GEN-LAST:event_tbl_ClientesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Clientes;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_Endereco;
    private javax.swing.JTextField txt_Pesquisar;
    private javax.swing.JFormattedTextField txt_fone;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nome;
    // End of variables declaration//GEN-END:variables
}
