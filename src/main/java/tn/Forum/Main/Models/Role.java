package tn.Forum.Main.Models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userRole ;
	
	@JsonBackReference
	@OneToMany
	private List<User> UserList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<User> getUserList() {
		return UserList;
	}

	public void setUserList(List<User> userList) {
		UserList = userList;
	}
	


	
	
}
