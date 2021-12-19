package repertory.com.repertory.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.constant.Tone;
import repertory.com.repertory.model.Block;

@Getter
@Setter
public class BlockDto {
	
	@JsonIgnoreProperties(allowGetters = true)
	private Long id;
	private String name; 
	private Tone tone; 
	private List<SongDto> songs;
	
	public static BlockDto from(Block block) {
		BlockDto blockDto = new BlockDto();
		
		blockDto.setName(block.getName());
		blockDto.setTone(block.getTone());
		blockDto.setSongs(block.getSongs().stream().map(SongDto::from).toList());
		blockDto.setId(block.getId());
		
		return blockDto;
		
	}

}
