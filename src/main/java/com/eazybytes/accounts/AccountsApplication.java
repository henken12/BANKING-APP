package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//for now our project is in one package and sub packages but if we are using different package we can use this
/*@componentScans({com.eazybytes.accounts.repository})
@EnableScan("com.eazybytes.accounts.model")
@EntityScan("com.eazybytes.accounts.model")
 */
@SpringBootApplication
//this is when we need to do auditing when it is created by who
// instead of hard code we use open spring boot
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
//this is the information we need to provide for the swagger api users
// it has link information about who need to contact if there is any malfunctioning
@OpenAPIDefinition(info = @Info(
        title = "Account microservices REST API Documentation",
        description = "EazyBank Accounts microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
                name = "Henok Gebremichael",
                email = "gebremichaelhenken@gmail.com",
                url = "https://www.henok.com"
        ),
        license = @License(
                name = "Apache 2.0",
                url = "https://www.henok.com"
        )
        ),
        externalDocs = @ExternalDocumentation(
                description = "eazybank Accounts microservice REST API Documentation",
                url = "https://www.damydocumentation/swagger-ui.html"
        )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
