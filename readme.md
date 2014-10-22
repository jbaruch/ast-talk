**Jpoint 2014**

Крутотень с AST Transformations
-------------------------------

 - Зачем?
     - Пример getters/setters
     - Теория AST (неприлично мало)
        - Вечеринка
        - Assert
            - AST Browser
 - Добавляем поле автора
     - Глобальные транформации vs. локальные
     - Тестирование трансформаций
     - Пишем глобальную трансформацию
        - AuthorAdderASTTransformation
        - Фазы компилятора
        - META-INF\services\org.codehaus.groovy.transform.ASTTransformation
 - Добавляем Метод message
    - Пишем локальную транформацию
        - маркер аннотация @Message
        - MessageAdderASTTransformation
            - Achievement unlocked: [x] Адовый AST!
 - Добавляем Метод main
    - Пишем локальную транформацию
        - маркер аннотация @Main
        - MainAdderASTTransformation
        - Пишем защищенный код
              - Achievement unlocked: [x] Компилятор разговаривает вашими сообщениями!
- AST для людей
    - Меньше ада (привет, Груви!)
        - AuthorAdderGroovyASTTransformation
    - Ещё меньше ада (ещё больше Груви)
        - MessageAdderGroovyASTTransformation
 - Вопросы от оставшихся в <s>живых</s> зале