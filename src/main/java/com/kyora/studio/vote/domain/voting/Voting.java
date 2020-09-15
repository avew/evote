package com.kyora.studio.vote.domain.voting;

/*
 * Developed by Asep Rojali on 12/21/18 10:44 AM
 * Last modified 12/21/18 10:44 AM
 * Copyright (c) 2018. All rights reserved.
 */

import com.kyora.studio.vote.domain.audit.AbstractAuditingEntity;
import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.domain.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_voting")
public class Voting extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(optional = false)
    @NotNull
    private Candidate candidate;

    @ManyToOne(optional = false)
    @NotNull
    private User user;

    private int count;


}
