package org.realdolmen.webbroker.model;

import org.junit.Before;
import org.junit.Test;
import org.realdolmen.webbroker.DataSetPersistenceTest;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Persistence test for the {@link Region} entity.
 *
 * @author Youri Flement
 */
public class RegionPersistenceTest extends DataSetPersistenceTest {

    private Region region;

    @Before
    public void setup() {
        region = new Region("name", "code");
    }

    @Test
    public void regionCanBePersisted() throws Exception {
        entityManager().persist(region);
        assertNotNull(region.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void regionMustHaveName() throws Exception {
        region.setName(null);
        entityManager().persist(region);
    }

    @Test(expected = ConstraintViolationException.class)
    public void regionMustHaveCode() throws Exception {
        region.setCode(null);
        entityManager().persist(region);
    }

    @Test
    public void canFindAllRegions() throws Exception {
        List<Region> result = entityManager().createQuery("select r from Region r", Region.class).getResultList();
        assertEquals(3, result.size());
    }

    @Test
    public void canFindRegionById() throws Exception {
        entityManager().persist(region);

        Region fromDatabase = entityManager().find(Region.class, region.getId());

        assertEquals(region, fromDatabase);
    }

}
