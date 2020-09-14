package com.kyora.studio.vote.web.rest.upload;

/*
 * Developed by Asep Rojali on 12/21/18 1:44 PM
 * Last modified 12/21/18 1:44 PM
 * Copyright (c) 2018. All rights reserved.
 */

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.upload.image.UploadImage;
import com.kyora.studio.vote.exception.NotFoundException;
import com.kyora.studio.vote.service.upload.image.UploadImageCriteria;
import com.kyora.studio.vote.service.upload.image.UploadImageQueryService;
import com.kyora.studio.vote.service.upload.image.UploadImageService;
import com.kyora.studio.vote.util.PaginationUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Api(value = "upload image")
@RestController
@RequestMapping(ApiConstant.API_V1 + ApiConstant.UPLOAD.ROOT + ApiConstant.UPLOAD.IMAGE)
@RequiredArgsConstructor
@Slf4j
public class UploadImageResource {

    private final UploadImageService uploadImageService;
    private final UploadImageQueryService uploadImageQueryService;

    @PostMapping
    @Timed
    public ResponseEntity<?> postImage(
            @RequestParam(value = "file") MultipartFile multipartFile
    ) throws IOException {
        log.debug("REST REQUEST POST IMAGE: {}", multipartFile.getOriginalFilename());
        UploadImage uploadImage = uploadImageService.save(multipartFile, "image");
        return new ResponseEntity<>(uploadImage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImage(@PathVariable String id) {
        log.debug("REST REQUEST GET IMAGE BY ID: {}", id);
        return uploadImageQueryService
                .findById(id)
                .map(uploadImage -> {
                    File file = new File(uploadImage.getPath());
                    FileSystemResource isr = new FileSystemResource(file);
                    HttpHeaders respHeaders = new HttpHeaders();
                    respHeaders.setContentType(MediaType.parseMediaType(uploadImage.getContentType()));
                    respHeaders.set("filename", uploadImage.getFilename());
                    respHeaders.set("originalFilename", uploadImage.getOriginalFilename());
                    respHeaders.setContentDispositionFormData("attachment", file.getName());
                    respHeaders.setContentLength(uploadImage.getLength());
                    return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
                })
                .orElseThrow(() -> new NotFoundException(id, "Image"));
    }

    @SuppressWarnings("Duplicates")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<UploadImage>> get(
            UploadImageCriteria criteria,
            @ApiIgnore Pageable pageable) {
        log.debug("REST REQUEST GET WITH CRITERIA: {}, PAGEABLE: {}", criteria, pageable);
        Page<UploadImage> page = uploadImageQueryService.findByCriteriaWithPage(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, ApiConstant.API_V1 + ApiConstant.UPLOAD.ROOT + ApiConstant.UPLOAD.IMAGE);
        return new ResponseEntity<>(page.getContent(), headers, OK);

    }


}

