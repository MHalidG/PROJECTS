package com.hillrent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hillrent.domain.ContactMessage;
import com.hillrent.exception.ResourceNotFoundException;
import com.hillrent.exception.message.ErrorMessage;
import com.hillrent.repository.ContactMessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactMessageService {

	@Autowired
	private ContactMessageRepository repository;

	public void createContactMessage(ContactMessage contactMessage) {
		repository.save(contactMessage);
	}

	public List<ContactMessage> getAll() {
		return repository.findAll();
	}

	public ContactMessage getContactMessage(Long id) throws ResourceNotFoundException {
		return repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
	}

	public void deleteContactMessage(Long id) throws ResourceNotFoundException {
		ContactMessage message = getContactMessage(id);
		// repository.delete(message);
		repository.deleteById(message.getId());

	}

	public void updateContactMessage(Long id, ContactMessage newContactMessage) {
		ContactMessage foundMessage = getContactMessage(id);

		foundMessage.setSubject(newContactMessage.getSubject());
		foundMessage.setName(newContactMessage.getName());
		foundMessage.setBody(newContactMessage.getBody());
		foundMessage.setEmail(newContactMessage.getEmail());

		repository.save(foundMessage);

	}

	public Page<ContactMessage> getAllWithPage(Pageable pageable) {
		return repository.findAll(pageable);
	}

}
