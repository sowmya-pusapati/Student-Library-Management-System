package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.Models.Student;
import com.example.StudentLibraryManagement.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/addstudent")
    public String createStudent(@RequestBody Student student)
    {
        return studentService.createStudent(student);
    }

    @GetMapping("/getstudent")
    public String getNameByEmail(@RequestParam String emailId)
    {
        return studentService.getNameByEmail(emailId);
    }

    @PutMapping("/updatestudent")
    public String updateMobileNo(@RequestBody Student student)
    {
        return studentService.updateMobileNo(student);
    }

}
