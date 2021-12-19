package repertory.com.repertory.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.model.Repertory;

@Getter
@Setter
public class RepertoryDto {

	@JsonIgnoreProperties(allowGetters = true)
	private Long id;
	private String name; 
	private boolean active; 
	private List<BlockDto> blocks;
	
	public static RepertoryDto from(Repertory repertory) {
		RepertoryDto repertoryDto = new RepertoryDto();
		
		repertoryDto.setName(repertory.getName());
		repertoryDto.setActive(repertory.isActive());
		repertoryDto.setBlocks(repertory.getBlocks().stream().map(BlockDto::from).toList());
		repertoryDto.setId(repertory.getId());
		
		
		return repertoryDto;
		
	}
	
}
