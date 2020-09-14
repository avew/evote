package com.kyora.studio.vote.service.upload.image;

import com.kyora.studio.vote.config.query.LongFilter;
import com.kyora.studio.vote.config.query.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadImageCriteria {


    private StringFilter id;

    private StringFilter category;

    private StringFilter path;

    private StringFilter filename;

    private StringFilter contentType;

    private StringFilter originalFilename;

    private LongFilter length;

    private StringFilter md5;

    private StringFilter npwpProfile;


}
