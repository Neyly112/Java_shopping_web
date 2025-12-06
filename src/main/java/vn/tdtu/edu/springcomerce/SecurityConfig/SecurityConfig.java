package vn.tdtu.edu.springcomerce.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.tdtu.edu.springcomerce.Authentication.CustomUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new CustomUrlAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        // Bỏ qua bảo vệ CSRF cho các endpoint được chỉ định
                        .ignoringRequestMatchers("/delete/**", "/ordersView/{id}", "/register", "/add-to-cart", "/deleteView/{id}", "/deleteUser/{id}", "/orders/**", "/deleteAd/**", "/updateQuantity", "/updateQuantity1", "/api/products/delete/{id}", "/add-to-cartAd", "/deleteOrder/{id}", "/acceptOrder/{id}", "/**", "/accept/{id}", "/images/**", "/searchUser", "/deleteView/{id}", "/indexUser", "/indexAdmin", "/updateQuantity", "/updateQuantity1",
                                "/forgotPassword", "/deleteUser/{id}", "/resetPassword", "/indexUser",
                                "/login", "/register", "/products", "/products/{id}", "/products/add", "/add-to-cart",
                                "/products/filter", "/cart", "/cart/add",
                                "/remove-from-cart", "/orders/checkout", "/deleteOrder/{id}", "/delete/**", "/delete", "/orders/**", "/search", "/api/products/delete/{id}")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**", "/accept/{id}", "/images/**", "/searchUser", "/deleteView/{id}", "/indexUser", "/indexAdmin", "/updateQuantity", "/updateQuantity1",
                                "/forgotPassword", "/deleteUser/{id}", "/resetPassword", "/indexUser",
                                "/login", "/register", "/products", "/products/{id}", "/products/add", "/add-to-cart",
                                "/products/filter", "/cart", "/cart/add",
                                "/remove-from-cart", "/orders/checkout", "/deleteOrder/{id}", "/delete/**", "/delete", "/orders/**", "/search", "/api/products/delete/{id}", "/ordersView/{id}")
                        .permitAll().anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(myAuthenticationSuccessHandler())
                        .permitAll())
                .rememberMe(rememberMe -> rememberMe.tokenValiditySeconds(86400)
                        // thời gian hiệu lực của cookie (1 ngày)
                        .key("uniqueAndSecret"))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll());
        return http.build();
    }

}