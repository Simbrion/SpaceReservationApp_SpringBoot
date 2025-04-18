package com.srasb.repository;

import com.srasb.model.entity.SpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<SpaceEntity, Integer> {

    boolean existsByName(String name);

}
