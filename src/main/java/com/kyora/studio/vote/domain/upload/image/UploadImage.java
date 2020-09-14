package com.kyora.studio.vote.domain.upload.image;

import com.kyora.studio.vote.domain.AbstractAuditingEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_upload_image")
public class UploadImage extends AbstractAuditingEntity {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(name = "category")
    private String category;

    @NotNull
    @Column(name = "path")
    private String path;

    @NotNull
    @Column(name = "filename")
    private String filename;

    @NotNull
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "original_filename")
    private String originalFilename;

    @NotNull
    private long length;

    @NotNull
    private String md5;

}
