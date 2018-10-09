CREATE TABLE ACTIONS (
    ID BIGSERIAL PRIMARY KEY,
    CODE VARCHAR(20) NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL,
    VALUE INTEGER NOT NULL
);
CREATE UNIQUE INDEX NEW_TABLE_CODE_UINDEX ON ACTIONS (CODE);

INSERT INTO ACTIONS(CODE, DESCRIPTION, VALUE) VALUES
  ('brush', 'Почесать питомца', 25),
  ('takePicture', 'Сфотографировать питомца', 50),
  ('playBall', 'Поиграть в мяч', 75),
  ('playTicTacToe', 'Поиграть в крестики-нолики', 75);
