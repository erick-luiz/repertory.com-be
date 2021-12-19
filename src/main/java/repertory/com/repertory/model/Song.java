package repertory.com.repertory.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.dto.SongDto;

@Entity
@Table(name = "SONG")
@Getter
@Setter
public class Song {

	@Id
	@GeneratedValue(generator="song_sequence")
	@SequenceGenerator(name="song_sequence",sequenceName="song_sequence", allocationSize=1)
	@Column(name = "SONG_ID", nullable = false)
	private long id;
	
	@Column(name = "artist_name")
	private String artistName;
	
	private String title;
	private String url; 
	private List<Part> parts;

	public static Song from(SongDto musicDto) {
		Song music = new Song();
		music.setArtistName(musicDto.getArtistName());
		music.setTitle(musicDto.getTitle());
		return music;
	}
	
	@ManyToMany(mappedBy = "songs")
	private Set<Block> blocks = new HashSet<>();
	
}
