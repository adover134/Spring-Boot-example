package resource;

import java.util.Map;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import enumeration.Status;
import implementation.ServerServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import model.Response;
import model.Server;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ServerResources {
	private final ServerServiceImplementation serverService;
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("servers", serverService.list(30)))
				.message("Servers retrieved")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws UnknownHostException {
		
		Server server = serverService.ping(ipAddress);
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", server))
				.message(server.getStatus() == Status.SERVER_UP? "Ping Success" : "Ping Failed")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		// create는 반환 값이 없어서 ok로 대체한다고 함
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				// 전송할 내용
				.data(Map.of("server", serverService.create(server)))
				.message("Server created")
				.status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value())
				.build()
		);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", serverService.get(id)))
				.message("Server retrieved")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		return ResponseEntity.ok(
			Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("deleted", serverService.delete(id)))
				.message("Server deleted")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
		);
	}
	
	@GetMapping(path="/image/{fileName}")
	public ResponseEntity<byte[]> getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		byte[] imageBytes = Files.readAllBytes(Paths.get("C:/Users/ASUS/OneDrive/바탕 화면/Spring boot workspace/server_demo/image/" + fileName));
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> home() {
	    return ResponseEntity.ok("서버가 정상적으로 실행 중입니다 🚀");
	}
}
