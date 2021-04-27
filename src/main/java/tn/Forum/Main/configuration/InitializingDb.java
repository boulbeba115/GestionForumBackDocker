package tn.Forum.Main.configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tn.Forum.Main.Models.Role;
import tn.Forum.Main.Models.User;
import tn.Forum.Main.Repositories.RoleRepo;
import tn.Forum.Main.Repositories.UserRepo;

@Component
public class InitializingDb implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		long nbUsers = userRepo.count();
		long nbRoles = roleRepo.count();
		if (nbRoles < 4)
			this.createRoles();
		if (nbUsers < 1)
			this.createAdminUser();
	}

	private void createRoles() {
		List<Role> listRoles = new ArrayList<Role>();
		listRoles = createAdminRole(listRoles);
		listRoles = createModeratorRole(listRoles);
		listRoles = createMemberRole(listRoles);
		if (listRoles.size() > 0)
			roleRepo.saveAll(listRoles);
	}

	private void createAdminUser() {
		Role roleAdmin = roleRepo.findFirstByUserRole("ROLE_ADMIN");
		User admin = new User("user_1".toUpperCase(), "adminGenearated", "adminGenearated", "admin", "admin@forums.com", false,
				"admin", User.AccountStatus.Active, roleAdmin, new Date());
		String salt = PasswordUtils.getSalt(30);
		String securePassword = PasswordUtils.generateSecurePassword("admin", salt);
		admin.setSalt(salt);
		admin.setUserPassword(securePassword);
		admin.setUserPic("default.png");
		userRepo.save(admin);
	}

	private List<Role> createAdminRole(List<Role> listRoles) {
		if (roleRepo.findFirstByUserRole("ROLE_ADMIN") == null) {
			Role roleAdmin = new Role();
			roleAdmin.setUserRole("ROLE_ADMIN");
			listRoles.add(roleAdmin);
		}
		return listRoles;
	}

	private List<Role> createModeratorRole(List<Role> listRoles) {
		if (roleRepo.findFirstByUserRole("ROLE_MODERATOR") == null) {
			Role roleAdmin = new Role();
			roleAdmin.setUserRole("ROLE_MODERATOR");
			listRoles.add(roleAdmin);
		}
		return listRoles;
	}

	private List<Role> createMemberRole(List<Role> listRoles) {
		if (roleRepo.findFirstByUserRole("ROLE_MEMEBER") == null) {
			Role roleAdmin = new Role();
			roleAdmin.setUserRole("ROLE_MEMEBER");
			listRoles.add(roleAdmin);
		}
		return listRoles;
	}

}
