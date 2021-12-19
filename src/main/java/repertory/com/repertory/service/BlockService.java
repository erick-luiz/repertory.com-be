package repertory.com.repertory.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import repertory.com.repertory.dto.BlockCreateDto;
import repertory.com.repertory.exeption.ResourceNotFoundException;
import repertory.com.repertory.model.Block;
import repertory.com.repertory.repository.BlockRepository;

@Service
public class BlockService {
	
	private BlockRepository blockRepository;
	private SongService musicService;
	
	
	BlockService(BlockRepository blockRepository, SongService musicService){
		this.blockRepository = blockRepository;
		this.musicService = musicService;
	}
	
	public Block create(@Valid BlockCreateDto blockCreateDto) {
		Block block = Block.from(blockCreateDto);
		
		Block savedBlock = blockRepository.save(block);
	
		return savedBlock;
	}

	public List<Block> getAllBlocks() {
		return blockRepository.findAll();
	}

	public Block getBlockById(Long id) {
		return blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	public void delete(Long id) {
		Block block = blockRepository.getById(id);
		if(block  != null) {
			blockRepository.delete(block);			
		}
	}
	
	public void addMusics(Long blockId, List<Long> musicIds) {
		changeMusicList(blockId, musicIds, new ArrayList<>());
	}
	
	public void removeMusics(Long blockId, List<Long> musicIds) {
		changeMusicList(blockId, new ArrayList<>(), musicIds);
	}
	
	private void changeMusicList(Long blockId, List<Long> songsToAdd, List<Long> songsToRemove) {
		Block block = getBlockById(blockId);
		block.getSongs().addAll(musicService.getAllSongs(songsToAdd));
		block.getSongs().removeAll(musicService.getAllSongs(songsToRemove));
		blockRepository.save(block);
	}

	public List<Block> getAllBlocks(List<Long> blocksIds) {
		return blockRepository.findAllById(blocksIds);
	}	
	
}

