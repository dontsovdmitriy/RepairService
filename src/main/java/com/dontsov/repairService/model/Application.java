package com.dontsov.repairService.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
	private int id;
	private LocalDate creationDate;
	private MalfunctionType malfunctionType;
	private String description;
	private User client;
	private User manager;
	private User master;
	private Long price;
	private String serviceComment;
	private ApplicationStatus status;
	private LocalDate completionDate;

	public enum ApplicationStatus {
		TODO, INPROGRESS, DONE, CLOSED, CANCELED;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}

	public static class Builder{
		private Application application;

		public Builder() {
			application = new Application();
		}

		public Builder setId(int id) {
			application.setId(id);
			return this;
		}

		public Builder setCreationDate(LocalDate creationDate) {
			application.setCreationDate(creationDate);
			return this;
		}

		public Builder setMalfunctionType(MalfunctionType malfunctionType) {
			application.setMalfunctionType(malfunctionType);
			return this;
		}

		public Builder setDescription(String description) {
			application.setDescription(description);
			return this;
		}

		public Builder setClient(User client) {
			application.setClient(client);
			return this;
		}

		public Builder setManager(User manager) {
			application.setManager(manager);
			return this;
		}

		public Builder setMaster(User master) {
			application.setMaster(master);
			return this;
		}

		public Builder setPrice(Long price) {
			application.setPrice(price);
			return this;
		}

		public Builder setStatus(ApplicationStatus status) {
			application.setStatus(status);
			return this;
		}

		public Builder setCompletionDate(LocalDate completionDate) {
			application.setCompletionDate(completionDate);
			return this;
		}

		public Builder setServiceComment(String serviceComment) {
			application.setServiceComment(serviceComment);
			return this;
		}

		public Application build() {
			return application;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public MalfunctionType getMalfunctionType() {
		return malfunctionType;
	}

	public void setMalfunctionType(MalfunctionType malfunctionType) {
		this.malfunctionType = malfunctionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public User getMaster() {
		return master;
	}

	public void setMaster(User master) {
		this.master = master;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getServiceComment() {
		return serviceComment;
	}

	public void setServiceComment(String serviceComment) {
		this.serviceComment = serviceComment;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", creationDate=" + creationDate + ", malfunctionType=" + malfunctionType
				+ ", description=" + description + ", client=" + client + ", manager=" + manager + ", master=" + master
				+ ", price=" + price + ", serviceComment=" + serviceComment + ", status=" + status + ", completionDate="
				+ completionDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((malfunctionType == null) ? 0 : malfunctionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (malfunctionType == null) {
			if (other.malfunctionType != null)
				return false;
		} else if (!malfunctionType.equals(other.malfunctionType))
			return false;
		return true;
	}



	//TODO overload equals & hashcode
}
