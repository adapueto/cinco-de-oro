#!/bin/bash
# Script para compilar el proyecto 5 de Oro

echo "🔨 Compilando proyecto 5 de Oro..."

# Crear directorio bin si no existe
mkdir -p bin

# Compilar todos los archivos Java
javac -d bin -cp ".:mysql-connector-j-8.0.33.jar" src/**/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilación exitosa!"
    echo ""
    echo "Para ejecutar, usa: ./ejecutar.sh"
else
    echo "❌ Error en la compilación"
    exit 1
fi
