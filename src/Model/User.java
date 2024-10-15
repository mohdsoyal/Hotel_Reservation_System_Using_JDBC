package Model;

import java.time.LocalDateTime;

public class User {
	
	    private int userId;
	    private String username;
	    private String password;
	    private String email;
	    private LocalDateTime created_at;
	    
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
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
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCreated_at() {
			return created_at;
		}
		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}
		public User(int userId, String username, String password, String email, String created_at) {
			super();
			this.userId = userId;
			this.username = username;
			this.password = password;
			this.email = email;
			this.created_at = created_at;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", created_at=" + created_at + "]";
		}
	    
		
	    

}
