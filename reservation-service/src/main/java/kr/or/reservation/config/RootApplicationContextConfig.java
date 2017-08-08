package kr.or.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {
        "kr.or.reservation.dao",
        "kr.or.reservation.service"
})
@Import({DbConfig.class}) // DBConfig 를 설정한다.
public class RootApplicationContextConfig {
}