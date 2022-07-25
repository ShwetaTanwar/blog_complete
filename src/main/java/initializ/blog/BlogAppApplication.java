package initializ.blog;

import java.util.List;

import org.hibernate.id.ResultSetIdentifierConsumer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import initializ.blog.config.AppConstansts;
import initializ.blog.entities.Role;
import initializ.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	 //ROLE_NON_ADMIN=2;
	//   ROLE_ADMIN=1;
	

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper ();
	}

	@Override
	public void run(String... args) throws Exception {
	System.out.println(this.passwordEncoder.encode("shweta"));
	try { 
		 Role role=new Role();                          //  create both role if not exist, in table
		 role.setId(AppConstansts.ROLE_ADMIN);
		 role.setName("ROLE_ADMIN");
		 
		 Role role1=new Role();
		 role1.setId(AppConstansts.ROLE_NON_ADMIN);
		 role1.setName("ROLE_NON_ADMIN");
		 
		 List<Role> roles = List.of(role,role1);
		 List<Role>result=this.roleRepo.saveAll(roles);
		 result.forEach(r->{  System.out.println(r.getName());
	                    });                                 
     	}
	catch (Exception e) {e.printStackTrace();}
}}
