package com.training.SpringBootRESTRepo.entity;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name ="book_table")
public class Book {
    @Id//PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;
    @Column(unique = true, nullable = false, length = 100)

    private String title;
    @NotNull(message = "Author cannot be empty")
    private String author;
    @Column(name="description")
    private String desc;
    @Column(columnDefinition = "decimal(10,2) default 100.0")
    @Positive(message = "Price cannot be negative")
    private double price;

    public Book(String title, String author, String desc, double price) {
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.price = price; }
}
