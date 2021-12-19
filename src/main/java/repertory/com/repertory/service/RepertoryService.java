package repertory.com.repertory.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import repertory.com.repertory.dto.RepertoryCreateDto;
import repertory.com.repertory.exeption.ResourceNotFoundException;
import repertory.com.repertory.model.Repertory;
import repertory.com.repertory.repository.RepertoryRepository;

@Service
public class RepertoryService {

	private RepertoryRepository repertoryRepository;
	private BlockService blockService;
	
	
	public RepertoryService(RepertoryRepository repertoryRepository, BlockService blockService) {
		this.repertoryRepository = repertoryRepository;
		this.blockService = blockService;
	}

	public Repertory create(@Valid RepertoryCreateDto repertoryCreateDto) {
		Repertory repertory = Repertory.from(repertoryCreateDto);
		
		Repertory savedRepertory = repertoryRepository.save(repertory);
	
		return savedRepertory;
	}

	public List<Repertory> getAllRepertories() {
		return repertoryRepository.findAll();
	}

	public Repertory getRepertoryById(Long id) {
		return repertoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	public void delete(Long id) {
		Repertory repertory = repertoryRepository.getById(id);
		if(repertory  != null) {
			repertoryRepository.delete(repertory);			
		}
	}
	
	public void addBlocks(Long repertoryId, List<Long> blockIds) {
		changeMusicList(repertoryId, blockIds, new ArrayList<>());
	}
	
	public void removeBlocks(Long repertoryId, List<Long> blockIds) {
		changeMusicList(repertoryId,new ArrayList<>(), blockIds);
	}

	
	private void changeMusicList(Long repertoryId, List<Long> blocksToAdd, List<Long> blocksToRemove) {
		// TODO: We need to avoid the exceptions on the queries 
		Repertory repertory = getRepertoryById(repertoryId);
		repertory.getBlocks().addAll(blockService.getAllBlocks(blocksToAdd));
		repertory.getBlocks().removeAll(blockService.getAllBlocks(blocksToRemove));
		repertoryRepository.save(repertory);
	}
}
