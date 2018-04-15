package nl.teamrockstars.chapter.east.scoreboard.model;

import org.springframework.security.core.GrantedAuthority;

public enum Right implements GrantedAuthority {

    ROLE_INLOGGEN("ROLE_INLOGGEN"),

    ROLE_ROLEMANAGEMENT("ROLE_ROLEMANAGEMENT"),

    ROLE_USER_CREATE("ROLE_USER_CREATE"),

    ROLE_USER_MODIFY("ROLE_USER_MODIFY"),

    ROLE_USER_DELETE("ROLE_USER_DELETE");

    private String authority;

    Right(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
