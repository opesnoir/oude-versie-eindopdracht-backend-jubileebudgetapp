package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.SavingGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {
    // query('s)


}
