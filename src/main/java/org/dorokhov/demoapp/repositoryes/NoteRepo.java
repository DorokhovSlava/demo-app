package org.dorokhov.demoapp.repositoryes;
import org.dorokhov.demoapp.entityes.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo extends JpaRepository<Note, Integer> {

}
