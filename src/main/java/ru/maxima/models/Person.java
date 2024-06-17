package ru.maxima.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name  =  "id")
    @GeneratedValue(strategy  =  GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max=50, message="Name must be less than 50 characters")
    @Pattern(regexp = "^[A-Za-z]+ [A-Za-z]+ [A-Za-z]+$", message = "Name must contain full name (first, middle and last name)")
    @Column(name = "full_name")
    private String name;

    @Min(value = 1950, message = "Birth year must be greater than or equal to 1950")
    @Column(name  =  "birth_year")
    private Integer birthYear;

    @OneToMany(mappedBy = "bookOwner", fetch = FetchType.LAZY)
    private List<Book> books;
}


