package pl.springinaction.tacocloud;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.springinaction.tacocloud.model.User;
import pl.springinaction.tacocloud.repository.UserRepository;
import pl.springinaction.tacocloud.service.UserService;

@Configuration
public class Start {


    public Start(UserService userService) {
//        String message = new SimpleGrantedAuthority("ROLE_USER").toString();
//        System.out.println(message);
        User user = new User();
        user.setUsername("username");
        user.setPassword("pass");
        user.setEnabled(true);
        user.setPhone("12312312");
        userService.addUSer(user);

    }
}
