package repertory.com.repertory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repertory.com.repertory.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

}
