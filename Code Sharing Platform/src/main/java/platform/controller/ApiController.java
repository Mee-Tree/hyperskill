package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import platform.domain.Code;
import platform.exception.CodeNotFoundException;
import platform.exception.ValidationException;
import platform.service.CodeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/code")
public class ApiController {
    private final CodeService codeService;

    @Autowired
    public ApiController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/{id}")
    public Code code(@PathVariable UUID id) {
        return codeService.findHiddenById(id).orElseThrow(CodeNotFoundException::new);
    }

    @GetMapping("/latest")
    public List<Code> latest() {
        return codeService.getLatest();
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object newCode(@Valid @RequestBody Code code, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return Map.of("id", codeService.save(code).toString());
    }
}
