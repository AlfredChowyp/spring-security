package com.yun.security.core.social.qq;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.yun.security.core.properties.SecurityProperties;

@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator,Encryptors.noOpText());
	}
	
	@Bean
	public SpringSocialConfigurer yunSocialSecurityConfig(){
		String filterProcessUrl = securityProperties.getSocial().getFilterProcessesUrl();
		YunSpringSocialConfigurer yunSpringSocialConfigurer = new YunSpringSocialConfigurer(filterProcessUrl);
		return yunSpringSocialConfigurer;
	}
}
