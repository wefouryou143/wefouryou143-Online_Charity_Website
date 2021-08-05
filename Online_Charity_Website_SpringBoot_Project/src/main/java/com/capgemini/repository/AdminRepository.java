package com.capgemini.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Admin;
import com.capgemini.entity.Ngo;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query(value = "select a from Admin a Where a.user.username = :username")
	public Admin readAdminByUsername(@Param("username") String username);

	@Query(value = "select a from Admin a Where a.email = :email")
	public Admin readAdminByEmail(@Param("email") String email);

	@Query(value = "select a from Admin a Where a.firstName = :firstName")
	public List<Admin> readAdminByFirstName(@Param("firstName") String firstName);

	@Query(value = "select a from Admin a Where a.lastName = :lastName")
	public List<Admin> readAdminByLastName(@Param("lastName") String lastName);

	@Modifying
	@Query(value = "delete from Admin a Where a.email= :email")
	public void removeAdminByEmail(@Param("email") String email);

	@Modifying
	@Query(value = "update Admin a set a.email = :email where a.adminId = :adminId")
	public void updateAdminEmail(@Param("adminId") int adminId, @Param("email") String email);

	@Modifying
	@Query(value = "update Admin a set a.phoneNumber = :phoneNumber where a.adminId = :adminId")
	public void updateAdminPhoneNumber(@Param("adminId") int adminId, @Param("phoneNumber") String phoneNumber);

	@Query(value = "select a from Admin a Where a.phoneNumber = :phoneNumber")
	public Admin readAdminByPhoneNumber(@Param("phoneNumber") String phoneNumber);

	// For Ngo

	@Query(value = "select n from Ngo n Where n.ngoName = :ngoName")
	public Ngo readNgoByName(@Param("ngoName") String ngoName);

	@Transactional
	@Modifying
	@Query(value = "delete from Ngo n Where n.ngoName = :ngoName")
	public void removeNgoByName(@Param("ngoName") String ngoName);

}
