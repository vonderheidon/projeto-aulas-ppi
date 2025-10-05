package br.com.catolicapb.controller;

import br.com.catolicapb.client.dto.ProductDTO;
import br.com.catolicapb.dto.CustomerDTO;
import br.com.catolicapb.dto.ResponseDTO;
import br.com.catolicapb.messenger.dto.ScheduleRequestDTO;
import br.com.catolicapb.service.CustomerService;
import br.com.catolicapb.service.ProductClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.catolicapb.constants.CustomerConstants.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ProductClientService productClientService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.builder()
                        .statusCode(CODE_STATUS_201)
                        .message(CUSTOMER_MESSAGE_CREATED_201)
                        .build());
    }

    @GetMapping("/findAll")
    public Page<CustomerDTO> findAll(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @PutMapping("/update/{cpf}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String cpf, @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.update(cpf, customerDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .statusCode(CODE_STATUS_200)
                        .message(CUSTOMER_MESSAGE_UPDATED_200)
                        .build());
    }

    @PatchMapping("/toggle-status/{cpf}")
    public ResponseEntity<ResponseDTO> toggleActiveStatus(@PathVariable String cpf) {
        String status = customerService.toggleActiveStatus(cpf);
        String message = status.equals("ativado") ? CUSTOMER_MESSAGE_ACTIVATED_200 : CUSTOMER_MESSAGE_DEACTIVATED_200;

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .statusCode(CODE_STATUS_200)
                        .message(message)
                        .build());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productClientService.findProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/create-async")
    public ResponseEntity<ResponseDTO> saveAsync(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
        customerService.saveScheduleAsync(scheduleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDTO.builder()
                        .statusCode(CODE_STATUS_201)
                        .message(CUSTOMER_MESSAGE_CREATED_201)
                        .build());
    }
}
