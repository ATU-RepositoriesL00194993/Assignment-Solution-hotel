# Hotel booking system
Java project for booking hotel rooms
## Module - Software implementation
### Date 19/11/2026

## Author
Mykhailo Yehrashkin

Student number - L00194993

---
### Build without IDE:
Powershell build:
`javac -d bin -sourcepath src (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })`

GNU/Linux build;
`javac -d bin -sourcepath src $(find src -type f -name "*.java")`

RUN;
`java -cp bin ie.atu.menu.SwingMenu`
