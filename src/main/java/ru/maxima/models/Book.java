package ru.maxima.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy  =  GenerationType.IDENTITY)
    @Column(name  =  "book_id")
    private Long bookId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should not be empty")
    @Column(name  = "title")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=2, max=50, message="Author name must be between 2 and 50 symbols")
    @Column(name   = "author")
    private String author;

    @NotNull(message = "Year should not be null")
    @Column(name  = "year_of_publication")
    private Integer year;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person bookOwner;
}
