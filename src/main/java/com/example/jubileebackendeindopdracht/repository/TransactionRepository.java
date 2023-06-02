package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository <Transaction, Long>{

    //query('s)

}
