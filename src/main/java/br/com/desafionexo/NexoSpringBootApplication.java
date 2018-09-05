package br.com.desafionexo;

import br.com.desafionexo.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ReadingConverter
@SpringBootApplication
@AutoConfigurationPackage
public class NexoSpringBootApplication extends SpringBootServletInitializer implements CommandLineRunner  {

    @Autowired
    UsuarioService userService;

   @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NexoSpringBootApplication.class);
    }

    @Override
    public void run(String... params) throws Exception {

       //Just rtun one time
      /*  UsuariosEntity admin = new UsuariosEntity();
        admin.setUsername("admin");
        admin.setSenha("admin");
        admin.setRole(Role.ROLE_ADMIN.name());

        userService.signup(admin);

        UsuariosEntity client = new UsuariosEntity();
        client.setUsername("client");
        client.setSenha("client");
        client.setRole(Role.ROLE_CLIENT.name());

        String signup = userService.signup(client);*/
    }


    public static void main(String[] args) {
        SpringApplication.run(NexoSpringBootApplication.class, args);
    }
}
