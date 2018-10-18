package jp.co.ixui.scheduleadjustment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	


	// 特定のリクエストに対してセキュリティ設定をカスタマイズする
		@Override
		public void configure(WebSecurity web) throws Exception {
			// 静的リソースに対する認証処理を無効にする
			web.ignoring()
					.antMatchers("/css/**", "/js/**")
					.antMatchers(HttpMethod.POST, "/userregisted/**");
		}

		
		// 認証設定
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			// index、新規登録にはすべてのユーザがアクセスできるようにする
			http.authorizeRequests()
					.antMatchers("/","/newuser","/login-error","/userregisted/**").permitAll()
					.anyRequest().authenticated();

			// ログイン
			// フォーム認証を有効に
			http.formLogin()
					// ログインフォーム表示パス
					.loginPage("/")
					// ログイン処理のパス
					.loginProcessingUrl("/login")
					//失敗時にどこに遷移するか
					.failureUrl("/login-error")
					// 認証成功時の遷移先
					.defaultSuccessUrl("/eventlist", true)
					// ユーザ名、パスワードのパラメータ名
					.usernameParameter("empNum").passwordParameter("passWord")
					.and();

			// ログアウト
			http.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					// ログアウト完了後の遷移先
					.logoutSuccessUrl("/")
					// ログアウト時のセッション破棄を有効化
					.invalidateHttpSession(true)
					.permitAll();
		}

		@Configuration
		protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

			@Autowired
			UserDetailsService userDetailsService;
			
			
			@Override
			public void init(AuthenticationManagerBuilder auth) throws Exception {

				// 認証するユーザーを設定する
				auth.userDetailsService(userDetailsService)
				// 入力値をbcryptでハッシュ化した値でパスワード認証を行う
				.passwordEncoder(new BCryptPasswordEncoder());

				
			}
			
		}
}
