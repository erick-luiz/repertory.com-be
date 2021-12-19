package repertory.com.repertory.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import repertory.com.repertory.dto.SongDto;
import repertory.com.repertory.exeption.ResourceNotFoundException;
import repertory.com.repertory.model.Song;
import repertory.com.repertory.repository.SongRepository;

@Service
public class SongService {

	private SongRepository songRepostiry;	
	
	SongService(SongRepository songRepostiry){
		this.songRepostiry = songRepostiry;
	}
	
	
	public Song create(SongDto songDto) {
		Song song = Song.from(songDto);
		
		Song savedMusic = songRepostiry.save(song);
	
		return savedMusic;
	}


	public List<Song> getAllSongs() {
		return songRepostiry.findAll();
	}

	@Transactional
	public void delete(Long id) {
		Song song = songRepostiry.getById(id);
		if(song != null) {
			songRepostiry.deleteById(id);			
		}
	}


	public Song getSongById(Long id) {
		return songRepostiry.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}


	public List<Song> getAllSongs(List<Long> songIds) {

		return songRepostiry.findAllById(songIds);
	}
	
}
