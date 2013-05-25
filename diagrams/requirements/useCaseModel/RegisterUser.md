Name: Register user
Initiator: User
Goal: Register with the system to create an account
Main success szenario:
1. Click register.
2. Enter your name and your email address.
3. Submit data.
4. System checks for users already registered with the given details.
5. System registers the user.

Extensions:
3. User aborts.
  a. Stop
3. User did not fill all forms.
  a2. Prompt user to fill all forms.
  b2. Stop.
4. System detects a user already existing with given details.
  a. Fail.
