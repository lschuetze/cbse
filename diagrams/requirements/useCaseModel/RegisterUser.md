######Name: Register user
######Initiator: User
######Goal: Register with the system to create an account
######Main success scenario:
1. User click register.
2. User enters his name, email address and password.
3. User submits data.
4. System checks user details.
5. System registers the user.

######Extensions:
* 3. User did not fill all forms.
  * a2. System prompts to fill all forms.
* 4. System detects a user already existing with given email.
  * a. Fail.

######Variations
At every stage the user may abort.
