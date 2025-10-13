# Отчёт по первой практике
## Выполнил: Зверев Д.С. БСБО-07-22
---
## Ход работы
### 1. Создание use-case диаграммы
Сначала в рамках проектирования приложения была создана use-case диаграмма с основным функционалом приложения:

<img width="1841" height="1221" alt="image" src="https://github.com/user-attachments/assets/0990f23f-c234-4e3f-840f-787b9b441691" />

### 2. Декомпозиция слоёв domain - presentation
Затем была проведена декомпозиция слоёв domain - presentation проектируемого приложения:

<img width="1521" height="483" alt="image" src="https://github.com/user-attachments/assets/bd6ba751-c1c7-4d35-a678-7c846fcf6ab3" />

### 3. Декомпозиция слоя data
После чего было описано строение слоя data

<img width="948" height="523" alt="image" src="https://github.com/user-attachments/assets/8435b198-8852-4864-bdfc-1ce15b1d8cb2" />

### 4. Декомпозиция слоёв data - domain - presentation с разделением ответственности 
Заключительным этапом проектирования приложения стала декомпозиция слоёв data - domain - presentation с разделением ответственности экранов:

<img width="1509" height="2164" alt="image" src="https://github.com/user-attachments/assets/75186385-6f45-470e-8873-75b25cae16ab" />

### 5. Movie Project
В качестве тестовой реализации логики разделения слоёв было создано приложение Movie Project с демонстрацией будущей структуры.
Проект находится [по данной ссылке](https://github.com/Z-Den/Mobile-Development/tree/main/Lesson9), а результат работы представлен ниже:

![MovieApp](https://github.com/user-attachments/assets/d9fba129-6da1-4dd3-9159-d47531b112e3)

### 6. Создание каркаса приложения FungiFinder
С готовым проектом приложения была начата работа над его реализацией для мобильных приложений. Был создан каркас приложения с тестовыми
данными, пока что без подключений сторонних библиотек. Результаты представлены далее и разбиты по функциям:
- идентификация гриба по фото:

  ![indent by photo](https://github.com/user-attachments/assets/ceb5a5e3-80c8-4932-a908-5d89c1ccab8a)

- коллекция находок:

  ![finds collection](https://github.com/user-attachments/assets/3a4bb393-ffa0-4b66-a50d-40dc38227a69)

- поиск по названию:

  ![search by name](https://github.com/user-attachments/assets/295dbd58-7c93-4bee-a359-4e8509d2133f)

- обучающие материалы:

  ![learning lessons](https://github.com/user-attachments/assets/9e486c36-2260-41c7-bfa0-e64989e1eb59)

- авторизация:

  ![auth](https://github.com/user-attachments/assets/2ce259a0-22f7-4272-8243-12bdd1609eef)

- настройки профиля:
  
![profile settings](https://github.com/user-attachments/assets/ba2abaac-508b-48b8-8f7d-239edeb02fe0)

