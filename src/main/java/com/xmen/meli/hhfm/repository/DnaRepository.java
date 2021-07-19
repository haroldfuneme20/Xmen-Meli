package com.xmen.meli.hhfm.repository;

import com.xmen.meli.hhfm.entetity.DNAEntity;
import com.xmen.meli.hhfm.enumeration.KindPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DnaRepository extends JpaRepository<DNAEntity, Long> {

    @Query("SELECT COUNT(e) FROM DNAEntity e WHERE e.kindPerson=:kind_person")
    long getAllKindPerson(@Param("kind_person") KindPerson kind_person);
}
