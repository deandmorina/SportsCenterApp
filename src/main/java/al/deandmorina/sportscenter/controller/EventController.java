package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.EventResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.EventSaveDTO;
import al.deandmorina.sportscenter.service.EventService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public EventResponseDTO createEvent(@Valid @RequestBody EventSaveDTO eventSaveDTO) {
        return this.eventService.createEvent(eventSaveDTO);
    }

}
