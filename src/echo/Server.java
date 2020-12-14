package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// Server랑 다른 컴에서 실행되어 있다는 가정
		ServerSocket serverSocket = new ServerSocket();

		// 아이피(고정) 하고 포트 번호(부여가능) 설정해줘야함
		// 바인드 220.86.74.92 10001
		serverSocket.bind(new InetSocketAddress("220.86.74.92", 10001));

		System.out.println("<서버시작>");
		System.out.println("====================================");
		System.out.println("[연결을 기다리고 있습니다.]");

		while (true) {
			// accept
			Socket socket = serverSocket.accept();
			System.out.println("[클라이언트가 연결되었습니다.]");
						
			//	출장보내기 .start
			Thread thread = new ServerThread(socket);
			thread.start();
		}

		/* 위 while 문이 무한 반복임으로 오류남
		System.out.println("=========================");
		System.out.println("<서버 종료>");

		serverSocket.close();
		*/
		
	}

}
