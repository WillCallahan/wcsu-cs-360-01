package edu.wcsu.cs360.battleship.common.repository;

import edu.wcsu.cs360.battleship.common.domain.entity.User;
import edu.wcsu.cs360.battleship.common.service.io.PropertyFileService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by William Callahan on 11/7/2016.
 */
public class UserRepositoryTest {

	private Log log = LogFactory.getLog(this.getClass());
	private IUserRepository iUserRepository;
	private EntityManagerFactory entityManagerFactory;

	private User user;

	{
		user = new User();
		user.setPassword("password");
		user.setUsername("willCallahan1994");
		user.setFirstName("William");
		user.setMiddleName("Gregory");
		user.setLastName("Callahan");
		user.setEmail("Test@test.com");
	}

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
		user = iUserRepository.save(user);
		User newUser = iUserRepository.findOne(user.getUserId());
		Assert.assertNotNull(newUser);
		iUserRepository.delete(user);
	}

	@Test
	public void findAll() throws Exception {
		user = iUserRepository.save(user);
		Iterable<User> userIterable = iUserRepository.findAll();
		Assert.assertNotNull(userIterable);
		iUserRepository.delete(user);
	}

	@Test
	public void count() throws Exception {
		Long count = iUserRepository.count();
		Assert.assertTrue(count != 0L);
	}

	@Test
	public void save() throws Exception {
		User newUser = iUserRepository.save(user);
		user.setUserId(newUser.getUserId());
		Assert.assertTrue(user.equals(newUser));
		iUserRepository.delete(user);
	}

	@Test
	public void save1() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(user);
		Iterable<User> userIterable = iUserRepository.saveAll(userList);
		Assert.assertNotNull(userIterable);
		iUserRepository.delete(user);
	}

	@Test
	public void delete() throws Exception {
		User newUser = iUserRepository.save(user);
		Assert.assertNotNull(iUserRepository.findOne(newUser.getUserId()));
		iUserRepository.deleteById(newUser.getUserId());
		Assert.assertNull(iUserRepository.findOne(newUser.getUserId()));
	}

	@Test
	public void delete1() throws Exception {
		User newUser = iUserRepository.save(user);
		Assert.assertNotNull(iUserRepository.findOne(newUser.getUserId()));
		iUserRepository.delete(newUser);
		Assert.assertNull(iUserRepository.findOne(newUser.getUserId()));
	}

}