package lab.phb.mhsangularb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web
	.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.annotation.web
	.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation
	.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation
	.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf
	.CsrfTokenRepository;
import org.springframework.security.web.csrf
	.HttpSessionCsrfTokenRepository;
import lab.phb.mhsangularb.util.CsrfHeaderFilter;
import javax.sql.DataSource;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource ds;

	private String qryUsers = 
	"select username, password, enabled " +
	"from users " +
	"where username = ? ";

	private String qryRoles =
	"select u.username, r.role " +
	"from users u " +
	"join user_role ur on (ur.username = u.username) " +
	"join roles r on (r.id = ur.id_role) " +
	"where u.username = ? ";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
			throws Exception {

		// auth.inMemoryAuthentication()
		// 	.withUser("azka")
		// 	.password("rahasia")
		// 	.roles("ADMIN");

			auth.jdbcAuthentication()
				.dataSource(ds)
				.usersByUsernameQuery(qryUsers)
				.authoritiesByUsernameQuery(qryRoles);

	}

	@Override
	protected void configure(HttpSecurity http) 
			throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/bootstrap/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/coba-app.js").permitAll()
				.antMatchers("/edit-controller.js").permitAll()
				.antMatchers("/form-controller.js").permitAll()
				.antMatchers("/hapus/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			.and()
				.formLogin().permitAll()
			.and()
				.logout()
			.and()
				.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.csrf().csrfTokenRepository(csrfTokenRepository());
	}

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository tokenRepo = new HttpSessionCsrfTokenRepository();
		tokenRepo.setHeaderName("X-XSRF-TOKEN");
		return tokenRepo;
	}
	
}