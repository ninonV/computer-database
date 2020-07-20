package com.excilys.java.persistence.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.java.model.Computer;

/**
 *  Class doing the relation with the table computer  
 *  @author ninonV
 *  **/

@Repository
public interface ComputerDAO extends JpaRepository<Computer, Long> {
}