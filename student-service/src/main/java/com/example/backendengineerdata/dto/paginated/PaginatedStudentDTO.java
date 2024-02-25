package com.example.backendengineerdata.dto.paginated;

import com.example.backendengineerdata.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedStudentDTO {
    private List<StudentDTO> paginatedStudentDTOlist;
    private long dataCount;
}
