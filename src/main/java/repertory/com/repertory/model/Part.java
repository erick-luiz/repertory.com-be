package repertory.com.repertory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PART")
@Getter
@Setter
public class Part {
	
	@Id
	@GeneratedValue(generator="part_sequence")
	@SequenceGenerator(name="part_sequence",sequenceName="part_sequence", allocationSize=1)
	@Column(name = "PART_ID", nullable = false)
	private long id;

	private String name;
	private String notes;
	@OneToMany
	private List<Finalization> finalizations;
}
