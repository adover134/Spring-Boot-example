package model;
import enumeration.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column (unique = true)
	@NotEmpty(message = "IP address cannot be NULL or empty")
	private String ipAddress;
	private String name;
	private String memory;
	private String type;
	private String imageUrl;
	private Status status;
}
