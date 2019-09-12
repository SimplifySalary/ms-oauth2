package com.rfsc.oauth2.repositories;

import com.rfsc.oauth2.model.database.ClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author biandra
 */
@Repository
public interface CustomClientDetailsRepository  extends JpaRepository<ClientDetailsEntity, String> {
}
