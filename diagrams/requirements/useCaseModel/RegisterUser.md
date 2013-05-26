######Name: Register user
######Initiator: User
######Goal: Register with the system to create an account
######Main success szenario:
1. Click register.
2. Enter your name and your email address.
3. Submit data.
4. System checks user credentials.
5. System registers the user.

######Extensions:
* 3. User did not fill all forms.
  * a2. System prompts to fill all forms.
* 4. System detects a user already existing with given details.
  * a. Fail.

######Variations
At every stage the user may abort.
