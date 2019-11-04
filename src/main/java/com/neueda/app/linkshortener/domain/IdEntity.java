package com.neueda.app.linkshortener.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class IdEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id = -1;

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (id != -1 && obj != null && getClass().equals(obj.getClass())) {
			// Zero id - is new object
			return Objects.equals(id, ((IdEntity) obj).id);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// http://stackoverflow.com/questions/4045063/how-should-i-map-long-to-int-in-hashcode
		return (id == -1) ? System.identityHashCode(this) : (int) (id ^ (id >>> 32));
	}
}
