-- Library Catalogue (Books)
create table library_catalogue (
    id serial primary key,
    isbn varchar(20) not null unique,
    year date
);

-- Authors
create table author (
    id serial primary key,
    name varchar(100) not null
);

-- Many-to-Many: Books ↔ Authors
create table library_catalogue_author (
    library_catalogue_id int not null,
    author_id int not null,
    primary key(library_catalogue_id, author_id),
    foreign key (library_catalogue_id) references library_catalogue(id) on delete cascade,
    foreign key (author_id) references author(id) on delete cascade
);

-- Borrowers (Library Members)
create table borrowers (
    id serial primary key,
    name varchar(100) not null,
    email varchar(100) unique not null,
    membership_id varchar(50) unique not null
);

-- Many-to-Many: Books ↔ Borrowers (Issue records)
create table library_catalogue_borrowers (
    library_catalogue_id int not null,
    borrower_id int not null,
    issue_date date not null,
    return_date date,
    primary key(library_catalogue_id, borrower_id, issue_date),
    foreign key (library_catalogue_id) references library_catalogue(id) on delete cascade,
    foreign key (borrower_id) references borrowers(id) on delete cascade
);
