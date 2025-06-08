/*
 * Created by Vraj Contractor
 */

package com.movieexplorer.repository;

import com.movieexplorer.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FeedbackRepository interface extends JpaRepository to provide
 * CRUD operations for Feedback entity.
 * 
 * JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // No extra methods needed currently, but you can add custom queries here if needed
}
