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

      User user1 = new User("Leva", "Alekseev", "Leva@mail.ru");
      user1.setCar(new Car("BMW", 5));
      User user2 = new User("Maksim", "Valiullin", "Valiullin@mail.ru");
      user2.setCar(new Car("Boing", 700));
      User user3 = new User("Sergei", "Petrov", "Petrov@yandex.ru");
      user3.setCar(new Car("Lada", 2114));
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      User user4 = userService.getUser("BMW", 5);

      /*userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));*/

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car_id = " + user.getCar().getId());
         System.out.println();
      }

      context.close();
   }
}
