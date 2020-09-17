package br.com.housetech.telas;
/*
 * @author Armando
 */
import java.sql.*;
import br.com.housetech.dal.ModuloConexao;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Data do Sistema
    Calendar c = Calendar.getInstance();
    java.util.Date data = c.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //Método Logar
    public void logar() {
        String sql = "SELECT * FROM tab_usuarios WHERE login=? AND senha=?";
        try {
            //Preparar as consultas ao banco de dados
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_Usuario.getText());
            pst.setString(2, txt_Senha.getText());
            //executando a consulta (query)
            rs = pst.executeQuery();
            // se existir usuario e senha correspondente
            if (rs.next()) {
                //Obter o conteúdo do campo perfil e usuario tab_usuarios
                String usuarios = rs.getString(2);
                String perfil = rs.getString(6);
                //Tratamento de perfil
                TelaPrincipal telaPrincipal = new TelaPrincipal();
                if (perfil.equals("Administrador")) {
                    TelaPrincipal.menu_usu.setEnabled(true);
                    TelaPrincipal.menu_rel.setEnabled(true);
                    TelaPrincipal.lbl_Perfil.setForeground(Color.red);
                }
                    TelaPrincipal.lbl_Perfil.setText(perfil);
                    TelaPrincipal.lbl_Nome.setText(usuarios);
                    telaPrincipal.setVisible(true);
                    this.dispose();
                conexao.close();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou Senha inválido!","Login",JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro: "+ erro.getMessage(),"Login",JOptionPane.ERROR_MESSAGE);
        }
    }

    public TelaLogin() {
        initComponents();
        setIcon();
        conexao = ModuloConexao.conector();
        lbl_data.setText("" + sdf.format(data));
        //Icone do banco
        if (conexao != null) {
            iconeBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/bancodedadossim.png")));
        } else {
            iconeBanco.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/bancodedadosnao.png")));
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuario = new javax.swing.JLabel();
        senha = new javax.swing.JLabel();
        iconeBanco = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        lbl_data = new javax.swing.JLabel();
        txt_Usuario = new javax.swing.JTextField();
        txt_Senha = new javax.swing.JPasswordField();
        btn_Entrar = new javax.swing.JButton();
        btn_Sair = new javax.swing.JButton();
        btn_insta = new javax.swing.JButton();
        descricao = new javax.swing.JLabel();
        btn_zap = new javax.swing.JButton();
        btn_face = new javax.swing.JButton();
        tel = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HouseTech - Login");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/usuario.png"))); // NOI18N
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        senha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        senha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/cadeado.png"))); // NOI18N
        getContentPane().add(senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));
        getContentPane().add(iconeBanco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 50, 37));

        titulo.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        titulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/reparar (1).png"))); // NOI18N
        titulo.setText("HouseTech");
        titulo.setToolTipText("");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        lbl_data.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbl_data.setForeground(new java.awt.Color(153, 153, 153));
        lbl_data.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_data.setText("Data");
        getContentPane().add(lbl_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 170, 30));

        txt_Usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        txt_Usuario.setText("Apvictor");
        txt_Usuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_Usuario.setOpaque(false);
        getContentPane().add(txt_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 183, 30));

        txt_Senha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Senha.setForeground(new java.awt.Color(255, 255, 255));
        txt_Senha.setText("0312");
        txt_Senha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_Senha.setOpaque(false);
        getContentPane().add(txt_Senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 183, 30));

        btn_Entrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Entrar.setText("ENTRAR");
        btn_Entrar.setBorder(null);
        btn_Entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 80, 30));

        btn_Sair.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Sair.setText("SAIR");
        btn_Sair.setBorder(null);
        btn_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SairActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 80, 30));

        btn_insta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/instagram.png"))); // NOI18N
        btn_insta.setContentAreaFilled(false);
        btn_insta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_instaActionPerformed(evt);
            }
        });
        getContentPane().add(btn_insta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 40, 30));

        descricao.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        descricao.setText("Assistência Técnica de Celulares");
        getContentPane().add(descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        btn_zap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/whatsapp.png"))); // NOI18N
        btn_zap.setContentAreaFilled(false);
        btn_zap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_zapActionPerformed(evt);
            }
        });
        getContentPane().add(btn_zap, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 40, 30));

        btn_face.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/facebook.png"))); // NOI18N
        btn_face.setContentAreaFilled(false);
        btn_face.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_faceActionPerformed(evt);
            }
        });
        getContentPane().add(btn_face, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, 40, 30));

        tel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/telefone.png"))); // NOI18N
        tel.setText("(11) 99505-2373");
        getContentPane().add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, -1, -1));

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/housetech/icones/capa.png"))); // NOI18N
        getContentPane().add(fundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 400));

        setSize(new java.awt.Dimension(579, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//Botão Facebook
    private void btn_faceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_faceActionPerformed
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.facebook.com/Armandinho2019/"));
        } catch (URISyntaxException erro1) {
            System.out.println("Falha1: " + erro1);
        } catch (IOException erro2) {
            System.out.println("Falha2: " + erro2);
        }
    }//GEN-LAST:event_btn_faceActionPerformed
//Botão Insta
    private void btn_instaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_instaActionPerformed
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.instagram.com/housetech_2020/"));
        } catch (URISyntaxException erro1) {
            System.out.println("Falha1: " + erro1);
        } catch (IOException erro2) {
            System.out.println("Falha2: " + erro2);
        }
    }//GEN-LAST:event_btn_instaActionPerformed
//Botão Zap
    private void btn_zapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_zapActionPerformed
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://chat.whatsapp.com/Dc4viBvaNSQA7RfocmUnKR"));
        } catch (URISyntaxException erro1) {
            System.out.println("Falha1: " + erro1);
        } catch (IOException erro2) {
            System.out.println("Falha2: " + erro2);
        }
    }//GEN-LAST:event_btn_zapActionPerformed
//Bptão Sair
    private void btn_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SairActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_SairActionPerformed
//Botão Entrar
    private void btn_EntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EntrarActionPerformed
        //Validações dos campos
        if (!txt_Usuario.getText().isEmpty() && !txt_Senha.getText().isEmpty()) {
            logar();
        } else {
            JOptionPane.showMessageDialog(null, "Preencher Usuário e Senha!", "Login", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btn_EntrarActionPerformed

   public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Entrar;
    private javax.swing.JButton btn_Sair;
    private javax.swing.JButton btn_face;
    private javax.swing.JButton btn_insta;
    private javax.swing.JButton btn_zap;
    private javax.swing.JLabel descricao;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel iconeBanco;
    private javax.swing.JLabel lbl_data;
    private javax.swing.JLabel senha;
    private javax.swing.JLabel tel;
    private javax.swing.JLabel titulo;
    private javax.swing.JPasswordField txt_Senha;
    private javax.swing.JTextField txt_Usuario;
    private javax.swing.JLabel usuario;
    // End of variables declaration//GEN-END:variables
//ICONE DA JANELA
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/br/com/housetech/icones/reparar.png")));
    }
}