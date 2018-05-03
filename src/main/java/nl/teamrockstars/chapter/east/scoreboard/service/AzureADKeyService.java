package nl.teamrockstars.chapter.east.scoreboard.service;

import java.security.PublicKey;

public interface AzureADKeyService {

    PublicKey createADPublicKey(String key);

}
