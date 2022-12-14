### Что хотелось бы улучшить:

- [x] Тестов мало, хотелось бы увидеть больше кейсов, которых ты посчитаешь нужными;
    - Переработан `UserTest` - удален копипаст
    - Переработан `TodoTest` - добавлены новые тесты, а также каждый тест внутри класса теперь можно запустить из IDEA отдельно
    - Добавлен `TeamTest` - тест для проверки членов команды, а также временной доступности участников команды
    - Переработан `NavigationTest` - добавлена проверка панелей быстрого доступа
    - Добавлен `CreatePagesNavigationTest` - тест для проверки навигации на страницы для быстрого создания задач
    - Добавлен `SearchTest` - тест для проверки работоспособности быстрого поиска
- [x] В тех тестах, что есть, не хватает ассертов. E.g: e2e.space.test.todo.TodoTest#02 todo list navigation - кажется, что проверки на стиль табика
  недостаточно
    - Добавлена проверка при переходе на страницу, что loader элемент скрыт
    - Переработан `TodoTest` - добавлена активация панели быстрого доступа перед переходом на страницу задач
- [x] Логин тест в каждом классе - не очень хорошее решение
    - login вынесен в отдельный метод класса `e2e.space.test.login.LoginTestBase` вызываемый в самом начале тестов ![login.png](feedback/login.png)
- [x] Необходимость запускать тесты в хардкорной последовательности
    - В тесты добавлена testNG зависимость `dependsOnMethods`, позволяющая определить зависимость между тестами
      класса ![dependsOnMethods.png](feedback/dependsOnMethods.png)
- [x] Невозможно запустить один тест из IDE (п3) (все кроме логина)
    - Теперь каждый тест внутри класса можно запустить из `IDEA` отдельно (п4), для этого в IDEA необходимо выбрать "use IntelliJ
      IDEA" ![gradle.png](feedback/gradle.png)

### Мысли и замечания:

- [x] Почему бы не сделать не на GH actions, а на Space automation? - мы могли бы сделать там ревью.
    - Добавлен файл `.space.kts` для запуска тестов в `Space automation`
    - В процессе выполнения задачи в `Space automation` запускается докер с нодами хрома, выполняются тесты и формируется отчет, который загружается
      как артефакт в файловый репозиторий Space. ![jobs.png](feedback/jobs.png)
    - Результаты теста можно загрузить как `zip` архив. ![zip-archive.png](feedback/zip-archive.png)
- [x] docker-compose - зачем 3 ноды хрома? Можно скалировать сервис.
    - Замечание исправлено, в `docker-compose` добавлен параметр `deploy: replicas: 3`, скалирующий ноду хрому до трех экземпляров для параллельного
      выполнения тестов ![chrome-replicas.png](feedback/chrome-replicas.png)
- [x] e2e.space.pages.google.GooglePage copy/paste
    - Ненужный тест и соответствующая ей страница были удалены
- [x] testee.it.e2e.core.pages.WaitForLoaded#waitForLoaded - ненадежная проверка загрузки, лучше асинхронно.
    - Метод был переработан, теперь мы полагаемся на `FluentWait`, ожидая полной загрузки страницы.
- [x] e2e.space.pages.BasePage#isOpened etc. - странно что is*, не boolean.
    - Замечание было исправлено в репозитории `testee`, адаптированы соответствующие названия методов

- [x] e2e.space.test.login.UserTest - хочется параметризованных тестов тут (много копипаста).
    - Переработан `UserTest` - удален копипаст и добавлен обход пользователей по списку
- [x] e2e.space.pages.space.LoginPage#login(java.lang.String, java.lang.String) - а что если элемент не найдется из за баги? Оно не упадет.
    - Метод был переработан, удалена ненужная проверка на элемент. В данном методе мы полагаемся на `FluentWait`, если элемент не будет найден, то
      выпадет WebDriver TimeoutException, это является сигналом того, что тест завершился
      неудачей. ![login-fluent-wait.png](feedback/login-fluent-wait.png)
- [x] ^^^ оба теста делают приблизительно одно и тоже. isUserNamePresent логичнее чекать в loginTest
    - Проверка была добавлена внутри метода прохождения регистрации ![login.png](feedback/login.png)

- [x] Тесты с id > 01 не делает логин и падает (локальный запуск из идеи). Не очень удобно.
    - Замечание исправлено см. пункт №4: в тесты добавлена testNG зависимость `dependsOnMethods`, позволяющая определить зависимость между тестами
      класса ![dependsOnMethods.png](feedback/dependsOnMethods.png)
- [x] Из идеи не запустить весь класс, т.к. идея стартует класс через e2e таску.
    - Cм. пункт №5: каждый тест внутри класса можно запустить из `IDEA` отдельно, для этого в IDEA необходимо выбрать "use IntelliJ
      IDEA" ![gradle.png](feedback/gradle.png)
- [x] Хочется ссылку на репорт в логе прогона тестов.
    - Добавлена ссылка в лог на отчет с результатами запуска теста ![report.png](feedback/report-link.png)
    - По данной ссылке можно открыть в любом удобном браузере `html` отчет с результатами теста ![report-local.png](feedback/report-local.png)

- [x] 3 ноды браузеров - тесты параллелятся?
    - Да, тесты параллелятся с помощью `testNG`, используется параметр `thread-count` внутри `docker.xml`. Тесты запускаются в трех потоках, для этого
      используется скалирование нод хрома
      в `docker-compose`  ![testng-thread-count.png](feedback/testng-thread-count.png) ![chrome-replicas.png](feedback/chrome-replicas.png)
- [x] e2e.space.test.todo.TodoTest - зачем ожидания в конце каждого теста?
    - Данные временные ожидания были добавлены для демонстрации работы тестов на интервью, в текущей версии эти ожидания были удалены

### Minor:

- [x] doc: brew cask install chromedriver - Error: brew cask is no longer a brew command. Use brew <command> --cask instead.
    - `README.md` переработан, замечания были исправлены

- [x] gradle issue - повторный запуск тестов из command line не запускает тесты
  ``./gradlew test --tests "e2e.space.test.navigation.NavigationTest"``
  ``
  BUILD SUCCESSFUL in 1s
  3 actionable tasks: 3 up-to-date
  ``
  ``
  BUILD SUCCESSFUL in 1s
  3 actionable tasks: 3 up-to-date
  ``
    - Данный недостаток был исправлен в `build.gradle.kts` добавлен параметр `outputs.upToDateWhen { false }`, исключающий переиспользование
      результата предыдущего запуска теста ![upToDateWhen.png](feedback/upToDateWhen.png)

- [x] Хотелось бы конфигурацию для запуска тестов с браузерами в докере. И репорт, чтобы можно было посмотреть.
    - В `build.gradle.kts` добавлен плагин `docker-compose` запускающий в докер-контейнере selenium hub и selenium ноды хрома для параллельного
      запуска e2e тестов. В результате запуска тест кейсов формируется отчет доступный по ссылке в логе.
      ``./gradlew docker`` ![docker-report-link.png](feedback/docker-report-link.png)
    - По данной ссылке можно открыть в любом удобном браузере `html` отчет с результатами теста ![docker-report.png](feedback/docker-report.png)
    - Также результат запуска тестов в докере на GitHub Actions можно посмотреть на странице
      Actions: https://github.com/hibissscus/selenium-kotlin-e2e/actions
      в любом из последних запусков и загрузить в виде zip архива ![img.png](feedback/github-actions.png)