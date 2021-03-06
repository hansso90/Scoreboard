package nl.teamrockstars.chapter.east.scoreboard.model;

import org.springframework.security.core.GrantedAuthority;

public enum Right implements GrantedAuthority {
	
		ROLE_DASHBOARD("ROLE_DASHBOARD"),

    ROLE_ROLEMANAGEMENT("ROLE_ROLEMANAGEMENT"),

    ROLE_USERMANAGEMENT("ROLE_USERMANAGEMENT"),

    ROLE_CHAPTERS("ROLE_CHAPTERS"),

    ROLE_CHAPTERMANAGEMENT("ROLE_CHAPTERMANAGEMENT"),

    ROLE_ACTIVITYMANAGEMENT("ROLE_ACTIVITYMANAGEMENT");

    private String authority;

    Right(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
