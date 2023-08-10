package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Serviceability;

@Repository
public interface ServiceabilityRepository extends JpaRepository<Serviceability, Long> {

}
