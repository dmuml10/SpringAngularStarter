package io.test.sas.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.test.sas.common.enums.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = User.SEQUENCE_NAME, sequenceName = User.SEQUENCE_NAME, allocationSize = AbstractAuditingEntity.SEQUENCE_ALLOCATION_SIZE)
@Table(name = "APPLICATION_USER")
@NoArgsConstructor
@Getter @Setter
public class User extends AbstractAuditingEntity {

    static final String SEQUENCE_NAME = "APPLICATION_USER" + SEQUENCE_SUFFIX;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    private long id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String name;

    @Enumerated(EnumType.STRING)
    private StatusType status;

}