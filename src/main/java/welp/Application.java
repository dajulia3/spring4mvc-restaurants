package welp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration // Also turns on @EnableWebMvc :-)
@ComponentScan(basePackages = {"welp"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    public static void main(String[] args) {
//        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//        UserRepository repository = context.getBean(UserRepository.class);
//
//        // save a couple of Users
//        repository.save(new User("Jack"));
//        repository.save(new User("Chloe"));
//        repository.save(new User("Kim"));
//        repository.save(new User("David"));
//        repository.save(new User("Michelle"));
//
//        // fetch all Users
//        Iterable<User> Users = repository.findAll();
//        System.out.println("Users found with findAll():");
//        System.out.println("-------------------------------");
//        for (User User : Users) {
//            System.out.println(User);
//        }
//        System.out.println();
//
//        // fetch an individual User by ID
//        User User = repository.findOne(1L);
//        System.out.println("User found with findOne(1L):");
//        System.out.println("--------------------------------");
//        System.out.println(User);
//        System.out.println();
//
//        // fetch Users by last name
//        List<User> bauers = repository.findByName("Jack");
//        System.out.println("User found with findByName('Jack'):");
//        System.out.println("--------------------------------------------");
//        for (User bauer : bauers) {
//            System.out.println(bauer);
//        }
//
//        context.close();
//    }


}