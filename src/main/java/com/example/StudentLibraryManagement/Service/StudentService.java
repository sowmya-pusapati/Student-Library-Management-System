package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.Enums.CardStatus;
import com.example.StudentLibraryManagement.Models.LibraryCard;
import com.example.StudentLibraryManagement.Models.Student;
import com.example.StudentLibraryManagement.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public String createStudent(Student student)
    {
        LibraryCard card=new LibraryCard();
        card.setCardstatus(CardStatus.ACTIVATED);
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "student and card added";
    }

    public String getNameByEmail(String emailId)
    {
        Student student=studentRepository.findByEmailId(emailId);
        return student.getName();
    }

    public String updateMobileNo(Student student)
    {
        Student oldstudent=studentRepository.findById(student.getId()).get();
        oldstudent.setMobileNumber(student.getMobileNumber());
        studentRepository.save(oldstudent);
        return "mobilenumber has been updated";
    }
}
