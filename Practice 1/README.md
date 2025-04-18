# Отчёт по первой практике
## Выполнил: Зверев Д.С. БСБО-07-22
---
## Ход работы
### 1. Создание и подготовка проекта к работе
- Первым делом был создан проект по требуемым критериям - в названии пакетов и модулей фигурирует ФИО и mirea  
***Структура созданного проекта и добавленного модуля:***  
![image](https://github.com/user-attachments/assets/ca3a3def-258d-43ee-ae12-4c745caa9797)
- Далее был добавлен эмулятор Pixel 9 с API 35  
***Создание эмулятора Android***  
![image](https://github.com/user-attachments/assets/e3250377-2543-43ed-8862-495e76ce2df8)

Далее производилась непосредственная работа над заданиями практики
### 2. Первое задание - работа с layoutами
- В модуле layouttype были протестированы возможности редактора разметки - был изменён текст элемента textView  
  ***Изменённый текст элемента textView:***  
  ![image](https://github.com/user-attachments/assets/b98769c1-f4c6-487a-9df8-2ed3de7dbf1d)
  
  ***Кодовая часть:***  
  ![image](https://github.com/user-attachments/assets/3687df5c-28ba-4071-b0c6-e453ae044a53)

- После этого в том же модуле было создано несколько layoutов для отработки навыков. Среди них:
  - *Linear layout - выравнивание элементов в ряд вертикально или горизонтально, в зависимости от настроек*  
    ![image](https://github.com/user-attachments/assets/1b8ac37d-1852-4220-93dc-e039a2a40c64)

  - *Constraint layout - расположение объектов на экране с позиционированием относительно друг друга*  
    ![image](https://github.com/user-attachments/assets/bdca7bb2-db23-42a7-ab37-9ce376a9586c)

  - *Table layout - выравнивание элементов в виде таблицы (похоже на сочетание вертикальных и горизонтальных Linear layoutов)*  
    ![image](https://github.com/user-attachments/assets/9b772bcd-953c-4ee7-ae0c-6876c2664302)

### 3. Контрольное задание 1 - создание своего экрана
Для выполнения данного задания применялись знания расстановки, описанные ранее.  
Заголовок, картинка, текст к картинке и кнопка были созданы при помощи Constraint layoutа, а форма сформирована Table layoutом  
***Итог:***  
![image](https://github.com/user-attachments/assets/e3311383-5040-4f3a-8026-556d847f628b)

### 4. Контрольное задание 1 - смена ориентации
Был создан второй layout с выбранным qualifier - orientation landscape. Далее выставлены все те же элементы, что и в предыдущем шаге, только с учётом горизонтального положения телефона.  
***Итог:***  
![image](https://github.com/user-attachments/assets/e0d7259a-c3de-4cc1-9a1d-8cada13cb864)

### 5. Функции, обработка событий
По заданию требовалось создать новый модуль, в котором будет экран с одним textView и двумя кнопками. Одна из кнопок (WhoAmI) меняет текст на "Мой номер по списку №6", обработчик для данной кнопки задаётся при помощи кода:
```java
View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
  @Override
  public void onClick(View v) {
  tvOut.setText("Мой номер по списку No 6");
}
btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
```
Вторая кнопка должна задаваться из редактора layouta, это было сделано следующим образом:
**Основной файл:**
```java
public void onITNMClick(View view) {
  tvOut.setText("Это не я сделал");
}
```
**Файл layouta**
```java
<Button
  android:id="@+id/btnItIsNotMe" android:layout_width="wrap_content"
  ...
  android:onClick="onITNMClick"
  android:text="IT'S NOT ME"
  app:layout_constraintEnd_toEnd0f="parent" app:layout_constraintStart_toEndOf="@+id/tvOut" app:layout_constraintTop_toBottomOf="@+id/tvOut" />
```
### 5. Контрольное задание 2 - дополнить предыдущее задание, создав чекбокс, на который будет влиять кнопки
Было решено, что чекбокс будет олицетворять позицию лжи - т.е. чекбокс при нажатии на кнопку "IT'S NOT ME" будет отмечаться как true. Далее чекбокс был размещён под соответствующую кнопку. Разумеется, для обращения к нему и работы с ним, он был объявлен в коде. Затем, были доработаны ранее описанные функции нажатия кнопок. Получился следующий код:
```java
private TextView tvOut;
private Button btnWhoAmI;
private CheckBox liarCheckBox;

public void onITNMClick(View view) {
  tvOut.setText("Это не я сделал");
  liarCheckBox.setChecked(true);
}
@Override
protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  EdgeToEdge.enable( $this$enableEdgeToEdge: this);
  setContentView(R.layout.activity_main);
  ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {...});
  tvOut = findViewById(R.id.tvout);
  btnWhoAmI = findViewById(R.id.btnWhoAmI);
  liarCheckBox = findViewById(R.id.liarCheck);
  View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        tvOut.setText("Мой номер по списку No 6");
        liarCheckBox.setChecked(false);
    }
  };

  btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
}
```
Код разметки layouta остался тем же - там просто прослушивается собитие, которое было изменено здесь. Таким образом, работу можно считать выполненной все поставленные задачи исполнены, требуемые результаты достигнуты.
## Результаты работы:
- Смена ориентации  
![Layout app demo - Trim](https://github.com/user-attachments/assets/1bc907f0-7e12-41b1-9d85-4a96f7cba752)
- Обработчик событий  
![App Demo - Trim](https://github.com/user-attachments/assets/64f1d5d3-abec-4324-9ccb-0d5b0ddec555)
