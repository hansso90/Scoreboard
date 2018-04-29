package nl.teamrockstars.chapter.east.scoreboard.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.springframework.stereotype.Service;

import nl.teamrockstars.chapter.east.scoreboard.service.AzureADKeyService;

@Service
public class AzureADKeyServiceImpl implements AzureADKeyService {

	@Override
	public PublicKey getADPublicKey() {

		String pubKeySTRING = "-----BEGIN PUBLIC KEY-----\n" + //
				"MIIDBTCCAe2gAwIBAgIQev76BWqjWZxChmKkGqoAfDANBgkqhkiG9w0BAQsFADAt\n" + //
				"MSswKQYDVQQDEyJhY2NvdW50cy5hY2Nlc3Njb250cm9sLndpbmRvd3MubmV0MB4X\n" + //
				"DTE4MDIxODAwMDAwMFoXDTIwMDIxOTAwMDAwMFowLTErMCkGA1UEAxMiYWNjb3Vu\n" + //
				"dHMuYWNjZXNzY29udHJvbC53aW5kb3dzLm5ldDCCASIwDQYJKoZIhvcNAQEBBQAD\n" + //
				"ggEPADCCAQoCggEBAMgmGiRfLh6Fdi99XI2VA3XKHStWNRLEy5Aw/gxFxchnh2kP\n" + //
				"dk/bejFOs2swcx7yUWqxujjCNRsLBcWfaKUlTnrkY7i9x9noZlMrijgJy/Lk+HH5\n" + //
				"HX24PQCDf+twjnHHxZ9G6/8VLM2e5ZBeZm+t7M3vhuumEHG3UwloLF6cUeuPdW+e\n" + //
				"xnOB1U1fHBIFOG8ns4SSIoq6zw5rdt0CSI6+l7b1DEjVvPLtJF+zyjlJ1Qp7NgBv\n" + //
				"AwdiPiRMU4l8IRVbuSVKoKYJoyJ4L3eXsjczoBSTJ6VjV2mygz96DC70MY3avccF\n" + //
				"rk7tCEC6ZlMRBfY1XPLyldT7tsR3EuzjecSa1M8CAwEAAaMhMB8wHQYDVR0OBBYE\n" + //
				"FIks1srixjpSLXeiR8zES5cTY6fBMA0GCSqGSIb3DQEBCwUAA4IBAQCKthfK4C31\n" + //
				"DMuDyQZVS3F7+4Evld3hjiwqu2uGDK+qFZas/D/eDunxsFpiwqC01RIMFFN8yvmM\n" + //
				"jHphLHiBHWxcBTS+tm7AhmAvWMdxO5lzJLS+UWAyPF5ICROe8Mu9iNJiO5JlCo0W\n" + //
				"pui9RbB1C81Xhax1gWHK245ESL6k7YWvyMYWrGqr1NuQcNS0B/AIT1Nsj1WY7efM\n" + //
				"JQOmnMHkPUTWryVZlthijYyd7P2Gz6rY5a81DAFqhDNJl2pGIAE6HWtSzeUEh3jC\n" + //
				"sHEkoglKfm4VrGJEuXcALmfCMbdfTvtu4rlsaP2hQad+MG/KJFlenoTK34EMHeBP\n" + //
				"DCpqNDz8UVNk\n" + //
				"-----END PUBLIC KEY-----";

		InputStream in = new ByteArrayInputStream(pubKeySTRING.getBytes());
		CertificateFactory certFactory;
		try {
			certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory.generateCertificate(in);
			return cert.getPublicKey();
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
