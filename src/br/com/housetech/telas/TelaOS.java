package br.com.housetech.telas;

import EmailJFrame.EnviarEmailAnexo;
import java.sql.*;
import br.com.housetech.dal.ModuloConexao;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import java.util.*;

public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public int flag = 0;

    private String tipo;

    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Método Pesquisar
    private void pesquisar() {
        String sql = "SELECT idcli as ID, nomecli as NOME, fonecli as FONE FROM tab_clientes WHERE nomecli LIKE ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_Pesquisar.getText() + "%");
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
        txt_ID.setText(tbl_Clientes.getModel().getValueAt(setar, 0).toString());
    }

    //Método Cadastro
    private void emitir_OS() {
        String sql = "INSERT INTO tab_os(tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) VALUES(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, combo_Sit.getSelectedItem().toString());
            pst.setString(3, txt_Equip.getText());
            pst.setString(4, txt_Def.getText());
            pst.setString(5, txt_Serv.getText());
            pst.setString(6, txt_Tec.getText());
            pst.setString(7, txt_Valor.getText().replace(",", "."));
            pst.setString(8, txt_ID.getText());

            //Validações
            if (txt_ID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_ID.requestFocus();
            } else if (txt_Equip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Equipamento!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_Equip.requestFocus();
            } else if (txt_Def.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Defeito!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_Def.requestFocus();
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS emitida com Sucesso!", "OS", JOptionPane.INFORMATION_MESSAGE);
                    txt_ID.setText(null);
                    txt_Equip.setText(null);
                    txt_Def.setText(null);
                    txt_Serv.setText(null);
                    txt_Tec.setText(null);
                    txt_Valor.setText(null);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Create): " + erro.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Pesquisar
    private void pesquisar_OS() {
        String num_os = JOptionPane.showInputDialog("Número da OS:");
        String sql = "SELECT * FROM tab_os WHERE os = " + num_os;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txt_Os.setText(rs.getString(1));
                txt_Data.setText(rs.getString(2));
                String rbt_Tipo = rs.getString(3);
                if (rbt_Tipo.equals("OS")) {
                    rbt_Os.setSelected(true);
                    tipo = "OS";
                } else {
                    rbt_Orc.setSelected(true);
                    tipo = "ORÇAMENTO";
                }
                combo_Sit.setSelectedItem(rs.getString(4));
                txt_Equip.setText(rs.getString(5));
                txt_Def.setText(rs.getString(6));
                txt_Serv.setText(rs.getString(7));
                txt_Tec.setText(rs.getString(8));
                txt_Valor.setText(rs.getString(9));
                txt_ID.setText(rs.getString(10));
                btn_Create.setEnabled(false);
                txt_Pesquisar.setEnabled(false);
                tbl_Clientes.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "OS não Cadastrada! ", "OS", JOptionPane.ERROR_MESSAGE);
            }

        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException erro) {
            JOptionPane.showMessageDialog(null, "OS Inválida! " + erro.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException erro1) {
            JOptionPane.showMessageDialog(null, "Erro(Read OS): " + erro1.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Alterar
    private void alterar() {
        String sql = "UPDATE tab_os SET tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? WHERE os = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, combo_Sit.getSelectedItem().toString());
            pst.setString(3, txt_Equip.getText());
            pst.setString(4, txt_Def.getText());
            pst.setString(5, txt_Serv.getText());
            pst.setString(6, txt_Tec.getText());
            pst.setString(7, txt_Valor.getText().replace(",", "."));
            pst.setString(8, txt_Os.getText());

            //Validações
            if (txt_ID.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o ID!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_ID.requestFocus();
            } else if (txt_Equip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Equipamento!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_Equip.requestFocus();
            } else if (txt_Def.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencher o Defeito!", "OS", JOptionPane.INFORMATION_MESSAGE);
                txt_Def.requestFocus();
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterada com Sucesso!", "OS", JOptionPane.INFORMATION_MESSAGE);
                    txt_Os.setText(null);
                    txt_ID.setText(null);
                    txt_Data.setText(null);
                    txt_Equip.setText(null);
                    txt_Def.setText(null);
                    txt_Serv.setText(null);
                    txt_Tec.setText(null);
                    txt_Valor.setText(null);
                    btn_Create.setEnabled(true);
                    txt_Pesquisar.setEnabled(true);
                    tbl_Clientes.setVisible(true);
                }
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro(Update): " + erro.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método Remover
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja Remover esta OS?", "OS", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tab_os WHERE os=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_Os.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Removido com Sucesso!", "OS", JOptionPane.INFORMATION_MESSAGE);
                    txt_Os.setText(null);
                    txt_ID.setText(null);
                    txt_Data.setText(null);
                    txt_Equip.setText(null);
                    txt_Def.setText(null);
                    txt_Serv.setText(null);
                    txt_Tec.setText(null);
                    txt_Valor.setText(null);
                    btn_Create.setEnabled(true);
                    txt_Pesquisar.setEnabled(true);
                    tbl_Clientes.setVisible(true);
                }
            } catch (HeadlessException | SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro(Delete): " + erro.getMessage(), "OS", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //Método para imprimir uma OS
    private void imprimir_os() {
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão desta OS?", "Impressão", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                //USANDO A CLASS HASHMAP PARA FAZER UM FILTRO
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txt_Os.getText()));

                //Preparando impressão
                JasperPrint print = JasperFillManager.fillReport("C:/Users/Armando/Documents/NetBeansProjects/SistemaAssistenciaTecnica/Relatorios/OS.jasper", filtro, conexao);
                //Exibir relatótio
                JasperViewer.viewReport(print, false);
            } catch (NumberFormatException | JRException erro) {
                JOptionPane.showMessageDialog(null, "Erro(Impressão)! \nErro:" + erro.getMessage(), "Impressão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_Os = new javax.swing.JTextField();
        txt_Data = new javax.swing.JTextField();
        rbt_Orc = new javax.swing.JRadioButton();
        rbt_Os = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        combo_Sit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_Pesquisar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Clientes = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_Equip = new javax.swing.JTextField();
        txt_Def = new javax.swing.JTextField();
        txt_Serv = new javax.swing.JTextField();
        txt_Tec = new javax.swing.JTextField();
        txt_Valor = new javax.swing.JTextField();
        btn_Create = new javax.swing.JButton();
        btn_Read = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        btn_Email = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Tela OS");
        setPreferredSize(new java.awt.Dimension(592, 431));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nº OS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel2.setText("DATA");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        txt_Os.setEditable(false);
        txt_Os.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_Os.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txt_Os, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 50, -1));

        txt_Data.setEditable(false);
        txt_Data.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_Data.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel1.add(txt_Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 170, -1));

        buttonGroup1.add(rbt_Orc);
        rbt_Orc.setSelected(true);
        rbt_Orc.setText("ORÇAMENTO");
        rbt_Orc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_OrcActionPerformed(evt);
            }
        });
        jPanel1.add(rbt_Orc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        buttonGroup1.add(rbt_Os);
        rbt_Os.setText("OS");
        rbt_Os.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbt_OsActionPerformed(evt);
            }
        });
        jPanel1.add(rbt_Os, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 270, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("SITUAÇÃO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 20));

        combo_Sit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na BANCADA", "Aguardando APROVAÇÃO", "Abandonado pelo Cliente", "Orçamento REPROVADO", "Aguardando PEÇAS", "Entrega OK", "RETORNOU", " ", " " }));
        getContentPane().add(combo_Sit, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 210, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTE"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/pesquisar_1.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        txt_Pesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_PesquisarKeyReleased(evt);
            }
        });
        jPanel2.add(txt_Pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, -1));

        jLabel5.setText("*ID:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, 20));

        txt_ID.setEditable(false);
        txt_ID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_ID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txt_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 50, -1));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 260, 90));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 280, 150));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("*EQUIPAMENTO:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("*DEFEITO:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("  SERVIÇO:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 60, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("  TÉCNICO:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 60, 20));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("VALOR TOTAL:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, 20));
        getContentPane().add(txt_Equip, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 380, -1));
        getContentPane().add(txt_Def, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 380, -1));
        getContentPane().add(txt_Serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 380, -1));
        getContentPane().add(txt_Tec, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 180, -1));

        txt_Valor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Valor.setText("0");
        getContentPane().add(txt_Valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 100, -1));

        btn_Create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/create.png"))); // NOI18N
        btn_Create.setToolTipText("Adicionar");
        btn_Create.setContentAreaFilled(false);
        btn_Create.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CreateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Create, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 40, -1));

        btn_Read.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/read.png"))); // NOI18N
        btn_Read.setToolTipText("Consultar");
        btn_Read.setContentAreaFilled(false);
        btn_Read.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Read.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReadActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Read, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 40, -1));

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/update.png"))); // NOI18N
        btn_Update.setToolTipText("Alterar");
        btn_Update.setContentAreaFilled(false);
        btn_Update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 40, -1));

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/delete.png"))); // NOI18N
        btn_Delete.setToolTipText("Remover");
        btn_Delete.setContentAreaFilled(false);
        btn_Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 330, 40, -1));

        btn_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/print.png"))); // NOI18N
        btn_Imprimir.setToolTipText("Imprimir OS");
        btn_Imprimir.setContentAreaFilled(false);
        btn_Imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 40, -1));

        btn_Email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/gmail.png"))); // NOI18N
        btn_Email.setToolTipText("Enviar Email");
        btn_Email.setContentAreaFilled(false);
        btn_Email.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EmailActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, 40, -1));

        setBounds(0, 0, 592, 431);
    }// </editor-fold>//GEN-END:initComponents
//Botão adicionar
    private void btn_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CreateActionPerformed
        emitir_OS();
    }//GEN-LAST:event_btn_CreateActionPerformed
//Botão pesquisar OS 
    private void btn_ReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReadActionPerformed
        pesquisar_OS();
    }//GEN-LAST:event_btn_ReadActionPerformed
//Botão Alterar
    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        alterar();
    }//GEN-LAST:event_btn_UpdateActionPerformed
//Botão Remover
    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
        remover();
    }//GEN-LAST:event_btn_DeleteActionPerformed
//Botão Imprimir
    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed
        if (txt_Os.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor realizar consulta antes de imprimir!");
        } else {
            imprimir_os();
        }
    }//GEN-LAST:event_btn_ImprimirActionPerformed
//Pesquisar
    private void txt_PesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_PesquisarKeyReleased
        pesquisar();
    }//GEN-LAST:event_txt_PesquisarKeyReleased
//Clicar na Tabela
    private void tbl_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClientesMouseClicked
        clicarTabela();
    }//GEN-LAST:event_tbl_ClientesMouseClicked
//Radio Orçamento
    private void rbt_OrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_OrcActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_rbt_OrcActionPerformed
//Radio OS
    private void rbt_OsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbt_OsActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_rbt_OsActionPerformed
//Botão Email
    private void btn_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EmailActionPerformed
        EnviarEmailAnexo emailAnexo = new EnviarEmailAnexo();
        emailAnexo.setVisible(true);
    }//GEN-LAST:event_btn_EmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Create;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Email;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.JButton btn_Read;
    private javax.swing.JButton btn_Update;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> combo_Sit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbt_Orc;
    private javax.swing.JRadioButton rbt_Os;
    private javax.swing.JTable tbl_Clientes;
    private javax.swing.JTextField txt_Data;
    private javax.swing.JTextField txt_Def;
    private javax.swing.JTextField txt_Equip;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Os;
    private javax.swing.JTextField txt_Pesquisar;
    private javax.swing.JTextField txt_Serv;
    private javax.swing.JTextField txt_Tec;
    private javax.swing.JTextField txt_Valor;
    // End of variables declaration//GEN-END:variables
}
