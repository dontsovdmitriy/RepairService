package com.dontsov.repairService.model;

public class MalfunctionType {

	private int Id;
	private String type;
	private int repairDay;

	public static class Builder{
		private MalfunctionType malfunctionType;

		public Builder() {
			malfunctionType = new MalfunctionType();
		}

		public Builder setId(int id) {
			malfunctionType.setId(id);
			return this;
		}

		public Builder setType(String type) {
			malfunctionType.setType(type);
			return this;
		}

		public Builder setRepairDay(int repairDay) {
			malfunctionType.setRepairDay(repairDay);
			return this;
		}

		public MalfunctionType build() {
			return malfunctionType;
		}
	}

	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRepairDay() {
		return repairDay;
	}
	public void setRepairDay(int repairDay) {
		this.repairDay = repairDay;
	}
	
	@Override
	public String toString() {
		return "MalfunctionType [Id=" + Id + ", type=" + type + ", repairDay=" + repairDay + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MalfunctionType other = (MalfunctionType) obj;
		if (Id != other.Id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
