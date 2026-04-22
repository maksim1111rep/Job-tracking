# CLI Симулятор системы рекомендаций вакансий - Job-tracking

Консольное Java-приложение для работы с пользователями, вакансиями, подбором лучших предложений и расчетом статистики по совместимости пользователя и вакансий и фонового поиска лучших вакансий для каждого пользователя.

Приложение выполнено в качестве pet-project.

## Содержание

- [Технологии](#технологии)
- [Архитектура проекта](#архитектура-проекта)
- [Использование](#использование)
- [Начало работы](#начало-работы)
- [To do](#to-do)
- [Автор](#автор)

## Технологии

- Java Core 17
- Gradle
- Java Collections / Stream API

## Архитектура проекта

Структура проекта:

- `cli` — парсинг, диспетчеризация и запуск приложения
- `cli.schemas` — валидация аргументов команд
- `cli.commands` — реализация CLI-команд
- `service` — бизнес-логика
- `domain` — доменные сущности
- `exceptions` — кастомные исключения

Обработка команды устроена так:

1. `LineParser` разбирает строку на имя команды и аргументы
2. `CommandParser` находит описание команды через `CommandSpecification`.
3. `schema` валидирует аргументы и собирает `ValidatedArgs`.
4. Фабрика создает нужную команду.
5. Команда выполняет нужные действия

## Использование

### `user`

Добавляет пользователя в систему.

```text
user <name> --skills=... --exp=...
```

Пример:

```text
user alice --skills=java,ml,linux --exp=2
```

### `user-list`

Выводит список пользователей.

```text
user-list
```

Пример:

```text
Alice java,ml,linux 2
Bob c++,python 5
```

### `job`

Добавляет вакансию.

```text
job <title> --company=... --tags=... --exp=...
```

Пример:

```text
job Backend_Dev --company=VK --tags=java,backend,linux --exp=1
```

### `job-list`

Выводит список вакансий.

```text
job-list
```

Пример:

```text
Backend_Dev at VK
Java_developer at Google
```

### `suggest`

Выводит не больше 2 вакансий, наиболее подходящих указанному пользователю.

```text
suggest <username>
```

### `history`

Показывает историю команд.

```text
history
```

### `stat`

Показывает нужную статистику в зависимости от аргумента.

Вакансии с опытом не менее указанного:

```text
stat --exp <requiredExperience>
```

Список пользователей, у которых хотя бы `N` мэтчей с вакансиями:

```text
stat --match <N>
```

Топ `N` скиллов среди всех пользователей:

```text
stat --top-skills <N>
```

### `exit`

Завершает работу приложения с кодом `0`.

```text
exit
```

### Пример сессии

```text
user alice --skills=java,ml,linux --exp=2
user bob --skills=java --exp=10
user-list
user fred --skills=linux,c++ --exp=10
user piter --skills=c,python --exp=10
user john --skills=c --exp=10
user mary --skills=c --exp=10
job Backend_Dev --company=VK --tags=java,backend,linux --exp=1
job-list
stat --match 1
exit
```

## Начало работы

### Через Gradle

```bash
./gradlew jar
java -jar build/libs/job-tracking-0.0.1-SNAPSHOT.jar
```

### Через `javac` / `java`

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
java -cp out ru.vk.education.job.Main
```

## To do

- покрыть код unit-тестами
- улучшить форматирование вывода и тексты ошибок
- ввести поддержку Spring

## Автор

Максим Некрасов
