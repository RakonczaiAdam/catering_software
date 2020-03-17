package hu.radamka.catering_software.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hu.radamka.catering_software.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{

	Company findByName(String name);
	List<Company> findAll();
	
}
