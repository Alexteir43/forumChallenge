ALTER TABLE topics ADD active TINYINT;
UPDATE topics SET active = 1