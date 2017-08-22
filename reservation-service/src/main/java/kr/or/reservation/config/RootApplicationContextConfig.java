package kr.or.reservation.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

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
	public String getNaverClientId() {
		return NAVER_CLIENT_ID;
	}
	
	@Bean
	public String getNaverClientSecret() {
		return NAVER_CLIENT_SECRET;
	}
}