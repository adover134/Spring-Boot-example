package repo;
import model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository는 <모델, PK의 타입>을 템플릿으로 가진다.
// 해당하는 모델에 대해 쉬운 CRUD를 지원한다.
// serializer의 역할을 포함한다.
public interface ServerRepo extends JpaRepository<Server, Long>{
	Server findByIpAddress (String ipAddress);
}