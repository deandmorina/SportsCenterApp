package al.deandmorina.sportscenter.service;

import al.deandmorina.sportscenter.entity.Hall;
import al.deandmorina.sportscenter.entity.User;
import al.deandmorina.sportscenter.payload.responseDTO.HallResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.HallSaveDTO;
import al.deandmorina.sportscenter.repository.HallRepository;
import al.deandmorina.sportscenter.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {
    private final HallRepository hallRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public HallService(HallRepository hallRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.hallRepository = hallRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public HallResponseDTO createHall(HallSaveDTO hallSaveDTO) throws Exception {
        Hall hall = modelMapper.map(hallSaveDTO, Hall.class);
        User manager = this.userRepository.findById(hallSaveDTO.getManagerId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (manager.getHall() != null) {
            throw new Exception("This manager already manages a hall!");
        }
        hall.setManager(manager);
//        hall.setHallType(hallSaveDTO.getHallType().getType());
        Hall savedHall = this.hallRepository.save(hall);
        return modelMapper.map(savedHall, HallResponseDTO.class);
    }

    public List<HallResponseDTO> getAllHalls() {
        List<Hall> halls = this.hallRepository.findAll();
        return halls.stream().map(hall -> modelMapper.map(hall, HallResponseDTO.class)).collect(Collectors.toList());
    }
}
