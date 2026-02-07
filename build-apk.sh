#!/bin/bash

echo "=========================================="
echo "Camera Viewer - Script di Compilazione APK"
echo "=========================================="
echo ""

# Verifica se gradlew esiste
if [ ! -f "./gradlew" ]; then
    echo "❌ File gradlew non trovato!"
    echo "Assicurati di essere nella directory root del progetto."
    exit 1
fi

# Rendi gradlew eseguibile
chmod +x ./gradlew

echo "Scegli il tipo di build:"
echo "1) Debug APK (veloce, per test)"
echo "2) Release APK (ottimizzato)"
echo ""
read -p "Scelta (1 o 2): " choice

case $choice in
    1)
        echo ""
        echo "📱 Compilazione Debug APK in corso..."
        echo ""
        ./gradlew clean assembleDebug
        
        if [ $? -eq 0 ]; then
            echo ""
            echo "✅ Compilazione completata con successo!"
            echo ""
            echo "📍 APK generato in:"
            echo "   app/build/outputs/apk/debug/app-debug.apk"
            echo ""
            echo "Per installare su dispositivo connesso via USB:"
            echo "   adb install app/build/outputs/apk/debug/app-debug.apk"
            echo ""
            
            # Copia l'APK nella directory corrente per facilità
            cp app/build/outputs/apk/debug/app-debug.apk ./camera-viewer-debug.apk
            echo "✅ APK copiato anche in: ./camera-viewer-debug.apk"
        else
            echo ""
            echo "❌ Errore durante la compilazione!"
            exit 1
        fi
        ;;
        
    2)
        echo ""
        echo "📱 Compilazione Release APK in corso..."
        echo ""
        ./gradlew clean assembleRelease
        
        if [ $? -eq 0 ]; then
            echo ""
            echo "✅ Compilazione completata con successo!"
            echo ""
            echo "📍 APK generato in:"
            echo "   app/build/outputs/apk/release/app-release-unsigned.apk"
            echo ""
            echo "⚠️  NOTA: L'APK release non è firmato."
            echo "   Per firmarlo, usa jarsigner o Android Studio."
            echo ""
            
            # Copia l'APK nella directory corrente
            cp app/build/outputs/apk/release/app-release-unsigned.apk ./camera-viewer-release-unsigned.apk
            echo "✅ APK copiato anche in: ./camera-viewer-release-unsigned.apk"
        else
            echo ""
            echo "❌ Errore durante la compilazione!"
            exit 1
        fi
        ;;
        
    *)
        echo "❌ Scelta non valida!"
        exit 1
        ;;
esac

echo ""
echo "=========================================="
echo "Compilazione terminata!"
echo "=========================================="
