package hu.radamka.catering_software.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.radamka.catering_software.entity.Company;
import hu.radamka.catering_software.entity.User;
import hu.radamka.catering_software.repo.CompanyRepository;
import hu.radamka.catering_software.repo.UserRepository;

@Service
public class DatabaseService {

	private CompanyRepository companyRepo;
	private UserRepository userRepo;
	
	@Autowired
	public void setCompanyRepo(CompanyRepository companyRepo) {
		this.companyRepo = companyRepo;
	}
	
	@Autowired
	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public List<Company> getCompanies(){
		return companyRepo.findAll();
	}
	
	public List<User> getUsers(){
		return userRepo.findAll();
	}
	
}
