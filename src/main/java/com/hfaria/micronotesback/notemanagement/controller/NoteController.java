package com.hfaria.micronotesback.notemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hfaria.micronotesback.authentication.service.AuthenticationService;
import com.hfaria.micronotesback.model.Note;
import com.hfaria.micronotesback.notemanagement.dto.NoteDTO;
import com.hfaria.micronotesback.notemanagement.service.NoteService;

@RestController
@RequestMapping(path="/api/notes")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(path="create")
	public ResponseEntity<Note> createNote(@RequestBody NoteDTO note) {
		noteService.createNote(note);
		return new ResponseEntity<Note>(HttpStatus.OK);
	}
	
	@GetMapping(path="/hello")
	public String hello() {
		return "Hello Notes!";
	}
	
	
}
