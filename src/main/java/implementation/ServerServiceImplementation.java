package implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import model.Server;
import repo.ServerRepo;
import service.ServerService;
import enumeration.Status;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
	private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {}", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepo.save(server);
	}

	@Override
	public Server ping(String ipAddress) throws UnknownHostException {
		log.info("Pinging server IP: {}", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		try {
			server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		serverRepo.save(server);
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by ID: {}", id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server: {}", server.getName());
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by id: {}", id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;
	}
	
	private String setServerImageUrl() {
		// 1단계: 이미지 리스트를 만들고
		String[] serverImages = {"server1.png", "server2.png", "server3.png", "server4.png", "server5.png"};
		// 2단계: Uri 만드는 녀셕으로부터 경로를 만든 후 Uri로 출력시킨다.
		// 이때, Random.nextInt로 수를 구한다.
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+serverImages[new Random().nextInt(5)]).toUriString();
	}
	
}
