package com.kyora.studio.vote.service.candidate;


import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.repository.candidate.CandidateCriteria;
import com.kyora.studio.vote.util.BaseNonTransactional;

public interface CandidateQueryService extends BaseNonTransactional<Candidate, String, CandidateCriteria> {

}
