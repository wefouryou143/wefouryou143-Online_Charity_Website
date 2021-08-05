package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.capgemini.entity.Ngo;

@Repository("ngoRepository")
public interface NgoRepository extends JpaRepository<Ngo, Integer> {

}
