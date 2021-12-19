package repertory.com.repertory.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repertory.com.repertory.dto.RepertoryBlocksDto;
import repertory.com.repertory.dto.RepertoryCreateDto;
import repertory.com.repertory.dto.RepertoryDto;
import repertory.com.repertory.service.RepertoryService;

@RequestMapping("/repertories")
@RestController
@PreAuthorize("USER")
public class RepertoryController {
	
	
	private RepertoryService repertoryService;
	
	RepertoryController(RepertoryService repertoryService){
		this.repertoryService = repertoryService;
	}
	
	
	
	@PostMapping
	public ResponseEntity<RepertoryDto> create(@Valid @RequestBody RepertoryCreateDto repertoryCreateDto) {
		return ResponseEntity.ok(RepertoryDto.from(repertoryService.create(repertoryCreateDto))); 
	}
	
	@GetMapping
	public List<RepertoryDto> get(){
		return repertoryService.getAllRepertories().stream().map(RepertoryDto::from).toList();
	}
	
	@GetMapping("/{id}")
	public RepertoryDto get(@PathVariable String id){
		return RepertoryDto.from(repertoryService.getRepertoryById(Long.valueOf(id)));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		
		if(id != null && id.matches("\\d*")) {
			repertoryService.delete(Long.valueOf(id));
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}/add-blocks")
	public ResponseEntity<Void> addSongs(@PathVariable String id, @Valid @RequestBody RepertoryBlocksDto RepertoryBlocks) {
		repertoryService.addBlocks(Long.valueOf(id), RepertoryBlocks.getBlockIds());
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/remove-blocks")
	public ResponseEntity<Void> removeSongs(@PathVariable String id, @Valid @RequestBody RepertoryBlocksDto RepertoryBlocks) {
		repertoryService.removeBlocks(Long.valueOf(id), RepertoryBlocks.getBlockIds());
		return ResponseEntity.noContent().build();
	}
	

}
