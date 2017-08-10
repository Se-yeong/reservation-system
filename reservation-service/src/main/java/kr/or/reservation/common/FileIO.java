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

	private static String fileBaseDir;
	
	static Logger log = Logger.getLogger(FileIO.class);

	@Autowired
	public FileIO(String fileBaseDir) {
		this.fileBaseDir = fileBaseDir;
	}

	public static void readFile(String fileName, OutputStream outputStream) throws IOException {
		String saveFileName = fileBaseDir + fileName;
		log.info(saveFileName);
	
		java.io.File readFile = new java.io.File(saveFileName);

		if (!readFile.exists()) {
			throw new RuntimeException("file not found");
		}

		FileInputStream fileInputStream = null;

		fileInputStream = new FileInputStream(readFile);
		FileCopyUtils.copy(fileInputStream, outputStream); // 파일을 저장할때도 사용할 수 있다.
		outputStream.flush();

		fileInputStream.close();
	}
}
