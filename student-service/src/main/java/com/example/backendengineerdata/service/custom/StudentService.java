package com.example.backendengineerdata.service.custom;

import com.example.backendengineerdata.dto.StudentDTO;
import com.example.backendengineerdata.dto.paginated.PaginatedStudentDTO;
import com.example.backendengineerdata.service.CrudService;

public interface StudentService extends CrudService<StudentDTO,String> {
    PaginatedStudentDTO getAllStudentAsPages(int page, int size);
}
