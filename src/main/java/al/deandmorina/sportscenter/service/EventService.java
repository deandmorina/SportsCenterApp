package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Event;
import al.deandmorina.sportscenter.entity.Hall;
import al.deandmorina.sportscenter.payload.responseDTO.EventResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.EventSaveDTO;
import al.deandmorina.sportscenter.repository.EventRepository;
import al.deandmorina.sportscenter.repository.HallRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }
}
