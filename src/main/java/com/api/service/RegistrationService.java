package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;


    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getRegistrations(){
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;

        //using java 8 features in my project which is stream api.which help me to convert all the entity to dto.
//This service layer has to be called from controller.we have to follow a proper architecture for easy maintenance.
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {//job of this method is to save the data in database.
      //  copy Dto to Entity
       Registration registration = mapToEntity(registrationDto);
        Registration SavedEntity = registrationRepository.save(registration);

        //copy Entity to Dto
        RegistrationDto dto = mapToDto(SavedEntity);
        return dto;
    }
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }
    Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = modelMapper.map(registrationDto, Registration.class);
//        Registration registration = new Registration();
//        registration.setName(registrationDto.getName());
//        registration.setEmail(registrationDto.getEmail());
//        registration.setMobile(registrationDto.getMobile());
        return registration;
    }
    RegistrationDto mapToDto(Registration registration){
        RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
//        RegistrationDto dto = new RegistrationDto();
//        dto.setName(registration.getName());
//        dto.setEmail(registration.getEmail());
//        dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegistrationById(long id) {
//        Registration registration = registrationRepository.findById(id).get();
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("Record not found")
                );
        return mapToDto(registration);
    }
}
/*we are using lombok which avoid getters and setters.somewhere we are reducing the number of lines of code.
now we are shifted to go to a method by making a code reusable,So again the code maintainability will be easy.
 */