drop table Rate;
drop table Book;
drop table Recipe;
drop table Client;
drop table Title;


create table Title
(
    idTitle smallint not null primary key,
    titleName varchar(50) not null
);

create table Client /*porque no se puede USER*/
(
    password varchar (50) not null,
    id varchar(50) not null primary key,
    cname varchar(50),
    surname varchar(100),
    email varchar(100),
    exp int,
    clevel smallint,
    biography varchar(400),
    profilePic blob,
    FOREIGN KEY(clevel) REFERENCES Title(idTitle)
);


create table Recipe
(
    id integer not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    rname varchar(150) not null, 
    preparationTime int not null,
    steps varchar(400) not null,
    ingredients varchar(400) not null,
    views int not null,
    score int not null,
    visibility boolean not null,
    creator varchar(50) not null,
    tags varchar(30) not null,
    multimediaFiles blob,
    FOREIGN KEY(creator) REFERENCES Client(id)
);


create table Book 
(
    client varchar(50) not null,
    recipe int not null,
    FOREIGN KEY(client) REFERENCES Client(id),
    FOREIGN KEY(recipe) REFERENCES Recipe(id),
    PRIMARY KEY (client,recipe)
);
    
create table Rate
(
    score int not null,
    rater varchar(50) not null,
    recipe int not null,
    FOREIGN KEY(rater) REFERENCES Client(id),
    FOREIGN KEY(recipe) REFERENCES Recipe(id),
    PRIMARY KEY (rater,recipe)
);


