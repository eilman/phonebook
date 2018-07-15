package com.demo.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NumberController {

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/numbers")
    public List<Number> listNumber(){
        return new ArrayList<>(numberRepository.findAll());
    }

    @PostMapping("/numbers/createNumber")
    public Number createNumber(@RequestBody AddNumberRequestWrapper addNumberRequest){
        Optional<Contact> contact = contactRepository.findById(addNumberRequest.getContactId()); //null olmamalı
        if(contact.isPresent()){
            Number number = new Number(addNumberRequest.getNumber(), addNumberRequest.getWhich(), contact.get());
            numberRepository.save(number);
            return number;
        }

        return null;
    }

    @PostMapping("/contacts/{contactId}/numbers")
    public Page<Number> getNumbers(@PathVariable(value = "contactId") Long contactId, Pageable pageable){
        Optional<Contact> contact = contactRepository.findById(contactId);
        if (contact.isPresent()){
            return numberRepository.findByContact(contact.get(), pageable);
        }
        return null;
    }

    @DeleteMapping("/contacts/deleteNumber")
    public ResponseEntity<?> deleteNumber(@RequestBody DeleteNumberRequestWrapper deleteNumberRequest){
        if(!contactRepository.existsById(deleteNumberRequest.getContactId())) {
            throw new ResourceNotFoundException("PostId " + deleteNumberRequest.getContactId() + " not found");
        }

        return numberRepository.findById(deleteNumberRequest.getNumberId()).map(number -> {
            numberRepository.delete(number);
            return new ResponseEntity<>("Deleted",
                    HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + deleteNumberRequest.getNumberId() + " not found"));
    }

    @PutMapping("/contacts/updateNumber")
    public Number updateNumber(@RequestBody UpdateNumberRequestWrapper updateNumberRequest){
        if(!contactRepository.existsById(updateNumberRequest.getContactId())) {
            throw new ResourceNotFoundException("ContactId " + updateNumberRequest.getContactId() + " not found");
        }

        return numberRepository.findById(updateNumberRequest.getNumberId()).map(number -> {
            number.setNumber(updateNumberRequest.getNumber());

            return numberRepository.save(number);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + updateNumberRequest.getNumberId() + "not found"));

    }

}
class AddNumberRequestWrapper{
    private Long contactId;
    private String number;
    private String which;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

}

class DeleteNumberRequestWrapper{

    private Long numberId;
    private Long contactId;

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}

class UpdateNumberRequestWrapper{

    private Long contactId;
    private String number;
    private String which;
    private Long numberId;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }
}