package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//instruction for swagger ui
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(
            description = "Name of the Customer",example = "Henok Gebremichael"
    )
    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5,max = 30,message = "The length on the customer name should be between 5 and 30")
    private String name;
    @Schema(
            description = "Email address of the customer",example = "gebremichaelhenken@gmail.com"
    )
    @NotEmpty(message = "Email address can't be null or empty")
    @Email(message = "email address should be a valid value")
    private  String email;
    @Schema(
            description = "Mobile Number of the customer", example = "5614553378"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @Schema(
            description = "Account details of the customer"
    )
    private AccountsDto accountsDto;
}
