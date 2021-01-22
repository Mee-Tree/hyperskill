package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.exception.CodeNotFoundException;
import platform.service.CodeService;

import java.util.UUID;

@Controller
@RequestMapping("/code")
public class WebController {
    private final CodeService codeService;

    @Autowired
    public WebController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/{id}")
    public String code(@PathVariable UUID id, Model model) {
        model.addAttribute("code", codeService.findHiddenById(id)
                .orElseThrow(CodeNotFoundException::new));
        return "CodePage";
    }

    @GetMapping("/latest")
    public String latest(Model model) {
        model.addAttribute("latest", codeService.getLatest());
        return "LatestPage";
    }

    @GetMapping("/new")
    public String newCode() {
        return "NewCodePage";
    }
}
