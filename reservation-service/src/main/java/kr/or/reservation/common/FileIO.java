package kr.or.reservation.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.or.reservation.domain.FileDomain;

@Component
public class FileIO {

	private static String FILE_BASE_DIR;

	static Logger log = Logger.getLogger(FileIO.class);

	@Autowired
	@Qualifier("fileBaseDir")
	public void setFileBaseDir(String fileBaseDir) {
		FILE_BASE_DIR = fileBaseDir;
	}

	// Static을 지우고 DI
	public static void readFile(String fileName, OutputStream outputStream) {
		String saveFileName = FILE_BASE_DIR + fileName;

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

	public static FileDomain[] writeFile(long userId, MultipartFile[] files) {
		int fileLength = files.length;
		FileDomain[] fileArray = new FileDomain[fileLength];

		if (files != null && fileLength > 0) {
			String formattedDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			File f = new File(FILE_BASE_DIR + formattedDate);
		
			if (!f.exists()) {
				f.mkdirs();
			}
			for (int i = 0; i < fileLength; ++i) {
				String contentType = files[i].getContentType();
				String originalFilename = files[i].getOriginalFilename();
				long size = files[i].getSize();

				String uuid = UUID.randomUUID().toString();
				String saveFileName = formattedDate + "/" + uuid + ".jpg";
				
				try (InputStream in = files[i].getInputStream();
						FileOutputStream fos = new FileOutputStream(FILE_BASE_DIR + saveFileName)) {
					int readCount = 0;
					byte[] buffer = new byte[512];
					while ((readCount = in.read(buffer)) != -1) {
						fos.write(buffer, 0, readCount);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				saveFileName = "/api/file?fileName=" + saveFileName;
				FileDomain file = new FileDomain();
				file.setUserId(userId);
				file.setFileName(originalFilename);
				file.setSaveFileName(saveFileName);
				file.setFileLength(size);
				file.setDeleteFlag(0);
				file.setContentType(contentType);
				file.setCreateDate(new Timestamp(System.currentTimeMillis()));
				file.setModifyDate(new Timestamp(System.currentTimeMillis()));
				fileArray[i] = file;
				
				log.info(file);
			} // for

		} // if
		return fileArray;
	}

}
