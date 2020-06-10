package com.teco.market.search;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudyController {

    @GetMapping("/study")
    public ResponseEntity<StudySearch> hello (@ModelAttribute StudySearch studySearch) {
        return ResponseEntity.ok(studySearch);
    }
}
