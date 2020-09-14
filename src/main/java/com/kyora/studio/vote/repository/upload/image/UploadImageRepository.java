package com.kyora.studio.vote.repository.upload.image;

import com.kyora.studio.vote.domain.upload.image.UploadImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface UploadImageRepository extends JpaRepository<UploadImage, String>, JpaSpecificationExecutor<UploadImage> {

}
