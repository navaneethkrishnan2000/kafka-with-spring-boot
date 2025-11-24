package com.learnkafka.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    @Id
    @GeneratedValue
    private Integer bookId;
    private String bookName;
    private String bookAuthor;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "libraryEventId")
    private LibraryEvent libraryEvent;

}
