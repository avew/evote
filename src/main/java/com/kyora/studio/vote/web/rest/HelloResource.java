package com.kyora.studio.vote.web.rest;


import com.kyora.studio.vote.config.EvoteProperties;
import com.kyora.studio.vote.web.dto.ApplicationVersionDTO;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class HelloResource {

    private final EvoteProperties epptProperties;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ApplicationVersionDTO say() {
        return ApplicationVersionDTO.builder()
                .name(epptProperties.getApplication().getName())
                .version(epptProperties.getApplication().getVersion())
                .timestamp(epptProperties.getApplication().getTimestamp())
                .build();
    }
}
