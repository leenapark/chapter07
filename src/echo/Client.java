package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// Server와 다른 컴퓨터라는 가정

		Socket socket = new Socket();

		// 확인용 프린트
		System.out.println("<클라이언트 시작>");
		System.out.println("=================================");
		System.out.println("[서버에 연결을 요청합니다.]");

		// 접속 시도
		socket.connect(new InetSocketAddress("220.86.74.92", 10001));

		System.out.println("[서버에 연결되었습니다.]");

		// 정상 작동시
		// socket <-서로 연결-> socket
		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		// 메세지 받기 용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		// Scanner로 입력 받아서 보내고 받기
		// 키보드 입력
		Scanner sc = new Scanner(System.in);

		// 반복 구간
		while (true) {//디버깅을 해줘야함 (디버깅이란? - 제작자가 의도한대로 프로그램이 돌아가는지 확인하는 것)
			String str = sc.nextLine();
			//어떤 조건이 충족되었을 때 반복 그만
			if("/q".equals(str)) {	//문자열 비교 - .equals() *str.equals("/q") - nullpoint 오류가 날 수 있음
				break;
			}
			
			// 메세지 보내기
			bw.write(str);
			bw.newLine();
			bw.flush(); // 강제로 보내줌

			// 메세지 받기
			String reMsg = br.readLine();
			System.out.println("server:[" + reMsg + "]");
		}

		// 프로그램 종료 잊지 말고 닫아주기
		sc.close();
		bw.close();
		br.close();

		System.out.println("=========================");
		System.out.println("<클라이언트 종료>");

		socket.close();
	}

}
