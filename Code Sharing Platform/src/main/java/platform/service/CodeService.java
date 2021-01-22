package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.domain.Code;
import platform.repository.CodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodeService {
    private final static long LATEST_COUNT = 10;

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public UUID save(Code code) {
        return codeRepository.save(code).getId();
    }

    public Optional<Code> findById(UUID id) {
        return id == null ? Optional.empty() : codeRepository.findById(id);
    }

    public List<Code> getLatest() {
        return codeRepository.findLatestNonHidden(LATEST_COUNT);
    }

    public Optional<Code> findHiddenById(UUID id) {
        findById(id)
                .filter(Code::isRestricted)
                .ifPresent(codeRepository::update);
        return findById(id);
    }
}
