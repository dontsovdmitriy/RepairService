package com.dontsov.repairService.model;


public class User {
	
	private int id;
	private String surname;
	private String name;
	private String secondName;
	private String email;
	private String phone;
	private String username;
	private String password;
	private Role role;
	
	public enum Role{
		 USER, MANAGER, MASTER, ADMIN;

		@Override
		public String toString() {
			return name().toLowerCase();
		}
	}
	
	public static class Builder{
		private User user;

		public Builder() {
			user = new User();
		}

		public Builder setId(int id) {
			user.setId(id);
			return this;
		}

		public Builder setSurname(String surname) {
			user.setSurname(surname);
			return this;
		}

		public Builder setName(String name) {
			user.setName(name);
			return this;
		}

		public Builder setSecondName(String secondName) {
			user.setSecondName(secondName);
			return this;
		}

		public Builder setEmail(String email) {
			user.setEmail(email);
			return this;
		}
		
		public Builder setPhone(String phone) {
			user.setPhone(phone);
			return this;
		}

		public Builder setUsername(String username) {
			user.setUsername(username);
			return this;
		}
		
		public Builder setPassword(String password) {
			user.setPassword(password);
			return this;
		}
		
		public Builder setRole(Role role) {
			user.setRole(role);
			return this;
		}
				
		public User build() {
			return user;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", surname=" + surname + ", name=" + name + ", secondName=" + secondName + ", email="
				+ email + ", phone=" + phone + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	

	//TODO overload equals & hashcode


}
