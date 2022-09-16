### Что хотелось бы улучшить:

- [ ] Тестов мало, хотелось бы увидеть больше кейсов, которых ты посчитаешь нужными;
- [ ] В тех тестах, что есть, не хватает ассертов. E.g: e2e.test.space.task.TodoTest#02 todo list navigation - кажется, что проверки на стиль табика недостаточно
- [ ] Логин тест в каждом классе - не очень хорошее решение
- [ ] Необходимость запускать тесты в хардкорной последовательности
- [ ] Невозможно запустить один тест из IDE (п3) (все кроме логина)

### Мысли и замечания:

- [ ] Почему бы не сделать не на GH actions, а на Space automation? - мы могли бы сделать там ревью.
- [ ] docker-compose - зачем 3 ноды хрома? Можно скалировать сервис.
- [ ] e2e.pages.google.GooglePage copy/paste
- [ ] testee.it.e2e.core.pages.WaitForLoaded#waitForLoaded - ненадежная проверка загрузки, лучше асинхронно.
- [ ] e2e.pages.BasePage#isOpened etc - странно что is*, не boolean.

- [ ] e2e.test.space.login.UserTest - хочется параметризованных тестов тут (много копипаста).
- [ ] e2e.pages.space.LoginPage#login(java.lang.String, java.lang.String) - а что если элемент не найдется из за баги? Оно не упадет.
- [ ] ^^^ оба теста делают приблизительно одно и тоже. isUserNamePresent логичнее чекать в loginTest

- [ ] Тесты с id > 01 не делает логин и падает (локальный запуск из идеи). Не очень удобно.
- [ ] Из идеи не запустить весь класс, т.к. идея стартует класс через e2e таску.
- [ ] Хочется ссылку на репорт в логе прогона тестов.

- [ ] 3 ноды браузеров - тесты параллелятся?
- [ ] e2e.test.space.task.TodoTest - зачем ожидания в конце каждого теста?

### Minor:

- [x] doc: brew cask install chromedriver - Error: brew cask is no longer a brew command. Use brew <command> --cask instead.
- [ ] gradle issue - повторный запуск тестов из command line не запускает тесты
``
./gradlew test --tests "e2e.test.space.navigation.NavigationTest
BUILD SUCCESSFUL in 1s
3 actionable tasks: 3 up-to-date
``
``
BUILD SUCCESSFUL in 1s
3 actionable tasks: 3 up-to-date
``
- [ ] Хотелось бы конфигурацию для запуска тестов с браузерами в докере. И репорт, чтобы можно было посмотреть.