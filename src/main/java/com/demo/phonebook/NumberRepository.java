package com.demo.phonebook;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NumberRepository extends JpaRepository<Number, Long> {

    public Number findByNumber(String number);
    public Number findByWhich(String which);
    public Page<Number> findByContact(Contact contact, Pageable pageable);


}
