package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.entity.Address;
import uz.saralash.entity.University;
import uz.saralash.payload.ApiResponse;
import uz.saralash.payload.UniversityDto;
import uz.saralash.repository.AddressRepository;
import uz.saralash.repository.UniversityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityService {

    final UniversityRepository universityRepository;
    final AddressRepository addressRepository;

    public ApiResponse getAll() {

        return new ApiResponse("Get All", true, universityRepository.findAllByDeletedFalse());
    }

    public ApiResponse getOne(Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent() && !optionalUniversity.get().isDeleted()) {
            return new ApiResponse("One University", true, optionalUniversity.get());
        }
        return new ApiResponse("No content", false);
    }

    public ApiResponse save(UniversityDto dto) {
        Optional<University> optionalUniversity = universityRepository.findByName(dto.getName());
        if (optionalUniversity.isEmpty()) {
            University university = new University();
            university.setName(dto.getName());
            Address address = new Address();
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setHome(dto.getHome());
            addressRepository.save(address);
            university.setAddress(address);
            university.setOpenYear(dto.getOpenYear());
            universityRepository.save(university);
            return new ApiResponse("Save University", true);
        }
        return new ApiResponse("There is a university with the same name", false);
    }

    public ApiResponse edit(Integer id, UniversityDto dto) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            University university = byId.get();
            Address address = new Address();
            address.setCity(dto.getCity());
            address.setStreet(dto.getStreet());
            address.setHome(dto.getHome());
            if (!university.getName().equals(dto.getName()) && !university.getAddress().getCity().equals(address.getCity())
                    && !university.getAddress().getStreet().equals(address.getStreet()) && !university.getAddress().getHome().equals(address.getHome())) {
                university.setName(dto.getName());
                Address address1 = university.getAddress();
                address1.setCity(address.getCity());
                address1.setStreet(address.getStreet());
                address1.setHome(address.getHome());
                university.setAddress(address1);
                university.setOpenYear(dto.getOpenYear());
                universityRepository.save(university);
                return new ApiResponse("Edited University!", true);
            }
        }
        return new ApiResponse("No Edited!", true);

    }

    public ApiResponse delete(Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            University university = byId.get();
            university.setDeleted(true);
            universityRepository.save(university);
            return new ApiResponse("Deleted", true, university.getName());
        }
        return new ApiResponse("No Deleted!", true);
    }
}
