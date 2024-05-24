package ru.maxima.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Person {
    private Long id;

    @NotEmpty(message = "Name is required")
    @Size(min=2, max=50, message="Name must be between 2 and 50 symbols")
    private String name;

    @Min(value = 5, message = "Age should be min 5 years")
    private Integer birthYear;

    private List<Book> books;
}


