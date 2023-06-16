package com.example.jubileebackendeindopdracht.repository;

import com.example.jubileebackendeindopdracht.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadRepository extends JpaRepository <Upload, Long> {

}
