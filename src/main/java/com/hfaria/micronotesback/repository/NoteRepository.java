package com.hfaria.micronotesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hfaria.micronotesback.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long>{

}
