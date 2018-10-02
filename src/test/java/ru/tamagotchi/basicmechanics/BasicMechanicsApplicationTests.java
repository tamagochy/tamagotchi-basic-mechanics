package ru.tamagotchi.basicmechanics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.tamagotchi.basicmechanics.dao.PetDao;
import ru.tamagotchi.basicmechanics.domain.Pet;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.ACTIVE;
import static ru.tamagotchi.basicmechanics.domain.PetStatus.LEAVE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicMechanicsApplicationTests {

    @Autowired
    private PetDao petDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSearch() {
        int ownerId = 42;
        Pet pet1 = new Pet(ownerId, 100, 100, 100, 100, LocalDateTime.now(), LEAVE);
        Pet pet2 = new Pet(ownerId, 100, 100, 100, 100, LocalDateTime.now(), ACTIVE);
        petDao.save(pet1);
        petDao.save(pet2);

        List<Pet> pets = petDao.getAllByOwnerId(ownerId);
        assertEquals(2, pets.size());
    }
}
