# GamesSearchApp
Додаток здатний знайти будь-яку гру з сайту www.freetogame.com, показати список з  відсортованими іграми. Також є можливість переглянути деталі ігри, зберегти зацікавлену гру в улюблені, поділитись грою.

Коли у користувача пропадає зв'язок з інтернетом, додаток переходить в offline режим. Всі попередньо переглянуті ігри виводяться на екран з допомогою бази даних.

Якщо користувач випадково вийшов з додатку або android система при малій кількості оперативної пам'яті "вбила" додаток, при поверненні користувач повернеться на ту позицію, до якої проскролив контент.

Додаток створено за допомогою api -> https://www.freetogame.com/api-doc на базі чистої архітектури (Clean Architecture), дотримуючись принципів ООП, а також SOLID.
На початку створення цього додатку, деяка частина функціоналу була покрита  unit-тестами. У майбутньому планується повне покриття додатку unit та ui тестами. Також написано user story.

У цьому додатку було використано:

1. Мапінг даних через прошарки data, domain і presentation.
2. Архітектурний патерн MVVM
3. Retrofit для запитів до мережі
4. LiveData, Coroutines, Flow для асинхронної роботи
5. Room для локального зберігання даних
6. Glide для завантаження та кешування картинок
7. Патерн "Service locator" для надання залежностей ззовні



![photo_2021-11-26_11-56-06](https://user-images.githubusercontent.com/76904012/143565133-f64cc8d1-3590-4d98-8b85-e818ccdc68ec.jpg)
![photo_2021-11-26_11-56-03](https://user-images.githubusercontent.com/76904012/143565136-c9be51a5-d976-427b-845b-7261841af10f.jpg)
![photo_2021-11-26_11-56-00](https://user-images.githubusercontent.com/76904012/143565142-76fcd800-4b95-47d9-a0c4-363910da6220.jpg)
![photo_2021-11-26_11-55-57](https://user-images.githubusercontent.com/76904012/143565147-a2fa4a4d-60f1-409b-95d0-bcc5713883ed.jpg)
![photo_2021-11-26_11-55-53](https://user-images.githubusercontent.com/76904012/143565164-1b3456c5-9caf-4d84-ba94-189b63a60004.jpg)
![photo_2021-11-26_11-55-51](https://user-images.githubusercontent.com/76904012/143565170-c401908e-972a-40f3-99ce-749b66df090a.jpg)
![photo_2021-11-26_11-55-47](https://user-images.githubusercontent.com/76904012/143565182-277ed3f5-da67-430d-aed2-a114aa11652d.jpg)
![photo_2021-11-26_11-55-44](https://user-images.githubusercontent.com/76904012/143565189-be54b282-16ce-4e95-8372-51b5a7a607d6.jpg)
![photo_2021-11-26_11-55-38](https://user-images.githubusercontent.com/76904012/143565194-4d053308-7cb9-4d40-a0d5-444ee1538660.jpg)
![photo_2021-11-26_11-55-29](https://user-images.githubusercontent.com/76904012/143565215-773433cd-c51f-4264-b674-fef258b31962.jpg)


