import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by RDEAX37 on 2/10/2015.
 */
public class PersistenceTest extends DBTestCase {

    private EntityManagerFactory emf;
    private EntityTransaction t;

    public PersistenceTest()
    {
        getEmf();
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/webbroker" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root" );
        // System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "" );
    }

    @Before
    public void setup() throws Exception {
        t = entityManager().getTransaction();
        t.begin();
    }

    @After
    public void teardown() throws Exception {
        t.commit();
        entityManager().close();
        emf.close();
    }

    protected EntityManager entityManager() {
        return getEmf().createEntityManager();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getResource("/dataset.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.DELETE;
    }

    public EntityManagerFactory getEmf(){
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("MyTestPersistenceUnit");
        }
        return emf;
    }
}
