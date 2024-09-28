package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;
import za.ac.cput.service.interfaces.IContactService;

@Service
public class ContactService implements IContactService {

    ContactRepository contactRepository;

    @Autowired
    ContactService(ContactRepository contactRepository){this.contactRepository = contactRepository;}

    @Override
    public Contact create(Contact obj) {
        return contactRepository.save(obj);
    }

    @Override
    public Contact read(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact update(Contact obj) {
        return contactRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
