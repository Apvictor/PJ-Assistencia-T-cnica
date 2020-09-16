package br.com.housetech.telas;

import br.com.housetech.dal.ModuloConexao;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/*
 * @author Armando
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;

    //Data do Sistema
    Calendar c = Calendar.getInstance();
    java.util.Date data = c.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TelaPrincipal() {
        initComponents();
        setIcon();
        lbl_Data.setText("" + sdf.format(data));
        conexao = ModuloConexao.conector();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        Desktop = new javax.swing.JDesktopPane();
        lbl_Perfil = new javax.swing.JLabel();
        lbl_Data = new javax.swing.JLabel();
        lbl_Nome = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        menu_cad = new javax.swing.JMenu();
        menu_Cli = new javax.swing.JMenuItem();
        menu_Os = new javax.swing.JMenuItem();
        menu_usu = new javax.swing.JMenuItem();
        menu_rel = new javax.swing.JMenu();
        menu_relCli = new javax.swing.JMenuItem();
        menu_ser = new javax.swing.JMenuItem();
        menu_aju = new javax.swing.JMenu();
        menu_sob = new javax.swing.JMenuItem();
        menu_opc = new javax.swing.JMenu();
        menu_sair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HouseTech - Tela Principal");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/reparar (1).png"))); // NOI18N
        titulo.setText("HouseTech");
        titulo.setToolTipText("");
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 220, 130));

        Desktop.setPreferredSize(new java.awt.Dimension(592, 431));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 592, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        getContentPane().add(Desktop, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 430));

        lbl_Perfil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_Perfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Perfil.setText("Usuário");
        lbl_Perfil.setToolTipText("");
        lbl_Perfil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_Perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, 220, -1));

        lbl_Data.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_Data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Data.setText("Data");
        getContentPane().add(lbl_Data, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 220, -1));

        lbl_Nome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_Nome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Nome.setText("Nome");
        lbl_Nome.setToolTipText("");
        lbl_Nome.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 170, 220, -1));

        menu_cad.setText("CADASTRO");

        menu_Cli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menu_Cli.setText("CLIENTES");
        menu_Cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_CliActionPerformed(evt);
            }
        });
        menu_cad.add(menu_Cli);

        menu_Os.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        menu_Os.setText("ORDEM DE SERVIÇO");
        menu_Os.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_OsActionPerformed(evt);
            }
        });
        menu_cad.add(menu_Os);

        menu_usu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        menu_usu.setText("USUÁRIOS");
        menu_usu.setEnabled(false);
        menu_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_usuActionPerformed(evt);
            }
        });
        menu_cad.add(menu_usu);

        Menu.add(menu_cad);

        menu_rel.setText("RELATÓRIO");
        menu_rel.setEnabled(false);

        menu_relCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        menu_relCli.setText("CLIENTES");
        menu_relCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_relCliActionPerformed(evt);
            }
        });
        menu_rel.add(menu_relCli);

        menu_ser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        menu_ser.setText("SERVIÇOS");
        menu_ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_serActionPerformed(evt);
            }
        });
        menu_rel.add(menu_ser);

        Menu.add(menu_rel);

        menu_aju.setText("AJUDA");

        menu_sob.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        menu_sob.setText("SOBRE");
        menu_sob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sobActionPerformed(evt);
            }
        });
        menu_aju.add(menu_sob);

        Menu.add(menu_aju);

        menu_opc.setText("OPÇÕES");

        menu_sair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menu_sair.setText("SAIR");
        menu_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sairActionPerformed(evt);
            }
        });
        menu_opc.add(menu_sair);

        Menu.add(menu_opc);

        setJMenuBar(Menu);

        setSize(new java.awt.Dimension(839, 489));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//Menu Sair
    private void menu_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sairActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_menu_sairActionPerformed
//Menu Sobre
    private void menu_sobActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sobActionPerformed
        TelaSobre telaSobre = new TelaSobre();
        telaSobre.setVisible(true);
    }//GEN-LAST:event_menu_sobActionPerformed
//Menu Usuário
    private void menu_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_usuActionPerformed
        TelaUsuario telaUsuario = new TelaUsuario();
        telaUsuario.setVisible(true);
        Desktop.add(telaUsuario);
    }//GEN-LAST:event_menu_usuActionPerformed
//Menu Clientes
    private void menu_CliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_CliActionPerformed
        TelaCliente telaCliente = new TelaCliente();
        telaCliente.setVisible(true);
        Desktop.add(telaCliente);
    }//GEN-LAST:event_menu_CliActionPerformed
//Menu OS
    private void menu_OsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_OsActionPerformed
        TelaOS telaOS = new TelaOS();
        telaOS.setVisible(true);
        Desktop.add(telaOS);
    }//GEN-LAST:event_menu_OsActionPerformed
//Menu Relatório Clientes 
    private void menu_relCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_relCliActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?","Impressão",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            try {
                //Preparando impressão
                JasperPrint print = JasperFillManager.fillReport("C:/Users/Armando/Documents/NetBeansProjects/SistemaAssistenciaTecnica/Relatorios/Clientes.jasper",null,conexao);
                //Exibir relatótio
                JasperViewer.viewReport(print,false);
            } catch (JRException erro) {
                JOptionPane.showMessageDialog(null,"Erro(Impressão)! \nErro:" + erro.getMessage(),"Impressão",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_menu_relCliActionPerformed
//Menu Relatório Serviços 
    private void menu_serActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_serActionPerformed
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão deste relatório?","Impressão",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            try {
                //Preparando impressão
                JasperPrint print = JasperFillManager.fillReport("C:/Users/Armando/Documents/NetBeansProjects/SistemaAssistenciaTecnica/Relatorios/Servicos.jasper",null,conexao);
                //Exibir relatótio
                JasperViewer.viewReport(print,false);
            } catch (JRException erro) {
                JOptionPane.showMessageDialog(null,"Erro(Impressão)! \nErro:" + erro.getMessage(),"Impressão",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_menu_serActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JLabel lbl_Data;
    public static javax.swing.JLabel lbl_Nome;
    public static javax.swing.JLabel lbl_Perfil;
    private javax.swing.JMenuItem menu_Cli;
    private javax.swing.JMenuItem menu_Os;
    private javax.swing.JMenu menu_aju;
    private javax.swing.JMenu menu_cad;
    private javax.swing.JMenu menu_opc;
    public static javax.swing.JMenu menu_rel;
    private javax.swing.JMenuItem menu_relCli;
    private javax.swing.JMenuItem menu_sair;
    public static javax.swing.JMenuItem menu_ser;
    private javax.swing.JMenuItem menu_sob;
    public static javax.swing.JMenuItem menu_usu;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
//ICONE DA JANELA
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/housetech/icones/reparar.png")));
    }
}
