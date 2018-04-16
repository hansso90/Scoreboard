package nl.teamrockstars.chapter.east.scoreboard.model;

import org.springframework.security.core.GrantedAuthority;

public enum Right implements GrantedAuthority {

    ROLE_ROLEMANAGEMENT("ROLE_ROLEMANAGEMENT"),

    ;

    private String authority;

    Right(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
