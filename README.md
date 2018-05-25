# CatalogOnline
# Documentatie

I. User stories: https://trello.com/b/m2b7YwGc/catalog-online

II. Desing/arhitectura/UML: Diagrame incarcate in repository

III. Source control: Commit-uri pentru fiecare user

IV. Teste automate (unitare sau functionale)
      Testing Cases

      Test that Catalog Online works correctly.

      Testing Round 1

      Test that the login works fine for any type of users
      1.Login with sandru.adrian / password
      2.Login wih luminita.cobzaru/ password
      3.Login with mircea.popescu/password
      4.Login with vasile.ciuchina/password
      Notice that you are logged in everytime with a different interface.

      Testing Round 2

      Test that Elev can see his absences and his notes
      1.Login with sandru.adrian/password
      2.Select a subject from dropdown list .
      3.Click GetNote 
      4.Click getAbsente
      Notice that they are listed in the textbox below.

      Testing Round 3

      Test that Elev can see his level of competency in his class.
      1.Login with sandru.adrian/password
      2.Select a subject from the dropdown list
      3.Click Overview
      Notice that you shoudl see the students' level 

      Testing Round 4

      Test that a Teacher can choose a student and see his marks or absences
      1.Login with luminita.cobzaru/password
      2.Select a student after selecting the class and the subject
      3.See that the marks and abences are shown in the list below


      Testing Round 5 
      Test that a teacher can add a new mark or an absence to a specified student
      1.Login with luminta.cobzaru/password
      2.Select the student after selectin the class and the subjkect,
      3.Select a date from the DatePickerCalendar
      4.Add a mark.
      5.Add an Absence.
      6.Check if the Absence and the mark was added with the today date.

      Testing Round 6 
      Test that the director can see the information about a student
      1.Login with vasile.ciuchina/password
      2.Select a student
      Notice that the textboxes should show the info about the selected student.


      Testing Round 7
      Test that the director can add/edit/delete a student
      1.Login with vasile,ciuchina/password
      2.Add a student
      3.Check if the student was added in the list.
      4.Edit a student
      5.Check if the information was changed accordingly.
      6.Delete the student.
      7.Check if the sutdent was deleted from the list.
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

