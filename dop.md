* Почему нельзя тег скрипт в хедере
    можно, но осторожно
    если вставлять жс в конец боди, то можно быть увереным, что DOM построен + браузер точно не станет тормозить постройку DOM'а, чтобы исполнить скрипты
* Что за тильда
    выбрать всех "братьев" элемента, идущих после него (general sibling combinator)
* webpack
    * собрал в бандл весь жс
    * css подключить
    * HTMLWebpackPlugin создал итоговый хтмл

* promise vs async/await
    * промис позовляет возвращать успешный результат или ошибку и задать как коллбэки реакцию на них.
    * внутри промиса - функция (resolve, reject) => {...},
    * снаружи - promise.then(function(...){...}).catch(function(...){...}) т.е. по сути те же коллбэки
    * можно chain-ить промисы (promise.then(secondPromise))
    * async/await - синтаксический сахар для промиса, сокращая код и делая его более читаемым
    * async function doStuff(){
    *    await doOtherStuff();
    *    ...
    *}
    * async - функция всегда возвращает promise (амтоматом оборачивает возвращаемые значения)
    * await - вынуждает подождать, пока промис справа от него не выполнится, и возвращает соответственно результат
    * await можно только внутри async-функции

* в жс подождать ДОМ
    * document.addEventListener('DOMContentLoad', function...);
    * document.addEventListener('readystatechange', function...);
    * document.readyState!='loading'
    * document.readyState=='complete'
    * window.addEventListener("load", function...) //ждёт полной загрузки окна, включая картинки, ай-фреймы и т.д.