package com.learnkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learnkafka.domain.LibraryEvent;
import com.learnkafka.domain.LibraryEventType;
import com.learnkafka.producer.LibraryEventsProducer;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@RequestMapping("/v1/libraryevent")
public class LibraryEventsController {

    private final LibraryEventsProducer libraryEventsProducer;

    public LibraryEventsController(LibraryEventsProducer libraryEventsProducer) {
        this.libraryEventsProducer = libraryEventsProducer;
    }

    @PostMapping("/")
    public ResponseEntity<LibraryEvent> postLibraryEvent(
            @Valid @RequestBody LibraryEvent event
    ) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        log.info("libraryEvent: {}", event);

        // Invoke the kafka producer
//        libraryEventsProducer.sendLibraryEvent(event);
//        libraryEventsProducer.sendLibraryEventSynchronously(event);
        libraryEventsProducer.sendLibraryEvent_approach3(event);

        log.info("After sending library event");
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateLibraryEvent(
            @Valid @RequestBody LibraryEvent event
    ) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        if (event.libraryEventId()==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the library event id");
        }

        if (!event.libraryEventType().equals(LibraryEventType.UPDATE)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only update event type is supported");
        }

        libraryEventsProducer.sendLibraryEvent_approach3(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }


}
