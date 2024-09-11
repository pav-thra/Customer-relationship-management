package com.example.CRM.Controller;

import com.example.CRM.Entities.Contacts;
import com.example.CRM.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/contacts")

public class ContactController
{
    /*The @Autowired annotation is used for dependency injection in Spring Framework, allowing you to inject beans (objects) into your application at runtime.
    It can be applied to constructors, setter methods, properties, and config methods.*/

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    private List<Contacts> getAllContacts()
    {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Optional<Contacts>> getContactById(@PathVariable Integer id)
    {
        Contacts contact = contactRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("Contact not found"));

        return ResponseEntity.ok(Optional.ofNullable(contact));
    }

    @PostMapping
    private Contacts createContact(@RequestBody Contacts contact)
    {
        return contactRepository.save(contact);
    }

    @PutMapping("/{id}")
    //@PathVariable is an annotation provided by Spring that indicates a method parameter should be bound to a URI template variable.
    // This annotation is used to retrieve data from the URL path in a Spring-based RESTful API.

    public ResponseEntity<Contacts> updateContact(@PathVariable Integer id, @RequestBody Contacts contactDetails)
    {
        Contacts contact = contactRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contact not found"));

        contact.setFirst_name(contactDetails.getFirst_name());
        contact.setLast_name(contactDetails.getLast_name());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setCompany(contactDetails.getCompany());

        Contacts updatedContacts = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContacts);
    }

    @DeleteMapping("/{id}")
    /* Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);
    */
    public ResponseEntity<Map<String, Boolean>> deleteContact(@PathVariable Integer id)
    {
        Contacts contact = contactRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("Contact not found"));

        contactRepository.delete(contact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
