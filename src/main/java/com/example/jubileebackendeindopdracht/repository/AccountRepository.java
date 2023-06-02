package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //query('s)
    List<Account> findAllByIdAndTotalIncome(Long id, BigDecimal totalIncome);
    List<Account> findAllByIdAndTotalExpense(Long id, BigDecimal totalExpense);

}
