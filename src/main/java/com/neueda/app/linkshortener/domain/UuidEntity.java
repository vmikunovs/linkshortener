package com.neueda.app.linkshortener.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class UuidEntity implements Serializable {

	@Id
	@Column(unique = true, nullable = false, length = 32)
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public UuidEntity() {
	}
	public UuidEntity(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UuidEntity)) return false;
		UuidEntity that = (UuidEntity) o;
		return Objects.equals(uuid, that.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
	}
}
