package libdirector.service;

import org.springframework.stereotype.Service;

import libdirector.domain.Publisher;
import libdirector.dto.PublisherDTO;
import libdirector.repository.PublisherRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublisherService {

    PublisherRepository publisherRepository;

    public Publisher savePublisher(PublisherDTO publisherDTO){
        Publisher publisher=new Publisher();
        publisher.setName(publisherDTO.getName());
        publisher.setBuiltIn(publisherDTO.getBuiltIn());
        publisherRepository.save(publisher);
        return publisher;
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }
}
