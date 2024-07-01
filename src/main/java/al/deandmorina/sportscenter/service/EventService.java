package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Event;
import al.deandmorina.sportscenter.entity.Hall;
import al.deandmorina.sportscenter.payload.responseDTO.EventResponseDTO;
import al.deandmorina.sportscenter.payload.responseDTO.PaginatedResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.EventSaveDTO;
import al.deandmorina.sportscenter.repository.EventRepository;
import al.deandmorina.sportscenter.repository.HallRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;
    private final HallRepository hallRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ModelMapper modelMapper, HallRepository hallRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.hallRepository = hallRepository;
    }

    public EventResponseDTO createEvent(EventSaveDTO eventSaveDTO) {
        Event event = this.modelMapper.map(eventSaveDTO, Event.class);
        Hall hall = this.hallRepository.findById(eventSaveDTO.getHallId()).orElseThrow(() -> new RuntimeException("Hall not found"));
        event.setHall(hall);
        Event savedEvent = this.eventRepository.save(event);
        return this.modelMapper.map(savedEvent, EventResponseDTO.class);
    }

    public PaginatedResponseDTO<EventResponseDTO> getEvents(int pageNumber, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Event> eventPage = this.eventRepository.findAll(pageable);
        List<Event> events = eventPage.getContent();
        List<EventResponseDTO> content = events.stream().map(event -> modelMapper.map(event, EventResponseDTO.class)).collect(Collectors.toList());
        PaginatedResponseDTO<EventResponseDTO> response = new PaginatedResponseDTO<>();
        response.setItems(content);
        response.setPageNumber(eventPage.getNumber());
        response.setPageSize(eventPage.getSize());
        response.setTotalItems(eventPage.getNumberOfElements());
        response.setTotalPages(eventPage.getTotalPages());
        response.setLast(eventPage.isLast());
        return response;
    }
}
