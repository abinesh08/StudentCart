package com.abi.studentcart.student.listener;


import com.abi.studentcart.event.DocumentPrintedEvent;
import com.abi.studentcart.student.model.StudentBaseData;
import com.abi.studentcart.student.repository.StudentBaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentNotificationListener {
    private final StudentBaseDataRepository studentBaseDataRepository;
    private final JavaMailSender mailSender;

    @EventListener
    public void handleDocumentPrinted(DocumentPrintedEvent event){
        String rollNumber= event.getRollNumber();
        StudentBaseData baseData= studentBaseDataRepository.getByRollNumber(rollNumber)
                .orElseThrow(()-> new RuntimeException("Student not found " + rollNumber));
        sendEmail(baseData.getEmail(), baseData.getRollNumber());
    }

    private void sendEmail(String to, String name){
        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your Document is ready !");
        message.setText("Hi " + name + ",\n\nYour document has been printed and is ready for collection.\n\nRegards,\nStudentCart Team");

        mailSender.send(message);
    }
}
