package ssii.dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PersonneRepositoryTest {
    @Autowired
    private PersonneRepository clientDao;

    @Test
    void daoExiste() {
        assertNotNull(clientDao);
    }
}
