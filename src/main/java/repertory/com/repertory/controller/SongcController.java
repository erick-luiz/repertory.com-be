package repertory.com.repertory.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repertory.com.repertory.dto.SongDto;
import repertory.com.repertory.model.Song;
import repertory.com.repertory.service.SongService;

@RequestMapping("/songs")
@RestController
public class SongcController {

	
	private SongService songService; 
	
	SongcController(SongService songService){
		this.songService = songService;
	}
	
	@PostMapping
	public Song create(@Valid @RequestBody SongDto songDto) {
		return songService.create(songDto); 
	}
	
	@GetMapping
	public List<SongDto> get(){
		return songService.getAllSongs().stream().map(SongDto::from).toList();
	}
	
	@GetMapping("/{id}")
	public SongDto get(@PathVariable String id){
		return SongDto.from(songService.getSongById(Long.valueOf(id)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		if(id != null && id.matches("\\d*")) {
			songService.delete(Long.valueOf(id));
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
}
