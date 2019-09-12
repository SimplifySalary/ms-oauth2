package com.ss.oauth2.model.database;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author biandra
 */
@Entity
@Data
@Table(name="authority", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}, name="AUTHORITY_UNIQUE_NAME"))
public class AuthorityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=20)
    private String name;

}
