--
-- Login Info
-- Username: testaccount
-- Password: testaccount
--


 Insert into status(description) values('Note Page'),('Archive'),('Trash');

 Insert into users(AccountID,Username,Password,Email,FirstName,LastName) VALUES(16,'testaccount','testaccount','testaccount@testaccount.com','test','test');

 Insert into category(CategoryID,AccountID,CategoryName) VALUES(10,16,'General Notes'),(11,16,'Header 1'),(12,16,'Header 2');

 Insert into notes(NoteID,NoteTitle,NoteContent,Color,AccountID,CategoryID,StatusID) VALUES(10,'Test Title 1','Test Content 1','#ffffff',16,10,1),(11,'Test Title 2','Test Content 2','#ffffff',16,10,1),
 (12,'Test Title 3','Test Content 3','#ffffff',16,10,1),(13,'Test Title 4','Test Content 4','#ffffff',16,10,1),(14,'Test Title 5','Test Content 5','#ffffff',16,10,1),
 (15,'Test Title 6','Test Content 6','#ffffff',16,10,1),(16,'Test Title 7','Test Content 7','#ffffff',16,10,1),(17,'Test Title 8','Test Content 8','#ffffff',16,10,1),
 (18,'Test Title 9','Test Content 9','#ffffff',16,11,1),(19,'Test Title 10','Test Content 10','#ffffff',16,11,1),(20,'Test Title 11','Test Content 11','#ffffff',16,11,1),
 (21,'Test Title 12','Test Content 12','#ffffff',16,12,1),(22,'Test Title 13','Test Content 13','#ffffff',16,12,1);

