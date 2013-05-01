package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.DogDAO;
import com.model.Dog;

/**
 * @author Lars Schuetze
 */

@Stateless
public class DogFacadeImpl implements DogFacade {
	
	@EJB
	private DogDAO dogDAO;

	/* (non-Javadoc)
	 * @see com.facade.DogFacade#save(com.model.Dog)
	 */
	@Override
	public void save(Dog dog) {
		isDogWithAllData(dog);
		dogDAO.save(dog);
	}

	/* (non-Javadoc)
	 * @see com.facade.DogFacade#update(com.model.Dog)
	 */
	@Override
	public Dog update(Dog dog) {
		isDogWithAllData(dog);
		return dogDAO.update(dog);
	}

	/* (non-Javadoc)
	 * @see com.facade.DogFacade#delete(com.model.Dog)
	 */
	@Override
	public void delete(Dog dog) {
		dogDAO.delete(dog);
	}

	/* (non-Javadoc)
	 * @see com.facade.DogFacade#find(int)
	 */
	@Override
	public Dog find(int entityId) {
		return dogDAO.find(entityId);
	}

	/* (non-Javadoc)
	 * @see com.facade.DogFacade#findAll()
	 */
	@Override
	public List<Dog> findAll() {
		return dogDAO.findAll();
	}

	private void isDogWithAllData(Dog dog) throws IllegalArgumentException {
		boolean hasError = false;
		
		hasError |= dog == null;
		hasError |= dog.getName() == null || "".equals(dog.getName().trim());
		hasError |= dog.getWeight() <= 0;
		
		if(hasError) {
			throw new IllegalArgumentException("The dog is missing data.");
		}
	}
}
