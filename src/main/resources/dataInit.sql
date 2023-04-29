insert into COUNTRY (ID, CONTINENT, NAME)
values
        (default, 'Europe', 'Austria'),
        (default, 'Asia', 'South Korea'),
        (default, 'Africa', 'Kenya');

insert into AUTHOR (ID, NAME, SURNAME, COUNTRY_ID)
values
    (default, 'Stefan', 'Todorovski', 1),
    (default, 'Ana', 'Todorovska', 1),
    (default, 'Petar', 'Todorovski', 2);

insert into BOOK (ID, AVAILABLE_COPIES, CATEGORY, NAME, AUTHOR_ID)
values
    (default, 51, 1, 'Novel 1 ', 1),
    (default, 52, 2, 'Novel 2 ', 2),
    (default, 53, 3, 'Novel 3 ', 3),
    (default, 54, 1, 'Novel 4 ', 1),
    (default, 55, 2, 'Novel 5 ', 2),
    (default, 56, 3, 'Novel 6 ', 3),
    (default, 57, 1, 'Novel 7 ', 1),
    (default, 58, 2, 'Novel 8 ', 2),
    (default, 59, 3, 'Novel 9 ', 3),
    (default, 50, 1, 'Novel 10', 1),
    (default, 51, 2, 'Novel 11', 2),
    (default, 52, 3, 'Novel 12', 3);