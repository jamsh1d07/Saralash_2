package uz.saralash.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.saralash.repository.AddressRepository;
import uz.saralash.repository.UniversityRepository;

@Service
@RequiredArgsConstructor
public class AddressService {

    final AddressRepository addressRepository;
}
