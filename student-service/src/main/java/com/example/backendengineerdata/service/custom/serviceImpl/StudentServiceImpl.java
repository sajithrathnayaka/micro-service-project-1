package com.example.backendengineerdata.service.custom.serviceImpl;

import com.example.backendengineerdata.dto.StudentDTO;
import com.example.backendengineerdata.dto.paginated.PaginatedStudentDTO;
import com.example.backendengineerdata.entity.Student;
import com.example.backendengineerdata.exception.NotFoundException;
import com.example.backendengineerdata.repository.StudentRepo;
import com.example.backendengineerdata.service.custom.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final ModelMapper mapper;

    public StudentServiceImpl(StudentRepo studentRepo, ModelMapper mapper) {
        this.studentRepo = studentRepo;
        this.mapper = mapper;
    }

    @Override
    public long count() throws Exception {
        return studentRepo.count();
    }

    @Override
    public String save(StudentDTO entity) throws Exception {
        Student student = mapper.map(entity, Student.class);
        studentRepo.save(student);
        return "saved successfully";
    }

    @Override
    public void update(StudentDTO studentDTO) throws Exception {
        studentRepo.save(mapper.map(studentDTO, Student.class));
    }

    @Override
    public boolean deleteById(String pk) throws Exception {
        try {
            studentRepo.deleteById(pk);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<StudentDTO> findById(String pk) throws Exception {

        Optional<Student> student = studentRepo.findById(pk);
        System.out.println("+++++++++++========================++++++++++++");
        System.out.println(student);
        if (Optional.empty().isPresent()) {
            return Optional.of(mapper.map(student, StudentDTO.class));
        } else {
            throw new NotFoundException("no student fount for this ");
        }
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
        List<Student> all = studentRepo.findAll();
        System.out.println(all);
        return all.stream().map(student -> mapper.map(student, StudentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public boolean existsById(String pk) throws Exception {
        return false;
    }

    @Override
    public PaginatedStudentDTO getAllStudentAsPages(int page, int size) {
        Page<Student> students = studentRepo.findAll(PageRequest.of(page, size));
        List<StudentDTO> studentDTOS = students.getContent().stream().map(student -> mapper.map(student, StudentDTO.class)).collect(Collectors.toList());
        PaginatedStudentDTO paginatedStudentDTO = new PaginatedStudentDTO(studentDTOS, studentRepo.count());
        return paginatedStudentDTO;
    }
}
