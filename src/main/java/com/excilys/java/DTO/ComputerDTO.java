package com.excilys.java.DTO;

public class ComputerDTO {
	
	private String id;
	private String name;
	private String introduced; 
	private String discontinued;
	private CompanyDTO company;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
		sb.append(" id :").append(this.id);
		sb.append(", name :").append(this.name);
		sb.append(", introduced :").append(this.introduced);
		sb.append(", discontinued :").append(this.discontinued).append(" ");
		sb.append(this.company);
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ComputerDTO other = (ComputerDTO) obj;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public static class Builder {
		private String id;
		private String name;
		private String introduced; 
		private String discontinued;
		private CompanyDTO company;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        
        public Builder setIntroduced(String introduced) {
            this.introduced = introduced;
            return this;
        }
        
        public Builder setDiscontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }
        
        public Builder setCompany(CompanyDTO company) {
            this.company = company;
            return this;
        }

        public ComputerDTO build() {
            ComputerDTO computerDTO = new ComputerDTO();
            computerDTO.id = this.id;
            computerDTO.name = this.name;
            computerDTO.introduced = this.introduced;
            computerDTO.discontinued = this.discontinued;
            computerDTO.company = this.company;
            return computerDTO;
        }
    }
	
}
