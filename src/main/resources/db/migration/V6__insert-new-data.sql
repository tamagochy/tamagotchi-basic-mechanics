INSERT INTO INDICATORS(ID, CODE, ROOM_CODE, DESCRIPTION)
VALUES (1, 'health', 'hospital', 'Показатель здоровья питомца'),
       (2, 'hunger', 'kitchen', 'Показатель сытости питомца'),
       (3, 'rest', 'bedroom', 'Показатель отдыха питомца'),
       (4, 'mood', 'playRoom', 'Показатель настроения питомца');

-- Игры
INSERT INTO ACTIONS(CODE, DESCRIPTION, MAIN_INDICATOR, VALUE_1)
VALUES ('brush', 'Почесать питомца', 4, 25),
       ('takePicture', 'Сфотографировать питомца', 4, 50),
       ('playBall', 'Поиграть в мяч', 4, 75),
       ('playTicTacToe', 'Поиграть в крестики-нолики', 4, 75);

-- Болезни
INSERT INTO ACTIONS(CODE, DESCRIPTION, MAIN_INDICATOR, VALUE_1, DISEASE_CODE)
VALUES ('giveAntiviral', 'Дать противовирусное', 1, 100, 'cold'),
       ('pshikIngalipt', 'Побрызгать ингалиптом', 1, 100, 'angina'),
       ('stripCandy', 'Забрать вкусняшки', 1, 100, 'inflammationTricks'),
       ('giveAntihistamine', 'Дать антигистаминный препарат', 1, 100, 'allergy');

-- Продукты, полезные
INSERT INTO ACTIONS(CODE, DESCRIPTION, MAIN_INDICATOR, VALUE_1)
VALUES ('porridge', 'Каша', 2, 20),
       ('tea', 'Чай', 2, 3),
       ('water', 'Вода', 2, 3),
       ('soup', 'Суп', 2, 25),
       ('dumplings', 'Пельмени', 2, 50),
       ('pizza', 'Пицца', 2, 45),
       ('paste', 'Паста', 2, 30),
       ('oatCookies', 'Овсяное печенье', 2, 10),
       ('yogurt', 'Йогурт', 2, 15),
       ('apple', 'Яблоко', 2, 5);

-- Продукты, вредные
INSERT INTO ACTIONS(CODE, DESCRIPTION, MAIN_INDICATOR, ADDITIONAL_INDICATOR, VALUE_1, VALUE_2)
VALUES ('candies', 'Конфеты', 2, 1, 5, -3),
       ('chips', 'Чипсы', 2, 1, 10, -5),
       ('crackers', 'Сухарики', 2, 1, 7, -4),
       ('sandwiches', 'Бутерброды', 2, 1, 15, -5),
       ('gingerbread', 'Пряник', 2, 1, 5, -2),
       ('waffles', 'Вафли', 2, 1, 4, -2);
