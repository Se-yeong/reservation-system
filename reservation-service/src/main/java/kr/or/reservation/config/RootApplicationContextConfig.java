package kr.or.reservation.config;

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

	@Bean
	public String fileBaseDir() {
		return fileBaseDir;
	}
}