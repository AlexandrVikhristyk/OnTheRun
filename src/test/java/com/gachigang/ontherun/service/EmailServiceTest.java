package com.gachigang.ontherun.service;

import com.gachigang.ontherun.model.dto.EmailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private MailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendShouldCallMailSenderSendOnce() {
        final String email = "test@example.com";
        final String subject = "subject";
        final String content = "content";
        final EmailDto emailDto = EmailDto.builder()
                .to(email)
                .subject(subject)
                .content(content)
                .build();

        emailService.send(emailDto);

        verify(mailSender, only()).send(any(SimpleMailMessage.class));
    }
}