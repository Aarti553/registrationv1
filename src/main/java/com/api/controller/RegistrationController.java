package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")//its not normal url,its api uri.
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public ResponseEntity <List<RegistrationDto>> getAllRegistrations() {
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos,HttpStatus.OK);//Status 200
    }
    @PostMapping
    //public ResponseEntity<RegistrationDto>CreateRegistration(
            public ResponseEntity<?>CreateRegistration(
         /*
         when multiple types of data we are returning change into the <?>.
         when the same method has different-different kinds of values,
         e.g;Integer,String,Double,its return multiple kinds of value.
         either make it <Question> or <?> .<Question> is super most class in java.
          */
        @Valid @RequestBody RegistrationDto registrationDto,
        BindingResult result
    ){
         if(result.hasErrors()){
             return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
         }

        /*@Valid-which will ensure that the data going to RegistrationDto(object).
         firstly we are validating it,with the validater of Hibernet,and if it all correct then
         will proceed further.
         */
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto,HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String>deleteRegistration(
            @RequestParam long id
    ) {
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @PutMapping("/{id}")
   public ResponseEntity<Registration>updateRegistration(
           @PathVariable long id,
           @RequestBody Registration registration
   ){
        Registration updateReg= registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(updateReg,HttpStatus.OK);
   }
   @GetMapping("/{id}")
   public ResponseEntity<RegistrationDto> getRegistrationById(
           @PathVariable long id
   ){
        RegistrationDto dto = registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
   }
}
/*Note-1
our first Api is ready,what this part of my project will do,
will talk to the database,it will get all the data in Java object,that java objects are in this.the java objects
in this registrations but now when we return the java object GetMapping will convert that java object into JSON object.
end result will see JSON object.
http://localhost:8080/api/v1/registration
Delete,update,retrieve status=200 and creating=201
@GetMapping//this mapping helps us to read the data from database via Api.
@PostMapping//this mapping helps us to save the record in database via Api.
@DeleteMapping//this mapping helps us to Delete a record from database via Api.
@PutMapping//this mapping helps us to update the record in database via Api.
 @PathVariable long id  //how values are supplied in the URl as path parameter not query parameter.
 Delete,update,retrieve=200 and creating=201
*/
/* validation Note-2
1.Add the spring validation library.
2.Add the Annotation.
3.Add @Valid annotation and finding result.
4.if Result.hasErrors then return back the error message.
 @Valid-which will ensure that the data going to RegistrationDto(object).
  ** firstly we are validating it,with the validater of Hibernet,and if it all correct then
 will proceed further.
 ** when multiple types of data we are returning change into the <?>.
 when the same method has different-different kinds of values,
 e.g;Integer,String,Double,its return multiple kinds of value.
 either make it <Question> or <?> .<Question> is super most class in java.

 */