package repertory.com.repertory.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import repertory.com.repertory.dto.RepertoryCreateDto;

@Entity
@Table(name = "REPERTORY")
@Getter
@Setter
public class Repertory {

	@Id
	@GeneratedValue(generator="repertory_sequence")
	@SequenceGenerator(name="repertory_sequence",sequenceName="repertory_sequence", allocationSize=1)
	@Column(name = "REPERTORY_ID", nullable = false)
	private long id;
	
	
	private String name; 
	private boolean active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "repertory_blocks", joinColumns = {@JoinColumn(name = "repertory_id")}, inverseJoinColumns = {@JoinColumn(name = "block_id")})
	private Set<Block> blocks = new HashSet<>();

	public static Repertory from(@Valid RepertoryCreateDto repertoryCreateDto) {
		Repertory repertory = new Repertory();
		
		repertory.setName(repertoryCreateDto.getName());
		repertory.setActive(repertoryCreateDto.isActive());
		
		return repertory;
	}
	
}
