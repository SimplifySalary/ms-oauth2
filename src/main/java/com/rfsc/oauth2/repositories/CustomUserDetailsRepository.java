package com.rfsc.oauth2.repositories;

import com.rfsc.oauth2.model.database.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author biandra
 */
@Repository
public interface CustomUserDetailsRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(@Param("username") String username);
}
