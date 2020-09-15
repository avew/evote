package com.kyora.studio.vote.repository.voting;

import com.kyora.studio.vote.domain.voting.Voting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VotingRepository extends JpaRepository<Voting, String>, JpaSpecificationExecutor<Voting> {

    Optional<Voting> findByCandidateIdAndUserId(String candidateId, String userId);
}
