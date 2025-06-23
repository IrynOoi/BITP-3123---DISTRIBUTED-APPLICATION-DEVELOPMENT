// AttendanceStatsRepository.java
package com.example.demo.repository;

import com.example.demo.model.AttendanceStats;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface provides CRUD operations for AttendanceStats entities.
 * It extends JpaRepository, which gives you methods like findAll(), findById(), save(), delete(), etc.
 */
public interface AttendanceStatsRepository extends JpaRepository<AttendanceStats, Integer>
{
    // No extra methods are needed for now.
    // JpaRepository already includes useful ones such as findAll(), findById(), save(), delete(), etc.
}

