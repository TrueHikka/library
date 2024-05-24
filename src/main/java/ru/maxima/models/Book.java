package ru.maxima.models;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private Long bookId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 255, message = "Title should not be empty")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min=2, max=50, message="Author name must be between 2 and 50 symbols")
    private String author;

    @NotNull(message = "Year should not be null")
    private Integer year;

    private Long personId;
}
