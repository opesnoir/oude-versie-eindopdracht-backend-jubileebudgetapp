package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.model.ContractUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractUploadRepository extends JpaRepository <ContractUpload, Long> {

    //query('s)
    Optional<ContractUpload> findByContractPdfUrl (String contractPdfUrl);

    @Modifying
    @Query("UPDATE ContractUpload c SET c.contractPdfUrl = :pdfUrl WHERE c.id = :id")
    void updateContractPdfUrl(@Param("id") Long id, @Param("pdfUrl") String pdfUrl);

}
