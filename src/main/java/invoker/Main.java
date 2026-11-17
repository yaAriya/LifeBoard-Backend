package invoker;

import config.RootConfig;
import exception.UserServiceException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.UserService;

public class Main {
    static void main() throws UserServiceException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
    }
}
