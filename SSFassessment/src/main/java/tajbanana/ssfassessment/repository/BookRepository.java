package tajbanana.ssfassessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static tajbanana.ssfassessment.Constants.*;

@Repository
public class BookRepository {

    @Autowired
    @Qualifier(BEAN_BOOK_CACHE)

    private RedisTemplate<String, String> template;

    public void save(String work_ID, String value) {
        template.opsForValue().set(normalize(work_ID), value, 10L, TimeUnit.MINUTES);
    }

    public Optional<String> get(String cityName) {
        String value = template.opsForValue().get(normalize(cityName));
        return Optional.ofNullable(value);
    }

    private String normalize(String k) {
        return k.trim().toLowerCase();
    }
}