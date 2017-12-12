package io.test.sas.common.filter.general;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserFilter extends AbstractFilter {

    private Long id;

    private String username;

    private String email;

}