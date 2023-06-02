package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //query('s)
    List<Account> findAllByTotalIncome(BigDecimal totalIncome);
    List<Account> findAllByTotalExpense(BigDecimal totalExpense);

}
