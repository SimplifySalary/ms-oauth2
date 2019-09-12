package com.rfsc.oauth2.model.database;

/**
 * @author biandra
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}, name="USER_UNIQUE_USERNAME"))
public class UserEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;
    @NotEmpty
    private String password;

    private Date dateCreated;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private Set<AuthorityEntity> authorities = new HashSet<>();

}
