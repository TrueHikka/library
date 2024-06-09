package ru.maxima.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.dao.book_dao.BookDAO;
import ru.maxima.dao.book_dao.BookDAOClass;
import ru.maxima.dao.person_dao.PersonDAO;
import ru.maxima.dao.person_dao.PersonDAOCLass;
import ru.maxima.models.Book;
import ru.maxima.models.Person;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final String redirectAllBooks = "redirect:/books";

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getAllBooks(Model model) {
        List<Book>  books = bookDAO.getAllBooks();
        model.addAttribute("allBooks", books);
        return "books/view-all-books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") Long bookId, Model model) {
        Book book = bookDAO.findBookById(bookId);
        List<Person> people = personDAO.getAllPeople();
        model.addAttribute("book", book);
        model.addAttribute("allPeople", people);


//        System.out.println("Book ID: " + bookId);
//        System.out.println("Book Person ID: " + book.getPersonId());

        return "books/view-book";
    }

    @GetMapping("/new")
    public String getPageToCreateNewBook(Model model) {
        model.addAttribute("newBook", new Book());
        return "books/view-to-create-new-book";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("newBook") @Valid Book book, BindingResult result) {
        if(result.hasErrors()) {
            return "books/view-to-create-new-book";
        }
        bookDAO.save(book);
        return redirectAllBooks;
    }

    @GetMapping("/{id}/edit")
    public String getPageToEditBook(@PathVariable("id") Long bookId, Model model) {
        Book book = bookDAO.findBookById(bookId);
        model.addAttribute("editedBook", book);
        return "books/view-to-edit-book";
    }

    @PostMapping("/{id}")
    public String updateBook(@ModelAttribute("bookById") @Valid Book book, BindingResult result, @PathVariable("id") Long bookId) {
        if(result.hasErrors()) {
            return "books/view-to-edit-book";
        }
        bookDAO.update(book, bookId);
        return redirectAllBooks;
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") Long bookId) {
        bookDAO.delete(bookId);
        return redirectAllBooks;
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") Long bookId, @RequestParam("personId") Long personId) {
        bookDAO.assignBookToPerson(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{id}/free")
    public String freeBook(@PathVariable("id") Long bookId) {
        bookDAO.freeBook(bookId);
        return "redirect:/books/" + bookId;
    }
}
