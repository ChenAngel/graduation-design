package chenangel.graduationdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("chenangel.graduationdesign.generator.mapper")
public class GraduationDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraduationDesignApplication.class, args);
	}
}
