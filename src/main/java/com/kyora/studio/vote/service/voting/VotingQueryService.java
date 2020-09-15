package com.kyora.studio.vote.service.voting;


import com.kyora.studio.vote.domain.voting.Voting;
import com.kyora.studio.vote.util.BaseNonTransactional;

import java.util.Optional;

public interface VotingQueryService extends BaseNonTransactional<Voting, String, VotingCriteria> {

    Optional<Voting> findByCandidateIdAndUserId(String candidateId, String userId);

}
