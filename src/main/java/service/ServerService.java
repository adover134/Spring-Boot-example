package service;

import java.net.UnknownHostException;
import java.util.Collection;

import model.Server;

public interface ServerService {
	// 서버 정보를 저장하기 위함
	Server create(Server server);
	// 서버 동작 여부를 확인하기 위함
	Server ping(String ipAddress) throws UnknownHostException;
	// pagination 을 위해 limit 을 사용
	Collection<Server> list(int limit);
	// 서버 정보를 얻기 위함
	Server get(Long id);
	// 서버 정보를 업데이트하기 위함
	Server update(Server server);
	// 서버 정보를 지우기 위함
	Boolean delete(Long id);
}
