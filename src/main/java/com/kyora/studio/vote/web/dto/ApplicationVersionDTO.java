package com.kyora.studio.vote.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationVersionDTO {

    private String name;
    private String version;
    private String timestamp;
}
