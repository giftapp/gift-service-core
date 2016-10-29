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
public class AuthorityName {
    public static final String ROLE_ANONYMOUS_STRING = "ROLE_ANONYMOUS";
    public static final String ROLE_USER_STRING = "ROLE_USER";
    public static final String ROLE_ADMIN_STRING = "ROLE_ADMIN";

    public enum AuthorityNameEnum {
        ANONYMOUS(ROLE_ANONYMOUS_STRING),
        USER(ROLE_USER_STRING),
        ADMIN(ROLE_ADMIN_STRING);

        private String role;

        AuthorityNameEnum(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }

        public static List<GrantedAuthority> getGrantedAuthority(AuthorityNameEnum authorityNameEnum) {
            return AuthorityUtils.createAuthorityList(authorityNameEnum.getRole());
        }
    }

}
