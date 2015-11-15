package org.pac4j.demo.spring.authorizer;

import org.pac4j.core.authorization.AuthorizationGenerator;
import org.pac4j.core.profile.CommonProfile;

import java.util.Arrays;

/**
 * Created by Mateusz on 14.11.2015.
 */
public class RolesAuthorizationGenerator <U extends CommonProfile> implements AuthorizationGenerator<U> {

    private String rolesAttributeName = "roles";

    @Override
    public void generate(final U profile) {
        String rolesValue = (String) profile.getAttribute(rolesAttributeName);
        profile.addRoles(Arrays.asList(rolesValue.split(",")));
    }
}
