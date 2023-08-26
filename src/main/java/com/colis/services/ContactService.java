package com.colis.services;

import com.colis.dto.ContactDTO;
import com.colis.dto.ProfileDTO;
import com.colis.entities.ContactEntity;
import com.colis.entities.ProfileEntity;
import com.colis.mappers.ContactMapper;
import com.colis.mappers.ProfileMapper;
import com.colis.repositories.ContactRepository;
import com.colis.repositories.ProfileReopsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

    private final ContactRepository contactRepository;

    private final ProfileReopsitory profileReopsitory ;

    private final ProfileMapper profileMapper;

    private final ContactMapper contactMapper;

    public List<ProfileDTO> findAllContactByUser(String id){

        List<ContactEntity> listId = contactRepository.findAllByProfile_Id(id);

        List<ProfileDTO> listProfile = new ArrayList<>();

        listId.forEach(contact -> listProfile.add(profileMapper.map(profileReopsitory.findById(contact.getContactId()).get())) );

        return listProfile ;

    }

    public void SaveContact(ContactDTO contactDTO){
         contactRepository.save(contactMapper.map(contactDTO));
    }
}
