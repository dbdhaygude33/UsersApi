package Model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Personal {

	private String firstName;
	private String lastName;
	private ArrayList<String> topics;
	private ArrayList<Integer> fees;
	private Address address;
}
