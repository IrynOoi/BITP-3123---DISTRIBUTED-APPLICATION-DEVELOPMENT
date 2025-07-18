//EventRepository.java
package com.example.demo.repository;

import com.example.demo.model.Event;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
	
	Optional<Event> findById(Integer id);

    // No need to add anything unless you want custom queries
}
