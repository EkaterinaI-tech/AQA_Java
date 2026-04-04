# Code Review: коммит `57492d0` (review)

## Что изменилось

Коммит затрагивает 3 файла: `WelcomeStepik.java`, `CoursePage.java`, `SearchTest.java`.

---

### 1. WelcomeStepik.java — поиск курса по названию вместо индекса

**Было:**
```java
// Закомментированный метод openCourseByName, не работавший из-за lazy-loading
/*
public CoursePage openCourseByName(String courseName) {
    courseTitles.find(text(courseName)).scrollTo().click();
    return new CoursePage();
}
*/
```

**Стало:**
```java
private static final String COURSE_TITLES = ".course-card__title";

public CoursePage openCourseByName(String courseName) {
    $$(COURSE_TITLES).shouldHave(sizeGreaterThan(0));
    sleep(3000);

    for (int i = 0; i < $$(COURSE_TITLES).size(); i++) {
        sleep(300);
        SelenideElement title = $$(COURSE_TITLES).get(i);
        title.scrollTo();

        if (title.getText().contains(courseName)) {
            title.scrollIntoCenter().click();
            return new CoursePage();
        }
    }
    throw new RuntimeException("Курс '" + courseName + "' не найден");
}
```

**Зачем:** Курсы на Stepik подгружаются лениво — элементы появляются в DOM только при скролле. Простой `find(text(...))` не находил курс, если он ещё не отрендерился. Новый метод:
- Ждёт появления хотя бы одной карточки (`shouldHave(sizeGreaterThan(0))`)
- Последовательно скроллит к каждому заголовку, провоцируя подгрузку следующих
- На каждой итерации `$$()` заново запрашивает коллекцию из DOM, поэтому новые элементы попадают в обход
- Использует `scrollIntoCenter()` вместо `scrollTo()`, чтобы sticky-хедер сайта не перекрывал элемент

**Также:** селектор вынесен в константу `COURSE_TITLES`, чтобы не дублировать строку.

---

### 2. CoursePage.java — разделение скролла и проверки

**Было:**
```java
private final SelenideElement LevelCourse = ...;

public CoursePage checkLevel(String expectedLevel) {
    LevelCourse.shouldBe(visible)
               .scrollTo()
               .shouldHave(text(expectedLevel));
    return this;
}
```

**Стало:**
```java
private final SelenideElement levelCourse = ...;

public CoursePage scrollToLevel() {
    levelCourse.scrollTo();
    return this;
}

public CoursePage checkLevel(String expectedLevel) {
    levelCourse.shouldHave(text(expectedLevel));
    return this;
}
```

**Зачем:**
- Скролл и проверка — разные действия, теперь они в отдельных методах. В тесте читается понятнее: сначала `.scrollToLevel()`, потом `.checkLevel(...)`.
- Переменная переименована `LevelCourse` → `levelCourse` (Java naming convention: поля начинаются с маленькой буквы).

---

### 3. SearchTest.java — использование нового API

**Было:**
```java
Configuration.holdBrowserOpen = true;
Configuration.timeout = 15000;

    ...
    .openCourseByIndex(10)
    ...
    .checkLevel("Начальный уровень");
```

**Стало:**
```java
Configuration.timeout = 25000;
Configuration.browserSize = "1920x1080";

    ...
    .openCourseByName("Тестирование ПО с нуля. Теория + Практика")
    ...
    .scrollToLevel()
    .checkLevel("Начальный уровень");
```

**Зачем:**
- `openCourseByIndex(10)` → `openCourseByName(...)` — тест больше не зависит от позиции курса в выдаче, которая может меняться
- Добавлен `scrollToLevel()` перед проверкой уровня
- `timeout` увеличен до 25 сек (курсы грузятся долго), добавлен `browserSize`
- Убран `holdBrowserOpen = true` (не нужен в автотестах)
- Удалены устаревшие комментарии и неиспользуемый импорт `byAttribute`
