package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.CharityEvents;
@Repository("eventRepository")
public interface CharityEventsRepository extends JpaRepository<CharityEvents, Integer>  {

}
