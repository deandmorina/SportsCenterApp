package al.deandmorina.sportscenter.payload.responseDTO;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponseDTO<T> {
    private List<T> items;
    private int pageNumber;
    private int pageSize;
    private int totalItems;
    private int totalPages;
    private boolean last;
}
