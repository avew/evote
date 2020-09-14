package com.kyora.studio.vote.service.user;

import com.kyora.studio.vote.config.query.BooleanFilter;
import com.kyora.studio.vote.config.query.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by avew on 2/7/18.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteria {

    private StringFilter id;

    private StringFilter login;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter email;

    private BooleanFilter activated;

    private StringFilter langKey;

    private StringFilter createdBy;

    private StringFilter authority;
}
