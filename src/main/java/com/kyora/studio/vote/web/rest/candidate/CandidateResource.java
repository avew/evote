package com.kyora.studio.vote.web.rest.candidate;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.exception.NotFoundException;
import com.kyora.studio.vote.service.candidate.CandidateCriteria;
import com.kyora.studio.vote.service.candidate.CandidateQueryService;
import com.kyora.studio.vote.service.candidate.CandidateService;
import com.kyora.studio.vote.util.PaginationUtil;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Api(value = "candidate")
@RestController
@RequestMapping(ApiConstant.API_V1 + ApiConstant.CANDIDATE.ROOT)
@RequiredArgsConstructor
@Slf4j
public class CandidateResource {

    private final CandidateService candidateService;
    private final CandidateQueryService candidateQueryService;

    /**
     * @param dto
     * @return
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> create(
            @Valid @RequestBody Candidate dto) {
        Candidate save = candidateService.save(dto);
        return new ResponseEntity<>(save, CREATED);

    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> update(
            @Valid @RequestBody Candidate dto) {
        log.debug("REST REQUEST UPDATE: {}", dto);
        Candidate updated = candidateService.update(dto);
        return new ResponseEntity<>(updated, OK);
    }


    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> getById(
            @PathVariable String id) {
        log.debug("REST REQUEST GET BY ID: {}", id);
        return candidateQueryService
                .findById(id)
                .map(spt -> new ResponseEntity<>(spt, OK))
                .orElseThrow(() -> new NotFoundException(id, "id"));
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<?>> get(
            CandidateCriteria criteria,
            @ApiIgnore Pageable pageable) {


        if (criteria == null)
            criteria = new CandidateCriteria();

        log.debug("REST REQUEST GET BY CRITERIA: {}, PAGEABLE: {}", criteria, pageable);
        Page<Candidate> page = candidateQueryService.findByCriteriaWithPage(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, ApiConstant.API_V1 + ApiConstant.CANDIDATE.ROOT);
        return new ResponseEntity<>(page.getContent(), headers, OK);
    }

    @DeleteMapping(value = "/{id}")
    @Timed
    public ResponseEntity<?> delete(
            @PathVariable String id) {
        log.debug("REST REQUEST DELETE BY ID: {}", id);
        return candidateQueryService
                .findById(id)
                .map(spt -> {
                    candidateService.deleteById(id);
                    return new ResponseEntity<>(OK);
                })
                .orElseThrow(() -> new NotFoundException(id, "id"));
    }

}
