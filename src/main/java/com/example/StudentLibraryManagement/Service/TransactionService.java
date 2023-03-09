package com.example.StudentLibraryManagement.Service;

import com.example.StudentLibraryManagement.DTOs.IssueBookRequestDto;
import com.example.StudentLibraryManagement.Enums.CardStatus;
import com.example.StudentLibraryManagement.Enums.TransactionStatus;
import com.example.StudentLibraryManagement.Models.Book;
import com.example.StudentLibraryManagement.Models.LibraryCard;
import com.example.StudentLibraryManagement.Models.Transactions;
import com.example.StudentLibraryManagement.Repositories.BookRepository;
import com.example.StudentLibraryManagement.Repositories.CardRepository;
import com.example.StudentLibraryManagement.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;

    public String issueBook(@RequestBody IssueBookRequestDto issueBookRequestDto) throws Exception
    {
        int bookId=issueBookRequestDto.getBookId();
        int cardId=issueBookRequestDto.getCardId();

        Book book=bookRepository.findById(bookId).get();
        LibraryCard card=cardRepository.findById(cardId).get();

        Transactions transactions=new Transactions();

        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        if(book==null||book.isIssued()==true)
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Book not available");
        }

        if(card==null||card.getCardstatus()!= CardStatus.ACTIVATED)
        {
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card not valid");
        }

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        book.setIssued(true);
        book.getTransactionsList().add(transactions);

        card.getBookList().add(book);

        for(Book b:card.getBookList())
        {
            System.out.println(b.getName());
        }

        card.getTransactionsList().add(transactions);
        cardRepository.save(card);
        return "Book Issued Successfully";

    }

    public String getTransactionId(int bookId,int cardId)
    {
        List<Transactions> getTransactions=transactionRepository.getTransactionsForBookAndCard( bookId, cardId);
        String transactionId=getTransactions.get(0).getTransactionId();
        return transactionId;


    }
}
