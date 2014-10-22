

 ** Крутотень с AST Transformations **
 -------------------------------------

  - Зачем?
      - Пример getters/setters
        - org.kievjavaday.ast.Person
      - Теория AST (неприлично, мало)
         - Вечеринка
         - Assert
             - AST Browser
  - Добавляем поле автора
      - Глобальные транформации vs. локальные
      - Тестирование трансформаций
        - org.kievjavaday.ast.AstTransformationsTests
      - Пишем глобальную трансформацию
         - org.kievjavaday.ast.AuthorAdderASTTransformation
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
     - Меньше ада: - Статические импорты и GeneralUtils в помощь!
        - Упрощаем MessageAdder
     - Ещё Меньше ада (привет, Груви!)
         - AuthorAdderGroovyASTTransformation
     - Совсем мало ада (да и даже не официально трансформация!)
        - org.kievjavaday.ast.SafeAdderMacroExtension
            - META-INF\services\org.codehaus.groovy.runtime.ExtensionModule
  - Вопросы от оставшихся в <s>живых</s> зале