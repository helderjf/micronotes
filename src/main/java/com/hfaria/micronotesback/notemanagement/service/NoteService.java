package com.hfaria.micronotesback.notemanagement.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfaria.micronotesback.model.Note;
import com.hfaria.micronotesback.notemanagement.dto.NoteDTO;
import com.hfaria.micronotesback.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;
	
	public Note createNote(NoteDTO note) {
		Note newNote = new Note();
		newNote.setTitle(note.title);
		newNote.setText(note.text);
		newNote.setDateCreated(new Date());
		newNote.setDateEdited(new Date());
		
		return noteRepository.save(newNote);
	}
	
	
}
