package com.rfsc.oauth2.repositories;

import com.rfsc.oauth2.model.database.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author biandra
 */
@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
