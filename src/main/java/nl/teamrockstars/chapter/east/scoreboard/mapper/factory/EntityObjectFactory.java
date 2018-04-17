package nl.teamrockstars.chapter.east.scoreboard.mapper.factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import nl.teamrockstars.chapter.east.scoreboard.dto.DtoObject;
import nl.teamrockstars.chapter.east.scoreboard.model.AbstractHibernateObject;

@Component
public class EntityObjectFactory {

	@PersistenceContext
	private EntityManager em;

	@ObjectFactory
	@Transactional
	public <T extends AbstractHibernateObject, S extends DtoObject> T createEntity(@TargetType Class<T> entityClass, S dto) {

		Long id = dto.getId();
		if (id != null) {
			T entity = em.find(entityClass, id);
			if (entity != null) {
				return entity;
			}
		}

		try {
			return entityClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
