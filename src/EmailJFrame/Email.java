package EmailJFrame;

import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Email {

    //Método
    public void enviar(String nome, String destino, String caminho, String nome_arquivo) {
        //Credenciais
        String meuEmail = "armandinho14.ap@gmail.com";
        String minhaSenha = "C#misa10";

        //Configurações do Email
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
        email.setSSLOnConnect(true);

        try {
            //Origem
            email.setFrom(meuEmail);
            //Assunto
            email.setSubject("HouseTech - OS");
            //Mensagem
            email.setMsg("Caro(a), " + nome + "\n"
                    + "\n"
                    + "O objetivo dessa mensagem é informar a confirmação da Ordem de Serviço do(s) produto(s) deixado(s) conosco.\n"
                    + "\n"
                    + "A Ordem de Serviço está em anexo.\n"
                    + "\n"
                    + "Se houver dúvida, entre em contato pelo telefone.\n"
                    + "\n"
                    + "Agradecemos pela sua confiança.");
            //Destinatário
            email.addTo(meuEmail);

            //Anexando Arquivo
            EmailAttachment anexo = new EmailAttachment();
            //Caminho do arquivo
            anexo.setPath(caminho);
            //Nomear o Arquivo
            anexo.setName(nome_arquivo);
            //vinculando ao email
            email.attach(anexo);
            //Enviar
            email.send();
            JOptionPane.showMessageDialog(null, "Enviado com Sucesso!");

        } catch (EmailException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Email", JOptionPane.ERROR_MESSAGE);
        }

    }
}
