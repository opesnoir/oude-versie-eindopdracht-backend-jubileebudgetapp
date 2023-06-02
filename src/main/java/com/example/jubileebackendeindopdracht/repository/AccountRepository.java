package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
