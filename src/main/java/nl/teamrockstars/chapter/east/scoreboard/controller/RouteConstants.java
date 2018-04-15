package nl.teamrockstars.chapter.east.scoreboard.controller;

public final class RouteConstants {

    public static final String BASE = "/api";
    public static final int VERSION = 0;
    public static final String MOUNT = BASE + "/v" + VERSION;

    public static final String PUBLIC = MOUNT;

    public static final String PRIVATE = PUBLIC + "/restricted";
}
