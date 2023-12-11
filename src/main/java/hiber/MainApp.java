package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User ("Tom", "Cat", "leo@mail.ru" );
      Car car1 = new Car ( user1, "Niva", 777 );
      user1.setCar( car1 );
      userService.add( user1 );

      User user2 = new User ("Jerry", "Mouse", "batman@mail.ru" );
      Car car2 = new Car ( user2, "Moskvich", 123 );
      user2.setCar( car2 );
      userService.add( user2 );

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      System.out.println( userService.getUser( "Niva", 777 ) );

      context.close();
   }
}
