package life;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * Created by alexb on 12/18/2017.
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("GOD");
    }

}