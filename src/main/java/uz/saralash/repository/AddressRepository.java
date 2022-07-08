package uz.saralash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.saralash.entity.Address;
import uz.saralash.entity.University;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
