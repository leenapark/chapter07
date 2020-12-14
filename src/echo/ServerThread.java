package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {

	// 필드
	private Socket socket;

	// 생성자 - 디폴트 생성자 삭제
	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 메소드 g/s - 생략

	// 메소드 일반
	@Override
	public void run() {

		try {
			// 정상 작동시
			// socket <-서로 연결-> socket
			// 메세지 받기용
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			// 메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			// 반복 구간
			while (true) {// 몇번 시도할 지 모를 때
				// 메세지 받기
				String msg = br.readLine();

				if (msg == null) {
					break;
				}

				System.out.println("받은 메세지:" + msg);

				// 메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();

			}

			// 연결 잊지 말고 닫아주기
			br.close();
			bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
