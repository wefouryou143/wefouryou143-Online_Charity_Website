package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.Donor;

@Repository("donorRepository")
public interface DonorRepository extends  JpaRepository<Donor, Integer> {

}
