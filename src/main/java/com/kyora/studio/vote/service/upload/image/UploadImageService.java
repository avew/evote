package com.kyora.studio.vote.service.upload.image;

import com.kyora.studio.vote.domain.upload.image.UploadImage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
public interface UploadImageService {

    UploadImage save(MultipartFile multipartFile, String category) throws IOException;
}
