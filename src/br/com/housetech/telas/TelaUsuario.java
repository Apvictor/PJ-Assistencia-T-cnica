package br.com.housetech.telas;

/*
 * @author Armando
 */
import java.sql.*;
import br.com.housetech.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;


    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Método Consultar
    private void consultar() {
        String sql = "SELECT * FROM tab_usuarios WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txt_nome.setText(rs.getString(2));
                txt_fone.setText(rs.getString(3));
                txt_login.setText(rs.getString(4));
                txt_senha.setText(rs.getString(5));
                combo_perfil.setSelectedItem(rs.getString(6));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado!", "Usuários", JOptionPane.ERROR_MESSAGE);
                txt_nome.setText(null);
                txt_fone.setText(null);
                txt_login.setText(null);
                txt_senha.setText(null);
                combo_perfil.setSelectedItem("SELECIONE:");
            }
        } catch (HeadlessException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Read): " + erro.getMessage(), "Usuários", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Adicionar
    private void adicionar() {
        String sql = "INSERT INTO tab_Usuarios(iduser,usuario,fone,login,senha,perfil) VALUES(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.setString(2, txt_nome.getText());
            pst.setString(3, txt_fone.getText());
            pst.setString(4, txt_login.getText());
            pst.setString(5, txt_senha.getText());
            pst.setString(6, combo_perfil.getSelectedItem().toString());

            //Validações
            if (txt_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_id.requestFocus();
            } else if (txt_nome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Nome!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_nome.requestFocus();
            } else if (txt_login.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Login!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_login.requestFocus();
            } else if (txt_senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher a Senha!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_senha.requestFocus();
            } else if (combo_perfil.getSelectedItem().equals("SELECIONE:")) {
                JOptionPane.showMessageDialog(null, "Selecione o Perfil!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    txt_id.setText(null);
                    txt_nome.setText(null);
                    txt_fone.setText(null);
                    txt_login.setText(null);
                    txt_senha.setText(null);
                    combo_perfil.setSelectedItem("SELECIONE:");
                    JOptionPane.showMessageDialog(null, "Adicionado com Sucesso!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Create): " + erro.getMessage(), "Usuários", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Alterar
    private void alterar() {
        String sql = "UPDATE tab_usuarios SET usuario=?,fone=?,login=?,senha=?,perfil=? WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_nome.getText());
            pst.setString(2, txt_fone.getText());
            pst.setString(3, txt_login.getText());
            pst.setString(4, txt_senha.getText());
            pst.setString(5, combo_perfil.getSelectedItem().toString());
            pst.setString(6, txt_id.getText());

            //Validações
            if (txt_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_id.requestFocus();
            } else if (txt_nome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Nome!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_nome.requestFocus();
            } else if (txt_login.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Login!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_login.requestFocus();
            } else if (txt_senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher a Senha!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_senha.requestFocus();
            } else if (combo_perfil.getSelectedItem().equals("SELECIONE:")) {
                JOptionPane.showMessageDialog(null, "Selecione o Perfil!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    txt_id.setText(null);
                    txt_nome.setText(null);
                    txt_fone.setText(null);
                    txt_login.setText(null);
                    txt_senha.setText(null);
                    combo_perfil.setSelectedItem("SELECIONE:");
                    JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Update): " + erro.getMessage(), "Usuários", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Remover
    private void remover() {

        String sql = "DELETE FROM tab_usuarios WHERE iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            if (txt_id.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                txt_id.requestFocus();
            } else {
                int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Remover este Usuário?", "Usuários", JOptionPane.YES_NO_OPTION);
                if (confirma == JOptionPane.YES_OPTION) {
                    int remover = pst.executeUpdate();
                    if (remover > 0) {
                        txt_id.setText(null);
                        txt_nome.setText(null);
                        txt_fone.setText(null);
                        txt_login.setText(null);
                        txt_senha.setText(null);
                        combo_perfil.setSelectedItem("SELECIONE:");
                        JOptionPane.showMessageDialog(null, "Removido com Sucesso!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Delete): " + erro.getMessage(), "Usuários", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_nome = new javax.swing.JTextField();
        txt_senha = new javax.swing.JTextField();
        combo_perfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_fone = new javax.swing.JFormattedTextField();
        txt_login = new javax.swing.JTextField();
        titulo = new javax.swing.JLabel();
        btn_Create = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Read = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Tela Usuários");
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(720, 520));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("*ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("*NOME");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("FONE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("*SENHA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("*PERFIL");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, -1, -1));
        getContentPane().add(txt_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 50, -1));

        txt_nome.setName(""); // NOI18N
        getContentPane().add(txt_nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 280, -1));
        getContentPane().add(txt_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 110, -1));

        combo_perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE:", "Administrador", "Técnico", "Usuário" }));
        combo_perfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(combo_perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 110, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("*LOGIN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        try {
            txt_fone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(txt_fone, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 100, -1));
        getContentPane().add(txt_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 100, -1));

        titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/reparar (1).png"))); // NOI18N
        titulo.setText("Tela Usuários");
        titulo.setToolTipText("");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        btn_Create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/create.png"))); // NOI18N
        btn_Create.setToolTipText("Adicionar");
        btn_Create.setContentAreaFilled(false);
        btn_Create.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Create, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 40, -1));

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/update.png"))); // NOI18N
        btn_Update.setToolTipText("Alterar");
        btn_Update.setContentAreaFilled(false);
        btn_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 40, -1));

        btn_Read.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/read.png"))); // NOI18N
        btn_Read.setToolTipText("Consultar");
        btn_Read.setContentAreaFilled(false);
        btn_Read.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Read.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReadActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Read, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 40, -1));

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/delete.png"))); // NOI18N
        btn_Delete.setToolTipText("Remover");
        btn_Delete.setContentAreaFilled(false);
        btn_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 40, -1));

        jLabel7.setText("*Campos obrigatórios");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/capa.png"))); // NOI18N
        getContentPane().add(fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setBounds(0, 0, 592, 431);
    }// </editor-fold>//GEN-END:initComponents
//Botão Consultar
    private void btn_ReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReadActionPerformed
        if (txt_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencher o ID!", "Usuários", JOptionPane.INFORMATION_MESSAGE);
        } else {
            consultar();
        }
    }//GEN-LAST:event_btn_ReadActionPerformed
//Botão Adicionar
    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        adicionar();
    }//GEN-LAST:event_btn_CreateActionPerformed
//Botão Editar
    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btn_UpdateActionPerformed
//Botão Remover
    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        remover();
    }//GEN-LAST:event_btn_DeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Read;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> combo_perfil;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel titulo;
    private javax.swing.JFormattedTextField txt_fone;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_login;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_senha;
    // End of variables declaration//GEN-END:variables
}
