package io.test.sas.common.filter.general;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserFilter extends AbstractFilter {

    private Long id;

    private String username;

    private String email;

    private List<String> names;

    private Long userGroupId;

}