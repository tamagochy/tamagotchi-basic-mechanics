CREATE TABLE SCHEDULE_ITEMS (
    ID BIGSERIAL PRIMARY KEY,
    TIME TIME NOT NULL,
    DESCRIPTION varchar(50) NOT NULL,
    IS_BEGINNING_OF_DAY BOOLEAN NOT NULL,
    IS_DECREASE_HUNGER BOOLEAN NOT NULL,
    IS_DECREASE_REST BOOLEAN NOT NULL,
    IS_DECREASE_MOOD BOOLEAN NOT NULL
);
CREATE UNIQUE INDEX SCHEDULE_ITEMS_TIME_uindex ON SCHEDULE_ITEMS (TIME);

INSERT INTO SCHEDULE_ITEMS (
    TIME,
    DESCRIPTION,
    IS_BEGINNING_OF_DAY,
    IS_DECREASE_HUNGER,
    IS_DECREASE_REST,
    IS_DECREASE_MOOD
) VALUES
    ('06:00:00', 'начало дня, персонаж просыпается', TRUE, FALSE, FALSE, FALSE),
    ('09:00:00', 'первый приём пищи (завтрак)', FALSE, TRUE, FALSE, FALSE),
    ('10:00:00', 'первое игровое занятие', FALSE, FALSE, FALSE, TRUE),
    ('13:00:00', 'второй приём пищи (обед)', FALSE, TRUE, FALSE, FALSE),
    ('17:00:00', 'третий приём пищи (полдник)', FALSE, TRUE, FALSE, FALSE),
    ('18:00:00', 'второе игровое занятие', FALSE, FALSE, FALSE, TRUE),
    ('20:00:00', 'четвёртый приём пищи (ужин)', FALSE, TRUE, FALSE, FALSE),
    ('21:00:00', 'конец дня, персонаж отправляется на отдых', FALSE, FALSE, TRUE, FALSE);
