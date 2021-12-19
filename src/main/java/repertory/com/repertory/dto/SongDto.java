package repertory.com.repertory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.model.Song;

@Getter
@Setter
public class SongDto {
	
	public static SongDto from(Song music) {
		SongDto songDto = new SongDto();
		
		songDto.setArtistName(music.getArtistName());
		songDto.setId(music.getId());
		songDto.setTitle(music.getTitle());
		
		return songDto;
		
	}
	
	@JsonIgnoreProperties(allowSetters = true)
	private Long id; 
	private String title;
	private String artistName;

}
