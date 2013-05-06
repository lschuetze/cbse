package de.manuel.proto.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: User
 * Test Entity for persistence service.
 *
 */
@Entity
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5123680268141069527L;
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	private String username;

	/**
	 * Default constructor.
	 */
	public UserEntity() {
		super();
	}   
	
	/**
	 * Give the ID of User...
	 * @return
	 */
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}   
   
}
