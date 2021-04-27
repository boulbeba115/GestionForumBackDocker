package tn.Forum.Main.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.Forum.Main.Models.User;
import tn.Forum.Main.queryManaging.User.ProtectedUser;
import tn.Forum.Main.queryManaging.User.UserIdSolo;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUserId(String upperCase);
	@Query("SELECT u FROM User u WHERE u.userRole.userRole = 0")
	List<User> findAllMembers();
	@Query("SELECT u FROM User u WHERE u.userRole.userRole = 1")
	List<User> findAllModerators();
	UserIdSolo findFirstByOrderByIdDesc();
	User findByUserName(String userName);
	
	List<ProtectedUser> findAllProjectedBy();
	
	ProtectedUser findAllProjectedByUserId(String userId);
}
