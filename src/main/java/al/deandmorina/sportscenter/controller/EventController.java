package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.EventResponseDTO;
import al.deandmorina.sportscenter.payload.responseDTO.PaginatedResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.EventSaveDTO;
import al.deandmorina.sportscenter.service.EventService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public PaginatedResponseDTO<EventResponseDTO> getEvents(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                            @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                            @RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
        return this.eventService.getEvents(pageNumber,pageSize,sortBy,sortOrder);
    }
}
