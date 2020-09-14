package com.kyora.studio.vote.domain.candidate;

import com.kyora.studio.vote.domain.audit.AbstractAuditingEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@SuppressWarnings("SpellCheckingInspection")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_candidate")
public class Candidate extends AbstractAuditingEntity {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "vision")
    private String vision;

    @NotNull
    @Column(name = "mission")
    private String mission;

    @NotNull
    @Column(name = "periode")
    private String periode;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    private long length;

    @NotNull
    private String md5;

}
