package com.example.backendengineerdata.api;

import com.example.backendengineerdata.dto.StudentDTO;
import com.example.backendengineerdata.dto.paginated.PaginatedStudentDTO;
import com.example.backendengineerdata.service.custom.StudentService;
import com.example.backendengineerdata.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/students")
@CrossOrigin
public class StudentHttpController {
    private final StudentService studentService;

    public StudentHttpController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<StandardResponse> saveStudent(@RequestBody StudentDTO studentDTO) throws Exception {
        System.out.println("======================+++++++++++++++++===================");
        System.out.println(studentDTO);
        String message = studentService.save(studentDTO);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "saved successful", message), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAll() throws Exception {
        List<StudentDTO> all = studentService.findAll();
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "request success", all), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getStudentById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(new StandardResponse(200, "found the student", studentService.findById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandardResponse> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) throws Exception {
        studentService.update(studentDTO);
        return new ResponseEntity<>(new StandardResponse(200, "update successful", studentDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StandardResponse> deleteById(@PathVariable String id) throws Exception {

        try {
            boolean deleted = studentService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<>(new StandardResponse(200, "student data deleted successfully" + id, id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StandardResponse(404, "student not found" + id, id), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new StandardResponse(500, "error deleting student" + id, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(
            path = "/get-all-student-page-by-page",
            params = {"page", "size"}
    )
    public ResponseEntity getAllStudentAsPages(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(30) int size
            ) {
        PaginatedStudentDTO paginatedStudentDTO = studentService.getAllStudentAsPages(page,size);
        return new ResponseEntity(new StandardResponse(200,"success",paginatedStudentDTO),
                HttpStatus.OK);
    }
}
