package com.hfaria.micronotesback.notemanagement.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hfaria.micronotesback.authentication.service.AuthenticationService;
import com.hfaria.micronotesback.model.Note;
import com.hfaria.micronotesback.model.User;
import com.hfaria.micronotesback.notemanagement.dto.NoteDTO;
import com.hfaria.micronotesback.repository.NoteRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private AuthenticationService authenticationService;
	
	public Note createNote(NoteDTO note) {
		Note newNote = new Note();
		newNote.setTitle(note.title);
		newNote.setText(note.text);
		newNote.setDateCreated(new Date());
		newNote.setDateEdited(new Date());
		
		User owner = authenticationService.getCurrentUser()
		        .orElseThrow(()-> new IllegalArgumentException("No user loggedin."));
		newNote.setOwner(owner);
		
		return noteRepository.save(newNote);
	}

    public List<NoteDTO> getUserNotesDTO() {
        User owner = getCurrentUser();
        List<Note> notes = noteRepository.findByOwner(owner).get();
        List<NoteDTO> dtoNotes = new ArrayList<NoteDTO>();
        for (Note note : notes) {
            dtoNotes.add(new NoteDTO(note));
        }
        return dtoNotes;
    }


    public NoteDTO getNoteDTO(Long id) {
        Note note = noteRepository.findById(id).get();
        return new NoteDTO(note);
    }
    
    
//    public boolean isOwner(Long noteId, Long ownerId) {
//        Note note = noteRepository.findById(noteId).get();
//        return note.getOwner().getId().equals(ownerId);
//    }
	
    private User getCurrentUser() {
        User owner = authenticationService.getCurrentUser()
                .orElseThrow(()-> new IllegalArgumentException("No user loggedin."));
        return owner;
    }

    public NoteDTO getNoteDTOFromOwner(Long noteId, Long ownerId) {
        Note note = noteRepository.findById(noteId).get();
        if(note.getOwner().getId().equals(ownerId)) {
            return new NoteDTO(note);
        }
        return null;
    }

    public boolean deleteNoteFromOwner(Long noteId, Long ownerId) {
        Note note = noteRepository.findById(noteId).get();
        if(note.getOwner().getId().equals(ownerId)) {
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }

    public Note updateNote(NoteDTO updatedNoteDTO) {
        Note note = noteRepository.findById(Long.parseLong(updatedNoteDTO.id)).get();
        User currentUser = getCurrentUser();
        
        if(currentUser.equals(note.getOwner())) {
            note.setTitle(updatedNoteDTO.title);
            note.setText(updatedNoteDTO.text);
            note.setDateEdited(new Date());
            noteRepository.save(note);
        }
        return note;
    }
	
}
