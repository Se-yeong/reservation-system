package kr.or.reservation.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class FileIO {

	private static String FILE_BASE_DIR;

	static Logger log = Logger.getLogger(FileIO.class);

	@Autowired
	public FileIO(String fileBaseDir) {
		this.FILE_BASE_DIR = fileBaseDir;
	}

	// Static을 지우고 DI
	public static void readFile(String fileName, OutputStream outputStream) {
		String saveFileName = fileBaseDir + fileName;

		java.io.File readFile = new java.io.File(saveFileName);
		
		try (FileInputStream fileInputStream = new FileInputStream(readFile)) {
			if (!readFile.exists()) {
				throw new RuntimeException("file not found");
			}
			FileCopyUtils.copy(fileInputStream, outputStream); // 파일을 저장할때도 사용할 수 있다.

			outputStream.flush();
		} catch (IOException e) {

		}

	}
}
