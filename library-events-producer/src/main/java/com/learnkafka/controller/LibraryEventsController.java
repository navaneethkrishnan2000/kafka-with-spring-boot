package com.learnkafka.controller;

import com.learnkafka.domain.LibraryEvent;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/libraryevent")
public class LibraryEventsController {

    @PostMapping("/")
    public ResponseEntity<LibraryEvent> postLibraryEvent(
            @Valid @RequestBody LibraryEvent event
    ) {
        log.info("libraryEvent: {}", event);
        // invoke the kafka producer
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }
}
