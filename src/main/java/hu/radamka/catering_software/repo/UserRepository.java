package hu.radamka.catering_software.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import hu.radamka.catering_software.entity.Company;
import hu.radamka.catering_software.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();
	
	User findByName(String name);
	
	@Query("SELECT u FROM User u WHERE u.name = :name AND u.company = :company")
	User findByCompanyAndName(@Param("company") Company company, @Param("name") String name);
	
	@Modifying
	@Query("DELETE FROM User WHERE name = :name")
	void deleteUserByName(@Param("name") String name);
	
	@Modifying
	@Query("UPDATE User u SET u.name = :new_name, u.password = :new_pw WHERE u.name = :old_name AND u.password = :old_pw")
	void updateUserByName(@Param("old_name") String old_name, @Param("old_pw") String old_pw, @Param("new_name") String new_name, @Param("new_pw") String new_pw);

}
