package kr.or.reservation.controller.rest;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.reservation.common.FileIO;
import kr.or.reservation.domain.FileDomain;
import kr.or.reservation.domain.User;
import kr.or.reservation.service.CommentService;
import kr.or.reservation.service.ImageService;

@RestController
@RequestMapping("/api/file")
public class FileController {

	ImageService imgService;
	CommentService commentService;
	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	public void setImgService(ImageService imgService) {
		this.imgService = imgService;
	}

	@Autowired
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping
	public void downloadFile(@RequestParam String fileName, HttpServletResponse response) {
		try {
			FileIO.readFile(fileName, response.getOutputStream());
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

	@PostMapping("/image")
	public String insertFileImageAndUpdate(@RequestParam("files") MultipartFile[] files,
			@RequestParam("commentId") int commentId, HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		FileDomain[] fileArray = FileIO.writeFile(user.getId(), files);
		int[] fileId = imgService.insertFileArray(fileArray);
		log.info(fileId[0] + " " + fileArray[0].toString());
		boolean insertImage = imgService.insertImageArray(commentId, fileId);
		boolean updateFile = commentService.updatefirstFileName(commentId, fileArray[0], fileId.length);
		if (insertImage && updateFile) {
			return "redirect:/img";
		}
		// 실패시 main으로
		return "redirect:/";
	}

}
