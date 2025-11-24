package com.learnkafka.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@ToString
public class LibraryEvent {

    @Id
    @GeneratedValue
    private Integer libraryEventId;

    @Enumerated(EnumType.STRING)
    private LibraryEventType libraryEventType;

    @JsonManagedReference
    @OneToOne(mappedBy = "libraryEvent", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Book book;
}
