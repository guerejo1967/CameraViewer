# Guida Rapida - Camera Viewer

## 🚀 Compilazione Rapida

### Metodo 1: Script Automatico (Consigliato)
```bash
cd CameraViewer
./build-apk.sh
```
Scegli opzione 1 per Debug APK (più veloce)

### Metodo 2: Android Studio
1. Apri Android Studio
2. File → Open → Seleziona cartella `CameraViewer`
3. Attendi sync Gradle
4. Build → Build Bundle(s) / APK(s) → Build APK(s)
5. Trova APK in `app/build/outputs/apk/debug/`

### Metodo 3: Gradle da riga di comando
```bash
cd CameraViewer
./gradlew assembleDebug
```

## 📱 Installazione APK

### Su dispositivo Android:
1. Trasferisci l'APK sul telefono
2. Apri il file con File Manager
3. Consenti installazione da origini sconosciute se richiesto
4. Installa

### Via USB (ADB):
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📋 Uso dell'App

### Primo Utilizzo

1. **Crea un Sito**
   - Tocca il pulsante `+` in basso
   - Inserisci nome (es: "Ufficio") e descrizione
   - Salva

2. **Aggiungi Telecamere**
   - Tocca il sito creato
   - Tocca `+` per aggiungere una camera
   - Inserisci:
     - Nome: "Ingresso"
     - URL RTSP: `rtsp://192.168.1.100:554/stream`
     - Username e Password (se necessari)
   - Salva

3. **Visualizza Stream**
   - Torna alla lista siti
   - Tocca "Visualizza Telecamere"
   - Le telecamere appariranno in griglia
   - Usa l'icona griglia per cambiare layout

### Import/Export

#### Esportare la Configurazione
1. Menu (⋮) → "Esporta Configurazione"
2. Scegli dove salvare (es: Download)
3. Nome file suggerito: `camera_config_[timestamp].json`

#### Importare la Configurazione
1. Menu (⋮) → "Importa Configurazione"
2. Leggi l'avviso e tocca "Procedi"
3. Seleziona il file JSON
4. Scegli modalità:
   - **Sostituisci Tutto**: Elimina tutto e importa il nuovo
   - **Unisci**: Aggiungi ai siti esistenti

#### Condividere come Testo
1. Menu (⋮) → "Condividi come Testo"
2. Scegli l'app (Email, WhatsApp, ecc.)
3. Invia il backup testuale

## 🔧 Configurazione Telecamere

### URL RTSP Comuni

**Hikvision:**
```
rtsp://username:password@IP:554/Streaming/Channels/101
```

**Dahua:**
```
rtsp://username:password@IP:554/cam/realmonitor?channel=1&subtype=0
```

**TP-Link:**
```
rtsp://username:password@IP:554/stream1
```

**Generica:**
```
rtsp://IP:554/stream
rtsp://IP:554/h264
rtsp://IP:8554/unicast
```

### Trovare l'URL RTSP della tua telecamera

1. **Manuale della telecamera**: Cerca "RTSP URL" o "Streaming URL"
2. **Software del produttore**: Molte app mostrano l'URL
3. **ONVIF Device Manager**: Tool gratuito per scoprire URL RTSP
4. **Browser web**: Accedi all'interfaccia della camera e cerca nelle impostazioni streaming

### Porte Comuni
- **554**: Porta RTSP standard
- **8554**: Porta alternativa comune
- **88**: Alcune telecamere economiche

## 📊 Layout Griglia

- **1x1**: Telecamera singola a schermo intero
- **2x2**: 4 telecamere (ideale per 2-4 camere)
- **3x3**: 9 telecamere (buon compromesso)
- **4x4**: 16 telecamere (massimo, richiede buona rete)

## ⚠️ Risoluzione Problemi

### "Impossibile connettersi"
✅ Verifica che IP e porta siano corretti
✅ Controlla che il telefono sia sulla stessa rete
✅ Prova a pingare l'IP della telecamera
✅ Verifica username e password

### Stream lento o a scatti
✅ Riduci numero di telecamere visualizzate
✅ Avvicinati al router WiFi
✅ Usa rete 5GHz se disponibile
✅ Verifica che le telecamere non stiano usando troppa banda

### "Errore di autenticazione"
✅ Ricontrolla username e password
✅ Verifica maiuscole/minuscole
✅ Prova a inserire credenziali direttamente nell'URL

### L'app si blocca
✅ Riavvia l'app
✅ Riduci il numero di stream simultanei
✅ Verifica spazio di archiviazione disponibile

## 💡 Consigli

1. **Backup Regolari**: Esporta la configurazione periodicamente
2. **Nomi Descrittivi**: Usa nomi chiari per siti e telecamere
3. **Test Iniziale**: Prova prima con 1-2 telecamere
4. **Rete Locale**: Per prestazioni migliori, usa la rete locale
5. **Qualità Stream**: Se disponibile, usa stream a qualità ridotta per più telecamere

## 📁 Esempio File Configurazione

Un file di esempio è fornito in `esempio_configurazione.json`

Puoi:
1. Modificarlo con i tuoi dati
2. Importarlo nell'app
3. Usarlo come modello

## 🎯 Caratteristiche Avanzate

### Modifica Rapida
- Long press su una telecamera per opzioni rapide (future versioni)

### Rotazione Schermo
- La visualizzazione griglia usa modalità landscape automatica
- Ritorna in portrait quando esci

### Gestione Memoria
- L'app rilascia automaticamente risorse quando non in uso
- Chiudi e riapri se noti rallentamenti

## 📞 Supporto

**Problemi comuni risolti nel README principale**

**Per configurazioni specifiche delle telecamere:**
- Consulta il manuale del produttore
- Cerca "[Modello Camera] RTSP URL" online
- Usa tool come ONVIF Device Manager

## 🔐 Sicurezza

- ⚠️ Le password sono salvate in chiaro sul dispositivo
- 🔒 Usa password dedicate per le telecamere
- 📱 Non condividere file di configurazione con password
- 🔄 Cambia password periodicamente
- 🏠 Limita l'accesso alle telecamere alla rete locale

## ⏭️ Prossimi Passi

1. Installa l'app
2. Configura il primo sito
3. Aggiungi 1-2 telecamere di test
4. Verifica che lo streaming funzioni
5. Aggiungi altre telecamere gradualmente
6. Esporta la configurazione come backup

Buona visione! 🎥📹
