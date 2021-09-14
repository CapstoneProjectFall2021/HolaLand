package com.hola.holalandweb.util;

public interface SendEmailService {

    void send(String subject, String text, String... toEmails);
}
