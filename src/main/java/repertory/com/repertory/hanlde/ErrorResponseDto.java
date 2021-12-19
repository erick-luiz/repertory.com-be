package repertory.com.repertory.hanlde;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ErrorResponseDto {
	
	private String message;
	
	ErrorResponseDto(String message){
		this.message = message;
	}
	
}
