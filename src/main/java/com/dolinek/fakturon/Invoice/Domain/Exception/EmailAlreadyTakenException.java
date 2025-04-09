package com.dolinek.fakturon.Invoice.Domain.Exception;

public class EmailAlreadyTakenException extends RuntimeException
{
    public EmailAlreadyTakenException(String message)
    {
        super(message);
    }
}
