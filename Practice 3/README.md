# Отчёт по третьей практике
## Выполнил Зверев Д.С. БСБО-07-22
---
## Ход работы
### 1. Намерения
В данной главе была проведена работа с намерениями с различными параметрами, функциями и методами
#### 1.5. Задание IntentApp
- Создан новый модуль IntentApp с двумя активностями: MainActivity и SecondActivity.
- В MainActivity получено системное время с использованием System.currentTimeMillis() и преобразовано в строку формата  
  ***yyyy-MM-dd HH:mm:ss***.
- Данные переданы во SecondActivity через Intent с помощью метода putExtra().
- Во SecondActivity данные извлечены с помощью getIntent().getStringExtra() и отображены в TextView в требуемом формате.
   
Системное время эмулятора берётся по Гринвичу. Для того, чтобы время отображалось корректно, к dateInMills было прибавлено 3 часа в миллисекундах:

![image](https://github.com/user-attachments/assets/00e41029-d70f-41f0-9862-c544b3821301)
![image](https://github.com/user-attachments/assets/09e8522b-6325-461f-8396-5492f2fb256a)

#### 1.6. Задание Sharer
- Создан новый модуль Sharer с одной активностью.
- Реализован Intent с действием ACTION_SEND и типом данных text/plain.
- Использован метод createChooser() для принудительного отображения диалогового окна выбора приложения.

Приложение успешно вызывает диалоговое окно для выбора приложения и передаёт данные:

![image](https://github.com/user-attachments/assets/52f114ef-d2b7-46a0-a528-fc36204b6af6)

#### 1.7. Задание FavoriteBook
- Создан модуль FavoriteBook с MainActivity и ShareActivity.
- В MainActivity использован ActivityResultLauncher для запуска ShareActivity и обработки результата.
- В ShareActivity реализован ввод данных пользователем и их возврат в MainActivity через setResult().
- Данные отображаются в MainActivity после закрытия ShareActivity.

Как итог, приложение корректно передаёт данные между активностями с использованием Activity Result API:

![image](https://github.com/user-attachments/assets/44549804-634e-4c23-a3ad-225d4562d87c)
![image](https://github.com/user-attachments/assets/a71163b4-f110-4739-b105-59f4f429c5bf)
![image](https://github.com/user-attachments/assets/16d178ac-4c6d-4602-bad6-66e18f7e476e)

#### 1.9. Задание SystemIntentsApp
- Создан модуль SystemIntentsApp с тремя кнопками:
  - "Позвонить" – вызывает ACTION_DIAL с номером телефона.
  - "Открыть браузер" – вызывает ACTION_VIEW с URL.
  - "Открыть карту" – вызывает ACTION_VIEW с координатами.
- Проверена работа на эмуляторе с установленными системными приложениями.

Как можно видеть далее, приложение успешно вызывает системные приложения для выполнения соответствующих действий:
![image](https://github.com/user-attachments/assets/97229cd2-3868-4136-ad19-45d4a193c318)
![image](https://github.com/user-attachments/assets/b8e4004d-9ca2-451a-bc93-20a69aec1392)
![image](https://github.com/user-attachments/assets/ab630953-61e8-4ca1-88d5-f29061800096)
![image](https://github.com/user-attachments/assets/5fd12716-b197-481b-8ce8-4f5d6a4ed6db)

### 2. Фрагменты
В данной главе была проведена работа с фрагментами - их созданием, отображением и переключением.
#### 2.1. Задание SimpleFragmentApp
- Создан модуль SimpleFragmentApp с FirstFragment и SecondFragment.
- Реализовано переключение фрагментов через FragmentManager и FragmentTransaction.
- Добавлена горизонтальная ориентация с одновременным отображением обоих фрагментов.
- Проверена работа при изменении ориентации устройства.

В результате приложение корректно отображает фрагменты в вертикальной и горизонтальной ориентациях:

![image](https://github.com/user-attachments/assets/bdedf0d6-5996-4449-89cb-0d64854cdd72)
![image](https://github.com/user-attachments/assets/b887ede9-023c-41a5-bc8a-16dde173514f)
![image](https://github.com/user-attachments/assets/d8fe1279-da6c-4084-80b9-bfe496d8fdc6)


### 3. Контрольное задание
В данном задании требовалось создать приложение MireaProject с навигационным меню и двумя фрагментами: DataFragment (информация) и WebViewFragment (браузер).
Для этого были:
- Создан проект MireaProject с шаблоном Navigation Drawer Activity.
- Добавлены фрагменты:
  - DataFragment – содержит информацию с оформлением.
  - WebViewFragment – реализует простой браузер с загрузкой страницы по умолчанию.
- Настроена навигация через NavController и NavGraph.
- Проверена работа меню и переходов между фрагментами.

Работу описанного приложения можно увидеть далее:

![MireaProjectApp](https://github.com/user-attachments/assets/3e4c872e-cbbb-4eb8-b998-399fec95adb2)

