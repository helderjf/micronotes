package com.hfaria.micronotesback.notemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfaria.micronotesback.authentication.service.AuthenticationService;
import com.hfaria.micronotesback.model.Note;
import com.hfaria.micronotesback.model.User;
import com.hfaria.micronotesback.notemanagement.dto.NoteDTO;
import com.hfaria.micronotesback.notemanagement.service.NoteService;

@RestController
@RequestMapping(path = "/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello Notes!";
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Note> createNote(@RequestBody NoteDTO note) {
        noteService.createNote(note);
        return new ResponseEntity<Note>(HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<NoteDTO>> showUserNotes() {
        List<NoteDTO> notes = noteService.getUserNotesDTO();
        return new ResponseEntity<List<NoteDTO>>(notes, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> viewNote(@PathVariable @RequestBody Long id) {
        User user = getCurrentUser();
        NoteDTO note = noteService.getNoteDTOFromOwner(id, user.getId());
        if (note != null) {
            return new ResponseEntity<NoteDTO>(note, HttpStatus.OK);
        }
        return new ResponseEntity<NoteDTO>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable @RequestBody Long id) {
        User user = getCurrentUser();

        if (noteService.deleteNoteFromOwner(id, user.getId())) {
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }

    
    @PutMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> editNote(@RequestBody NoteDTO editedNoteDTO) {
        NoteDTO updatedNoteDTO = new NoteDTO(noteService.updateNote(editedNoteDTO));
        return new ResponseEntity<NoteDTO>(updatedNoteDTO,HttpStatus.OK);
    }

    private User getCurrentUser() {
        User user = authenticationService.getCurrentUser()
                .orElseThrow(() -> new IllegalArgumentException("No user logged in."));
        return user;
    }

}
