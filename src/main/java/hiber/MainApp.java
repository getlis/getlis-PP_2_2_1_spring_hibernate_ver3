package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User( "User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {

         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user1 = new User ("Tom", "Cat", "leo@mail.ru" );
      Car car1 = new Car ( user1, "Niva", 777 );
      user1.setCar( car1 );
      userService.add( user1 );

      User user2 = new User ("Jerry", "Mouse", "batman@mail.ru" );
      Car car2 = new Car ( user2, "Moskvich", 123 );
      user2.setCar( car2 );
      userService.add( user2 );

      System.out.println( userService.getCar( "Niva", 777 ) );

      context.close();
   }
}
