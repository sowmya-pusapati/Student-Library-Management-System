package com.example.StudentLibraryManagement.Controller;

import com.example.StudentLibraryManagement.DTOs.AuthorEntryDto;
import com.example.StudentLibraryManagement.DTOs.AuthorResponseDto;
import com.example.StudentLibraryManagement.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/addauthor")
    public String addAuthor(@RequestBody AuthorEntryDto authorEntryDto)
    {
          return authorService.addAuthor(authorEntryDto);
    }

    @GetMapping("/getAuthor")
    public AuthorResponseDto getAuthor(@RequestParam("authorId")Integer authorId){
        return authorService.getAuthor(authorId);
    }

}
