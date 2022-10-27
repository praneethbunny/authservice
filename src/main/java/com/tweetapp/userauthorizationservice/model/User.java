package com.tweetapp.userauthorizationservice.model;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.Data;


@DynamoDBTable(tableName="user")
@Data
public class User {
	
	@DynamoDBHashKey
	private String userName;
	
	@DynamoDBAttribute
	@NotEmpty(message = "Password cannot be null")
	private String password;
	
	@DynamoDBAttribute
	private boolean active;
	
	@DynamoDBAttribute
	private String role;
	
	@DynamoDBAttribute
	@NotEmpty(message = "First Name cannot be null")
	private String firstName;
	
	@DynamoDBAttribute
	@NotEmpty(message = "Last Name cannot be null")
	private String lastName;
	
	@DynamoDBAttribute
	@NotEmpty(message = "Email Id cannot be null")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$",message = "Email is not valid")
	private String emailId;
	
	@DynamoDBAttribute
	@NotEmpty(message = "Contact Number cannot be null")
	private String contactNo;
	

	
}
