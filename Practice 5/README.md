# Отчёт по пятой практике
## Выполнил Зверев Д.С. БСБО-07-22
---
## Ход работы
### 1. Основы использования аппаратных возможностей
#### 1.1 Задание. Список датчиков
В данном пункте было создано приложение в котором вывелись все датчики, доступные на эмуляторе

![image](https://github.com/user-attachments/assets/e9292afc-141d-4a41-aa9c-a78adcd069b3)

#### 1.2 Показания акселерометров
В данном задании выводились значения акселерометра при движении смартфона

![image](https://github.com/user-attachments/assets/9db07c94-4eba-43f2-8ac9-5c113fcfe05e)

### 2. Задание
В данном задании на экране смартфона выводились данные акселерометра о вращении телефона

![image](https://github.com/user-attachments/assets/8c2b56eb-ce73-43d4-a48d-3d775d7dc25c)

### 3. Механихм разрешений
Для начала в манифесте была добавлена необходимость в подтверждении данных разрешений:

![image](https://github.com/user-attachments/assets/631be49a-56e7-413e-9f7a-1e14f49ebb39)

Затем в приложении были выданы разрешения на доступ к камере:

![image](https://github.com/user-attachments/assets/a32ff09b-52ab-438d-89f9-fffe681c9efc)

Приложение реагирует нормально, разрешение выдано, функцией можно пользоваться:

![image](https://github.com/user-attachments/assets/d27a390b-c3e2-4878-9ca9-38a4aeb4836d)

### 4. Задание. Камера
В приложении были выданы разрешения на доступ к камере:

![image](https://github.com/user-attachments/assets/7b2b0420-d190-46e3-b603-d08f3f940418)

После получения разрешения открывается доступ к функционалу приложения:

![image](https://github.com/user-attachments/assets/284c1805-2df0-4951-84ff-4afa61e9c4a7)

После нажатия открывается приложение камеры:

![image](https://github.com/user-attachments/assets/de754c2f-1af2-4e48-a86a-4ec44dba5d5c)

После того как фотография была сделана и подтверждена, она становится заставкой приложения:

![image](https://github.com/user-attachments/assets/2c35b09e-2c9d-400b-8ee6-88da73613c9f)

### 5. Задание. Микрофон
После получения разрешения открывается доступ к функционалу приложения:

![image](https://github.com/user-attachments/assets/5c3bfd87-aa01-4c90-916c-00aa10bda12a)

При начале записи, приложение начинает использовать микрофон, воспроизведение недоступно:

![image](https://github.com/user-attachments/assets/8e817e99-3d6d-4b2e-a299-c75927b9104a)

После остановки записи открывается возможность воспроизвести запись:

![image](https://github.com/user-attachments/assets/49ba157a-18d2-45a1-952a-da0594487e31)

Во время воспроизведения записи возможность записи, разумеется, недоступна:

![image](https://github.com/user-attachments/assets/b53c5638-2fc3-4710-92be-8711f6fe9335)

### 6. Контрольное задание
В данном контрольном задании к уже известному приложению - Mirea Project был добавлен функционал, позволяющий:
- запрос разрешений

![image](https://github.com/user-attachments/assets/04025a2d-6390-4cf7-a462-04e902f88c0f)

- экран, в котором используется результат с датчика освещения для определения яркости экрана

![image](https://github.com/user-attachments/assets/5fd85166-53d4-405a-aeec-3c0a39005cfb)


- экран, в котором используется результат приложения «камера» для создания коллажа фотографий пользователя

![image](https://github.com/user-attachments/assets/5764022c-7f74-48f8-88f1-5560a79539a7)

- экран, в котором используется функционал микрофона для создания голосовых заметок

![image](https://github.com/user-attachments/assets/6768ec98-8a6a-45ea-9d1a-713c75d703ed)

Полная работа вышеописанных модулей представлена ниже:

![mirea proj](https://github.com/user-attachments/assets/2537a9a5-68ab-439e-bba6-8f4ffd443268)
