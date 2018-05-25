# CatalogOnline
# Documentatie

I. User stories: https://trello.com/b/m2b7YwGc/catalog-online

II. Desing/arhitectura/UML: Diagrame incarcate in repository

III. Source control: Commit-uri pentru fiecare user

IV. teste automate (unitare sau functionale)

V. Bug reporting: 
  1) Ana: Updateul parolei noi nu se exacuta -> REZOLVAT
  2) Frigo: Media generala dadea Nan, daca elevul nu avea nici o nota -> REZOLVAT
  3) Adi: Parola nu functiona sa fie criptata -> REZOLVAT
  4) Lavinia: Nu se pot adauga string-uri in lista pentru comentariile profesorilor -> REZOLVAT
  
VI. Refactoring:
  1) Initial conexiunea era facuta static: O clasa 
  statica cu metode statice pentru realizareaza conexiunii. 
  Dupa a fost schimbata pentru a respecta principiul incapsularii: 
  metodele sunt private iar clasa trebuie instantiata pentru a face o conexiune

  2) Initial aveam un cod plin de comentarii; am renuntat la ele

