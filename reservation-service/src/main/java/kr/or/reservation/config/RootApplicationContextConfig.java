package kr.or.reservation.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:/application.properties")
@ComponentScan(basePackages = { "kr.or.reservation.dao", "kr.or.reservation.service", "kr.or.reservation.common" })
@Import({ DbConfig.class }) // DBConfig 를 설정한다.
public class RootApplicationContextConfig {

	@Value("${file.baseDir}")
	private String fileBaseDir;
	
	@Value("${naver.client.id}")
	private String NAVER_CLIENT_ID;
	
	@Value("${naver.client.secret}")
	private String NAVER_CLIENT_SECRET;

	@Bean
	@Qualifier("fileBaseDir")
	public String getFileBaseDir() {
		return fileBaseDir;
	}
	
	@Bean
	@Qualifier("NAVER_CLIENT_ID")
	public String getNaverClientId() {
		return NAVER_CLIENT_ID;
	}
	
	@Bean
	@Qualifier("NAVER_CLIENT_SECRET")
	public String getNaverClientSecret() {
		return NAVER_CLIENT_SECRET;
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
	     
	    requestFactory.setReadTimeout(5000);
	    requestFactory.setConnectTimeout(5000);

	    HttpMessageConverter<?> formHttpMessageConverter = new FormHttpMessageConverter();
	    StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter( Charset.forName( "UTF-8" ) );
	    MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
	    messageConverters.add(formHttpMessageConverter);
	    messageConverters.add(stringMessageConverter);
	    messageConverters.add(jackson2Converter);
	    
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(requestFactory);
	    restTemplate.setMessageConverters(messageConverters);
	    
	    return restTemplate;
	}
}