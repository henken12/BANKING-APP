package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//this annotation show information in the swagger ui
@Tag(
        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EasyBank to CREATE,UPDATE,FETCH AND DELETE account details"
)
@RestController
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountsService iAccountsService;
// this shows information with in the post method in swagger ui
    @Operation(
            summary = "Creat Account REST API",
            description = "REST API to creat new Customer & Account inside EazyBank"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
@PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

      iAccountsService.createAccount(customerDto);
      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
  }

  @Operation(
          summary = "Fetch Account Details REST API",
          description = "REST API to fetch Customer & Account details based on a mobile number"
  )
 @ApiResponse(
          responseCode ="200",
    description ="HTTP status OK"
  )

  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                           String mobileNumber){
            CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
            return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }
  @Operation(
          summary = "update Account Details REST API",
          description = "REST API to update Customer & Account details based on a account number"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "HTTP Status OK"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "HTTP Status Internal Server Error",
                  //since out open api documentation can't scan global exeption
                  //we need to provide to provide in shema here
                  content = @Content(
                          schema = @Schema(implementation = ErrorResponseDto.class)
                  )
          )
  })

  @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
    boolean isUpdated = iAccountsService.updateAccount(customerDto);
      if (isUpdated) {
          return ResponseEntity.status(HttpStatus.OK)
                  .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
      }else{
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
      }
  }

  @Operation(
          summary = "Delete Account & Customer Details REST API",
          description = "REST API to delete Customer & Account details based on a mobile number"
  )
  @ApiResponses({
          @ApiResponse(
                  responseCode = "200",
                  description = "HTTP Status OK"
          ),
          @ApiResponse(
                  responseCode = "500",
                  description = "HTTP Status Internal Server Error"
          )
  })

  @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                              @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                              String mobileNumber){
    boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
    if(isDeleted){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
    }else{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }
  }
}
