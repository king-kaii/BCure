package com.bcure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcure.entity.Image;


public interface ImageRepo extends JpaRepository<Image, Integer> {

}
