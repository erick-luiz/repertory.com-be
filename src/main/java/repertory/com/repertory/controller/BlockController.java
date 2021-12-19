package repertory.com.repertory.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

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

import repertory.com.repertory.dto.BlockCreateDto;
import repertory.com.repertory.dto.BlockDto;
import repertory.com.repertory.dto.BlockMusicsDto;
import repertory.com.repertory.service.BlockService;

@RequestMapping("/blocks")
@RestController
@PreAuthorize("USER")
public class BlockController {

	private BlockService blockService; 
	
	BlockController(BlockService blockService){
		this.blockService = blockService;
	}
	
	@PostMapping
	public ResponseEntity<BlockDto> create(@Valid @RequestBody BlockCreateDto blockCreateDto) {
		return ResponseEntity.ok(BlockDto.from(blockService.create(blockCreateDto))); 
	}
	
	@GetMapping
	public List<BlockDto> get(){
		return blockService.getAllBlocks().stream().map(BlockDto::from).toList();
	}
	
	@GetMapping("/{id}")
	public BlockDto get(@PathVariable String id){
		return BlockDto.from(blockService.getBlockById(Long.valueOf(id)));
	}
	
	// TODO: fix the path validation
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @PathVariable @Pattern(regexp = "\\d*") String id){
		
		if(id != null && id.matches("\\d*")) {
			blockService.delete(Long.valueOf(id));
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}/add-songs")
	public ResponseEntity<Void> addSongs(@PathVariable String id, @Valid @RequestBody BlockMusicsDto blockMusics) {
		blockService.addMusics(Long.valueOf(id), blockMusics.getMusicIds());
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/remove-songs")
	public ResponseEntity<Void> removeSongs(@PathVariable String id, @Valid @RequestBody BlockMusicsDto blockMusics) {
		blockService.removeMusics(Long.valueOf(id), blockMusics.getMusicIds());
		return ResponseEntity.noContent().build();
	}
	
	
}
