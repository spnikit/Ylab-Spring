# Getting Started YLAB

## Задания предыдущих уроков

- [lesson 4 README](LESSON4_README.MD)

---
## Предыдущие версии текущего задания

- [версия 1.0](lesson5_1.0.md)
---

## Задание урока 5

Адрес корневого ресурса:

    localhost:8080/gameplay

### Регистрация игроков

Для начала игры необходимо зарегистрировать 2-х игроков. Регистрация 
производится по адресу:
    
    localhost:8080/gameplay/register/player

Путем отправки методом `POST` тела запроса в формате `JSON` следующей 
структуры:

```json
{
  "name": "petya",
  "symbol": "x"
}
```
Где поля `name` и  `symbol` типа `string`. Причем `symbol` принимает
значения `'x'` или `'o'`, регистр символов не важен. 

Игрок, выбравший `x`, ходит первым.

---
### Выполнение ходов в игре

Ходы производятся посредством отправки запроса методом `POST` по адресу:

    localhost:8080/gameplay/move

В теле запроса ожидается JSON следующего вида:

```json
    {
        "cellNumber": 1,
        "playerId": 1
    }
```

Ключ `"cellNumber"` принимает значения типа `number` в диапазоне
от 1 до 9 включительно, согласно цифровому обозначению полей игрового
поля (numpad)

![tic-tac-toe board](img_1.png)

Ключ `playerId` принимает значения типа `number` `1` или
`2` соответственно по номерам игроков. Игру всегда начинает игрок `1`.
Игроки ходят по очереди.

Входящие значения ключей валидируются, в случае несоответствия указанным
ограничениям приходит ответный JSON следующего формата с указанием ошибки:
```json
{
	"message": "cell number must be from 1 to 9",
	"localDate": "2022-03-27T01:24:25"
}
```

При успешном выполнении хода в ответ получаем JSON данного рода:

```json
{
	"board": [
		"-",
		"X",
		"-",
		"-",
		"-",
		"-",
		"O",
		"-",
		"-"
	],
	"playerNextMoveId": 1,
	"winnerId": null,
	"draw": false
}
```
Поля имеют следующий смысл:

- `board` - состояние игрового поля после хода;
- `playerNextMoveId` - id игрока, который должен сделать следующий ход;
- `winnerId` - id игрока-победителя, если таковой определился после хода;
- `draw` - поле типа `boolean` с указанием установилась ли ничья
после совершенного хода;

После завершения игры ничьей или выигрышем, последующие ходы
не принимаются, и в ответ приходит JSON с указанием причины.

(Для удобства на стороне сервера воспроизводится поле прогресса игры)

--- 
### Получение итогов игры
После окончания игры можно получить ее итоги. Для этого необходимо
отправить запрос методом `GET` по адресу

    localhost:8080/gameplay/result

В ответ приходит JSON слеующего вида:
```json
{
	"Player": [
		{
			"_id": 1,
			"_name": "petya",
			"_symbol": "X"
		},
		{
			"_id": 2,
			"_name": "sasha",
			"_symbol": "O"
		}
	],
	"Game": {
		"Step": [
			{
				"xCoord": 0,
				"yCoord": 2,
				"_num": 1,
				"_playerId": "1"
			},
			{
				"xCoord": 0,
				"yCoord": 0,
				"_num": 2,
				"_playerId": "2"
			},
			{
				"xCoord": 2,
				"yCoord": 1,
				"_num": 3,
				"_playerId": "1"
			},
			{
				"xCoord": 1,
				"yCoord": 0,
				"_num": 4,
				"_playerId": "2"
			},
			{
				"xCoord": 0,
				"yCoord": 1,
				"_num": 5,
				"_playerId": "1"
			},
			{
				"xCoord": 2,
				"yCoord": 0,
				"_num": 6,
				"_playerId": "2"
			}
		]
	},
	"GameResult": {
		"Player": {
			"_id": 2,
			"_name": "sasha",
			"_symbol": "O"
		}
	}
}
```

---
### Новая игра
Для того чтобы начать новую игру, необходимо отправить запрос 
методом `GET` по адресу:
    
    localhost:8080/gameplay/new

И уже затем вновь переходить к ходам. 

    localhost:8080/gameplay/move