package repertory.com.repertory.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.constant.Tone;
import repertory.com.repertory.dto.BlockCreateDto;

@Entity
@Table(name = "Block")
@Getter
@Setter
public class Block {
	
	@Id
	@GeneratedValue(generator="block_sequence")
	@SequenceGenerator(name="block_sequence",sequenceName="block_sequence", allocationSize=1)
	@Column(name = "BLOCK_ID", nullable = false)
	private long id;
	
	private String name;

	private Tone tone;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "song_blocks", joinColumns = {@JoinColumn(name = "block_id")}, inverseJoinColumns = {@JoinColumn(name = "song_id")})
	private Set<Song> songs = new HashSet<>();

	public static Block from(@Valid BlockCreateDto blockCreateDto) {
		Block block = new Block();
		
		block.setName(blockCreateDto.getName());
		block.setTone(blockCreateDto.getTone());
		
		return block;
	}

	@ManyToMany(mappedBy = "blocks")
	private Set<Repertory> repertories = new HashSet<>();
}
