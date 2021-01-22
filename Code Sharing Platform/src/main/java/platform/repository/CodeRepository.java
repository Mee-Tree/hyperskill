package platform.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import platform.domain.Code;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("SqlResolve")
public interface CodeRepository extends CrudRepository<Code, UUID> {
    @Query(value = "SELECT * FROM code WHERE time = 0 AND views = 0 ORDER BY date DESC LIMIT ?1", nativeQuery = true)
    List<Code> findLatestNonHidden(long limit);

    default void update(Code code) {
        if (code.view()) {
            delete(code);
        } else {
            save(code);
        }
    }
}
