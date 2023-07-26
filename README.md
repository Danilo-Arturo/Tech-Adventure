# Alumni Project - Choose Your Own Tech Adventure

Every tech adventure is different. Your mission is to build **TECH ADVENTURE**, a program which allows people to
choose different tech activities (or tech distractions) which help (or hurt) them on their journey. Perhaps a social event conflicts with your Pathway check-in. If you go to the social event, you might miss an important job opening from Pathway, but you might also meet a contact who helps you land the perfect position with Google. You choose the adventure, but you don't always get the results you expect. A key feature of **TECH ADVENTURE** is that it combines
elements of choice with elements of chance. You never know quite what to expect, but good choices are still usually your best bet. 

## Use cases

### Required use cases

You should attempt to complete all of the following required use cases.

1. **[COMPLETE]** As a user of the system, I need to be able to register myself with a username and password.
   1. A newly registered user starts with a Tech Elevator certificate, a Pathway director, and 1000 social points to spend.
   2. The ability to register has been provided in your starter code.
2. **[COMPLETE]** As a user of the system, I need to be able to log in using my registered username and password.
   1. Logging in returns an Authentication Token. I need to include this token with all my subsequent interactions with the system outside of registering and logging in.
   2. The ability to log in has been provided in your starter code.
3. As an authenticated user of the system, I need to be able to see my Social Points Balance.
4. As an authenticated user of the system, I need to be able to accept or reject interviews and social events.
   1. I should be able to choose from a list of employers to apply to.
   2. I cannot accept an interview to which I have not been invited, but I can host a social event. If I host a social event, I can invite others by name, and each one who accepts earns social points.
   3. Every social event has a cost, but also might have a surprise benefit.
5. As an authenticated user of the system, I need to be able to work on a side project.
   1. Work on a side project earns social points.
   2. Posting about a side project costs social points, but may earn an unexpected interview.
6. As an authenticated user of the system, I should be able to see my social point balance, track my interviews, and respond to social invitations.

### Optional use cases

These are up to you. If you think it would be fun to have other distractions or positive/negative activities for your
tech adventure, go ahead and add them. Just make sure there is both the element of choice and the element of chance.

## Technology to use

As part of this project, you **should** use the core technology you used through Module Two. This includes Java/C#,  IntelliJ/Visual Studio, PostgreSQL/SQL Server, and client/server communication. You **should** be able to use Postman to test your server. You **may** choose to use a web front end with Module Three technologies.

I would suggest starting with a console application like your Module Two Capstone. If it is going well, you should be able to add in a front end with some pizazz, but the higher priority is working with Java/C#, databases, and client/server coding. Do bear in mind that console interaction should be isolated to one class so it is easier to switch out the input/output.

If you get stuck, remember that your Module Two Capstone had a lot of similarities, so is not a bad place to go back to for inspiration. Also, don't hesitate to ask me (Ben Langhinrichs) questions. I will also be happy to admire your project when you get something working, even partially. It is always better to get something functional and then keep adding to it, as otherwise you can grow frustrated and have nothing to show for your work. 

## Database design

You are in charge of designing other database tables, but since the authentication is built for you, the following two tables are assumed.

### `techadventure_user` table

Stores the login information for users of the system.

| Field           | Description                                                                    |
| --------------- | ------------------------------------------------------------------------------ |
| `user_id`       | Unique identifier of the user                                                  |
| `username`      | String that identifies the name of the user; used as part of the login process |
| `password_hash` | Hashed version of the user's password                                          |


## How to set up the database

Create a new Postgres database called `techadventure`. Run the `database/techadventure.sql` script in pgAdmin/SQL Server to set up the database.

## Authentication

The user registration and authentication functionality for the system has already been implemented. If you review the login code, you'll notice that after successful authentication, an instance of `AuthenticatedUser` is stored in the `currentUser` member variable of `App`. The user's authorization token—meaning JWT—can be accessed from `App` as `currentUser.getToken()`.

When the use cases refer to an "authenticated user", this means a request that includes the token as a header. You can also reference other information about the current user by using the `User` object retrieved from `currentUser.getUser()`.