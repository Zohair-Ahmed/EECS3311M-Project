# EECS3311M-Project
Software Project for EECS3311

# ABOUT THIS PROJECT:

Same Page (SP) will be used to seek and maintain a community-based library of books that are personalized for each member.
Like a digital library, readers can search for, be recommended and learn more about their favorite books as well as view
the latest releases. Extra features that allow the reader's data to be stored will be available for those readers
registered as members with Same Page, writing reviews on books, joining the same book clubs as other interested readers and tracking
reading progress.  

For the foreseeable future, the system is envisioned as a desktop application, however, it will be extensible so that
future releases can have a web and mobile interface.

# HOW IT WAS BUILT:

The functionality of the application is programmed entirely on java, and the GUI was created by using Java Swing
components. In the future iterations, a database will be added to store the book information and user profiles,
allowing SamePage to login and register users. This application follows SOLID principles, and utilizes the MVP design
architecture.

###### Landing Page
<img width="1435" alt="image" src="https://user-images.githubusercontent.com/77363160/224361847-d49aa4ad-aeb9-4851-b52f-a8536003d8a0.png">

###### Login Page
<img width="1439" alt="image" src="https://user-images.githubusercontent.com/77363160/224360864-f637039e-55e4-4d0b-96a6-a5022c541bb3.png">

###### Register Page
<img width="1435" alt="image" src="https://user-images.githubusercontent.com/77363160/224360934-b7f00a54-21a6-4d08-903e-82893ce8a391.png">

##### Book Information
<img width="1149" alt="image" src="https://user-images.githubusercontent.com/77363160/224361046-9bdd1b47-830f-43b4-9b82-5642502e0144.png">

##### Profile Page
<img width="1436" alt="image" src="https://user-images.githubusercontent.com/77363160/224361986-f7ee3f01-46d9-4bf9-8084-7afe7e84b78d.png">

# BUILD LOCALLY
To build this project locally, you will need access to the database credentials. Please create a `config.properties` file
under the `resources` directory. Please reach out to your teammates for the respective credentials.
```shell
db.driver=${db.driver}
db.url=${db.url}
db.username=${username}
db.password=${password}
```
