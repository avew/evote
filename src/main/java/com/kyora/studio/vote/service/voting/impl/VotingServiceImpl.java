package com.kyora.studio.vote.service.voting.impl;

import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.domain.voting.Voting;
import com.kyora.studio.vote.exception.UserNotFoundException;
import com.kyora.studio.vote.exception.VoteException;
import com.kyora.studio.vote.repository.voting.VotingRepository;
import com.kyora.studio.vote.security.AuthoritiesConstants;
import com.kyora.studio.vote.security.SecurityUtils;
import com.kyora.studio.vote.service.candidate.CandidateQueryService;
import com.kyora.studio.vote.service.user.UserQueryService;
import com.kyora.studio.vote.service.voting.VotingQueryService;
import com.kyora.studio.vote.service.voting.VotingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VotingServiceImpl implements VotingService {
    private final VotingRepository votingRepository;
    private final UserQueryService userQueryService;
    private final CandidateQueryService candidateQueryService;
    private final VotingQueryService votingQueryService;

    @Override
    public void addVote(String candidateId) {
        userQueryService
                .findByLogin(SecurityUtils.getCurrentUserLogin())
                .map(user -> {
                    boolean currentUserInRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.USER);
                    if (currentUserInRole) {
                        boolean checkIsVoted = votingQueryService.findByCandidateIdAndUserId(candidateId, user.getId())
                                .isPresent();
                        if (checkIsVoted) {
                            throw new VoteException("user has voted");
                        } else {
                            Candidate candidate = candidateQueryService.findById(candidateId).get();
                            Voting voting = Voting.builder().candidate(candidate).user(user).count(1).build();
                            return votingRepository.save(voting);
                        }
                    } else {
                        throw new VoteException("user has not require vote");
                    }
                })
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
