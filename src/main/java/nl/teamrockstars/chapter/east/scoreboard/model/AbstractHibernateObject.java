package nl.teamrockstars.chapter.east.scoreboard.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class AbstractHibernateObject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime lastModifiedAt;

    public Long getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHibernateObject that = (AbstractHibernateObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    public boolean isNew() {
  		return id == null;
  	}


		public LocalDateTime getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}


		public LocalDateTime getLastModifiedAt() {
			return lastModifiedAt;
		}


		public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
			this.lastModifiedAt = lastModifiedAt;
		}
    
		@PrePersist
    public void prePersist(){ 
			
			LocalDateTime now = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
			if(isNew()) {
				createdAt = now;
			}
			lastModifiedAt = now;
    	
    }
}
