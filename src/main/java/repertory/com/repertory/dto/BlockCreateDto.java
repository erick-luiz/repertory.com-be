package repertory.com.repertory.dto;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.constant.Tone;

@Getter
@Setter
public class BlockCreateDto {

	private String name; 
	private Tone tone;
}
