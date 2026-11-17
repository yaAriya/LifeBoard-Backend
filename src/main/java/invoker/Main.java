package invoker;

import config.RootConfig;
import exception.CardServiceException;
import exception.UserServiceException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CardService;
import service.UserService;

public class Main {
    static void main() throws UserServiceException, CardServiceException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RootConfig.class);
        UserService userService = context.getBean(UserService.class);
        CardService cardService = context.getBean(CardService.class);
    }
}
