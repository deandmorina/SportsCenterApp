package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.HallResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.HallSaveDTO;
import al.deandmorina.sportscenter.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {
    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping("/create")
    public HallResponseDTO createHall(@Valid @RequestBody HallSaveDTO hallSaveDTO) throws Exception {
        return this.hallService.createHall(hallSaveDTO);
    }

    @GetMapping("/all")
    public List<HallResponseDTO> getAllHalls() {
        return this.hallService.getAllHalls();
    }
}
