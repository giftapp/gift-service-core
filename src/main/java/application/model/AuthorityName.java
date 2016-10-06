package application.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;

/**
 * Available identity autherities for security usage.
 *
 * Created by matan,
 * On 04/10/2016.
 */
public enum AuthorityName {
    ROLE_ANONYMOUS, ROLE_USER, ROLE_ADMIN;

    public static List<GrantedAuthority> getGrantedAuthority(AuthorityName authorityName) {
        return AuthorityUtils.createAuthorityList(authorityName.name());
    }
}
