package org.dorokhov.demoapp.controllers;
import org.dorokhov.demoapp.entityes.Note;
import org.dorokhov.demoapp.repositoryes.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/note")
public class NoteController {

    private final NoteRepo noteRepository;

    @Autowired
    public NoteController(NoteRepo noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> noteList = noteRepository.findAll();
        return ResponseEntity.ok().body(noteList);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note notes = noteRepository.saveAndFlush(note);
        return ResponseEntity.ok().body(notes);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable("id") Integer note_id )
            throws EntityNotFoundException {
        Optional<Note> note = noteRepository.findById(note_id);
        if (note.isEmpty()) throw new EntityNotFoundException("id" + note_id);
        noteRepository.deleteById(note_id);
        return ResponseEntity.ok().body(note.get());
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Note> updateNoteById(@RequestBody @Validated Note note, @PathVariable("id") Integer note_id)
            throws EntityNotFoundException{
        Optional<Note> noteOptional = noteRepository.findById(note_id);
        if (noteOptional.isEmpty()) throw new EntityNotFoundException("note id " + note_id + " not found");
        return ResponseEntity.ok(noteRepository.saveAndFlush(note));
    }
}
