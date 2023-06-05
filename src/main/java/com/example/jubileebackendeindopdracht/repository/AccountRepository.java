package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // query('s)
}
