package com.kyora.studio.vote.repository.candidate;

import com.kyora.studio.vote.config.query.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCriteria {

    private StringFilter id;

    private StringFilter name;


    private StringFilter vision;


    private StringFilter mission;


    private StringFilter periode;


    private StringFilter imageUrl;
}
