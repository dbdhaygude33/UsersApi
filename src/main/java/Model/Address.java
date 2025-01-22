package Model;

import lombok.Data;

@Data
public class Address {

	private String street;
	private String area;
	private String city;
	private String state;
	private String country;
	private Integer pincode;
}
