package com.kyora.studio.vote.service.upload.image.impl;

import com.kyora.studio.vote.config.ApplicationConstant;
import com.kyora.studio.vote.domain.upload.image.UploadImage;
import com.kyora.studio.vote.repository.upload.image.UploadImageRepository;
import com.kyora.studio.vote.service.upload.image.UploadImageService;
import com.kyora.studio.vote.util.DirectoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadImageServiceImpl implements UploadImageService {

    private final UploadImageRepository uploadImageRepository;
    private final DirectoryService directoryService;

    @SuppressWarnings("Duplicates")
    @Override
    public UploadImage save(MultipartFile multipartFile, String category) throws IOException {
        log.debug("REQUEST SAVE MULTIPARTFILE: {}, CATEGORY: {}, NPWP: {}", multipartFile, category);

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + "." + extension;
        String contentType = multipartFile.getContentType();

        String path = directoryService.createFile(ApplicationConstant.DIR.IMAGE, filename);
        FileUtils.writeByteArrayToFile(new File(path), multipartFile.getBytes());

        UploadImage uploadImage = UploadImage.builder()
                .category(category)
                .path(path)
                .filename(filename)
                .contentType(contentType)
                .originalFilename(multipartFile.getOriginalFilename())
                .length(multipartFile.getSize())
                .md5(DigestUtils.md5Hex(multipartFile.getBytes()))
                .build();

        return uploadImageRepository.save(uploadImage);
    }


}
