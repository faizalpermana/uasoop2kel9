package lab.phb.mhsangularb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebConfig extends 
		WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(
			ViewControllerRegistry reg) {
		reg.addViewController("/")
			.setViewName("daftar-mahasiswa");
		reg.addViewController("/coba")
			.setViewName("coba");
		reg.addViewController("/daftar")
			.setViewName("daftar-mahasiswa");
		reg.addViewController("/form")
			.setViewName("form");
		reg.addViewController("/edit")
			.setViewName("form-edit");
	}
 
}