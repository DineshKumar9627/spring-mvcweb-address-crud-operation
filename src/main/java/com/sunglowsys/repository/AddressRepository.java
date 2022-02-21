package com.sunglowsys.repository;

import com.sunglowsys.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(" from Address address where address.addressLine1 like :searchText% " +
            " or address.addressLine2 like :searchText% or address.city like :searchText% " +
            " or address.state like :searchText% or address.country like :searchText% ")
    List<Address> search(@RequestParam String searchText);
}
