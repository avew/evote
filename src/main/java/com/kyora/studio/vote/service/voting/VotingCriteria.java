package com.kyora.studio.vote.service.voting;

import com.kyora.studio.vote.config.query.StringFilter;
import com.kyora.studio.vote.config.query.IntegerFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotingCriteria {

    private StringFilter id;
    private StringFilter candidateId;
    private StringFilter userId;
    private IntegerFilter countId;
}
