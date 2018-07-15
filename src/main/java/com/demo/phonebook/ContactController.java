package com.demo.phonebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ContactController {
        @Autowired
        private ContactRepository contactRepository;

        @GetMapping("/contacts")
        public List<Contact> listContact(){
            return new ArrayList<>(contactRepository.findAll());
        }
        @PostMapping("/contacts/create")
        public Contact createContact(@RequestBody Contact contact){
            return contactRepository.save(contact);
        }

    @DeleteMapping("/contacts/deleteContact")
    public ResponseEntity<?> deleteContact(@RequestBody DeleteContactRequestWrapper deleteContactRequest){
        return contactRepository.findById(deleteContactRequest.getContactId()).map(contact -> { //java 8 ile gelen bir özellik
            contactRepository.delete(contact);
            return new ResponseEntity<>("Deleted",
                    HttpStatus.OK);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + deleteContactRequest.getContactId() + " not found"));
    }

    @PutMapping("/contacts/updateContact")
    public Contact updateContact(@Valid @RequestBody Contact contactRequest) {
        return contactRepository.findById(contactRequest.getContactId()).map(contact -> {
            contact.setFirstName(contactRequest.getFirstName());
            contact.setLastName(contactRequest.getLastName());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new ResourceNotFoundException("ContactId " + contactRequest.getContactId() + " not found"));
    }
}

class DeleteContactRequestWrapper{

    private Long contactId;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
