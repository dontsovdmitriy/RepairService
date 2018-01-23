package com.dontsov.repairService.model;

public class Review {
	private int Id;
	private String description;
	private User client;
	
	public static class Builder{
		private Review review;

		public Builder() {
			review = new Review();
		}

		public Builder setId(int id) {
			review.setId(id);
			return this;
		}

		public Builder setDescription(String description) {
			review.setDescription(description);
			return this;
		}

		public Builder setClient(User client) {
			review.setClient(client);
			return this;
		}

		public Review build() {
			return review;
		}
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	@Override
	public String toString() {
		return "Review [Id=" + Id + ", description=" + description + ", client=" + client + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Review other = (Review) obj;
		if (Id != other.Id)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	
	//TODO overload equals & hashcode

	
}
