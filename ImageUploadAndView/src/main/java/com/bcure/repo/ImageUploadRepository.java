package com.bcure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcure.entity.ImageUpload;

public interface ImageUploadRepository extends JpaRepository<ImageUpload, Integer> {

}
