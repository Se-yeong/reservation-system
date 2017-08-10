package kr.or.reservation.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.reservation.common.FileIO;

@RestController
@RequestMapping("/download/file")
public class FIleController {

	@GetMapping
	public void downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        try{
        	FileIO.readFile(fileName, response.getOutputStream());
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
	
}
