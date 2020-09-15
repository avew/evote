package com.kyora.studio.vote.web.rest.voting;

import com.kyora.studio.vote.config.ApiConstant;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.service.voting.VotingService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@Api(value = "candidate")
@RestController
@RequestMapping(ApiConstant.API_V1 + ApiConstant.VOTING.ROOT)
@RequiredArgsConstructor
@Slf4j
public class VotingResource {

    private final VotingService votingService;


    @Secured({AuthoritiesConstants.USER})
    @GetMapping(
            value = "/{candidateId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<?> getVoteByCandidateId(
            @PathVariable String candidateId) {
        log.debug("REST REQUEST GET VOTING BY CANDIDATE ID: {}", candidateId);
        votingService.addVote(candidateId);
        return new ResponseEntity<>(OK);
    }


}
