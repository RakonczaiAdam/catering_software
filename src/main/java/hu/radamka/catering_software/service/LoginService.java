package hu.radamka.catering_software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.radamka.catering_software.entity.Company;
import hu.radamka.catering_software.entity.User;
import hu.radamka.catering_software.repo.CompanyRepository;
import hu.radamka.catering_software.repo.UserRepository;

@Service
public class LoginService {

	private CompanyRepository companyRepo;
	private UserRepository userRepo;
	private Company company;
	private User user;

	@Autowired
	private void setCompanyRepo(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}
	
	@Autowired
	public void setUserReop(UserRepository userReop) {
		this.userRepo = userReop;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public User getUser() {
		return user;
	}
	
	public boolean companyLoginConfirm(String company_name, String password) {
		//companyRepo.save(new Company(company_name, password));
		try {
			company = companyRepo.findByName(company_name);
			return company.getPassword().equals(password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean userLoginConfirm(String user_name, String password) {
		//userReop.save(new User(user_name, password, company));
		try {
			user = userRepo.findByName(user_name);
			return user.getPassword().equals(password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String insertUser(String name, String password) {
		User user = userRepo.findByCompanyAndName(company, name);
		if(user == null) {
			userRepo.save(new User(name, password, company));
			return "User "+name+" saved";
		}else {
			return "This username is taken";
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String deleteUser(String name) {
		try {
			userRepo.deleteUserByName(name);
			return "User "+name+" deleted";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String updateUser(String old_name, String old_pw, String new_name, String new_pw) {
		try {
			userRepo.updateUserByName(old_name, old_pw, new_name, new_pw);
			return "User "+new_name+" updated";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
}
