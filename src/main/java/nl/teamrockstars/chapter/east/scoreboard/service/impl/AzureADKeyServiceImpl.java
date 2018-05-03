package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import nl.teamrockstars.chapter.east.scoreboard.ScoreboardApplication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import nl.teamrockstars.chapter.east.scoreboard.service.AzureADKeyService;

@Service
public class AzureADKeyServiceImpl implements AzureADKeyService {

	private final static String BEGIN_KEY = "-----BEGIN PUBLIC KEY-----\n";
	private final static String END_KEY = "-----END PUBLIC KEY-----";

    private static Logger LOG = LoggerFactory.getLogger(AzureADKeyServiceImpl.class);

    @Override
	public PublicKey createADPublicKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            String[] keyArray = key.split("(?<=\\G.{64})");
            StringBuilder sb = new StringBuilder();
            sb.append(BEGIN_KEY);
            for (String keyPart : keyArray) {
                sb.append(keyPart);
                sb.append("\n");
            }
            sb.append(END_KEY);

            InputStream in = new ByteArrayInputStream(sb.toString().getBytes());
            CertificateFactory certFactory;
            try {
                certFactory = CertificateFactory.getInstance("X.509");
                X509Certificate cert = (X509Certificate) certFactory.generateCertificate(in);
                return cert.getPublicKey();
            } catch (CertificateException e) {
                LOG.error(e.getMessage());
                return null;
            }
        }
        return null;
	}

}
