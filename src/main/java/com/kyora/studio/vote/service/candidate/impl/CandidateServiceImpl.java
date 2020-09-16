package com.kyora.studio.vote.service.candidate.impl;

import com.kyora.studio.vote.domain.candidate.Candidate;
import com.kyora.studio.vote.repository.candidate.CandidateRepository;
import com.kyora.studio.vote.service.candidate.CandidateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate save(Candidate dto) {
        return candidateRepository.save(dto);
    }

    @Override
    public Candidate update(Candidate dto) {
        return candidateRepository.save(dto);
    }

    @Override
    public void deleteById(String s) {
        candidateRepository.deleteById(s);
    }
}
