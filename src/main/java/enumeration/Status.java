package enumeration;

public enum Status {
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");
	
	// 사용자가 접근할 값
	private final String status;
	
	// Status 값을 String 값으로 받아서 해당하는 객체에 매칭시켜 저장한다.
	// 예를 들어 "SERVER_UP"이 들어오면 이에 매칭되는 SERVER_UP으로 저장된다.
	private Status(String status) {
		this.status = status;
	}
	
	// status 변수의 getter 이다.
	// status 변수의 현재 값을 반환한다.
	public String getStatus() {
		return this.status;
	}
}
