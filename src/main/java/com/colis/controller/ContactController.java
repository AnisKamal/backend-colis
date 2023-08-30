package com.colis.controller;

import com.colis.dto.ContactDTO;
import com.colis.dto.PostDTO;
import com.colis.dto.ProfileDTO;
import com.colis.entities.PostEntity;
import com.colis.services.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ProfileDTO>> findContactByUser(@PathVariable String id) {
        log.info("find all contacts by user ");
        List<ProfileDTO> profileDTOList = contactService.findAllContactByUser(id);
        log.info("Contact : fetch contacts by user success ");
        return  new ResponseEntity<>(profileDTOList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveContact(@RequestBody ContactDTO contactDTO){
        log.info("save contact ");
        contactService.SaveContact(contactDTO);
        log.info("contact : contact save success ");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }





}
