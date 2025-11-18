# 🚀 QUICK START - Proyecto 5 de Oro desde CERO

Esta guía te permite configurar y ejecutar el proyecto desde cero en **cualquier sistema operativo**.

## 🖥️ SISTEMAS OPERATIVOS SOPORTADOS

| Sistema | Instrucciones | Scripts |
|---------|---------------|---------|
| 🍎 **macOS** | ✅ Incluidas | `.sh` |
| 🪟 **Windows** | ✅ Incluidas | `.bat` |
| 🐧 **Ubuntu/Linux** | ✅ Incluidas | `.sh` |

---

## ⚡ INICIO RÁPIDO (3 PASOS)

### **🍎 macOS / 🐧 Linux:**
```bash
mysql -u root < setup_database.sql
./compilar.sh
./ejecutar.sh
```

### **🪟 Windows:**
```cmd
mysql -u root < setup_database.sql
compilar.bat
ejecutar.bat
```

---

## 📋 REQUISITOS PREVIOS

### **1. Java JDK**

#### **Verificar si tienes Java instalado:**
```bash
# En cualquier sistema operativo
java -version

# Debe mostrar algo como: java version "1.8.0" o superior
```

#### **Si no tienes Java, instalar según tu sistema operativo:**

**🍎 macOS:**
```bash
# Opción 1: Con Homebrew
brew install openjdk@11

# Opción 2: Descargar desde Oracle
# https://www.oracle.com/java/technologies/downloads/
```

**🪟 Windows:**
```powershell
# Opción 1: Descargar instalador desde Oracle
# https://www.oracle.com/java/technologies/downloads/
# Ejecutar el instalador .exe y seguir el asistente

# Opción 2: Con Chocolatey (si lo tienes instalado)
choco install openjdk11

# Verificar instalación
java -version
javac -version

# Si no funciona, agregar a PATH:
# 1. Buscar "Variables de entorno" en Windows
# 2. Editar "Path" en Variables del sistema
# 3. Agregar: C:\Program Files\Java\jdk-11\bin
```

**🐧 Ubuntu/Debian:**
```bash
# Actualizar repositorios
sudo apt update

# Instalar OpenJDK 11
sudo apt install openjdk-11-jdk -y

# Verificar instalación
java -version
javac -version
```

---

### **2. MySQL**

#### **Verificar si tienes MySQL instalado:**
```bash
# En cualquier sistema operativo
mysql --version

# Debe mostrar algo como: mysql Ver 8.0.x
```

#### **Si no tienes MySQL, instalar según tu sistema operativo:**

**🍎 macOS:**
```bash
# Con Homebrew
brew install mysql

# Iniciar servicio
brew services start mysql

# Conectar por primera vez (sin contraseña)
mysql -u root
```

**🪟 Windows:**
```powershell
# Opción 1: Descargar MySQL Installer
# https://dev.mysql.com/downloads/installer/
# Ejecutar el instalador y seguir el asistente

# Durante la instalación:
# - Elegir "Developer Default" o "Server only"
# - Configurar contraseña para root (o dejarla vacía)
# - Iniciar MySQL como servicio de Windows

# Opción 2: Con Chocolatey
choco install mysql

# Iniciar servicio
net start MySQL80

# Conectar
mysql -u root -p
# (presionar Enter si no hay contraseña)

# Agregar MySQL al PATH si no funciona:
# 1. Buscar "Variables de entorno"
# 2. Editar "Path"
# 3. Agregar: C:\Program Files\MySQL\MySQL Server 8.0\bin
```

**🐧 Ubuntu/Debian:**
```bash
# Actualizar repositorios
sudo apt update

# Instalar MySQL Server
sudo apt install mysql-server -y

# Iniciar servicio
sudo systemctl start mysql
sudo systemctl enable mysql

# Configurar seguridad (opcional pero recomendado)
sudo mysql_secure_installation

# Conectar como root
sudo mysql -u root

# O si configuraste contraseña:
mysql -u root -p
```

---

### **3. MySQL Connector (JAR)**
Ya está incluido en el proyecto: `mysql-connector-j-8.0.33.jar`

**Si necesitas descargarlo:**
- https://dev.mysql.com/downloads/connector/j/
- Descargar "Platform Independent" (ZIP)
- Extraer el archivo `.jar` a la carpeta del proyecto

---

## 🗄️ PASO 1: CONFIGURAR LA BASE DE DATOS

### **Opción A: Desde la terminal**

```bash
# 1. Conectar a MySQL (sin contraseña por defecto)
mysql -u root

# 2. Crear la base de datos y las tablas
# Copiar y pegar todo el contenido del archivo setup_database.sql
```

### **Opción B: Ejecutar el script SQL directamente**

```bash
# Ejecutar el script completo
mysql -u root < setup_database.sql
```

### **Contenido del script (setup_database.sql):**

```sql
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS cinco_de_oro;
USE cinco_de_oro;

-- Tabla de apuestas
CREATE TABLE IF NOT EXISTS apuestas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_jugador VARCHAR(100) NOT NULL,
    fecha DATETIME NOT NULL,
    num1 INT NOT NULL,
    num2 INT NOT NULL,
    num3 INT NOT NULL,
    num4 INT NOT NULL,
    num5 INT NOT NULL,
    num6 INT DEFAULT 0,
    num7 INT DEFAULT 0,
    num8 INT DEFAULT 0,
    revancha BOOLEAN DEFAULT FALSE,
    costo_total DOUBLE NOT NULL,
    simple BOOLEAN DEFAULT TRUE
);

-- Tabla de sorteos
CREATE TABLE IF NOT EXISTS sorteos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    num1 INT NOT NULL,
    num2 INT NOT NULL,
    num3 INT NOT NULL,
    num4 INT NOT NULL,
    num5 INT NOT NULL,
    num_extra INT NOT NULL,
    total_apostado DOUBLE NOT NULL,
    pozo_oro DOUBLE NOT NULL,
    pozo_plata DOUBLE NOT NULL
);
```

### **Verificar que se creó correctamente:**

```bash
mysql -u root -e "USE cinco_de_oro; SHOW TABLES;"

# Debe mostrar:
# +-------------------------+
# | Tables_in_cinco_de_oro  |
# +-------------------------+
# | apuestas                |
# | sorteos                 |
# +-------------------------+
```

---

## 🔧 PASO 2: CONFIGURAR LA CONEXIÓN A LA BASE DE DATOS

Verificar el archivo `src/db/ConexionMySQL.java`:

```java
public class ConexionMySQL {
    
    private static final String URL = "jdbc:mysql://localhost:3306/cinco_de_oro";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Cambiar si tienes contraseña
    
    // ...
}
```

**Si tu MySQL tiene contraseña:**
1. Abrir `src/db/ConexionMySQL.java`
2. Cambiar la línea: `private static final String PASSWORD = "tu_contraseña";`

---

## 🔨 PASO 3: COMPILAR EL PROYECTO

### **Opción A: Compilar manualmente**

**🍎 macOS / 🐧 Linux:**
```bash
# 1. Crear carpeta bin si no existe
mkdir -p bin

# 2. Compilar todos los archivos Java
javac -d bin -cp ".:mysql-connector-j-8.0.33.jar" src/datos/*.java src/db/*.java src/disenio/*.java src/logica/*.java

# 3. Verificar que se compiló correctamente
ls bin/datos/
ls bin/db/
ls bin/disenio/
ls bin/logica/

# Debe mostrar archivos .class en cada carpeta
```

**🪟 Windows (CMD):**
```cmd
REM 1. Crear carpeta bin si no existe
mkdir bin

REM 2. Compilar todos los archivos Java (NOTA: usar ; en lugar de :)
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src\datos\*.java src\db\*.java src\disenio\*.java src\logica\*.java

REM 3. Verificar que se compiló correctamente
dir bin\datos
dir bin\db
dir bin\disenio
dir bin\logica

REM Debe mostrar archivos .class en cada carpeta
```

**🪟 Windows (PowerShell):**
```powershell
# 1. Crear carpeta bin si no existe
New-Item -ItemType Directory -Force -Path bin

# 2. Compilar todos los archivos Java
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src/datos/*.java src/db/*.java src/disenio/*.java src/logica/*.java

# 3. Verificar que se compiló correctamente
Get-ChildItem bin/datos
Get-ChildItem bin/db
Get-ChildItem bin/disenio
Get-ChildItem bin/logica
```

---

### **Opción B: Usar el script de compilación**

**🍎 macOS / 🐧 Linux:**
```bash
chmod +x compilar.sh
./compilar.sh
```

**🪟 Windows:**
Crear archivo `compilar.bat`:
```batch
@echo off
echo Compilando proyecto 5 de Oro...

REM Crear directorio bin si no existe
if not exist bin mkdir bin

REM Compilar todos los archivos Java
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src\datos\*.java src\db\*.java src\disenio\*.java src\logica\*.java

if %errorlevel% equ 0 (
    echo.
    echo Compilacion exitosa!
    echo.
    echo Para ejecutar, usa: ejecutar.bat
) else (
    echo.
    echo Error en la compilacion
    exit /b 1
)
```

Ejecutar:
```cmd
compilar.bat
```

### **Contenido del script compilar.sh:**

```bash
#!/bin/bash

echo "🔨 Compilando proyecto 5 de Oro..."

# Crear directorio bin si no existe
mkdir -p bin

# Compilar todos los archivos Java
javac -d bin -cp ".:mysql-connector-j-8.0.33.jar" \
    src/datos/*.java \
    src/db/*.java \
    src/disenio/*.java \
    src/logica/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa!"
    echo ""
    echo "Para ejecutar, usa: ./ejecutar.sh"
else
    echo "❌ Error en la compilación"
    exit 1
fi
```

---

## ▶️ PASO 4: EJECUTAR LA APLICACIÓN

### **Opción A: Ejecutar manualmente**

**🍎 macOS / 🐧 Linux:**
```bash
java -cp "bin:mysql-connector-j-8.0.33.jar" logica.Main
```

**🪟 Windows (CMD o PowerShell):**
```cmd
java -cp "bin;mysql-connector-j-8.0.33.jar" logica.Main
```

**NOTA:** En Windows se usa `;` (punto y coma) en lugar de `:` (dos puntos) para separar rutas en el classpath.

---

### **Opción B: Usar el script de ejecución**

**🍎 macOS / 🐧 Linux:**
```bash
chmod +x ejecutar.sh
./ejecutar.sh
```

**Contenido del script ejecutar.sh:**
```bash
#!/bin/bash

echo "🚀 Ejecutando 5 de Oro..."
echo ""

java -cp "bin:mysql-connector-j-8.0.33.jar" logica.Main
```

**🪟 Windows:**
Crear archivo `ejecutar.bat`:
```batch
@echo off
echo Ejecutando 5 de Oro...
echo.

java -cp "bin;mysql-connector-j-8.0.33.jar" logica.Main
```

Ejecutar:
```cmd
ejecutar.bat
```

---

## 🧪 PASO 5: PROBAR LA APLICACIÓN

### **1. Crear una apuesta simple**
1. Ir a "Apuesta Simple"
2. Ingresar nombre: PRUEBA
3. Ingresar 5 números: 5, 12, 23, 34, 45
4. Marcar o no "Revancha"
5. Clic en "Crear Apuesta"
6. Verificar mensaje de confirmación

### **2. Crear una apuesta múltiple**
1. Ir a "Apuesta Múltiple"
2. Clic en "6 Números" (o 7, 8)
3. Ingresar nombre: PRUEBA6
4. Ingresar 6 números: 5, 12, 23, 34, 45, 18
5. Verificar costo: $270.00
6. Clic en "Crear Apuesta"

### **3. Crear una apuesta de 4 números**
1. Ir a "Apuesta Múltiple"
2. Clic en "4 Números"
3. Ingresar nombre: PRUEBA4
4. Ingresar 4 números: 5, 12, 23, 34
5. Verificar costo: $45.00
6. Clic en "Crear Apuesta"

### **4. Realizar un sorteo**
1. Ir a "Sorteo"
2. Verificar pozos acumulados
3. Clic en "🎲 REALIZAR SORTEO"
4. Confirmar
5. Ver resultados y ganadores

### **5. Ver estadísticas**
1. Ir a "Estadísticas"
2. Ver números más jugados
3. Ver historial de sorteos

---

## 🔍 VERIFICAR QUE TODO FUNCIONA

### **1. Verificar conexión a BD**

```bash
mysql -u root -e "USE cinco_de_oro; SELECT COUNT(*) FROM apuestas;"

# Debe mostrar el número de apuestas creadas
```

### **2. Ver apuestas en la BD**

```bash
mysql -u root -e "USE cinco_de_oro; SELECT * FROM apuestas;"
```

### **3. Ver sorteos en la BD**

```bash
mysql -u root -e "USE cinco_de_oro; SELECT * FROM sorteos;"
```

---

## ⚠️ DIFERENCIAS IMPORTANTES ENTRE SISTEMAS OPERATIVOS

### **Separador de rutas en classpath:**
- **macOS/Linux:** Usar `:` (dos puntos)
  ```bash
  java -cp "bin:mysql-connector.jar" logica.Main
  ```
- **Windows:** Usar `;` (punto y coma)
  ```cmd
  java -cp "bin;mysql-connector.jar" logica.Main
  ```

### **Separador de directorios:**
- **macOS/Linux:** Usar `/` (slash)
  ```bash
  src/datos/DatoOro.java
  ```
- **Windows:** Usar `\` (backslash) en CMD, `/` funciona en PowerShell
  ```cmd
  src\datos\DatoOro.java
  ```

### **Wildcards en compilación:**
- **macOS/Linux:** `*.java` funciona directamente
  ```bash
  javac src/datos/*.java
  ```
- **Windows:** `*.java` funciona en CMD y PowerShell
  ```cmd
  javac src\datos\*.java
  ```

### **Scripts:**
- **macOS/Linux:** Archivos `.sh` (necesitan permisos de ejecución)
  ```bash
  chmod +x compilar.sh
  ./compilar.sh
  ```
- **Windows:** Archivos `.bat` (no necesitan permisos especiales)
  ```cmd
  compilar.bat
  ```

### **MySQL en Windows:**
- El servicio se llama `MySQL80` (o similar según versión)
- Iniciar: `net start MySQL80`
- Detener: `net stop MySQL80`
- Ruta típica: `C:\Program Files\MySQL\MySQL Server 8.0\bin`

### **MySQL en Linux:**
- El servicio se llama `mysql`
- Iniciar: `sudo systemctl start mysql`
- Detener: `sudo systemctl stop mysql`
- Puede requerir `sudo` para conectar: `sudo mysql -u root`

---

## 🐛 SOLUCIÓN DE PROBLEMAS

### **Error: "java: command not found" o "'java' no se reconoce"**

**🍎 macOS:**
```bash
# Instalar Java
brew install openjdk@11

# Agregar a PATH (agregar a ~/.zshrc o ~/.bash_profile)
export PATH="/usr/local/opt/openjdk@11/bin:$PATH"

# Recargar terminal
source ~/.zshrc
```

**🪟 Windows:**
```powershell
# 1. Verificar si Java está instalado pero no en PATH
"C:\Program Files\Java\jdk-11\bin\java.exe" -version

# 2. Si funciona, agregar a PATH:
# - Buscar "Variables de entorno" en el menú inicio
# - Clic en "Variables de entorno"
# - En "Variables del sistema", buscar "Path"
# - Clic en "Editar"
# - Clic en "Nuevo"
# - Agregar: C:\Program Files\Java\jdk-11\bin
# - Clic en "Aceptar" en todas las ventanas
# - Cerrar y abrir nueva terminal

# 3. Si no está instalado, descargar desde:
# https://www.oracle.com/java/technologies/downloads/
```

**🐧 Ubuntu/Linux:**
```bash
# Instalar Java
sudo apt update
sudo apt install openjdk-11-jdk -y

# Verificar
java -version
```

---

### **Error: "mysql: command not found" o "'mysql' no se reconoce"**

**🍎 macOS:**
```bash
# Instalar MySQL
brew install mysql

# Iniciar servicio
brew services start mysql
```

**🪟 Windows:**
```powershell
# 1. Verificar si MySQL está instalado pero no en PATH
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" --version

# 2. Si funciona, agregar a PATH:
# - Buscar "Variables de entorno"
# - Editar "Path" en Variables del sistema
# - Agregar: C:\Program Files\MySQL\MySQL Server 8.0\bin
# - Reiniciar terminal

# 3. Verificar que el servicio está corriendo
net start MySQL80

# 4. Si no está instalado, descargar desde:
# https://dev.mysql.com/downloads/installer/
```

**🐧 Ubuntu/Linux:**
```bash
# Instalar MySQL
sudo apt update
sudo apt install mysql-server -y

# Iniciar servicio
sudo systemctl start mysql
sudo systemctl enable mysql
```

### **Error: "Access denied for user 'root'@'localhost'"**
```bash
# Resetear contraseña de MySQL
mysql.server stop
mysqld_safe --skip-grant-tables &
mysql -u root

# En MySQL:
FLUSH PRIVILEGES;
ALTER USER 'root'@'localhost' IDENTIFIED BY '';
FLUSH PRIVILEGES;
exit;

# Reiniciar MySQL
mysql.server restart
```

### **Error: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"**
- Verificar que `mysql-connector-j-8.0.33.jar` está en la carpeta raíz
- Verificar que el classpath incluye el JAR: `-cp "bin:mysql-connector-j-8.0.33.jar"`

### **Error: "Communications link failure"**

**🍎 macOS:**
```bash
# Verificar que MySQL está corriendo
brew services list

# Si no está corriendo, iniciarlo
brew services start mysql

# Verificar puerto (debe ser 3306)
mysql -u root -e "SHOW VARIABLES LIKE 'port';"
```

**🪟 Windows:**
```cmd
REM Verificar que el servicio está corriendo
sc query MySQL80

REM Si no está corriendo, iniciarlo
net start MySQL80

REM Verificar puerto
mysql -u root -e "SHOW VARIABLES LIKE 'port';"
```

**🐧 Ubuntu/Linux:**
```bash
# Verificar que MySQL está corriendo
sudo systemctl status mysql

# Si no está corriendo, iniciarlo
sudo systemctl start mysql

# Verificar puerto
mysql -u root -e "SHOW VARIABLES LIKE 'port';"
```

### **Error de compilación: "package does not exist"**

**🍎 macOS / 🐧 Linux:**
```bash
# Limpiar y recompilar
rm -rf bin
mkdir bin
javac -d bin -cp ".:mysql-connector-j-8.0.33.jar" src/datos/*.java src/db/*.java src/disenio/*.java src/logica/*.java
```

**🪟 Windows:**
```cmd
REM Limpiar y recompilar
rmdir /s /q bin
mkdir bin
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src\datos\*.java src\db\*.java src\disenio\*.java src\logica\*.java
```

---

### **Error: "Permission denied" (Linux/macOS)**
```bash
# Dar permisos a los scripts
chmod +x compilar.sh ejecutar.sh

# Si el error persiste con MySQL
sudo mysql -u root

# O cambiar permisos del socket de MySQL
sudo chmod 777 /var/run/mysqld/mysqld.sock
```

---

### **Error: "Cannot connect to MySQL server on 'localhost'" (Windows)**
```cmd
REM Verificar que el servicio está instalado
sc query MySQL80

REM Si no existe, reinstalar MySQL o verificar el nombre del servicio
sc query | findstr MySQL

REM Iniciar el servicio con el nombre correcto
net start [nombre_del_servicio]
```

---

## 📁 ESTRUCTURA DEL PROYECTO

```
cinco-de-oro/
├── bin/                          # Archivos compilados (.class)
│   ├── datos/
│   ├── db/
│   ├── disenio/
│   └── logica/
├── src/                          # Código fuente
│   ├── datos/
│   │   ├── DatoOro.java
│   │   └── Sorteo.java
│   ├── db/
│   │   ├── ConexionMySQL.java
│   │   ├── ApuestaDAO.java
│   │   └── SorteoDAO.java
│   ├── disenio/
│   │   ├── Disenio.java
│   │   ├── PanelSimple.java
│   │   ├── PanelMultiple.java
│   │   ├── PanelSorteo.java
│   │   └── PanelEstadistica.java
│   └── logica/
│       └── Main.java
├── mysql-connector-j-8.0.33.jar  # Driver MySQL
├── setup_database.sql            # Script de BD
├── compilar.sh                   # Script de compilación
├── ejecutar.sh                   # Script de ejecución
└── README.md                     # Documentación
```

---

## 🎯 RESUMEN DE COMANDOS

### **Setup completo desde cero:**

**🍎 macOS / 🐧 Linux:**
```bash
# 1. Configurar base de datos
mysql -u root < setup_database.sql

# 2. Compilar
mkdir -p bin
javac -d bin -cp ".:mysql-connector-j-8.0.33.jar" src/datos/*.java src/db/*.java src/disenio/*.java src/logica/*.java

# 3. Ejecutar
java -cp "bin:mysql-connector-j-8.0.33.jar" logica.Main
```

**🪟 Windows (CMD):**
```cmd
REM 1. Configurar base de datos
mysql -u root < setup_database.sql

REM 2. Compilar
mkdir bin
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src\datos\*.java src\db\*.java src\disenio\*.java src\logica\*.java

REM 3. Ejecutar
java -cp "bin;mysql-connector-j-8.0.33.jar" logica.Main
```

**🪟 Windows (PowerShell):**
```powershell
# 1. Configurar base de datos
Get-Content setup_database.sql | mysql -u root

# 2. Compilar
New-Item -ItemType Directory -Force -Path bin
javac -d bin -cp ".;mysql-connector-j-8.0.33.jar" src/datos/*.java src/db/*.java src/disenio/*.java src/logica/*.java

# 3. Ejecutar
java -cp "bin;mysql-connector-j-8.0.33.jar" logica.Main
```

---

### **O usar los scripts:**

**🍎 macOS / 🐧 Linux:**
```bash
# 1. Configurar base de datos
mysql -u root < setup_database.sql

# 2. Dar permisos a los scripts
chmod +x compilar.sh ejecutar.sh

# 3. Compilar
./compilar.sh

# 4. Ejecutar
./ejecutar.sh
```

**🪟 Windows:**
```cmd
REM 1. Configurar base de datos
mysql -u root < setup_database.sql

REM 2. Compilar
compilar.bat

REM 3. Ejecutar
ejecutar.bat
```

---

## 📊 DATOS DE PRUEBA (OPCIONAL)

Si quieres insertar datos de prueba:

```sql
USE cinco_de_oro;

-- Insertar apuestas de prueba
INSERT INTO apuestas (nombre_jugador, fecha, num1, num2, num3, num4, num5, num6, num7, num8, revancha, costo_total, simple)
VALUES 
('JUAN', NOW(), 5, 12, 23, 34, 45, 0, 0, 0, FALSE, 45, TRUE),
('MARIA', NOW(), 10, 20, 30, 40, 48, 0, 0, 0, TRUE, 65, TRUE),
('PEDRO', NOW(), 1, 2, 3, 4, 5, 6, 0, 0, FALSE, 270, FALSE);

-- Verificar
SELECT * FROM apuestas;
```

---

## ✅ CHECKLIST DE VERIFICACIÓN

- [ ] Java instalado y funcionando (`java -version`)
- [ ] MySQL instalado y corriendo (`mysql --version`)
- [ ] Base de datos creada (`cinco_de_oro`)
- [ ] Tablas creadas (`apuestas`, `sorteos`)
- [ ] Proyecto compilado (carpeta `bin` con archivos `.class`)
- [ ] Aplicación ejecuta sin errores
- [ ] Puedo crear apuestas simples
- [ ] Puedo crear apuestas múltiples (4, 6, 7, 8 números)
- [ ] Puedo realizar sorteos
- [ ] Puedo ver estadísticas
- [ ] Los datos se guardan en la BD

---

## 🎓 PARA LA DEFENSA

**Profesor:** "¿Cómo ejecuto tu proyecto desde cero?"

**Respuesta:**
```
"Primero necesitas tener Java y MySQL instalados.

Luego ejecutas el script setup_database.sql para crear la base 
de datos y las tablas.

Después compilas con: javac -d bin -cp mysql-connector.jar src/**/*.java

Y ejecutas con: java -cp bin:mysql-connector.jar logica.Main

O simplemente usas los scripts compilar.sh y ejecutar.sh que 
incluí en el proyecto."
```

---

**¡Proyecto listo para ejecutar desde cero!** 🚀
