package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.DTOs.AuthorEntryDto;
import com.example.StudentLibraryManagement.DTOs.AuthorResponseDto;
import com.example.StudentLibraryManagement.DTOs.BookResponseDto;
import com.example.StudentLibraryManagement.Models.Author;
import com.example.StudentLibraryManagement.Models.Book;
import com.example.StudentLibraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(AuthorEntryDto authorEntryDto)
    {
        Author author=new Author();
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRatings(authorEntryDto.getRatings());
        authorRepository.save(author);
        return "author added";
    }

    public AuthorResponseDto getAuthor(Integer authorId){

        Author author =  authorRepository.findById(authorId).get();
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        //Set its attributes.
        //List<Book> --> List<BookResponseDto>
        List<Book> bookList = author.getListOfBooks();

        List<BookResponseDto> booksWrittenDto = new ArrayList<>();

        for(Book b : bookList){

            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setPages(b.getPages());
            bookResponseDto.setName(b.getName());

            booksWrittenDto.add(bookResponseDto);
        }
        //Set attributes for authorResponse Dto
        authorResponseDto.setBooksWritten(booksWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRatings());

        return authorResponseDto;

    }
}
