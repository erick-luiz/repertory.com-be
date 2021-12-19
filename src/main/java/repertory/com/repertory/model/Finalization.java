package repertory.com.repertory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FINALIZATION")
@Getter
@Setter
public class Finalization {
	
	@Id
	@GeneratedValue(generator="finalization_sequence")
	@SequenceGenerator(name="finalization_sequence",sequenceName="finalization_sequence", allocationSize=1)
	@Column(name = "FINALIZATION_ID", nullable = false)
	private long id;
	
	private int sort;
	private String notes;
	
}
