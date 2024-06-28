package ru.maxima.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.models.Book;
import ru.maxima.models.Person;
import ru.maxima.service.book_service.BookServiceInf;
import ru.maxima.service.people_service.PeopleServiceinf;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleServiceinf peopleServiceinf;
    private final BookServiceInf bookServiceInf;
    private final String redirectAllPeople = "redirect:/people";

    @Autowired
    public PeopleController(PeopleServiceinf peopleServiceinf, BookServiceInf bookServiceInf) {
        this.peopleServiceinf = peopleServiceinf;
        this.bookServiceInf = bookServiceInf;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        List<Person> people = peopleServiceinf.getAllPeople();
        model.addAttribute("allPeople", people);
        return "people/view-all-people";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable("id") Long id, Model model) {
        Person person = peopleServiceinf.findPersonById(id);
        List<Book> books = bookServiceInf.getAllBooks();
        model.addAttribute("personById", person);
        model.addAttribute("books", books);
        return "people/view-person";
    }

    @GetMapping("/new")
    public String getPageToCreateNewPerson(Model model) {
        model.addAttribute("newPerson", new Person());
        return "people/view-to-create-new-person";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("newPerson") @Valid Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "people/view-to-create-new-person";
        }
        peopleServiceinf.save(person);
        return redirectAllPeople;
    }

    @GetMapping("/{id}/edit")
    public String getPageToEditPerson(@PathVariable("id") Long id, Model model) {
        Person person = peopleServiceinf.findPersonById(id);
        model.addAttribute("editedPerson", person);
        return "people/view-to-edit-person";
    }

    @PostMapping("/{id}")
    public String editPerson(@PathVariable("id") Long id, @ModelAttribute("editedPerson") @Valid Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "people/view-to-edit-person";
        }
        peopleServiceinf.update(person, id);
        return redirectAllPeople;
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") Long id) {
        peopleServiceinf.delete(id);
        return redirectAllPeople;
    }
}
