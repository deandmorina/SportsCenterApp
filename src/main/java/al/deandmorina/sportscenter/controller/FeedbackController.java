package al.deandmorina.sportscenter.controller;

import al.deandmorina.sportscenter.payload.responseDTO.FeedbackResponseDTO;
import al.deandmorina.sportscenter.payload.saveDTO.FeedbackSaveDTO;
import al.deandmorina.sportscenter.service.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/leave")
    public FeedbackResponseDTO leaveFeedback(@Valid @RequestBody FeedbackSaveDTO feedbackSaveDTO, Principal principal) throws Exception {
        return this.feedbackService.leaveFeedback(feedbackSaveDTO, principal);
    }
}
