package hu.radamka.catering_software.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hu.radamka.catering_software.service.DatabaseService;
import hu.radamka.catering_software.service.LoginService;

@Controller
public class HomeController {

	private LoginService loginService;
	private DatabaseService databaseService;
	
	@Autowired
	private void setLoginServide(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Autowired
	public void setDatabaseService(DatabaseService databaseService) {
		this.databaseService = databaseService;
	}

	@RequestMapping(value="/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/company_login", method = RequestMethod.POST)
	public String companyLogin(Model model, @RequestParam("company") String company_name, @RequestParam("password") String password) {
		try {
			if(loginService.companyLoginConfirm(company_name, password)) {
				model.addAttribute("company_name", loginService.getCompany().getName());
				return "user_login";
			}else {
				model.addAttribute("errMsg", "wrong username or password");
				return "index";
			}
		} catch (Exception e) {
			model.addAttribute("errMsg", e.getMessage());
			return "index";
		}
	}
	
	@RequestMapping(value="/user_login", method = RequestMethod.POST)
	public String userLogin(Model model, @RequestParam("user") String user_name, @RequestParam("password") String password) {
		try {
			if(loginService.userLoginConfirm(user_name, password)) {
				model.addAttribute("user_name", loginService.getCompany().getName()+", "+loginService.getUser().getName());
				model.addAttribute("companies", databaseService.getCompanies());
				model.addAttribute("users", databaseService.getUsers());
				return "signed";
			}else {
				model.addAttribute("errMsg", "wrong username or password");
				return "user_login";
			}
		} catch (Exception e) {
			model.addAttribute("errMsg", e.getMessage());
			return "user_login";
		}
	}
	
	@RequestMapping(value="/user_insert", method = RequestMethod.POST)
	public String userInsert(Model model, @RequestParam("user_name") String user_name, @RequestParam("password") String password) {
		try {
			String message = loginService.insertUser(user_name, password);
			model.addAttribute("user_name", loginService.getCompany().getName()+", "+loginService.getUser().getName());
			model.addAttribute("companies", databaseService.getCompanies());
			model.addAttribute("users", databaseService.getUsers());
			model.addAttribute("errMsg", message);
			return "signed";
		} catch (Exception e) {
			model.addAttribute("errMsg", e.getMessage());
			return "signed";
		}
	}
	
	@RequestMapping(value="/user_delete", method = RequestMethod.POST)
	public String userDelete(Model model, @RequestParam("user_name") String user_name) {
		try {
			String message = loginService.deleteUser(user_name);
			model.addAttribute("user_name", loginService.getCompany().getName()+", "+loginService.getUser().getName());
			model.addAttribute("companies", databaseService.getCompanies());
			model.addAttribute("users", databaseService.getUsers());
			model.addAttribute("errMsg", message);
			return "signed";
		} catch (Exception e) {
			model.addAttribute("errMsg", e.getMessage());
			return "signed";
		}
	}
	
	@RequestMapping(value="/user_update", method = RequestMethod.POST)
	public String userUpdate(Model model, @RequestParam("old_user_name") String old_user_name, @RequestParam("new_user_name") String new_user_name, @RequestParam("old_user_password") String old_user_password, @RequestParam("new_user_password") String new_user_password) {
		try {
			String message = loginService.updateUser(old_user_name, old_user_password, new_user_name, new_user_password);
			model.addAttribute("user_name", loginService.getCompany().getName()+", "+loginService.getUser().getName());
			model.addAttribute("companies", databaseService.getCompanies());
			model.addAttribute("users", databaseService.getUsers());
			model.addAttribute("errMsg", message);
			return "signed";
		} catch (Exception e) {
			model.addAttribute("errMsg", e.getMessage());
			return "signed";
		}
	}
	
}
