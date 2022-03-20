package com.capillary.crawler.repository;

import com.capillary.crawler.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source,Integer> {
    @Query("select s from Source s where s.source = :url")
    Optional<Source> getSourceByUrl( String url);
}
