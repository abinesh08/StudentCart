package com.abi.studentcart.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class DocumentPrintedEvent extends ApplicationEvent {
    private final String rollNumber;

    public DocumentPrintedEvent(Object source, String rollNumber){
        super(source);
        this.rollNumber=rollNumber;
    }
}
