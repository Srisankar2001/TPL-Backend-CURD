package com.tpl.backend.repository;

import com.tpl.backend.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RuleRepository extends JpaRepository<Rule,Integer> {
    Optional<Rule> findOneByName(String name);
}
