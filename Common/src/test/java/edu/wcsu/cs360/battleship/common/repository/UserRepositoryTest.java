package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.service.PropertyFileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by William Callahan on 11/7/2016.
 */
public class UserRepositoryTest {

	private Log log = LogFactory.getLog(this.getClass());
	private IUserRepository iUserRepository;
	private EntityManagerFactory entityManagerFactory;

	@Before
	public void setUp() throws Exception {
		PropertyFileService propertyFileService = new PropertyFileService("database.properties");
		entityManagerFactory = Persistence.createEntityManagerFactory(propertyFileService.getProperty("jpa.persistence.unit.name").toString());
		iUserRepository = new UserRepository(entityManagerFactory.createEntityManager());
	}

	@After
	public void tearDown() throws Exception {
		entityManagerFactory.close();
	}

	@Test
	public void findOne() throws Exception {

	}

	@Test
	public void findAll() throws Exception {
		Iterable<User> userIterable = iUserRepository.findAll();
		Assert.assertNotNull(userIterable);
	}

	@Test
	public void count() throws Exception {

	}

	@Test
	public void save() throws Exception {
		User user = new User();
		user.setFirstName("William");
		user.setMiddleName("Gregory");
		user.setLastName("Callahan");
		user.setEmail("Test@test.com");

		User newUser = iUserRepository.save(user);

		user.setUserId(newUser.getUserId());

		Assert.assertTrue(user.equals(newUser));
	}

	@Test
	public void save1() throws Exception {

	}

	@Test
	public void delete() throws Exception {
		User user = new User();
		user.setFirstName("William");
		user.setMiddleName("Gregory");
		user.setLastName("Callahan");
		user.setEmail("Test@test.com");

		User newUser = iUserRepository.save(user);

		Assert.assertNotNull(iUserRepository.findOne(newUser.getUserId()));
		iUserRepository.delete(newUser.getUserId());
		Assert.assertNull(iUserRepository.findOne(newUser.getUserId()));
	}

	@Test
	public void delete1() throws Exception {

	}
	
}