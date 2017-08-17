package kr.or.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.reservation.domain.User;
import kr.or.reservation.service.impl.LoginService;

@Controller
@RequestMapping
public class LoginController {
	
	private final static String IS_LOGIN_KEY = "this is isLoginKey";
	private final static String NAVER_CLIENT_ID = "Stl7ORdgyu0_h9RK7br7";
	private final static String NAVER_CLIENT_SECRET = "ha6Tu8Btvt";
	private final static String LOGIN_CALL_BACK_URI = "http://localhost:8080/logincallback";
	
	private final static String NAVER_GET_TOKEN_URL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	private final static String NAVER_GET_USER_URL = "https://openapi.naver.com/v1/nid/me";

	
	@GetMapping("/login")
	public String viewLogin(Model model) {
		return "login";
	}
	
	
	public String loginCallback(HttpServletRequest request) {
		
		return "login";
	}
	
	
	public ModelAndView login(HttpServletRequest request) {
		if( isLoggedIn(request) ) {
			return new ModelAndView("redirect:/myreservation");
		} else {
			return requestLogin(request);
		}	
	}
	
	public Boolean isLoggedIn(HttpServletRequest request) {
		String loginKey = (String) request.getSession().getAttribute("isLoginKey");
		if( loginKey != null && loginKey.equals(IS_LOGIN_KEY) ) {
			return true;
		} else {
			return false;
		}
	}
	
	public ModelAndView requestLogin(HttpServletRequest request) {
		String clientId = NAVER_CLIENT_ID; //애플리케이션 클라이언트 아이디값";
		String redirectURI = null;
		
		try {
			redirectURI = URLEncoder.encode( LOGIN_CALL_BACK_URI , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			
			return new ModelAndView("loginfail");
		}
		
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		request.getSession().setAttribute("state", state);
		
		
		ModelAndView mv = new ModelAndView("login","apiURL", apiURL);
		
		return mv;
	}
	
	@GetMapping("/logincallback")
	public ModelAndView loginCallback(HttpServletRequest request) {
		
	    String state = request.getParameter("state");
	    
	    try {
    		if ( !state.equals(request.getSession().getAttribute("state")) ) {
 	    	throw new Exception("state mismatch");
    		}
			
			Map<String, Object> tokenResponse = requestToken(state, request.getParameter("code") );
			
			
			String accessToken = (String) tokenResponse.get("access_token");
			String refreshToken = (String) tokenResponse.get("refresh_token");
			String tokenType = (String) tokenResponse.get("token_type");
			
			request.getSession().setAttribute("refreshToken", refreshToken);
			
			
			Map<String, Object> userResponse = requestUserInfo(tokenType, accessToken);
			
			ModelAndView mv = new ModelAndView("redirect:/myreservation", "tokenResponse", tokenResponse);
			mv.addObject("userResponse", userResponse);
			
			Map<String, Object> userInfo = ( Map<String, Object> ) userResponse.get("response");
			
			//System.out.println (userInfo);
			
			updateUser(userInfo);
			request.getSession().setAttribute("isLoginKey", IS_LOGIN_KEY);

		    return mv;
    	 
		    
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return new ModelAndView("loginerror", "error", e);
	    }
	    
	}

	public Map<String, Object> requestToken(String state, String code) throws Exception {
		String clientId = NAVER_CLIENT_ID;//애플리케이션 클라이언트 아이디값";
	    String clientSecret = NAVER_CLIENT_SECRET;//애플리케이션 클라이언트 시크릿값";
	    String redirectURI = null;
		
	    String apiURL;
	    apiURL = NAVER_GET_TOKEN_URL;
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    
	  
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON); 
	    
	    Map<String, Object> response = doGet(apiURL, headers);
	  
	    
	    return response;
	}
	
	public Map<String, Object> requestUserInfo(String tokenType, String accessToken) throws Exception {
		String authorization = tokenType + " " + accessToken;
		String apiURL = NAVER_GET_USER_URL;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON); 
		headers.add(HttpHeaders.AUTHORIZATION, authorization);
		
		Map<String, Object> response = doGet(apiURL, headers);
		
		String resultcode = (String) response.get("resultcode");
		String message = (String) response.get("message");
		
		if( (resultcode == null && message == null ) || 
				!(resultcode.equals("00") || message.equals("success")) ) {
			throw new Exception("Failed to receive user information.");
		}
		
		return response;
		
	}
	
	
	public Map<String, Object> doGet(String url, HttpHeaders headers) throws Exception {
		
		
	    HttpEntity<?> requestEntity =  new HttpEntity<>(headers);
	    ResponseEntity<Map<String, Object>> responseEntity = 
	    		restTemplate.exchange(url, HttpMethod.GET, requestEntity, 
	    				new ParameterizedTypeReference<Map<String,Object>>(){});
	    
	    
	    // System.out.println( "getStatusCode: " + responseEntity.getStatusCode() );
	    if( !responseEntity.getStatusCode().is2xxSuccessful()) {
	    	throw new Exception("Failed to request to server.");
	    }
	    Map<String, Object> response = responseEntity.getBody();
	    return response;
	}
	
	
	
	
	
	public void updateUser(Map<String, Object> userInfo) {
		String nickname = (String) userInfo.get("nickname");
		String profileImage = (String) userInfo.get("profile_image");
		String email = (String) userInfo.get("email");
		Integer naverId = Integer.parseInt( (String) userInfo.get("id") );
		
		User user = new User();
		if(nickname != null) {
			user.setNickname(nickname); 
		}
		if(email != null) {
			user.setEmail(email); 
		}
		if(naverId != null) {
			user.setNaverId(naverId); 
		}
	
		//System.out.println(user);
		if( userService.existByNaverId(naverId) ) {
			userService.updateByNaverId(user);
			//System.out.println("update");
		} else {
			userService.add(user);
			//System.out.println("add");
		}
		
	}
	
	
	public ModelAndView logout(HttpServletRequest request) {
		request.getSession().removeAttribute("isLoginKey");
		request.getSession().removeAttribute("refreshToken");
		
		/*https://nid.naver.com/oauth2.0/token?
			grant_type=delete&
			client_id=jyvqXeaVOVmV&
			client_secret=527300A0_COq1_XV33cf&
			access_token=c8ceMEJisO4Se7uGCEYKK1p52L93bHXLnaoETis9YzjfnorlQwEisqemfpKHUq2gY&
			service_provider=NAVER*/
		
		return new ModelAndView("redirect:/");
	}

	
	
	
}
