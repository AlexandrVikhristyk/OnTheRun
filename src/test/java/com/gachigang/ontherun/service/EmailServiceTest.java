package com.gachigang.ontherun.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private MailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> captor;

    @Test
    void testSendShouldCallMailSenderSendOnce() {
        final String email = "test@example.com";
        final String subject = "subject";
        final String content = "content";

        emailService.send(email, subject, content);

        verify(mailSender).send(captor.capture());
        SimpleMailMessage capturedMailMessage = captor.getValue();

        assertEquals(email, Objects.requireNonNull(capturedMailMessage.getTo())[0]);
        assertEquals(subject, capturedMailMessage.getSubject());
        assertEquals(content, capturedMailMessage.getText());
    }
}