package com.example.backendengineerdata.api;

import com.example.backendengineerdata.dto.CompanyDTO;
import com.example.backendengineerdata.service.custom.CompanyService;
import com.example.backendengineerdata.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/companies")
public class CompanyHttpController {

    private final CompanyService companyService;

    public CompanyHttpController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<StandardResponse> saveCompany(@RequestBody CompanyDTO companyDTO) throws Exception {
        String message = companyService.save(companyDTO);
        return new ResponseEntity<>(new StandardResponse(201, message, message), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllCompanies() throws Exception {
        List<CompanyDTO> all = companyService.findAll();
        return new ResponseEntity<>(new StandardResponse(200, "request success", all), HttpStatus.OK);
    }
}
