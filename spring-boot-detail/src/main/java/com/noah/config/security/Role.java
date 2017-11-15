package com.noah.config.security;

public class Role {

	// Spring has a rule, when it looks for a role, its role name begin with "ROLE_" by default
	public static final String PREFIX = "ROLE_";

	public static final String READ_ONLY = "READ_ONLY";
	public static final String READ_WRITE = "READ_WRITE";
	public static final String ADMIN = "ADMIN";

}
