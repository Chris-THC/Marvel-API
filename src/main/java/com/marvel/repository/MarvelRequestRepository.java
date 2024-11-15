package com.marvel.repository;

import com.marvel.entity.MarvelRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarvelRequestRepository extends JpaRepository<MarvelRequest, Integer> {
}
