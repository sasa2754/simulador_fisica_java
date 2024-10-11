clear

# Se a pasta 'bin' não existir, cria
$binCount = ls | ? { $_.Name -eq "bin" } | measure | % { $_.Count }
if ($binCount -eq "0") {
    "Criando pasta bin..."
    mkdir bin
}

# Copia o arquivo .fxml para a pasta 'bin'
cp src/main/resources/com/desktopapp/MainScreen.fxml .\bin\

# Compila todos os arquivos .java na estrutura de diretórios correta
javac -d bin -sourcepath src/main/java src/main/java/com/desktopapp/*.java

# Entra na pasta 'bin' e executa a classe principal (App)
cd bin
java com.desktopapp.App

# Volta para o diretório anterior
cd ..
