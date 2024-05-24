package ru.maxima.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Person {
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(max=50, message="Name must be less than 50 characters")
    @Pattern(regexp = "^[A-Za-z]+ [A-Za-z]+ [A-Za-z]+$", message = "Name must contain full name (first, middle and last name)")
    private String name;

    @Min(value = 1950, message = "Birth year must be greater than or equal to 1950")
    private Integer birthYear;

    private List<Book> books;
}


