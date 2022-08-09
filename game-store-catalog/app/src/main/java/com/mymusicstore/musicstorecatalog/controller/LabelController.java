package com.mymusicstore.musicstorecatalog.controller;

import com.mymusicstore.musicstorecatalog.model.Label;
import com.mymusicstore.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LabelController {
    @Autowired
    LabelRepository labelRepository;

    @GetMapping("/labels")
    public List<Label> getAllLabels() {
        return labelRepository.findAll();
    }

    @GetMapping("/label/{id}")
    public Label getLabelById(@PathVariable("id") Long id) {
        Optional<Label> returnVal = labelRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/label")
    @ResponseStatus(HttpStatus.CREATED)
    public Label addLabel(@RequestBody Label label) {
        return labelRepository.save(label);
    }

    @PutMapping("/label/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelById(@PathVariable("id") Long id, @RequestBody Label label) {
        labelRepository.save(label);
    }

    @DeleteMapping("/label/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelById(@PathVariable("id") Long id) {
        labelRepository.deleteById(id);
    }
}
