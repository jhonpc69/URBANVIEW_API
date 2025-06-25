package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Services.emailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements emailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendVerificationCode(String to, String code) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Tu código de verificación");

            String htmlContent = """
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                      <meta charset="UTF-8">
                      <title>Código de Verificación</title>
                      <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f7; margin: 0; padding: 0; }
                        .container { max-width: 600px; margin: 40px auto; background-color: #ffffff;
                                     border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.05); overflow: hidden; }
                        .header { background-color: #F56F54; color: #ffffff; text-align: center; padding: 20px 0; }
                        .header h1 { margin: 0; font-size: 24px; }
                        .content { padding: 30px; color: #333333; }
                        .code-box {
                          background-color: #f0f4ff; color: #2c3e50; font-size: 24px; font-weight: bold;
                          text-align: center; padding: 15px; border-radius: 6px; letter-spacing: 3px; margin: 20px 0;
                        }
                        .footer { text-align: center; font-size: 13px; color: #888888; padding: 20px; }
                      </style>
                    </head>
                    <body>
                      <div class="container">
                        <div class="header">
                          <h1>Verificación de Seguridad</h1>
                        </div>
                        <div class="content">
                          <p>Hola,</p>
                          <p>Estás a un paso de completar tu proceso de verificación. Por favor, usa el siguiente código:</p>
                          <div class="code-box">%s</div>
                          <p>Este código expirará en 10 minutos. Si no has solicitado este código, puedes ignorar este mensaje.</p>
                          <p>Gracias,<br>El equipo de Soporte de UrbanView</p>
                        </div>
                        <div class="footer">
                          © 2025 UbanView Todos los derechos reservados.
                        </div>
                      </div>
                    </body>
                    </html>
                    """.formatted(code);

            helper.setText(htmlContent, true); // true = es HTML

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo de verificación", e);
        }
    }
}
