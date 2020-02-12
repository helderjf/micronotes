package com.hfaria.micronotesback.notemanagement.dto;

import com.hfaria.micronotesback.model.Note;

public class NoteDTO {

	public String id;
	public String title;
	public String text;
	public String dateCreated;
	public String dateEdited;
	public String ownerId;
	
	public NoteDTO() {
	}
	
	public NoteDTO(Note note) {
	    id = note.getId().toString();
	    title = note.getTitle();
	    text = note.getText();
	    dateCreated = note.getDateCreated().toString();
	    dateEdited = note.getDateEdited().toString();
	    ownerId = note.getOwner().getId().toString();
	    
	}

}
